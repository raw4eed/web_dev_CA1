package com.example.gamingportal.model;

public class bid {

    private int id;
    private int itemId;
    private String username;
    private int amount;

    public bid() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
