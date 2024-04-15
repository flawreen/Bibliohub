package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.factory.BookFactory;
import org.bibliohub.model.Book;

import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookService implements BookFactory {
    private ArrayList<Book> books;
    private static BookService instance;
    private static final LibraryService libraryService = LibraryService.getInstance();

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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book getBookById(long id) {
        try {
            int i = 0;
            while (books.get(i).getId() != id) {
                i++;
            }
            return books.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Book addBook(String password) {
        if (!password.equals("admin")) return null;
        try {
            Book newBook = BookFactory.createBook();
            this.books.add(newBook);
            System.out.printf("Successfully added book %s by %s with id %d!\n", newBook.getTitle(), newBook.getAuthor(), newBook.getId());
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
        try {
            try {
                getBookById(id).getShelf().getBookList().remove(getBookById(id));
            } catch (NullPointerException ignored) {}

            try {
                getBookById(id).getWishlist().getWishlistBooks().remove(getBookById(id));
            } catch (NullPointerException ignored) {}

            libraryService.removeBookById(id);
            var deleted = books.remove(getBookById(id));
            if (deleted) {
                System.out.printf("Successfully deleted book with id %d!\n", id);
            } else {
                System.out.printf("Couldn't delete book with id %d!\n", id);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        }
    }

    public void setBooks(ArrayList<Book> books) {
        this.books.addAll(books);
    }
}
