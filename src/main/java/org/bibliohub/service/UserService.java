package org.bibliohub.service;

import org.bibliohub.model.Book;
import org.bibliohub.model.Library;
import org.bibliohub.model.ShelfService;
import org.bibliohub.model.User;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> users;
    private static final LibraryService libraryService = LibraryService.getInstance();
    private static final ShelfService shelfService = ShelfService.getInstance();

    private static final BookService bookService = BookService.getInstance();
    private static UserService instance;

    private UserService() {
        this.users = new ArrayList<>();
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

    /*
    * User enters book id -> Borrow a book -> Delete book from library -> Add book to user shelf
    */
    public void borrowBook(long userId, long bookId) {
        try {
            libraryService.removeBook(bookId);
            shelfService.addToShelf((int) getUserById(userId).getShelfId(), bookService.getBookById(bookId));
        } catch (NullPointerException e) {
            System.out.println("Error borrowing book with id " + bookId);
        }
    }

    public void returnBook(long userId, long bookId) {
        try {
            shelfService.removeFromShelf((int) getUserById(userId).getShelfId(), bookService.getBookById((int) bookId));
            libraryService.addBook(bookService.getBookById((int) bookId));
        } catch (NullPointerException e) {
            System.out.println("Error returning book with id " + bookId);
        }
    }

}
