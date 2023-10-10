package com.example.cloudcounselageconnect;

public class msgModel {
    String message;
    boolean isSender;

    //empty constructor
    public msgModel() {
    }

    //constructor
    public msgModel(String message, boolean isSender) {
        this.message = message;
        this.isSender = isSender;
    }

    //getter
    public String getMessage() {
        return message;
    }

    //setter
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for isSender
    public boolean isSender() {
        return isSender;
    }

    // Setter for isSender
    public void setSender(boolean sender) {
        isSender = sender;
    }

}
