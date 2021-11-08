package com.bankingsystem.model;

import com.bankingsystem.controller.AccountController;
import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

public class CheckingAccount extends Account {

    private AccountController ac = new AccountController();

    public CheckingAccount(BigDecimal balance, Integer branch, Integer number) throws InvalidNumberException {
        super(balance, branch, number);
        ac.checkIfExistAccount(this);
    }

    public CheckingAccount() {

    }
}
