package com.example.gamingportal.dao;

import com.example.gamingportal.db;
import com.example.gamingportal.model.bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class biddao {

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

    public boolean placeBid(int itemId, String username, int amount) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = db.getConnection();
            Integer bidderId = getUserId(conn, username);
            if (bidderId == null) {
                return false;
            }
            ps = conn.prepareStatement(
                    "insert into bids (item_id, bidder_id, amount) values (?, ?, ?)"
            );
            ps.setInt(1, itemId);
            ps.setInt(2, bidderId);
            ps.setInt(3, amount);

            return ps.executeUpdate() > 0;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public ArrayList<bid> listBidsForItem(int itemId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<bid> bids = new ArrayList<>();

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    "select b.id, b.item_id, u.username, b.amount " + "from bids b join users u on b.bidder_id = u.id " + "where b.item_id = ?"
            );
            ps.setInt(1, itemId);
            rs = ps.executeQuery();

            while (rs.next()) {
                bid b = new bid();
                b.setId(rs.getInt("id"));
                b.setItemId(rs.getInt("item_id"));
                b.setUsername(rs.getString("username"));
                b.setAmount(rs.getInt("amount"));
                bids.add(b);
            }

            return bids;
        } 
        finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public ArrayList<bid> listBidsForUser(String username) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<bid> bids = new ArrayList<>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    "select b.id, b.item_id, u.username, b.amount " + "from bids b join users u on b.bidder_id = u.id " + "where u.username = ?"
            );
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                bid b = new bid();
                b.setId(rs.getInt("id"));
                b.setItemId(rs.getInt("item_id"));
                b.setUsername(rs.getString("username"));
                b.setAmount(rs.getInt("amount"));
                bids.add(b);
            }
            return bids;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}
