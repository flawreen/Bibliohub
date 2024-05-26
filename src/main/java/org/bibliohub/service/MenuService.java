package org.bibliohub.service;

import org.bibliohub.command.book.AddBook;
import org.bibliohub.command.book.DeleteBookById;
import org.bibliohub.command.book.MakeBookAvailable;
import org.bibliohub.command.company.AddCompany;
import org.bibliohub.command.company.DeleteCompanyById;
import org.bibliohub.command.library.RemoveBookFromLibrary;
import org.bibliohub.command.user.*;
import org.bibliohub.interfaces.Command;
import org.bibliohub.model.User;

import java.sql.SQLException;
import java.util.Scanner;

/*
[]#######################[]
[] Bibliohub menu ||||
[]_______________ VVVV
1. View available books
2. View wishlisted books
3. View borrowed books
4. Borrow a book
5. Return a book
6. Add book to wishlist
7. Remove book from wishlist
8. Search available books
 */

public class MenuService {
    private User user;
    private static final UserService userService;
    private static final CompanyService companyService;

    static {
        try {
            userService = UserService.getInstance();
            companyService = CompanyService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static MenuService instance;

    private MenuService() throws SQLException {
        chooseUser();
    }

    public static MenuService getInstance() throws SQLException {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }

    private void executeCommand(Command command) throws SQLException {
        command.execute();
    }

    public void chooseUser() throws SQLException {
        executeCommand(new PrintUsers());
        int id = waitForOption();
        user = userService.getUserById(id);
    }

    public void printMenu() {
        System.out.println("""
                \n[]------------------[]
                || Bibliohub menu   ||
                []------------------[]
                1. View available books
                2. View wishlisted books
                3. View borrowed books
                4. Borrow a book
                5. Return a book
                6. Add book to wishlist
                7. Remove book from wishlist
                8. Search available books
                Password protected
                9./10. Add/Delete user
                11./12. Add/Delete book
                13./14. Add/Delete company
                15. Remove book from library
                16. Make book available in library
                0. Exit""");
        System.out.print("> ");
    }

    private String waitForString() {
        return scanner.next();
    }

    private long waitForLong() {
        return scanner.nextLong();
    }

    public int waitForOption() {
        return scanner.nextInt();
    }

    private long readBookId() {
        System.out.print("Book id: ");
        return waitForLong();
    }

    private String readPassword() {
        System.out.print("Password: ");
        return waitForString();
    }

    public void chooseOption(int option) throws SQLException {
        long id;
        String str;
        switch (option) {
            case 0:
                return;
            case 1:
                executeCommand(new ViewAvailableBooks());
                break;
            case 2:
                executeCommand(new ViewWishlist(user));
                break;
            case 3:
                executeCommand(new ViewShelf(user));
                break;
            case 4:
                executeCommand(new BorrowBook(user, readBookId()));
                break;
            case 5:
                executeCommand(new ReturnBook(user, readBookId()));
                break;
            case 6:
                executeCommand(new AddToWishlist(user, readBookId()));
                break;
            case 7:
                executeCommand(new RemoveFromWishlistById(user, readBookId()));
                break;
            case 8:
                System.out.print("Title: ");
                str = waitForString();
                executeCommand(new SearchByTitle(str));
                break;
            case 9:
                executeCommand(new AddUser(readPassword()));
                break;
            case 10:
                System.out.printf("User id [%s\b\b]: ", userService.printUsers());
                id = waitForLong();
                if (id != user.getId()) {
                    executeCommand(new DeleteUserById(readPassword(), id));
                } else {
                    System.out.println("Cannot delete an active user!");
                }
                break;
            case 11:
                executeCommand(new AddBook(readPassword()));
                break;
            case 12:
                executeCommand(new DeleteBookById(readPassword(), readBookId()));
                break;
            case 13:
                executeCommand(new AddCompany(readPassword()));
                break;
            case 14:
                System.out.printf("Company id [%s\b\b]: ", companyService.printCompanies());
                id = waitForLong();
                if (id != user.getCompany().getId()) {
                    executeCommand(new DeleteCompanyById(readPassword(), id));
                } else {
                    System.out.println("Cannot delete an active user's company!");
                }
                break;
            case 15:
                executeCommand(new RemoveBookFromLibrary(readPassword(), readBookId()));
                break;
            case 16:
                executeCommand(new MakeBookAvailable(readPassword(), readBookId()));
                break;
            default:
                System.out.println("Choose a valid option!");
                break;
        }
    }

}
