package com.example.gamingportal;

public class player {
    private long id;
    private String gamerTag;
    private String passwordHash;
    private int credits;

    public player(long id, String gamerTag, String passwordHash, int credits) {
        this.id = id;
        this.gamerTag = gamerTag;
        this.passwordHash = passwordHash;
        this.credits = credits;
    }

    public long getId() { return id; }
    public String getGamerTag() { return gamerTag; }
    public String getPasswordHash() { return passwordHash; }
    public int getCredits() { return credits; }

    public void setCredits(int credits) { this.credits = credits; }
}
