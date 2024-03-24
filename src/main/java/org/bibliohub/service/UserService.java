package org.bibliohub.service;

import org.bibliohub.model.Library;
import org.bibliohub.model.ShelfService;
import org.bibliohub.model.User;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> users;
    private static final LibraryService libraryService = LibraryService.getInstance();
    private static final ShelfService shelfService = ShelfService.getInstance();
    private static UserService instance;

    private UserService() {
        this.users = new ArrayList<User>();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getUserById(long id) {
        try {
            return users.get((int) id);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    // TODO Book service pentru metoda getBookById
    public void borrowBook(long userId, long bookId) {
        try {
            libraryService.removeBook(bookId);
            shelfService.addToShelf((int) getUserById(userId).getShelfId(), getBookbyId(bookId));
        } catch (NullPointerException e) {
            System.out.println("Error borrowing book with id " + bookId);
        }
    }

}
