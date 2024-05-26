package org.bibliohub.service;

import org.bibliohub.interfaces.PrintBookArray;
import org.bibliohub.model.Book;
import org.bibliohub.model.User;
import org.bibliohub.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService implements PrintBookArray {
//    private ArrayList<User> users;
    private static final UserRepository userRepository;

    static {
        try {
            userRepository = UserRepository.getInstance("users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final LibraryService libraryService;
    private static final ShelfService shelfService;
    private static final WishlistService wishlistService;
    private static final BookService bookService;
    private static final CompanyService companyService;

    static {
        try {
            libraryService = LibraryService.getInstance();
            shelfService = ShelfService.getInstance();
            wishlistService = WishlistService.getInstance();
            bookService = BookService.getInstance();
            companyService = CompanyService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static UserService instance;

    private UserService() {
//        this.users = new ArrayList<>();
    }

    public static UserService getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


//    public void setUsers(ArrayList<User> users) {
//        for (int i = 0; i < users.size(); i++) {
//            this.users.add(users.get(i));
//            companyService.addEmployee(this.users.getLast().getCompanyId(), this.users.getLast());
//        }
//    }

    public User getUserById(long id) throws SQLException {
        return userRepository.findById((int) id);
//        try {
//            int i = 0;
//            while (users.get(i).getId() != id) {
//                i++;
//            }
//            return users.get(i);
//        } catch (IndexOutOfBoundsException e) {
//            return null;
//        }
    }

    public void deleteUser(String password, long id) throws SQLException {
        if (!password.equals("admin")) return;
        userRepository.deleteById((int) id);
//        try {
//            var deleted = users.remove(getUserById(id));
//            if (deleted) {
//                System.out.printf("Successfully deleted user with id %d!\n", id);
//            } else {
//                System.out.printf("User with id %d does not exist!\n", id);
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("User with id " + id + " not found.");
//        }
    }

    public void printUsersLn() throws SQLException {
        var users = userRepository.getAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (var user : users) {
            System.out.println(user.toString());
        }
    }

    public void addUser(String password) throws SQLException {
        if (!password.equals("admin")) return;
        Scanner read = new Scanner(System.in);
//        long id = User.getNextId();

        System.out.print("\nName: ");
        String name = read.nextLine();

        System.out.printf("\nCompany Id [%s\b\b]: ", companyService.printCompanies());
        long companyId = read.nextLong();

        try {
            userRepository.insert(
                    name,
                    (int) companyId,
                    shelfService.addShelf(),
                    wishlistService.addWishlist()
            );
//            users.add(new User(
//                    id,
//                    name,
//                    companyId,
//                    shelfService.addShelf(id),
//                    wishlistService.addWishlist(id),
//                    companyService.getCompanyById(companyId)
//            ));
//            companyService.addEmployee(companyId, users.getLast());
//            System.out.printf("Successfully added user %s with id %d, employee of %s!\n", name, id, companyService.getCompanyById(companyId).getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(String name, long companyId, int shelfId, int wishlistId) throws SQLException {
        userRepository.insert(
                name,
                (int) companyId,
                shelfService.addShelf(),
                wishlistService.addWishlist()
        );
    }

    /*
    * User enters book id -> Borrow a book -> Delete book from library -> Add book to user shelf
    */
    public boolean borrowBook(long userId, long bookId) {
        try {
            libraryService.removeBookById(bookId);
            shelfService.addToShelf(getUserById(userId).getShelfId(), bookService.getBookById(bookId));
            return true;
        } catch (NullPointerException e) {
            System.out.println("Error borrowing book with id " + bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean returnBook(long userId, long bookId) {
        try {
            shelfService.removeFromShelf(bookService.getBookById((int) bookId));
            libraryService.addBook(bookId);
            return true;
        } catch (NullPointerException | SQLException e) {
            System.out.println("Error returning book with id " + bookId);
        }
        return false;
    }

    public void addToWishlist(long userId, long bookId) {
        try {
            boolean isBookAvailable = false;
            for (var book : libraryService.getAvailableBooks()) {
                if (book.getId() == bookId) {
                    isBookAvailable = true;
                    break;
                }
            }
            if (isBookAvailable) {
                wishlistService.addBook(getUserById((int) userId).getWishlistId(), bookService.getBookById((int) bookId));
            } else {
                System.out.printf("Book with id %d not available in library.\n", bookId);
            }
        } catch (NullPointerException | SQLException e) {
            System.out.println("Error adding to wishlist book with id " + bookId);
        }
    }

    public void removeFromWishlist(long userId, long bookId) {
        try {
            wishlistService.removeBook(getUserById((int) userId).getWishlistId(), bookService.getBookById((int) bookId));
        } catch (NullPointerException e) {
            System.out.println("Error removing from wishlist book with id " + bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewWishlist(long wishlistId) {
        try {
            System.out.println(wishlistService.printWishlist(wishlistId));
        } catch (NullPointerException e) {
            System.out.println("Wishlist with id " + wishlistId + " not found\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewShelf(long shelfId) {
        try {
            System.out.println(shelfService.printShelf(shelfId));
        } catch (NullPointerException | SQLException e) {
            System.out.println("Shelf with id " + shelfId + " not found\n");
        }
    }

    public void viewAvailableBooks() {
        try {
            System.out.println(libraryService.printAvailableBooks());
        } catch (NullPointerException ignored) {} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printBooks(ArrayList<Book> books) {
        System.out.println(PrintBookArray.printBooks(books));
    }

    public void searchBookByTitle(String title) throws SQLException {
        var res = libraryService.searchBooksByTitle(title);
        if (res == null) {
            System.out.println("No books found");
            return;
        }
        System.out.printf("%d books found\n", res.size());
        printBooks(res);
    }

    String printUsers() throws SQLException {
        var users = userRepository.getAll();
        var res = new StringBuilder();
        for (var user : users) {
            res.append(user.getId()).append(". ").append(user.getName()).append("; ");
        }
        return res.toString();
    }

}
