package com.example.gamingportal.actions;

import com.example.gamingportal.dao.userdao;
import com.example.gamingportal.model.user;

import java.util.ArrayList;

public class listusers {

    private ArrayList<user> users = new ArrayList<>();
    private String message;
    public String execute() {
        try {
            userdao dao = new userdao();
            users = dao.listUsers();
            return "success";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }    }
    public ArrayList<user> getUsers() { return users; }
    public String getMessage() { return message; }
}
