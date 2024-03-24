package org.bibliohub.model;
import java.util.ArrayList;

public class Library {

    private static Library instance;
    private long id;
    private ArrayList<Book> availableBooks;

    private Library() {

    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(ArrayList<Book> availableBooks) {
        this.availableBooks = availableBooks;
    }
}
