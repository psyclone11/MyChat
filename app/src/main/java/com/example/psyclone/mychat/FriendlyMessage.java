package com.example.psyclone.mychat;

/**
 * Created by Psyclone on 5/7/2560.
 */

public class FriendlyMessage {
    private String text;
    private String username;

    public FriendlyMessage(){

    }

    public FriendlyMessage(String text, String username) {
        this.text = text;
        this.username = username;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
