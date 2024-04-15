package org.bibliohub.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppDb {
    private static Connection connection;
    private static final String connectionString = "jdbc:postgresql://localhost:5432/bibliohub";
    private static final String username = "postgres";
    private static final String password = "admin";

    private AppDb() { }

    public static Connection getAppDb() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(connectionString, username, password);
        }
        return connection;
    }
}
