package com.example.gamingportal.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        HttpSession s = req.getSession(false); //check for session 
        if (s != null) s.invalidate(); //clear if session exists 
        resp.sendRedirect("index.jsp"); // goto main page
    }
}
