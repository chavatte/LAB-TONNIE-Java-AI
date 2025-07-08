package dev.chavatte.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:chavatteboard.db";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        try (Statement statement = connection.createStatement()) {
            statement.execute("pragma enable_trigger = 1;");
        }
        return connection;
    }

    public static String getDbUrl() {
        return DB_URL;
    }
}