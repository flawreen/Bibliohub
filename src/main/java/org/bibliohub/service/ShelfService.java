package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.model.Book;
import org.bibliohub.model.Shelf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShelfService {
    private ArrayList<Shelf> shelves;
    private static ShelfService instance;

    private static final BookService bookService;

    static {
        try {
            bookService = BookService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection db;
    private ShelfService(Connection connection) {
        this.shelves = new ArrayList<>();
        this.db = connection;
    }

    public static ShelfService getInstance() throws SQLException {
        if (instance == null) {
            instance = new ShelfService(AppDb.getAppDb());
        }
        return instance;
    }

    public int addShelf(long userId) {
        shelves.add(new Shelf(shelves.size(), userId));
        return shelves.size()-1;
    }

    public Shelf getShelfById(long id) {
        try {
            int i = 0;
            while (shelves.get(i).getId() != id) {
                i++;
            }
            return shelves.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addToShelf(long id, Book book) {
        try {
            getShelfById(id).getBookList().add(book);
            bookService.getBookById(book.getId()).setShelf(getShelfById(id));
        } catch (NullPointerException e) {
            System.out.println("Could not add book to shelf with id " + id + " not found.\n");
        }
    }

    public void removeFromShelf(long id, Book book) {
        try {
            getShelfById((int) id).getBookList().remove(book);
        } catch (NullPointerException e) {
            System.out.println("Shelf with id " + id + " not found.\n");
        }
    }

}
