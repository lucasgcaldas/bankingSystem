package com.bankingsystem.util;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.PreparedStatement;
import totalcross.sql.Statement;
import totalcross.sys.Settings;

import java.sql.SQLException;

public class SQLiteConnection {

    private static SQLiteConnection instance = null;
    public SQLiteUtil util;

    public SQLiteConnection() {
        try {
            util = new SQLiteUtil(Settings.appPath, "db/banking.db");
            createTableAccount();
            createTableTransfer();
            createTableUser();
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }

    public void createTableAccount() {
        try {
            Statement st = util.con().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tb_account(" +
                    "pk_id INTEGER PRIMARY KEY," +
                    "kind_account STRING  NOT NULL," +
                    "balance DOUBLE," +
                    "branch INTEGER NOT NULL," +
                    "number INTEGER NOT NULL UNIQUE" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public void createTableTransfer() {
        try {
            Statement st = util.con().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tb_transfer(" +
                    "origin STRING  NOT NULL," +
                    "value DOUBLE  NOT NULL," +
                    "destiny STRING  NOT NULL," +
                    "new_origin_balance DECIMAL NOT NULL," +
                    "new_received_balance DECIMAL NOT NULL" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public void createTableUser() {
        try {
            Statement st = util.con().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tb_user(" +
                    "pk_id INTEGER PRIMARY KEY," +
                    "name STRING  NOT NULL," +
                    "cpf STRING  NOT NULL," +
                    "account INTEGER REFERENCES tb_account (number)" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
