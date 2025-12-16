package com.example.gamingportal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {

    static {
        try {
            // load the JDBC driver (as shown in the JDBC slides)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/gaming_portal";
    private static final String USER = "gp_user";
    private static final String PASSWORD = "gp_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
