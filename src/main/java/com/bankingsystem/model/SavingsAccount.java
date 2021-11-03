package com.bankingsystem.model;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(BigDecimal balance, Integer branch, Integer number){
        super(balance, branch, number);
    }

    public SavingsAccount() {
    }
}
