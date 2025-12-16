package com.example.gamingportal.actions;

import com.example.gamingportal.dao.userdao;
import com.example.gamingportal.model.user;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class login implements SessionAware {

    private Map session;
    private String username;
    private String password;
    private String message;
    public String execute() {
        try {
            userdao dao = new userdao();
            user u = dao.findByUsername(username);
            if (u == null) {
                message = "User not found";
                return "failure";
           }
            if (u.getPassword() != null && u.getPassword().equals(password)) {
                if (session != null) {
                    session.put("currentUser", u.getUsername());
                }
                return "success";
            }
            message = "Wrong password";
            return "failure";
        } catch (Exception e) {
           message = "Database error";
            return "failure";
        }
    }
    public void setSession(Map session) {
        this.session = session;
    }
    public String getUsername() 
    { return username; }
    public void setUsername(String username) 
    { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) 
    { this.password = password;

     }

    public String getMessage() { return message; }
}
