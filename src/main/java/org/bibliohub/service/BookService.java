package org.bibliohub.service;

import org.bibliohub.model.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookService {
    private ArrayList<Book> books;

    private static BookService instance;
    private static final LibraryService libraryService = LibraryService.getInstance();

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


    void deleteBookById(long id, String password) {
        if (!password.equals("admin")) {
            return;
        }
        libraryService.removeBookById(id);
        try {
            books.remove(getBookById(id));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        }
    }
}
