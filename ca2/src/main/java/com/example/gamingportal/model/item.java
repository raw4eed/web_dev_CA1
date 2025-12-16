package com.example.gamingportal.model;

public class item {

    private int id;
    private String title;
    private String description;
    private int startPrice;
    private String sellerUsername;

    public item() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStartPrice() { return startPrice; }
    public void setStartPrice(int startPrice) { this.startPrice = startPrice; }

    public String getSellerUsername() { return sellerUsername; }
    public void setSellerUsername(String sellerUsername) { this.sellerUsername = sellerUsername; }
}
