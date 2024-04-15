package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.interfaces.PrintBookArray;
import org.bibliohub.model.Book;
import org.bibliohub.model.Library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryService implements PrintBookArray {
    private static final Library libraryInstance = Library.getInstance();
    private static final BookService bookService = BookService.getInstance();
    private static LibraryService instance;

    private Connection db;
    private LibraryService(Connection connection) {
        this.db = connection;
    }

    public static LibraryService getInstance() throws SQLException {
        if (instance == null) {
            instance = new LibraryService(AppDb.getAppDb());
        }
        return instance;
    }

    public ArrayList<Book> getAvailableBooks() {
        return libraryInstance.getAvailableBooks();
    }

    public String printAvailableBooks() {
        return PrintBookArray.printBooks(getAvailableBooks());
    }

    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> res = new ArrayList<>();
        for (var book : getAvailableBooks()) {
            if (book.getTitle().toLowerCase().indexOf(title.toLowerCase()) != -1) {
                res.add(book);
            }
        }
        return res;
    }

    void addBook(Book newBook) {
        if (newBook == null) {
            return;
        }
        for (Book book : getAvailableBooks()) {
            if (book == newBook) return;
        }
        getAvailableBooks().add(newBook);
    }

    public void addBook(String password) {
        if (!password.equals("admin")) {
            System.out.println("Wrong password");
            return;
        }
        Book newBook = bookService.addBook(password);
        if (newBook == null) {
            System.out.println("Error adding book to library");
            return;
        }
        getAvailableBooks().add(newBook);
    }

    void removeBookById(long id) {
        try {
            int i = 0;
            while (getAvailableBooks().get(i).getId() != id) {
                i++;
            }
            getAvailableBooks().remove(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found in library.");
        }
    }

    public void removeBookById(long id, String password) {
        if (!password.equals("admin")) {
            System.out.println("Wrong password");
            return;
        }
        try {
            removeBookById(id);
            System.out.printf("Successfully deleted book with id %d from library!\n", id);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        }
    }

    public void editLibrary(ArrayList<Book> books) {
        libraryInstance.setAvailableBooks(books);
    }


}
