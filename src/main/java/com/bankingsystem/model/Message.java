package com.bankingsystem.model;

public class Message {

    private User user;
    private Account account;
    private String hour;
    private String messageDescription;

    public Message(User user, Account account, String hour, String messageDescription) {
        this.user = user;
        this.account = account;
        this.hour = hour;
        this.messageDescription = messageDescription;
    }

    public Message() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }
}
