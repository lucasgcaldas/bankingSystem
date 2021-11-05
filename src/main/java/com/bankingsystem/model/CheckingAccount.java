package com.bankingsystem.model;

import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

public class CheckingAccount extends Account {

    private BigDecimal rate = new BigDecimal("5.0");

    public CheckingAccount(BigDecimal balance, Integer branch, Integer number) throws InvalidNumberException {
        super(balance, branch, number);
    }

    public CheckingAccount() throws InvalidNumberException {
    }

//    @Override
//    public BigDecimal sendTransfer(BigDecimal value, Account account){
//        if (this.getBalance().compareTo(value) >= 0){
//            this.getBalance().subtract(value);
//            this.getBalance().subtract(rate);
//            account.receivedTransfer(value);
//            return this.getBalance();
//        }
//        throw new IllegalArgumentException();
//    }
}
