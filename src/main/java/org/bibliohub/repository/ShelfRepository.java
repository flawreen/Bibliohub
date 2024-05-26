package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.Shelf;
import org.bibliohub.model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;


public class ShelfRepository extends Repository<Shelf> {
    private static ShelfRepository instance;
    private ShelfRepository(String tableName) throws SQLException {
        super(tableName);
    }

    public static ShelfRepository getInstance(String tableName) throws SQLException {
        if (instance == null) {
            instance = new ShelfRepository(tableName);
        }
        return instance;
    }

    private String insert = "INSERT INTO shelves VALUES (DEFAULT) RETURNING ID";
    private String delete = "DELETE FROM shelves WHERE ID = ?";
    private String selectEBooks = "SELECT * FROM books b JOIN ebooks e ON e.book_id = b.id WHERE b.shelf_id = ?";
    private String selectPBooks = "SELECT * FROM books b JOIN physical_books e ON e.book_id = b.id WHERE b.shelf_id = ?";

    // Edit este folosit pentru insert si delete. Delete => id = null, Insert => id != null
    private String edit = "UPDATE books SET shelf_id = ? WHERE ID = ?";
    private String search = "SELECT * FROM books b JOIN shelves l ON b.shelf_id = l.id WHERE id = 1 AND lower(b.title) LIKE '%' || ? || '%' ";

    @Override
    public ArrayList<Shelf> getAll() throws SQLException {
        ArrayList<Shelf> shelves = new ArrayList<>();
        var result = getAllStatement.executeQuery();
        while(result.next()) {
            shelves.add(new Shelf(
                    result.getInt("id")
            ));
        }
        return shelves;
    }

    @Override
    public Shelf findById(int id) throws SQLException {
        findByIdStatement.setInt(1, id);
        var result = findByIdStatement.executeQuery();
        if (result.next()) {
            return new Shelf(
                    result.getInt("id")
            );
        } else return null;
    }

    public ArrayList<Book> getBooks(int id) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        var ebstatement = db.prepareStatement(selectEBooks);
        ebstatement.setInt(1, id);
        books.addAll(BookRepository.fetchResults(ebstatement.executeQuery()));
        var pbstatement = db.prepareStatement(selectPBooks);
        pbstatement.setInt(1, id);
        books.addAll(BookRepository.fetchResults(pbstatement.executeQuery()));
        return books;
    }

    public ArrayList<Book> searchBooksByTitle(String title) throws SQLException {
        var searchStatement = db.prepareStatement(search);
        searchStatement.setString(1, title.toLowerCase());
        return BookRepository.fetchResults(searchStatement.executeQuery());
    }

    public void insertBook(int bookId, int id) throws SQLException {
        var insertStatement = db.prepareStatement(edit);
        insertStatement.setInt(1, id);
        insertStatement.setInt(2, bookId);
        insertStatement.executeUpdate();
    }

    public void deleteBookById(int bookId) throws SQLException {
        var deleteStatement = db.prepareStatement(edit);
        deleteStatement.setNull(1, Types.BIGINT);
        deleteStatement.setInt(2, bookId);
        deleteStatement.executeUpdate();
    }

    // TODO is book in shelf
    public int insert() throws SQLException {
        var insertStatement = db.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        insertStatement.executeUpdate();

        // Return row PK
        var primaryKeys = insertStatement.getGeneratedKeys();
        primaryKeys.next();
        return primaryKeys.getInt(1);
    }

    public void delete(int id) throws SQLException {
        var deleteStatement = db.prepareStatement(delete);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

}
