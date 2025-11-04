package com.example.gamingportal;

public class player {
    private long id;
    private String gamerTag;
    private String passwordHash;
    private int credits;

    public player(long id, String gamerTag, String passwordHash, int credits) { //constructor 
        this.id = id;
        this.gamerTag = gamerTag;
        this.passwordHash = passwordHash;
        this.credits = credits;
    }
    //getters
    public long getId() 
    {return id;}
    public String getGamerTag() 
    {return gamerTag;}
    public String getPasswordHash()
    {return passwordHash;}
    public int getCredits() 
    {return credits;}
    //setters
    public void setCredits(int credits) 
    {this.credits = credits;}
}
