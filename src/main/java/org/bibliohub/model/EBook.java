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

    public EBook(long id, String title, String author, ArrayList<Shelf> shelves, ArrayList<Wishlist> wishlists, String format) {
        super(id, title, author, shelves, wishlists);
        this.format = format;
    }

    public EBook(long id, String title, String author, String format) {
        super(id, title, author);
        this.format = format;
    }

    public EBook(long id, String title, String author, ArrayList<Shelf> shelves, String format) {
        super(id, title, author, shelves);
        this.format = format;
    }
}
