package com.example.gamingportal.actions;

import com.example.gamingportal.dao.itemdao;
import com.example.gamingportal.model.item;

import java.util.ArrayList;

public class listitems {

    private ArrayList<item> items = new ArrayList<>();
    private String message;

    public String execute() {
        try {
            itemdao dao = new itemdao();
            items = dao.listItems();
            return "success";
        } catch (Exception e) {
            message = "Database error";
            return "failure";
        }
    }

    public ArrayList<item> getItems() { return items; }

    public String getMessage() { return message; }
}
