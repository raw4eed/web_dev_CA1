package com.example.gamingportal.actions;

import com.example.gamingportal.dao.biddao;
import com.example.gamingportal.model.bid;

import java.util.ArrayList;

public class itembids {

    private int itemId;
    private ArrayList<bid> bids = new ArrayList<>();

    private String message;

    public String execute() {
        try {
            biddao dao = new biddao();
            bids = dao.listBidsForItem(itemId);
            return "success";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public ArrayList<bid> getBids() {
         return bids; }
    public String getMessage() {
         return message; }
}
