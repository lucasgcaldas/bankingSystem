package com.bankingsystem;

import totalcross.TotalCrossApplication;
import totalcross.sys.InvalidNumberException;

public class RunBankingSystemApplication {
    public static void main(String[] args) throws InvalidNumberException {
        TotalCrossApplication.run(BankingSystem.class, "/scr", "360x639", "/r", "5443444B5AAEEB90306B00E4");
    }
}
