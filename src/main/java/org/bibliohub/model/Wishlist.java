package org.bibliohub.model;

import java.util.ArrayList;

public class Wishlist {
    private long id;
    private long userId;
    private ArrayList<Book> wishlistBooks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Book> getWishlistBooks() {
        return wishlistBooks;
    }

    public void setWishlistBooks(ArrayList<Book> wishlistBooks) {
        this.wishlistBooks = wishlistBooks;
    }

    public Wishlist(long id, long userId, ArrayList<Book> wishlistBooks) {
        this.id = id;
        this.userId = userId;
        this.wishlistBooks = wishlistBooks;
    }

    public Wishlist(long id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < wishlistBooks.size(); ++i) {
            res.append(wishlistBooks.get(i).toString());
            if (i > 0 && i % 2 == 0) {
                res.append("\n");
            } else {
                res.append("\t");
            }
        }
        return res.toString();
    }
}
