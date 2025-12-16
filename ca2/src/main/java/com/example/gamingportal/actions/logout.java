package com.example.gamingportal.actions;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class logout implements SessionAware {
    private Map session;
    public String execute() {
        if (session != null) {
            session.remove("currentUser");
        }        return "success";
    }
    public void setSession(Map session) {
        this.session = session;
    }
}
