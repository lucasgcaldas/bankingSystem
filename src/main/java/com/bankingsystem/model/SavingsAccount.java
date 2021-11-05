package com.bankingsystem.model;

import totalcross.util.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(BigDecimal balance, Integer branch, Integer number){
        super(balance, branch, number);
    }

    public SavingsAccount() {
    }
}
