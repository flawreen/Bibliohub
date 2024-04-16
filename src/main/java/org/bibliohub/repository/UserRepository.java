package org.bibliohub.repository;

import org.bibliohub.model.User;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserRepository extends Repository<User> {
    private static UserRepository instance;
    private UserRepository(String tableName) throws SQLException {
        super(tableName);
    }

    public static UserRepository getInstance(String tableName) throws SQLException {
        if (instance == null) {
            instance = new UserRepository(tableName);
        }
        return instance;
    }

    private String insert = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private String delete = "DELETE FROM users WHERE ID = ?";
    private String edit = "UPDATE users SET name = ?, companyId = ?, shelfId = ?, wishlistId = ? WHERE ID = ?";

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        var result = getAllStatement.executeQuery();
        while(result.next()) {
            users.add(new User(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("companyId"),
                    result.getInt("shelfId"),
                    result.getInt("wishlistId"))
            );
        }
        return users;
    }

    @Override
    public User findById(int id) throws SQLException {
        findByIdStatement.setInt(1, id);
        var result = findByIdStatement.executeQuery();
        if (result.next()) {
            return new User(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("companyId"),
                    result.getInt("shelfId"),
                    result.getInt("wishlistId")
            );
        } else return null;
    }

    public void insert(String name, int companyId, int shelfId, int wishlistId) throws SQLException {
        var insertStatement = db.prepareStatement(insert);
        insertStatement.setString(1, name);
        insertStatement.setInt(2, companyId);
        insertStatement.setInt(3, shelfId);
        insertStatement.setInt(4, wishlistId);
        insertStatement.executeUpdate();
    }

    public void deleteById(int id) throws SQLException {
        var deleteStatement = db.prepareStatement(delete);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    public void editById(int id, String name, int companyId, int shelfId, int wishlistId) throws SQLException {
        var editStatement = db.prepareStatement(edit);
        editStatement.setString(1, name);
        editStatement.setInt(2, companyId);
        editStatement.setInt(3, shelfId);
        editStatement.setInt(4, wishlistId);
        editStatement.setInt(5, id);
        editStatement.executeUpdate();
    }
}
