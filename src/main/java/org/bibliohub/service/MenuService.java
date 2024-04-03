package org.bibliohub.service;

import org.bibliohub.command.book.AddBookCommand;
import org.bibliohub.command.book.DeleteBookByIdCommand;
import org.bibliohub.command.book.MakeBookAvailableCommand;
import org.bibliohub.command.company.AddCompanyCommand;
import org.bibliohub.command.company.DeleteCompanyByIdCommand;
import org.bibliohub.command.library.AddBookToLibraryCommand;
import org.bibliohub.command.library.RemoveBookFromLibraryCommand;
import org.bibliohub.command.user.*;
import org.bibliohub.interfaces.Command;
import org.bibliohub.model.User;

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
    private static final UserService userService = UserService.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static MenuService instance;

    private MenuService() {
        chooseUser();
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }

    private void executeCommand(Command command) {
        command.execute();
    }

    public void chooseUser() {
        executeCommand(new PrintUsersCommand());
        int id = waitForOption();
        user = userService.getUserById(id);
    }

    public void printMenu() {
        System.out.println("""
                []//////////////////[]
                [] Bibliohub menu ||||
                []/////////////// VVVV
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
                15./16. Add/Delete book to/from library
                17. Make book available in library
                -1. Exit""");
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
        System.out.println("Book id: ");
        return waitForLong();
    }

    private String readPassword() {
        System.out.println("Password: ");
        return waitForString();
    }

    public void chooseOption(int option) {
        long id;
        String str;
        switch (option) {
            case -1:
                return;
            case 1:
                executeCommand(new ViewAvailableBooksCommand());
                break;
            case 2:
                executeCommand(new ViewWishlistCommand(user));
                break;
            case 3:
                executeCommand(new ViewShelfCommand(user));
                break;
            case 4:
                executeCommand(new BorrowBookCommand(user, readBookId()));
                break;
            case 5:
                executeCommand(new ReturnBookCommand(user, readBookId()));
                break;
            case 6:
                executeCommand(new AddToWishlistCommand(user, readBookId()));
                break;
            case 7:
                executeCommand(new RemoveFromWishlistByIdCommand(user, readBookId()));
                break;
            case 8:
                System.out.println("Title: ");
                str = waitForString();
                executeCommand(new SearchByTitleCommand(str));
                break;
            case 9:
                executeCommand(new AddUserCommand(readPassword()));
                break;
            case 10:
                System.out.println("User id: ");
                id = waitForLong();
                if (id != user.getId()) {
                    executeCommand(new DeleteUserByIdCommand(readPassword(), id));
                } else {
                    System.out.println("Cannot delete an active user!");
                }
                break;
            case 11:
                executeCommand(new AddBookCommand(readPassword()));
                break;
            case 12:
                executeCommand(new DeleteBookByIdCommand(readPassword(), readBookId()));
                break;
            case 13:
                executeCommand(new AddCompanyCommand(readPassword()));
                break;
            case 14:
                System.out.println("Company id: ");
                id = waitForLong();
                if (id != user.getCompany().getId()) {
                    executeCommand(new DeleteCompanyByIdCommand(readPassword(), id));
                } else {
                    System.out.println("Cannot delete an active user's company!");
                }
                break;
            case 15:
                executeCommand(new AddBookToLibraryCommand(readPassword()));
                break;
            case 16:
                executeCommand(new RemoveBookFromLibraryCommand(readPassword(), readBookId()));
                break;
            case 17:
                executeCommand(new MakeBookAvailableCommand(readPassword(), readBookId()));
                break;
            default:
                System.out.println("Choose a valid option!");
                break;
        }
    }

}
