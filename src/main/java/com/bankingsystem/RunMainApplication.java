package com.bankingsystem;

import com.bankingsystem.main.Main;
import totalcross.TotalCrossApplication;
import totalcross.sys.InvalidNumberException;

public class RunMainApplication {
    public static void main(String[] args) throws InvalidNumberException {
        TotalCrossApplication.run(Main.class, "/scr", "360x639", "/r", "5443444B5AAEEB90306B00E4");
    }
}
