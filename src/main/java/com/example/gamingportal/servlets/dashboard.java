package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // must be logged in
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("gamerTag") == null) {
            out.println(page("Please log in first.", "index.jsp", "Back to login"));
            return;
        }

        String tag = (String) s.getAttribute("gamerTag");

        int credits = 0;
        try {
            credits = playerDao.getCredits(tag);
        } catch (SQLException e) {
            out.println(page("Database error: " + e.getMessage(), "index.jsp", "Back"));
            return;
        }

        out.println("<html><body>");
        out.println("<h2>Dashboard</h2>");
        out.println("<p>Welcome, " + tag + "</p>");
        out.println("<p>Credits: " + credits + "</p>");

        out.println("<h3>Add credits</h3>");
        out.println("<form action='credit' method='post'>");
        out.println("<input type='number' name='amount' min='1' required>");
        out.println("<input type='hidden' name='action' value='earn'>");
        out.println("<input type='submit' value='Add'>");
        out.println("</form>");

        out.println("<h3>Spend credits</h3>");
        out.println("<form action='credit' method='post'>");
        out.println("<input type='number' name='amount' min='1' required>");
        out.println("<input type='hidden' name='action' value='spend'>");
        out.println("<input type='submit' value='Spend'>");
        out.println("</form>");

        out.println("<p><a href='logout'>Logout</a></p>");
        out.println("</body></html>");
    }

    private String page(String msg, String link, String linkText) {
        return "<html><body>"
                + "<p>" + msg + "</p>"
                + "<p><a href='" + link + "'>" + linkText + "</a></p>"
                + "</body></html>";
    }
}
