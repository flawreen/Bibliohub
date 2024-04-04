package org.bibliohub.service;

import org.bibliohub.model.Book;
import org.bibliohub.model.Shelf;

import java.util.ArrayList;

public class ShelfService {
    private ArrayList<Shelf> shelves;
    private static ShelfService instance;

    private static final BookService bookService = BookService.getInstance();

    private ShelfService() {
        this.shelves = new ArrayList<>();
    }

    public static ShelfService getInstance() {
        if (instance == null) {
            instance = new ShelfService();
        }
        return instance;
    }

    public int addShelf(long userId) {
        shelves.add(new Shelf(shelves.size(), userId));
        return shelves.size()-1;
    }

    public Shelf getShelfById(long id) {
        try {
            return shelves.get((int) id);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addToShelf(long id, Book book) {
        try {
            getShelfById(id).getBookList().add(book);
            bookService.getBookById(book.getId()).setShelf(getShelfById(id));
        } catch (NullPointerException e) {
            System.out.println("Shelf with id " + id + " not found.");
        }
    }

    public void removeFromShelf(long id, Book book) {
        try {
            getShelfById((int) id).getBookList().remove(book);
        } catch (NullPointerException e) {
            System.out.println("Shelf with id " + id + " not found.");
        }
    }

}
