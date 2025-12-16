package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class credit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // must be logged in
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("gamerTag") == null) {
            out.println(page("Please log in first.", "index.jsp", "Back to login"));
            return;
        }

        String tag = (String) s.getAttribute("gamerTag");

        String action = req.getParameter("action");
        String amountStr = req.getParameter("amount");

        int amount = 0;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (Exception ignored) {
            amount = 0;
        }

        if (amount <= 0) {
            out.println(page("Please enter a positive number.", "dashboard", "Back to dashboard"));
            return;
        }

        try {
            boolean ok = true;

            if ("earn".equals(action)) {
                playerDao.addCredits(tag, amount);
            } else if ("spend".equals(action)) {
                ok = playerDao.trySpend(tag, amount);
            } else {
                out.println(page("Unknown action.", "dashboard", "Back to dashboard"));
                return;
            }

            int balance = playerDao.getCredits(tag);

            if (!ok) {
                out.println(result("Not enough credits. Transaction cancelled.", tag, balance));
            } else {
                out.println(result("Transaction complete.", tag, balance));
            }

        } catch (SQLException e) {
            out.println(page("Database error: " + e.getMessage(), "dashboard", "Back to dashboard"));
        }
    }

    private String result(String msg, String tag, int credits) {
        return "<html><body>"
                + "<h3>" + msg + "</h3>"
                + "<p>" + tag + ", your new balance is " + credits + " credits</p>"
                + "<p><a href='dashboard'>Back to Dashboard</a></p>"
                + "</body></html>";
    }

    private String page(String msg, String link, String linkText) {
        return "<html><body>"
                + "<p>" + msg + "</p>"
                + "<p><a href='" + link + "'>" + linkText + "</a></p>"
                + "</body></html>";
    }
}
