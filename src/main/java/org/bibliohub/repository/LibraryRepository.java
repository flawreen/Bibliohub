package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.Library;
import java.sql.SQLException;
import java.util.ArrayList;


public class LibraryRepository extends Repository<Library> {
    private static LibraryRepository instance;
    private static final Library library = Library.getInstance();
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

    private String selectAll = "SELECT * FROM books b JOIN library l ON b.library_id = l.id WHERE id = 1";
    private String edit = "UPDATE books SET library_id = ? WHERE ID = ?";
    private String search = "SELECT * FROM books b JOIN library l ON b.library_id = l.id WHERE id = 1 AND lower(b.title) LIKE '%' || ? || '%' ";

    @Override
    public ArrayList<Library> getAll() throws SQLException {
        return null;
    }

    @Override
    public Library findById(int id) throws SQLException {
        return null;
    }

    public ArrayList<Book> getAvailableBooks() throws SQLException {
        var getBooksStatement = db.prepareStatement(selectAll);
        return BookRepository.fetchResults(getBooksStatement.executeQuery());
    }

    public ArrayList<Book> searchBooksByTitle(String title) throws SQLException {
        var searchStatement = db.prepareStatement(search);
        searchStatement.setString(1, title.toLowerCase());
        return BookRepository.fetchResults(searchStatement.executeQuery());
    }

    public void insertBook(int bookId) throws SQLException {
        var insertStatement = db.prepareStatement(edit);
        insertStatement.setInt(1, bookId);
        insertStatement.setInt(2, 1);
        insertStatement.executeUpdate();
    }

    public void deleteBookById(int bookId) throws SQLException {
        var deleteStatement = db.prepareStatement(edit);
        deleteStatement.setString(1, "NULL");
        deleteStatement.setInt(2, bookId);
        deleteStatement.executeUpdate();
    }

}
