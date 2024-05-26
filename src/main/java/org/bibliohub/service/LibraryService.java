package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.interfaces.PrintBookArray;
import org.bibliohub.model.Book;
import org.bibliohub.model.Library;
import org.bibliohub.repository.LibraryRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryService implements PrintBookArray {
    private static final Library libraryInstance = Library.getInstance();
    private static final LibraryRepository libraryRepository;

    static {
        try {
            libraryRepository = LibraryRepository.getInstance("library");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final BookService bookService;

    static {
        try {
            bookService = BookService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public ArrayList<Book> getAvailableBooks() throws SQLException {
        return libraryRepository.getAvailableBooks();
    }

    public String printAvailableBooks() throws SQLException {
        return PrintBookArray.printBooks(getAvailableBooks());
    }

    public ArrayList<Book> searchBooksByTitle(String title) throws SQLException {
        return libraryRepository.searchBooksByTitle(title);
//        ArrayList<Book> res = new ArrayList<>();
//        for (var book : getAvailableBooks()) {
//            if (book.getTitle().toLowerCase().indexOf(title.toLowerCase()) != -1) {
//                res.add(book);
//            }
//        }
//        return res;
    }

    public void addBook(long bookId) throws SQLException {
        libraryRepository.insertBook((int) bookId);
    }

//    public void addBook(String password) throws SQLException {
//        if (!password.equals("admin")) {
//            System.out.println("Wrong password");
//            return;
//        }
//        Book newBook = bookService.addBook(password);
//        if (newBook == null) {
//            System.out.println("Error adding book to library");
//            return;
//        }
//        addBook(newBook.getId());
//    }

    void removeBookById(long id) throws SQLException {
        libraryRepository.deleteBookById((int) id);
    }

    public void removeBookById(long id, String password) throws SQLException {
        if (!password.equals("admin")) {
            System.out.println("Wrong password");
            return;
        }
        removeBookById(id);
    }


}
