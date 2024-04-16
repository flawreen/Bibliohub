package org.bibliohub.repository;

import org.bibliohub.config.AppDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

abstract class Repository<T> {
    protected final Connection db;
    protected StringBuilder getAll;
    protected StringBuilder findById;
    protected PreparedStatement getAllStatement;
    protected PreparedStatement findByIdStatement;
    Repository(String tableName) throws SQLException {
        this.db = AppDb.getAppDb();

        this.getAll = new StringBuilder("SELECT * FROM ").append(tableName);
        this.getAllStatement = db.prepareStatement(getAll.toString());

        this.findById = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE id = ?");
        this.findByIdStatement = db.prepareStatement(findById.toString());

    }

    public abstract ArrayList<T> getAll() throws SQLException;
    public abstract T findById(int id) throws SQLException;
}
