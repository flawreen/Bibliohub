package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.Wishlist;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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

    private String insert = "INSERT INTO wishlists VALUES (DEFAULT) RETURNING ID";
    private String delete = "DELETE FROM wishlists WHERE ID = ?";
    private String selectEBooks = "SELECT * FROM books b JOIN ebooks e ON e.book_id = b.id WHERE b.wishlist_id = ?";
    private String selectPBooks = "SELECT * FROM books b JOIN physical_books e ON e.book_id = b.id WHERE b.wishlist_id = ?";

    // Edit este folosit pentru insert si delete. Delete => id = null, Insert => id != null
    private String edit = "UPDATE books SET wishlist_id = ? WHERE ID = ?";
    private String search = "SELECT * FROM books b JOIN wishlists l ON b.wishlist_id = l.id WHERE id = 1 AND lower(b.title) LIKE '%' || ? || '%' ";

    @Override
    public ArrayList<Wishlist> getAll() throws SQLException {
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        var result = getAllStatement.executeQuery();
        while(result.next()) {
            wishlists.add(new Wishlist(
                    result.getInt("id")
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

    public void insertBook(int id, int bookId) throws SQLException {
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

    // TODO is book in wishlist
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
