package com.example.gamingportal.servlets;

import com.example.gamingportal.player;
import com.example.gamingportal.playerDao;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tag = req.getParameter("gamerTag"); //grab from data
        String pass = req.getParameter("password");

        try{
            player p = playerDao.findByTag(tag); // find user in db
            if (p != null && BCrypt.verifyer().verify(pass.toCharArray(), p.getPasswordHash()).verified){ // if exists and pass matches BCrypt
                HttpSession s = req.getSession(true); //create session for later
                s.setAttribute("gamerTag", p.getGamerTag());
                resp.sendRedirect("dashboard"); //go to dashboard if succ login
                return;
            }
        } 
        catch (SQLException e){throw new IOException(e);} //error 
 
        PrintWriter out = resp.getWriter(); //if invalid show page
        out.println("<html><body>" + "<p style='color:red;'>Invalid login.</p>" + "<p><a href='index.jsp'>Try again</a></p></body></html>");
    }
}
