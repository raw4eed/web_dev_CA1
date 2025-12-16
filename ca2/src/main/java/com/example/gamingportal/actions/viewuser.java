package com.example.gamingportal.actions;

import com.example.gamingportal.dao.userdao;
import com.example.gamingportal.model.user;

public class viewuser {

    private String username;

    private user u;
    private String message;

    public String execute() {
        try {
            userdao dao = new userdao();
            u = dao.findByUsername(username);
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

    public String getUsername() { 
        return username; }
    public void setUsername(String username) { 
        this.username = username; }
    public user getU() { 
        return u; 
    }
    public String getMessage() { 
        return message;
     }
}
