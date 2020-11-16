package com.quiraxical.rollingpaper;

public class Rollingpaper {
    private int id;
    private User user;
    private String to;
    private String title;
    private boolean isClosed = false;
    
    public void setId(int val) {
        this.id = val;
    }
    public int getId() {
        return this.id;
    }

    public void setUser(User val) {
        this.user = val;
    }
    public User getUser() {
        return this.user;
    }

    public void setTo(String val) {
        this.to = val;
    }
    public String getTo() {
        return this.to;
    }

    public void setTitle(String val) {
        this.title = val;
    }
    public String getTitle() {
        return this.title;
    }

    public void setIsClosed(boolean val) {
        this.isClosed = val;
    }
    public boolean getIsClosed() {
        return this.isClosed;
    }
}
