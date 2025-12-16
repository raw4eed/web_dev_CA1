package com.example.gamingportal.actions;

import com.example.gamingportal.dao.biddao;
import com.example.gamingportal.model.bid;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class mybids implements SessionAware {
    private Map session;
    private ArrayList<bid> bids = new ArrayList<>();
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
            biddao dao = new biddao();
            bids = dao.listBidsForUser(currentUser);
            return "success";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }

    public void setSession(Map session) {
        this.session = session;
    }
    public ArrayList<bid> getBids() { return bids; }
    public String getMessage() { return message; }
}
