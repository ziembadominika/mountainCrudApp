package com.crudApp.mountain.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {

    private final Connection conn;
    private static DbManager dbManagerInstance;

    private DbManager() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "mountain_user");
        connectionProps.put("password", "mountain_password");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dominika_learning?serverTimezone=Europe/Warsaw" +
                        "&allowPublicKeyRetrieval=true" +
                        "&useSSL=false",
                (connectionProps));
    }

    public static DbManager getInstance() throws SQLException {
        if (dbManagerInstance == null) {
            dbManagerInstance = new DbManager();
        }
        return dbManagerInstance;
    }

    public Connection getConnection() {
        return conn;
    }
}
