package com.example.gamingportal.dao;

import com.example.gamingportal.db;
import com.example.gamingportal.model.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class itemdao {

    private Integer getUserId(Connection conn, String username) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select id from users where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
    public boolean addItem(String title, String description, int startPrice, String sellerUsername) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();

            Integer sellerId = getUserId(conn, sellerUsername);
            if (sellerId == null) {
                return false;
            }
            ps = conn.prepareStatement(
                    "insert into items (title, description, start_price, seller_id) values (?, ?, ?, ?)"
            );
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, startPrice);
            ps.setInt(4, sellerId);

            return ps.executeUpdate() > 0;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public ArrayList<item> listItems() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<item> items = new ArrayList<>();

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    "select i.id, i.title, i.description, i.start_price, u.username as seller_username " +
                    "from items i join users u on i.seller_id = u.id"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                item it = new item();
                it.setId(rs.getInt("id"));
                it.setTitle(rs.getString("title"));
                it.setDescription(rs.getString("description"));
                it.setStartPrice(rs.getInt("start_price"));
                it.setSellerUsername(rs.getString("seller_username"));
                items.add(it);
            }
            return items;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}
