package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/register")
public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("""
        <html><head><title>Register</title></head>
        <body>
          <h2>Register</h2>
          <form method='post' action='register'>
            Gamer Tag: <input type='text' name='gamerTag' required><br><br>
            Password: <input type='password' name='password' required><br><br>
            Confirm: <input type='password' name='confirm' required><br><br>
            <input type='submit' value='Register'>
          </form>
          <p><a href='index.jsp'>Back to Login</a></p>
        </body></html>
        """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tag = req.getParameter("gamerTag");
        String p1 = req.getParameter("password");
        String p2 = req.getParameter("confirm");
        PrintWriter out = resp.getWriter();

        if (tag == null || p1 == null || p2 == null ||
            tag.isEmpty() || p1.isEmpty() || p2.isEmpty()) {
            out.println(message("Please fill all fields", "red", "register"));
            return;
        }

        if (!p1.equals(p2)) {
            out.println(message("Passwords do not match", "red", "register"));
            return;
        }

        try {
            if (playerDao.existsByTag(tag)) {
                out.println(message("Gamer tag already exists", "red", "register"));
                return;
            }

            if (playerDao.register(tag, p1)) {
                out.println(message("Welcome, " + tag + ". You start with 500 credits.", "green", "index.jsp"));
            } else {
                out.println(message("Registration failed.", "red", "register"));
            }
        } catch (SQLException e) {
            out.println(message("Database error: " + e.getMessage(), "red", "register"));
        }
    }

    private static String message(String text, String color, String back) {
        return "<html><body>" +
               "<p style='color:" + color + ";'>" + text + "</p>" +
               "<p><a href='" + back + "'>Continue</a></p></body></html>";
    }
}
