package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.model.Book;
import org.bibliohub.model.Wishlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishlistService {

    private ArrayList<Wishlist> wishlists;
    private static WishlistService instance;
    private static final BookService bookService = BookService.getInstance();

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

    public int addWishlist(long userId) {
        wishlists.add(new Wishlist(wishlists.size(), userId));
        return wishlists.size()-1;
    }

    public Wishlist getWishlistById(long id) {
        try {
            int i = 0;
            while (wishlists.get(i).getId() != id) {
                i++;
            }
            return wishlists.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addBook(long id, Book book) {
        try {
            var wishlist = getWishlistById(id);
            if (wishlist.getWishlistBooks().indexOf(book) != -1) {
                throw new Exception("Book already wishlisted");
            }
            wishlist.getWishlistBooks().add(book);
            bookService.getBookById(book.getId()).setWishlist(getWishlistById(id));
        } catch (NullPointerException e) {
            System.out.println("Wishlist with id " + id + " not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBook(long id, Book book) {
        try {
            getWishlistById(id).getWishlistBooks().remove(book);
        } catch (NullPointerException e) {
            System.out.println("Wishlist with id " + id + " not found");
        }
    }


}
