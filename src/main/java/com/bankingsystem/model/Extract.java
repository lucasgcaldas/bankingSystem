package com.bankingsystem.model;

import totalcross.util.BigDecimal;

public class Extract {

    private User user;
    private User userDestiny;
    private BigDecimal value;
    private String date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserDestiny() {
        return userDestiny;
    }

    public void setUserDestiny(User userDestiny) {
        this.userDestiny = userDestiny;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
