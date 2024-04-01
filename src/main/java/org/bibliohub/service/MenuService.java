package org.bibliohub.service;

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

    void executeCommand(Command command) {
        command.execute();
    }

    void chooseUser() {
        executeCommand(new PrintUsersCommand());
        int id = waitForOption();
        user = userService.getUserById(id);
    }

    void printMenu() {
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
                8. Search available books""");
        System.out.print("> ");
    }

    String waitForString() {
        return scanner.next();
    }

    long waitForLong() {
        return scanner.nextLong();
    }

    int waitForOption() {
        return scanner.nextInt();
    }

    void chooseOption(int option) {
        long id;
        String str;
        switch (option) {
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
                System.out.println("Book id: ");
                id = waitForLong();
                executeCommand(new BorrowBookCommand(user, id));
                break;
            case 5:
                System.out.println("Book id: ");
                id = waitForLong();
                executeCommand(new ReturnBookCommand(user, id));
                break;
            case 6:
                System.out.println("Book id: ");
                id = waitForLong();
                executeCommand(new AddToWishlistCommand(user, id));
                break;
            case 7:
                System.out.println("Book id: ");
                id = waitForLong();
                executeCommand(new RemoveFromWishlistByIdCommand(user, id));
                break;
            case 8:
                System.out.println("Title: ");
                str = waitForString();
                executeCommand(new SearchByTitleCommand(str));
                break;
            default:
                System.out.println("Choose a valid option!");
                break;
        }
    }

}
