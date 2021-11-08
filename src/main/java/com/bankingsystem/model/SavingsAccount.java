package com.bankingsystem.model;

import com.bankingsystem.controller.AccountController;
import totalcross.util.BigDecimal;

public class SavingsAccount extends Account {

    private AccountController ac = new AccountController();

    public SavingsAccount(BigDecimal balance, Integer branch, Integer number) {
        super(balance, branch, number);
        ac.checkIfExistAccount(this);
    }

    public SavingsAccount() {

    }
}
