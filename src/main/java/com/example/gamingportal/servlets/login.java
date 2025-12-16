package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String tag = req.getParameter("gamerTag");
        String pass = req.getParameter("password");

        if (tag == null || pass == null || tag.equals("") || pass.equals("")) {
            out.println(page("Please enter gamer tag and password.", "index.jsp", "Back"));
            return;
        }

        try {
            boolean ok = playerDao.checkLogin(tag, pass);

            if (ok) {
                HttpSession s = req.getSession(true);
                s.setAttribute("gamerTag", tag);
                resp.sendRedirect("dashboard");
                return;
            }

            out.println(page("Invalid login.", "index.jsp", "Try again"));

        } catch (SQLException e) {
            out.println(page("Database error: " + e.getMessage(), "index.jsp", "Back"));
        }
    }

    private String page(String msg, String link, String linkText) {
        return "<html><body>"
                + "<p>" + msg + "</p>"
                + "<p><a href='" + link + "'>" + linkText + "</a></p>"
                + "</body></html>";
    }
}
