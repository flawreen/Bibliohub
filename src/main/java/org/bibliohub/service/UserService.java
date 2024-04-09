package org.bibliohub.service;

import org.bibliohub.interfaces.PrintBookArray;
import org.bibliohub.model.Book;
import org.bibliohub.model.Company;
import org.bibliohub.model.User;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService implements PrintBookArray {
    private ArrayList<User> users;
    private static final LibraryService libraryService = LibraryService.getInstance();
    private static final ShelfService shelfService = ShelfService.getInstance();
    private static final WishlistService wishlistService = WishlistService.getInstance();
    private static final BookService bookService = BookService.getInstance();

    private static final CompanyService companyService = CompanyService.getInstance();
    private static UserService instance;

    private UserService() {
        this.users = new ArrayList<>();
    }

    public void setUsers(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            this.users.add(users.get(i));
            companyService.addEmployee(this.users.getLast().getCompanyId(), this.users.getLast());
        }
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getUserById(long id) {
        try {
            int i = 0;
            while (users.get(i).getId() != id) {
                i++;
            }
            return users.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void deleteUser(String password, long id) {
        if (!password.equals("admin")) return;
        try {
            var deleted = users.remove(getUserById(id));
            if (deleted) {
                System.out.printf("Successfully deleted user with id %d!\n", id);
            } else {
                System.out.printf("User with id %d does not exist!\n", id);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("User with id " + id + " not found.");
        }
    }

    public void printUsersLn() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (var user : users) {
            System.out.println(user.toString());
        }
    }

    public void addUser(String password) {
        if (!password.equals("admin")) return;
        Scanner read = new Scanner(System.in);
        long id = User.getNextId();

        System.out.print("\nName: ");
        String name = read.nextLine();

        System.out.printf("\nCompany Id [%s\b\b]: ", companyService.printCompanies());
        long companyId = read.nextLong();

        try {
            users.add(new User(
                    id,
                    name,
                    companyId,
                    shelfService.addShelf(id),
                    wishlistService.addWishlist(id),
                    companyService.getCompanyById(companyId)
            ));
            companyService.addEmployee(companyId, users.getLast());
            System.out.printf("Successfully added user %s with id %d, employee of %s!\n", name, id, companyService.getCompanyById(companyId).getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    * User enters book id -> Borrow a book -> Delete book from library -> Add book to user shelf
    */
    public void borrowBook(long userId, long bookId) {
        try {
            libraryService.removeBookById(bookId);
            shelfService.addToShelf(getUserById(userId).getShelfId(), bookService.getBookById(bookId));
        } catch (NullPointerException e) {
            System.out.println("Error borrowing book with id " + bookId);
        }
    }

    public void returnBook(long userId, long bookId) {
        try {
            shelfService.removeFromShelf(getUserById(userId).getShelfId(), bookService.getBookById((int) bookId));
            libraryService.addBook(bookService.getBookById((int) bookId));
        } catch (NullPointerException e) {
            System.out.println("Error returning book with id " + bookId);
        }
    }

    public void addToWishlist(long userId, long bookId) {
        try {
            if (libraryService.getAvailableBooks().indexOf(bookService.getBookById(bookId)) != -1) {
                wishlistService.addBook(getUserById((int) userId).getWishlistId(), bookService.getBookById((int) bookId));
            } else {
                System.out.printf("Book with id %d not available in library.\n", bookId);
            }
        } catch (NullPointerException e) {
            System.out.println("Error adding to wishlist book with id " + bookId);
        }
    }

    public void removeFromWishlist(long userId, long bookId) {
        try {
            wishlistService.removeBook(getUserById((int) userId).getWishlistId(), bookService.getBookById((int) bookId));
        } catch (NullPointerException e) {
            System.out.println("Error removing from wishlist book with id " + bookId);
        }
    }

    public void viewWishlist(long wishlistId) {
        try {
            var wishlist = wishlistService.getWishlistById(wishlistId).getWishlistBooks();
            var availableBooks = 0;
            for (var book : wishlist) {
                if (libraryService.getAvailableBooks().indexOf(book) != -1) {
                    availableBooks++;
                }
            }
            System.out.println(PrintBookArray.printBooks(wishlist));
            if (!wishlist.isEmpty()) {
                System.out.printf("%d books available to borrow\n", availableBooks);
            }
        } catch (NullPointerException e) {
            System.out.println("Wishlist with id " + wishlistId + " not found\n");
        }
    }

    public void viewShelf(long shelfId) {
        try {
            System.out.println(shelfService.getShelfById(shelfId).toString());
        } catch (NullPointerException e) {
            System.out.println("Shelf with id " + shelfId + " not found\n");
        }
    }

    public void viewAvailableBooks() {
        try {
            System.out.println(libraryService.printAvailableBooks());
        } catch (NullPointerException e) {}
    }

    private void printBooks(ArrayList<Book> books) {
        System.out.println(PrintBookArray.printBooks(books));
    }

    public void searchBookByTitle(String title) {
        var res = libraryService.searchBooksByTitle(title);
        if (res == null) {
            System.out.println("No books found");
            return;
        }
        System.out.printf("%d books found\n", res.size());
        printBooks(res);
    }

    String printUsers() {
        var res = new StringBuilder();
        for (var user : users) {
            res.append(user.getId()).append(". ").append(user.getName()).append("; ");
        }
        return res.toString();
    }

}
