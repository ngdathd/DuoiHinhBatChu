package com.ngdat.duoihinhbatchu;

public class Question {
    private int id;
    private String content;

    public Question(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContext() {
        return content;
    }
}
