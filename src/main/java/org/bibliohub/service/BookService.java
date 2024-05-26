package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.factory.BookFactory;
import org.bibliohub.model.Book;
import org.bibliohub.repository.BookRepository;

import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookService implements BookFactory {
    private ArrayList<Book> books;
    private static BookService instance;
    private static final BookRepository bookRepository;

    static {
        try {
            bookRepository = BookRepository.getInstance("books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final LibraryService libraryService;

    static {
        try {
            libraryService = LibraryService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection db;
    private BookService(Connection connection) {
        this.books = new ArrayList<>();
        this.db = connection;
    }

    public static BookService getInstance() throws SQLException {
        if (instance == null) {
            instance = new BookService(AppDb.getAppDb());
        }
        return instance;
    }

    public ArrayList<Book> getBooks() throws SQLException {
        return bookRepository.getAll();
    }

    public Book getBookById(long id) throws SQLException {
        return bookRepository.findById((int) id);
    }

    public void addBook(String password) {
        if (!password.equals("admin")) return;
        try {
            BookFactory.createBook(bookRepository);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBook(String title, String author, String diffValue, String isbn,  String diffParam) throws SQLException {
        bookRepository.insert(title, author, isbn, diffParam, diffValue);
    }

    public void makeBookAvailable(long id, String password) throws SQLException {
        if (!password.equals("admin")) return;
        libraryService.addBook(id);
    }

    public void deleteBookById(long id, String password) {
        if (!password.equals("admin")) {
            return;
        }
        try {
            bookRepository.deleteById((int) id);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
