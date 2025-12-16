package com.example.gamingportal;

public class player {

    private long id;
    private String gamerTag;
    private String password;
    private int credits;

    public player() {
    }

    public player(long id, String gamerTag, String password, int credits) {
        this.id = id;
        this.gamerTag = gamerTag;
        this.password = password;
        this.credits = credits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
