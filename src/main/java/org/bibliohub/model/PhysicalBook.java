package org.bibliohub.model;

import java.util.ArrayList;

public class PhysicalBook extends Book {
    private String coverType;

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        if (!coverType.equals("hardcover") || !coverType.equals("paperback")) return;
        this.coverType = coverType;
    }

    public PhysicalBook(long id, String title, String author, ArrayList<Shelf> shelves, ArrayList<Wishlist> wishlists, String coverType) {
        super(id, title, author, shelves, wishlists);
        this.coverType = coverType;
    }

    public PhysicalBook(long id, String title, String author, String coverType) {
        super(id, title, author);
        this.coverType = coverType;
    }

    public PhysicalBook(long id, String title, String author, ArrayList<Shelf> shelves, String coverType) {
        super(id, title, author, shelves);
        this.coverType = coverType;
    }
}
