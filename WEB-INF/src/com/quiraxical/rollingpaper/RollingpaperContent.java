package com.quiraxical.rollingpaper;

public class RollingpaperContent {
    private int id;
    private String text;
    private String from;

    public void setId(int val) {
        this.id = val;
    }
    public int getId() {
        return this.id;
    }

    public void setText(String val) {
        this.text = val;
    }
    public String getText() {
        return this.text;
    }

    public void setFrom(String val) {
        this.from = val;
    }
    public String getFrom() {
        return this.from;
    }
}
