package org.bibliohub.service;

import org.bibliohub.model.Book;
import org.bibliohub.model.Wishlist;

import java.util.ArrayList;

public class WishlistService {

    private ArrayList<Wishlist> wishlists;
    private static WishlistService instance;

    private WishlistService() {
        this.wishlists = new ArrayList<>();
    }

    public static WishlistService getInstance() {
        if (instance == null) {
            instance = new WishlistService();
        }
        return instance;
    }

    public int addWishlist(long userId) {
        wishlists.add(new Wishlist(wishlists.size(), userId));
        return wishlists.size()-1;
    }

    public Wishlist getWishlistById(long id) {
        try {
            return wishlists.get((int) id);
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

    public String viewWishlist(long id) {
        try {
            return getWishlistById(id).toString();
        } catch (NullPointerException e) {
            System.out.println("Wishlist with id " + id + " not found");
            return "";
        }
    }
}
