package org.bibliohub.model;

import java.util.ArrayList;

public class PhysicalBook extends Book {
    private String coverType;

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        if (!coverType.equals("hardcover") && !coverType.equals("paperback")) return;
        this.coverType = coverType;
    }

    public PhysicalBook(long id, String title, String author, String coverType, String isbn) {
        super(id, title, author, isbn);
        this.coverType = coverType;
    }
}
