package org.bibliohub.repository;

import org.bibliohub.model.Book;
import org.bibliohub.model.EBook;
import org.bibliohub.model.PhysicalBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class BookRepository extends Repository<Book> {
    private static BookRepository instance;
    private BookRepository(String tableName) throws SQLException {
        super(tableName);
    }

    public static BookRepository getInstance(String tableName) throws SQLException {
        if (instance == null) {
            instance = new BookRepository(tableName);
        }
        return instance;
    }

    private String insert = "INSERT INTO books VALUES (DEFAULT, ?, ?, ?)";
    private String deleteEBook = "DELETE FROM ebooks WHERE book_id = ?";
    private String deletePhysicalBook = "DELETE FROM physical_books WHERE book_id = ?";
    private String edit = "UPDATE books SET title = ?, author = ?, isbn = ? WHERE ID = ?";

    @Override
    public ArrayList<Book> getAll() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        var physicalBooks = getAllPhysicalBooks();
        var eBooks = getAllEBooks();
        books.addAll(physicalBooks);
        books.addAll(eBooks);
        return books;
    }

    static ArrayList<Book> fetchResults(ResultSet books) throws SQLException {
        ArrayList<Book> result = new ArrayList<>();
        while (books.next()) {
            var lastParamValue = books.getObject(books.getMetaData().getColumnCount());
            if (lastParamValue.equals("pdf") || lastParamValue.equals("epub")) {
                result.add(new EBook(
                        books.getInt("id"),
                        books.getString("title"),
                        books.getString("author"),
                        books.getString("isbn"),
                        books.getString("format"))
                );
            } else if (lastParamValue.equals("hardcover") || lastParamValue.equals("paperback")) {
                result.add(new PhysicalBook(
                        books.getInt("id"),
                        books.getString("title"),
                        books.getString("author"),
                        books.getString("isbn"),
                        books.getString("cover"))
                );
            }
        }
        return result;
    }

    public ArrayList<Book> getAllEBooks() throws SQLException {
        var ebookStatement = db.prepareStatement("SELECT * FROM books b JOIN ebooks eb ON b.id = eb.book_id");
        var result = ebookStatement.executeQuery();
        return fetchResults(result);
    }

    public ArrayList<Book> getAllPhysicalBooks() throws SQLException {
        var physicalBookStatement = db.prepareStatement("SELECT * FROM books b JOIN physical_books pb ON b.id = pb.book_id");
        var result = physicalBookStatement.executeQuery();
        return fetchResults(result);
    }

    private PreparedStatement getFindStatement(String tableName) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM books b JOIN ").append(tableName).append(" eb ON b.id = eb.id WHERE b.book_id = ?");
        return db.prepareStatement(query.toString());
    }

    @Override
    public Book findById(int id) throws SQLException {
        var findEBook = getFindStatement("ebooks");
        findEBook.setInt(1, id);
        var books = findByIdStatement.executeQuery();
        if (books.next()) {
            return new EBook(
                    books.getInt("id"),
                    books.getString("title"),
                    books.getString("author"),
                    books.getString("isbn"),
                    books.getString("format")
            );
        }
        var findPhysicalBook = getFindStatement("physical_books");
        findPhysicalBook.setInt(1, id);
        books = findPhysicalBook.executeQuery();
        if (books.next()) {
            return new PhysicalBook(
                    books.getInt("id"),
                    books.getString("title"),
                    books.getString("author"),
                    books.getString("isbn"),
                    books.getString("cover")
            );
        }
        return null;
    }

    public void insert(String title, String author, String isbn, String diffParam, String diffValue) throws SQLException {
        var insertStatement = db.prepareStatement(insert);
        insertStatement.setString(1, title);
        insertStatement.setString(2, author);
        insertStatement.setString(3, isbn);
        insertStatement.executeUpdate();
        var fkStatement = db.prepareStatement("SELECT id FROM books b WHERE b.isbn = ?");

        fkStatement.setString(1, isbn);
        var fkResult = fkStatement.executeQuery();
        fkResult.next();
        int fkId = fkResult.getInt("id");

        if (diffParam.equals("format")) {
            var ebookStatement = db.prepareStatement("INSERT INTO ebooks VALUES (DEFAULT, ?, ?)");
            ebookStatement.setString(1, diffValue);
            ebookStatement.setInt(2, fkId);
        } else if (diffParam.equals("cover")) {
            var physicalBookStatement = db.prepareStatement("INSERT INTO physical_books VALUES (DEFAULT, ?, ?)");
            physicalBookStatement.setString(1, diffValue);
            physicalBookStatement.setInt(2, fkId);
        }
    }

    public void deleteById(int id) throws SQLException {
        var book = findById(id);
        if (book != null) {
            var deleteStatement = db.prepareStatement(deleteEBook);
            deleteStatement.setInt(1, book.getId());
            deleteStatement.executeUpdate();

            deleteStatement = db.prepareStatement(deletePhysicalBook);
            deleteStatement.setInt(1, book.getId());
            deleteStatement.executeUpdate();
        }
    }

    public void editById(int id, String title, String author, String isbn) throws SQLException {
        var editStatement = db.prepareStatement(edit);
        editStatement.setString(1, title);
        editStatement.setString(2, author);
        editStatement.setString(3, isbn);
        editStatement.executeUpdate();
    }
}
