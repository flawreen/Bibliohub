package org.bibliohub.service;

import org.bibliohub.model.Book;
import org.bibliohub.model.Library;

import java.util.ArrayList;

public class LibraryService {
    private static final Library libraryInstance = Library.getInstance();
    private static LibraryService instance;

    private LibraryService() {

    }

    public static LibraryService getInstance() {
        if (instance == null) {
            instance = new LibraryService();
        }
        return instance;
    }

    public ArrayList<Book> getAvailableBooks() {
        return libraryInstance.getAvailableBooks();
    }

    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> res = new ArrayList<Book>();
        for (var book : getAvailableBooks()) {
            if (book.getTitle().indexOf(title) != -1) {
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

    public void addBook(Book newBook, String password) {
        if (!password.equals("admin")) {
            System.out.println("Wrong password");
            return;
        }
        for (Book book : getAvailableBooks()) {
            if (book == newBook) return;
        }
        getAvailableBooks().add(newBook);
    }

    void removeBookById(long id) {
        try {
            getAvailableBooks().remove(getAvailableBooks().get((int) id));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        }
    }

    public void removeBookById(long id, String password) {
        if (!password.equals("admin")) {
            System.out.println("Wrong password");
            return;
        }
        try {
            getAvailableBooks().remove(getAvailableBooks().get((int) id));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        }
    }

    public void editLibrary(ArrayList<Book> books) {
        libraryInstance.setAvailableBooks(books);
    }

    public ArrayList<Book> searchBookByTitle(String title) {
        ArrayList<Book> searchResults = new ArrayList<>();
        for (var book : getAvailableBooks()) {
            if (book.getTitle().indexOf(title) != -1) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

}
