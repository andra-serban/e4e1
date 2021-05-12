package com.javamaster.e4e1.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //DB Credentials
    private static final String USERNAME = "sammy";
    private static final String PASSWORD = "password";

    //DB URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/e4e1";

    public Connection connection;
    private static DBConnection dbConnection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static Connection getConnection() throws Exception {
        if (dbConnection == null)
            dbConnection = new DBConnection();

        return dbConnection.connection;
    }

}
