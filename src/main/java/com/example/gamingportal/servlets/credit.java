package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/credit")
public class credit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("gamerTag") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String tag = (String) s.getAttribute("gamerTag");
        String action = req.getParameter("action");
        int amount = 0;

        try {
            amount = Integer.parseInt(req.getParameter("amount"));
        } catch (NumberFormatException ignored) {}

        if (amount <= 0) {
            resp.sendRedirect("dashboard");
            return;
        }

        boolean ok = true;

        try {
            if ("earn".equals(action)) {
                playerDao.addCredits(tag, amount);
            } else if ("spend".equals(action)) {
                ok = playerDao.trySpend(tag, amount);
            }
        } catch (SQLException e) {
            show(resp, "db error: " + e.getMessage(), tag, -1);
            return;
        }

        try {
            int balance = playerDao.getCredits(tag);
            if (!ok) {
                showPopup(resp, "not enough credits", tag, balance);
            } else {
                show(resp, "transaction complete", tag, balance);
            }
        } catch (SQLException e) {
            show(resp, "db error: " + e.getMessage(), tag, -1);
        }
    }

    private void show(HttpServletResponse resp, String msg, String tag, int credits)
            throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h3>" + msg + "</h3>");
        if (credits >= 0)
            out.println("<p>" + tag + ", your new balance is " + credits + " credits</p>");
        out.println("<p><a href='dashboard'>Back to Dashboard</a></p>");
        out.println("</body></html>");
    }

    //insufficient balance popup
    private void showPopup(HttpServletResponse resp, String msg, String tag, int credits)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<script>alert('" + msg + "'); window.location='dashboard';</script>");
        out.println("</body></html>");
    }
}
