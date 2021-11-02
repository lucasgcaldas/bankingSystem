package com.bankingsystem.model;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    
    public CheckingAccount(BigDecimal balance, Integer branch, Integer number){
        super(balance, branch, number);
    }

}
