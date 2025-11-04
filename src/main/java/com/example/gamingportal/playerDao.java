package com.example.gamingportal;

import java.sql.*;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class playerDao {
    public static boolean existsByTag(String tag) throws SQLException { //check if tag exists 
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT 1 FROM players WHERE gamer_tag=?")) 
        {
            ps.setString(1, tag);
            try(ResultSet rs = ps.executeQuery()) 
            { return rs.next(); }// true if row exists 
        }
    }
    // reg player with hash pass + 500 creds
    public static boolean register(String tag, String rawPassword)throws SQLException{
        String hash = BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO players (gamer_tag, password_hash, credits) VALUES (?, ?, 500)")) {
            ps.setString(1, tag);
            ps.setString(2, hash);
            return ps.executeUpdate() == 1; // true return  if insert 
        }
    }

    //find player by tag
    public static player findByTag(String tag) throws SQLException {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM players WHERE gamer_tag=?")) 
        {
            ps.setString(1, tag);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    return new player(
                        rs.getLong("id"),
                        rs.getString("gamer_tag"),
                        rs.getString("password_hash"),
                        rs.getInt("credits")
                    );
                }
                return null; //no match
            }
        }
    }
    //get curr amount
    public static int getCredits(String tag) throws SQLException {
        try (Connection cn = db.getConnection(); PreparedStatement ps = cn.prepareStatement( "SELECT credits FROM players WHERE gamer_tag=?")) 
        {
            ps.setString(1, tag);
            try (ResultSet rs = ps.executeQuery()) 
            { return rs.next() ? rs.getInt(1) : 0; }
        }
    }
    //increase credits by ammount 
    public static void addCredits(String tag, int amount) throws SQLException {
        try (Connection cn = db.getConnection(); PreparedStatement ps = cn.prepareStatement( "UPDATE players SET credits=credits+? WHERE gamer_tag=?")) {
            ps.setInt(1, amount);
            ps.setString(2, tag);
            ps.executeUpdate();
        }
    }

    //try spend 
    public static boolean trySpend(String tag, int amount) throws SQLException {
        try (Connection cn = db.getConnection(); PreparedStatement ps = cn.prepareStatement( "UPDATE players SET credits=credits-? WHERE gamer_tag=? AND credits>=?")) {
            ps.setInt(1, amount);
            ps.setString(2, tag);
            ps.setInt(3, amount);
            return ps.executeUpdate() == 1;
        }
    }
}
