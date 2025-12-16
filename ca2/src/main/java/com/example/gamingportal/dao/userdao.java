package com.example.gamingportal.dao;

import com.example.gamingportal.db;
import com.example.gamingportal.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class userdao {

    public boolean exists(String username) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("select id from users where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public boolean create(String username, String password, String email) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("insert into users (username, password, email) values (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            return ps.executeUpdate() > 0;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
    public user findByUsername(String username) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("select id, username, password, email from users where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            user u = new user();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            return u;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
    public ArrayList<user> listUsers() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<user> users = new ArrayList<>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("select id, username, password, email from users");
            rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                users.add(u);
            }
            return users;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}
