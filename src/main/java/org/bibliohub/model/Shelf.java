package org.bibliohub.model;

import java.util.ArrayList;

public class Shelf {
    private long id;

    private long userId;

    private ArrayList<Book> bookList;

    public Shelf(long id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Shelf(long id, long userId, ArrayList<Book> bookList) {
        this.id = id;
        this.userId = userId;
        this.bookList = bookList;
    }

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

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
}
