package com.adile.jdbc.education.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseOperations {

    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/jdbseducation";
    private final static String JDBC_USERNAME = "root";
    private final static String JDBC_PASSWORD = "root";
    private  Connection connection;

    public void connect() throws SQLException {

        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        }
    }
    public void disconnect() throws SQLException {
        if (connection != null || connection.isClosed()) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
