package com.example.recipeapp;
public class Feedback {

    // string variable for
    // storing name.
    private String name;

    // string variable for storing
    // content
    private String content;

    private String userId;
    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public Feedback() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId ){
        this.userId = userId;
    }
}
