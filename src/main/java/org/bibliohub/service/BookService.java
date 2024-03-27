package org.bibliohub.service;

import org.bibliohub.model.Book;

import java.util.ArrayList;

public class BookService {
    private ArrayList<Book> books;

    private static BookService instance;

    private BookService() {
        this.books = new ArrayList<>();
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public Book getBookById(long id) {
        try {
            return books.get((int) id);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
