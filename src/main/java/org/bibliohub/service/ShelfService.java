package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.interfaces.PrintBookArray;
import org.bibliohub.model.Book;
import org.bibliohub.model.Shelf;
import org.bibliohub.repository.ShelfRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShelfService {
    private ArrayList<Shelf> shelves;

    private static final ShelfRepository shelfRepository;

    static {
        try {
            shelfRepository = ShelfRepository.getInstance("shelves");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public int addShelf() throws SQLException {
        return shelfRepository.insert();
    }

    public Shelf getShelfById(long id) throws SQLException {
        return shelfRepository.findById((int)id);
    }

    public void addToShelf(long id, Book book) {
        try {
            shelfRepository.insertBook(book.getId(), (int) id);
        } catch (NullPointerException | SQLException e) {
            System.out.println("Could not add book to shelf with id " + id + " not found.\n");
        }
    }

    public void removeFromShelf(Book book) throws SQLException {
        shelfRepository.deleteBookById(book.getId());
    }

    public String printShelf(long id) throws SQLException {
        return PrintBookArray.printBooks(shelfRepository.getBooks((int) id));
    }

}
