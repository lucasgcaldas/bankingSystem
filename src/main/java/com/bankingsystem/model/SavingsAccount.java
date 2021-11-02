package com.bankingsystem.model;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private BigDecimal rate = new BigDecimal("5.0");

    public SavingsAccount(BigDecimal balance, Integer branch, Integer number){
        super(balance.multiply(new BigDecimal("1.01")), branch, number);
    }

    @Override
    public boolean transferToAccount(BigDecimal value, Account account){
        if (this.getBalance().compareTo(value) >= 0){
            this.getBalance().subtract(value);
            this.getBalance().subtract(rate);
            account.deposit(value);
            return true;
        }
        throw new IllegalArgumentException();
    }
}
