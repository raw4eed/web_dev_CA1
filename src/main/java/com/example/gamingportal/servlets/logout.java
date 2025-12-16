package com.example.gamingportal.servlets;

import jakarta.servlet.http.*;

import java.io.IOException;

public class logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession s = req.getSession(false);
        if (s != null) {
            s.invalidate();   // end session
        }

        resp.sendRedirect("index.jsp");
    }
}
