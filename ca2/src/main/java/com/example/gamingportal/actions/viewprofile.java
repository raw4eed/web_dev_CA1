package com.example.gamingportal.actions;

import com.example.gamingportal.dao.userdao;
import com.example.gamingportal.model.user;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class viewprofile implements SessionAware {

    private Map session;
    private user u;
    private String message;
    public String execute() {
        String currentUser = null;
        if (session != null) {
            currentUser = (String) session.get("currentUser");
        }
        if (currentUser == null) {
            return "login";
        }
        try {
            userdao dao = new userdao();
            u = dao.findByUsername(currentUser);
            if (u == null) {
                message = "User not found";
                return "failure";
            }
            return "success";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public user getU() {
         return u; }
    public String getMessage() { 
        return message; }
}
