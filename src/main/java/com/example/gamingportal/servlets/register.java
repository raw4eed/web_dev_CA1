package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h2>Register</h2>");
        out.println("<form method='post' action='register'>");
        out.println("Gamer Tag: <input type='text' name='gamerTag'><br><br>");
        out.println("Password: <input type='password' name='password'><br><br>");
        out.println("Confirm Password: <input type='password' name='confirm'><br><br>");
        out.println("<input type='submit' value='Register'>");
        out.println("</form>");
        out.println("<p><a href='index.jsp'>Back to login</a></p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String tag = req.getParameter("gamerTag");
        String p1 = req.getParameter("password");
        String p2 = req.getParameter("confirm");

        // basic validation (server-side)
        if (tag == null || p1 == null || p2 == null || tag.equals("") || p1.equals("") || p2.equals("")) {
            out.println(page("Please fill all fields.", "register", "Back"));
            return;
        }

        // password match check (must be done in servlet, not JS)
        if (!p1.equals(p2)) {
            out.println(page("Passwords do not match.", "register", "Back"));
            return;
        }

        try {
            // check if gamer tag is already taken
            if (playerDao.existsByTag(tag)) {
                out.println(page("Gamer tag already exists.", "register", "Back"));
                return;
            }

            // create user with 500 credits
            boolean created = playerDao.register(tag, p1);

            if (created) {
                out.println("<html><body>");
                out.println("<h3>Registration complete</h3>");
                out.println("<p>Welcome, " + tag + ". You start with 500 credits.</p>");
                out.println("<p><a href='index.jsp'>Go to login</a></p>");
                out.println("</body></html>");
            } else {
                out.println(page("Registration failed.", "register", "Back"));
            }

        } catch (SQLException e) {
            out.println(page("Database error: " + e.getMessage(), "register", "Back"));
        }
    }

    private String page(String msg, String link, String linkText) {
        return "<html><body>"
                + "<p>" + msg + "</p>"
                + "<p><a href='" + link + "'>" + linkText + "</a></p>"
                + "</body></html>";
    }
}
