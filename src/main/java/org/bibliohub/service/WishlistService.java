package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.interfaces.PrintBookArray;
import org.bibliohub.model.Book;
import org.bibliohub.model.Wishlist;
import org.bibliohub.repository.BookRepository;
import org.bibliohub.repository.WishlistRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishlistService {

    private ArrayList<Wishlist> wishlists;

    private static final WishlistRepository wishlistRepository;
    private static final BookRepository bookRepository;

    static {
        try {
            bookRepository = BookRepository.getInstance("books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            wishlistRepository = WishlistRepository.getInstance("wishlists");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static WishlistService instance;
    private static final BookService bookService;

    static {
        try {
            bookService = BookService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection db;
    private WishlistService(Connection connection) {
        this.wishlists = new ArrayList<>();
        this.db = connection;
    }

    public static WishlistService getInstance() throws SQLException {
        if (instance == null) {
            instance = new WishlistService(AppDb.getAppDb());
        }
        return instance;
    }

    public int addWishlist() throws SQLException {
        return wishlistRepository.insert();
    }

    public Wishlist getWishlistById(long id) throws SQLException {
        return wishlistRepository.findById((int) id);
    }

    public void addBook(long id, Book book) {
        try {
            wishlistRepository.insertBook((int) id, book.getId());
        } catch (NullPointerException e) {
            System.out.println("Wishlist with id " + id + " not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBook(long id, Book book) throws SQLException {
        wishlistRepository.deleteBookById(book.getId());
    }

    public String printWishlist(long id) throws SQLException {
        return PrintBookArray.printBooks(wishlistRepository.getBooks((int) id));
    }

}
