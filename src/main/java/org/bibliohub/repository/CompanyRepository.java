package org.bibliohub.repository;

import org.bibliohub.model.Company;
import java.sql.SQLException;
import java.util.ArrayList;


public class CompanyRepository extends Repository<Company> {
    private static CompanyRepository instance;
    private CompanyRepository(String tableName) throws SQLException {
        super(tableName);
    }

    public static CompanyRepository getInstance(String tableName) throws SQLException {
        if (instance == null) {
            instance = new CompanyRepository(tableName);
        }
        return instance;
    }

    private String insert = "INSERT INTO companies VALUES (DEFAULT, ?, ?)";
    private String delete = "DELETE FROM companies WHERE ID = ?";
    private String edit = "UPDATE companies SET name = ?, cui = ? WHERE ID = ?";

    @Override
    public ArrayList<Company> getAll() throws SQLException {
        ArrayList<Company> companies = new ArrayList<>();
        var result = getAllStatement.executeQuery();
        while(result.next()) {
            companies.add(new Company(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getLong("cui"))
            );
        }
        return companies;
    }

    @Override
    public Company findById(int id) throws SQLException {
        findByIdStatement.setInt(1, id);
        var result = findByIdStatement.executeQuery();
        if (result.next()) {
            return new Company(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getLong("cui")
            );
        } else return null;
    }

    public void insert(String name, long cui) throws SQLException {
        var insertStatement = db.prepareStatement(insert);
        insertStatement.setString(1, name);
        insertStatement.setLong(2, cui);
        insertStatement.executeUpdate();
    }

    public void deleteById(int id) throws SQLException {
        var deleteStatement = db.prepareStatement(delete);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    public void editById(int id, String name, long cui) throws SQLException {
        var editStatement = db.prepareStatement(edit);
        editStatement.setString(1, name);
        editStatement.setLong(2, cui);
        editStatement.setInt(3, id);
        editStatement.executeUpdate();
    }
}
