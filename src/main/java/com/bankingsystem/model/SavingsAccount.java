package com.bankingsystem.model;

import com.bankingsystem.controller.AccountController;
import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(BigDecimal balance, Integer branch, Integer number){
        super(balance, branch, number);
    }

    public SavingsAccount() throws InvalidNumberException {
        super(new BigDecimal("30000.00"), 2222, 56789);
        AccountController ac = new AccountController();
        ac.checkIfExistAccount(this);
    }
}
