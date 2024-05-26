package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.Library;
import java.sql.SQLException;
import java.util.ArrayList;


public class LibraryRepository extends Repository<Library> {
    private static LibraryRepository instance;
    private static final Library library = Library.getInstance();
    private static final BookRepository bookRepository;

    static {
        try {
            bookRepository = BookRepository.getInstance("books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private LibraryRepository(String tableName) throws SQLException {
        super(tableName);
    }

    public Library getLibrary() {
        return library;
    }

    public static LibraryRepository getInstance(String tableName) throws SQLException {
        if (instance == null) {
            instance = new LibraryRepository(tableName);
        }
        return instance;
    }

    private String selectEBooks = "SELECT * FROM books b JOIN ebooks e ON e.book_id = b.id WHERE library_id = 1";

    private String selectPBooks = "SELECT * FROM books b JOIN physical_books e ON e.book_id = b.id WHERE library_id = 1";

    // Edit este folosit pentru insert si delete. Delete => id = null, Insert => id != null
    private String edit = "UPDATE books SET library_id = ? WHERE ID = ?";
    private String search = "SELECT * FROM books b WHERE b.library_id = 1 AND lower(b.title) LIKE '%' || ? || '%' ";


    // Nu am getAll si findById pentru ca exista doar o linie in tabelul Library
    @Override
    public ArrayList<Library> getAll() throws SQLException {
        return null;
    }

    @Override
    public Library findById(int id) throws SQLException {
        return null;
    }

    public ArrayList<Book> getAvailableBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        var ebstatement = db.prepareStatement(selectEBooks);
        books.addAll(BookRepository.fetchResults(ebstatement.executeQuery()));
        var pbstatement = db.prepareStatement(selectPBooks);
        books.addAll(BookRepository.fetchResults(pbstatement.executeQuery()));
        return books;
    }


    public ArrayList<Book> searchBooksByTitle(String title) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        var searchStatement = db.prepareStatement(search);
        searchStatement.setString(1, title.toLowerCase());
        var result = searchStatement.executeQuery();
        while(result.next()) {
            books.add(bookRepository.findById(result.getInt("id")));
        }
        return books;
    }

    public void insertBook(int bookId) throws SQLException {
        var insertStatement = db.prepareStatement(edit);
        insertStatement.setInt(2, bookId);
        insertStatement.setInt(1, 1);
        insertStatement.executeUpdate();
    }

    public void deleteBookById(int bookId) throws SQLException {
        var deleteStatement = db.prepareStatement(edit);
        deleteStatement.setInt(1, 0);
        deleteStatement.setInt(2, bookId);
        deleteStatement.executeUpdate();
    }

}
