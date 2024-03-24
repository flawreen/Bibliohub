package org.bibliohub.model;

import java.util.ArrayList;

public class ShelfService {
    private ArrayList<Shelf> shelves;
    private static ShelfService instance;

    private ShelfService() {
        this.shelves = new ArrayList<Shelf>();
    }

    public static ShelfService getInstance() {
        if (instance == null) {
            instance = new ShelfService();
        }
        return instance;
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
        } catch (NullPointerException e) {
            System.out.println("Shelf with id " + id + " not found.");
        }
    }
}
