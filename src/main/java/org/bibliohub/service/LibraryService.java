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

    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> res = new ArrayList<Book>();
        for (var book : libraryInstance.getAvailableBooks()) {
            if (book.getTitle().indexOf(title) != -1) {
                res.add(book);
            }
        }
        return res;
    }

    public void addBook(Book newBook) {
        for (Book book : libraryInstance.getAvailableBooks()) {
            if (book == newBook) return;
        }
        libraryInstance.getAvailableBooks().add(newBook);
    }

    public void removeBook(long id) {
        try {
            libraryInstance.getAvailableBooks().remove(libraryInstance.getAvailableBooks().get((int) id));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Book with id " + id + " not found.");
        }
    }

    public void editLibrary(ArrayList<Book> books) {
        libraryInstance.setAvailableBooks(books);
    }

}
