package org.bibliohub.model;

import java.util.ArrayList;

public class EBook extends Book {
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        if (!format.equals("pdf") && !format.equals("epub")) return;
        this.format = format;
    }

    public EBook(long id, String title, String author, String isbn, String format) {
        super(id, title, author, isbn);
        this.format = format;
    }

    public EBook(long id, String title, String author, String isbn, ArrayList<Shelf> shelves, ArrayList<Wishlist> wishlists, String format) {
        super(id, title, author, isbn, shelves, wishlists);
        this.format = format;
    }

    public EBook(long id, String title, String author, String isbn, ArrayList<Shelf> shelves, String format) {
        super(id, title, author, isbn, shelves);
        this.format = format;
    }

    public EBook(long id, String title, String author, ArrayList<Wishlist> wishlists, String isbn, String format) {
        super(id, title, author, wishlists, isbn);
        this.format = format;
    }
}
