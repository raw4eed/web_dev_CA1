package com.example.gamingportal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    static {
        try { // load the mysql driver once class used 
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not on classpath", e); //error if not in path
        }
    }
    // connect config
    private static final String URL = "jdbc:mysql://localhost:3306/gaming_portal?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "gp_user";
    private static final String PASSWORD = "gp_password";
    //open new connect to db
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
