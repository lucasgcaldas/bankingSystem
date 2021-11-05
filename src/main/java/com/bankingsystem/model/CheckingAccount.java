package com.bankingsystem.model;

import com.bankingsystem.controller.AccountController;
import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

public class CheckingAccount extends Account {

//    private BigDecimal rate = new BigDecimal("5.0");

    public CheckingAccount(BigDecimal balance, Integer branch, Integer number) throws InvalidNumberException {
        super(balance, branch, number);
    }

    public CheckingAccount() throws InvalidNumberException {
        super(new BigDecimal("15000.00"), 1111, 12345);
        AccountController ac = new AccountController();
        ac.saveAccount(this);
    }
}
