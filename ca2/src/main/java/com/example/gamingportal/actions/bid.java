package com.example.gamingportal.actions;

import com.example.gamingportal.dao.biddao;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class bid implements SessionAware {

    private Map session;

    private int itemId;
    private int amount;

    private String message;

    public String execute() {
        String currentUser = null;
        if (session != null) {
            currentUser = (String) session.get("currentUser");
        }
        if (currentUser == null) {
            return "login";
        }

        if (amount == 0) {
            return "input";
        }

        try {
            biddao dao = new biddao();
            boolean ok = dao.placeBid(itemId, currentUser, amount);
            if (ok) {
                message = "Bid placed";
                return "success";
            }
            message = "Could not place bid";
            return "failure";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }
    public void setSession(Map session) {
        this.session = session;
    }
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getMessage() { return message; }
}