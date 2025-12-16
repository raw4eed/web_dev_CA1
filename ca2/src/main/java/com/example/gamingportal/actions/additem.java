package com.example.gamingportal.actions;

import com.example.gamingportal.dao.itemdao;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class additem implements SessionAware {

    private Map session;
    private String title;
    private String description;
    private int startPrice;
    private String message;
    public String execute() {
        String currentUser = null;
        if (session != null) {
            currentUser = (String) session.get("currentUser");
        }
        if (currentUser == null) {
            return "login";
        }
        if (title == null) {
            return "input";
        }
        try {
            itemdao dao = new itemdao();
            boolean ok = dao.addItem(title, description, startPrice, currentUser);
            if (ok) {
                message = "Item added";
                return "success";
            }
            message = "Could not add item";
            return "failure";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }

    public void setSession(Map session) {
        this.session = session;
    }

   public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getStartPrice() { return startPrice; }
    public void setStartPrice(int startPrice) { this.startPrice = startPrice; }

    public String getMessage() { return message; }
}
