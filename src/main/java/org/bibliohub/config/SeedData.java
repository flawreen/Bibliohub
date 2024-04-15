package org.bibliohub.config;

import org.bibliohub.model.*;
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

    public void seedData() {
        seedBooksAndLibrary();
        seedCompanies();
        seedUsers();
    }

    private void seedBooksAndLibrary() {
        ArrayList<Book> books = new ArrayList<>(List.of(
                new PhysicalBook(1, "The Brothers Karamazov", "Fyodor Dostoevsky", "paperback", "978-0374528379"),
                new PhysicalBook(2, "White Nights", "Fyodor Dostoevsky", "hardcover", "979-8883496515"),
                new PhysicalBook(3, "The Idiot", "Fyodor Dostoevsky", "hardcover", "978-1613828311"),
                new PhysicalBook(4, "Crime and Punishment", "Fyodor Dostoevsky", "hardcover", "978-6060065647"),
                new PhysicalBook(5, "Demons", "Fyodor Dostoevsky", "paperback", "978-0141441412"),
                new EBook(6, "Teatru", "Ion Luca Caragiale", "978-6303212548", "epub"),
                new EBook(7, "Hail Mary", "Andy Weir", "0593598652", "epub"),
                new EBook(8, "Dark Matter", "Blake Crouch", "978-1101904244", "pdf")
        ));
        libraryService.editLibrary(books);
        bookService.setBooks(books);
    }

    private void seedCompanies() {
        /* All data is fictional */
        companyService.setCompanies(new ArrayList<>(List.of(
                new Company(1, "Electronic Arts", 17799272),
                new Company(2, "Adobe", 13547272),
                new Company(3, "CrowdStrike", 36681949)
        )));
    }

    private void seedUsers() {
        userService.setUsers(new ArrayList<>(List.of(
                new User(1, "Ion Mincu", 2, shelfService.addShelf(1), wishlistService.addWishlist(1), companyService.getCompanyById(2)),
                new User(2, "Lascar Catargiu", 1, shelfService.addShelf(2), wishlistService.addWishlist(2), companyService.getCompanyById(1)),
                new User(3, "Carol I", 3, shelfService.addShelf(3), wishlistService.addWishlist(3), companyService.getCompanyById(3)),
                new User (4, "Constantin A. Rosetti", 1, shelfService.addShelf(4), wishlistService.addWishlist(4), companyService.getCompanyById(1))
        )));
    }
}
