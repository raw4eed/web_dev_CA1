package com.example.gamingportal;

import java.sql.*;

public class playerDao {

    public static boolean existsByTag(String tag) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement("SELECT 1 FROM players WHERE gamer_tag=?");
            ps.setString(1, tag);

            rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }

    public static boolean register(String tag, String password) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement(
                    "INSERT INTO players (gamer_tag, password, credits) VALUES (?, ?, 500)"
            );
            ps.setString(1, tag);
            ps.setString(2, password);

            return ps.executeUpdate() == 1;
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }

    public static boolean checkLogin(String tag, String pass) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement("SELECT password FROM players WHERE gamer_tag=?");
            ps.setString(1, tag);

            rs = ps.executeQuery();
            if (rs.next()) {
                String dbPass = rs.getString(1);
                return pass.equals(dbPass);
            }
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }

    public static int getCredits(String tag) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement("SELECT credits FROM players WHERE gamer_tag=?");
            ps.setString(1, tag);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }

    public static void addCredits(String tag, int amount) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement("UPDATE players SET credits = credits + ? WHERE gamer_tag=?");
            ps.setInt(1, amount);
            ps.setString(2, tag);

            ps.executeUpdate();
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }

    public static boolean trySpend(String tag, int amount) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement(
                    "UPDATE players SET credits = credits - ? WHERE gamer_tag=? AND credits >= ?"
            );
            ps.setInt(1, amount);
            ps.setString(2, tag);
            ps.setInt(3, amount);

            return ps.executeUpdate() == 1;
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }

    public static player findByTag(String tag) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = db.getConnection();
            ps = cn.prepareStatement("SELECT id, gamer_tag, password, credits FROM players WHERE gamer_tag=?");
            ps.setString(1, tag);

            rs = ps.executeQuery();
            if (rs.next()) {
                player p = new player();
                p.setId(rs.getLong("id"));
                p.setGamerTag(rs.getString("gamer_tag"));
                p.setPassword(rs.getString("password"));
                p.setCredits(rs.getInt("credits"));
                return p;
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (cn != null) try { cn.close(); } catch (Exception ignored) {}
        }
    }
}
