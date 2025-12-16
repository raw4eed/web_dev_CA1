package com.example.gamingportal.actions;

import com.example.gamingportal.dao.userdao;

public class register {

    private String username;
    private String password;
    private String email;
    private String message;

    public String execute() {
        try {
            userdao dao = new userdao();

            if (dao.exists(username)) {
                message = "Username already exists";
                return "failure";
            }

            boolean ok = dao.create(username, password, email);
            if (ok) {
                return "success";
            }

            message = "Could not create user";
            return "failure";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }
    public String getUsername() { 
        return username; }
    public void setUsername(String username) { 
        this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { 
        this.password = password; }
    public String getEmail() { 
        return email; }
    public void setEmail(String email) { 
        this.email = email; }
    public String getMessage() { return message; }
}
