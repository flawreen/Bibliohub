package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.Shelf;
import org.bibliohub.model.User;

import java.sql.SQLException;
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

    private String insert = "INSERT INTO shelves VALUES (DEFAULT, ?)";
    private String delete = "DELETE FROM shelves WHERE ID = ?";
    private String selectAll = "SELECT * FROM books b JOIN shelves l ON b.shelf_id = l.id WHERE id = 1";

    // Edit este folosit pentru insert si delete. Delete => id = null, Insert => id != null
    private String edit = "UPDATE books SET shelf_id = ? WHERE ID = ?";
    private String search = "SELECT * FROM books b JOIN shelves l ON b.shelf_id = l.id WHERE id = 1 AND lower(b.title) LIKE '%' || ? || '%' ";

    @Override
    public ArrayList<Shelf> getAll() throws SQLException {
        ArrayList<Shelf> shelves = new ArrayList<>();
        var result = getAllStatement.executeQuery();
        while(result.next()) {
            shelves.add(new Shelf(
                    result.getInt("id"),
                    result.getInt("user_id")
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
                    result.getInt("id"),
                    result.getInt("user_id")
            );
        } else return null;
    }

    public ArrayList<Book> getBooks() throws SQLException {
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

    // TODO is book in shelf
    public void insert(int userId) throws SQLException {
        var insertStatement = db.prepareStatement(insert);
        insertStatement.setInt(1, userId);
        insertStatement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        var deleteStatement = db.prepareStatement(delete);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

}
