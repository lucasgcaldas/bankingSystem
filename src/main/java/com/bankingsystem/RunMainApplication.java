package com.bankingsystem;

import com.bankingsystem.view.Login;
import totalcross.TotalCrossApplication;
import totalcross.sys.InvalidNumberException;

public class RunMainApplication {
    public static void main(String[] args) throws InvalidNumberException {
        TotalCrossApplication.run(Login.class, "/scr", "360x639", "/r", "5443444B5AAEEB90306B00E4");
    }
}
