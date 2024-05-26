package org.bibliohub.config;

import org.bibliohub.model.*;
import org.bibliohub.repository.CompanyRepository;
import org.bibliohub.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeedData {
    private static final UserService userService;
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
            userService = UserService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void seedData() throws SQLException {
        seedBooks();
        seedLibrary();
        seedCompanies();
        seedUsers();
    }

    public void seedLibrary() throws SQLException {
        libraryService.addBook(1);
        libraryService.addBook(2);
        libraryService.addBook(3);
        libraryService.addBook(4);
        libraryService.addBook(5);
        libraryService.addBook(6);
        libraryService.addBook(7);
        libraryService.addBook(8);
    }

    private void seedBooks() throws SQLException {

        bookService.addBook("The Brothers Karamazov", "Fyodor Dostoevsky", "paperback", "978-0374528379", "cover");

        bookService.addBook("White Nights", "Fyodor Dostoevsky", "hardcover", "979-8883496515", "cover");

        bookService.addBook("The Idiot", "Fyodor Dostoevsky", "hardcover", "978-1613828311", "cover");

        bookService.addBook("Crime and Punishment", "Fyodor Dostoevsky", "hardcover", "978-6060065647", "cover");

        bookService.addBook("Demons", "Fyodor Dostoevsky", "paperback", "978-0141441412", "cover");

        bookService.addBook("Teatru", "Ion Luca Caragiale", "epub", "978-6303212548", "format");

        bookService.addBook("Hail Mary", "Andy Weir", "epub", "0593598652", "format");

        bookService.addBook("Dark Matter", "Blake Crouch", "pdf", "978-1101904244", "format");
    }

    private void seedCompanies() throws SQLException {
        /* All data is fictional */
        companyService.addCompany("Electronic Arts", 17799272);
        companyService.addCompany("Adobe", 13547272);
        companyService.addCompany("CrowdStrike", 36681949);
    }

    private void seedUsers() throws SQLException {
        userService.addUser("Ion Mincu", 2, shelfService.addShelf(), wishlistService.addWishlist());

        userService.addUser("Lascar Catargiu", 1, shelfService.addShelf(), wishlistService.addWishlist());

        userService.addUser("Carol I", 3, shelfService.addShelf(), wishlistService.addWishlist());

        userService.addUser("Constantin A. Rosetti", 1, shelfService.addShelf(), wishlistService.addWishlist());

    }
}
