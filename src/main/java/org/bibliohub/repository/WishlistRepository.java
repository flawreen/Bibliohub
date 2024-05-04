package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.Wishlist;
import org.bibliohub.model.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class WishlistRepository extends Repository<Wishlist> {
    private static WishlistRepository instance;
    private WishlistRepository(String tableName) throws SQLException {
        super(tableName);
    }

    public static WishlistRepository getInstance(String tableName) throws SQLException {
        if (instance == null) {
            instance = new WishlistRepository(tableName);
        }
        return instance;
    }

    private String insert = "INSERT INTO wishlists VALUES (DEFAULT, ?)";
    private String delete = "DELETE FROM wishlists WHERE ID = ?";
    private String selectAll = "SELECT * FROM books b JOIN wishlists l ON b.wishlist_id = l.id WHERE id = 1";

    // Edit este folosit pentru insert si delete. Delete => id = null, Insert => id != null
    private String edit = "UPDATE books SET wishlist_id = ? WHERE ID = ?";
    private String search = "SELECT * FROM books b JOIN wishlists l ON b.wishlist_id = l.id WHERE id = 1 AND lower(b.title) LIKE '%' || ? || '%' ";

    @Override
    public ArrayList<Wishlist> getAll() throws SQLException {
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        var result = getAllStatement.executeQuery();
        while(result.next()) {
            wishlists.add(new Wishlist(
                    result.getInt("id"),
                    result.getInt("user_id")
            ));
        }
        return wishlists;
    }

    @Override
    public Wishlist findById(int id) throws SQLException {
        findByIdStatement.setInt(1, id);
        var result = findByIdStatement.executeQuery();
        if (result.next()) {
            return new Wishlist(
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

    // TODO is book in wishlist
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
