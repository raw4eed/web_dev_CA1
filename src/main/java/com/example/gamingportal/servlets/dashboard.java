package com.example.gamingportal.servlets;

import com.example.gamingportal.playerDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/dashboard")
public class dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);  //check if there is session
        if(s == null ||s.getAttribute("gamerTag") == null){ //if not logged - sends to the main page
            resp.sendRedirect("index.jsp");
            return;}

        String tag = (String) s.getAttribute("gamerTag"); //get tag from session 
        int credits = 0;
        try{ // ask db for balance 
            credits = playerDao.getCredits(tag);
        } 
        catch (SQLException e) {throw new IOException(e);} //error query

        PrintWriter out = resp.getWriter(); //html response 
        out.println("<html><body>");
        out.println("<h2>Welcome, " + tag + "</h2>");
        out.println("<p>Credits: " + credits + "</p>");
        out.println("""
        <form action='credit' method='post'>
          <input type='number' name='amount' min='1' required>
          <input type='hidden' name='action' value='earn'>
          <input type='submit' value='Add Credits'>
        </form>
        <form action='credit' method='post'>
          <input type='number' name='amount' min='1' required>
          <input type='hidden' name='action' value='spend'>
          <input type='submit' value='Spend Credits'>
        </form>
        <p><a href='logout'>Logout</a></p>
        </body></html>
        """);
    }
}
