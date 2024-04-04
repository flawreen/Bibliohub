package org.bibliohub.service;

import org.bibliohub.factory.BookFactory;
import org.bibliohub.model.Book;

import java.util.ArrayList;

public class BookService implements BookFactory {
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

    public Book addBook(String password) {
        if (!password.equals("admin")) return null;
        try {
            Book newBook = BookFactory.createBook();
            books.add(newBook);
            return newBook;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void makeBookAvailable(long id, String password) {
        if (!password.equals("admin")) return;
        libraryService.addBook(getBookById(id));
    }

    public void deleteBookById(long id, String password) {
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

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
