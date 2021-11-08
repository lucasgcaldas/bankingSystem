package com.bankingsystem.util;

import totalcross.db.sqlite.SQLiteUtil;
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
            e.printStackTrace();
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
                    "kind_account STRING  NOT NULL," +
                    "balance DOUBLE," +
                    "branch INTEGER NOT NULL," +
                    "pk_number INTEGER PRIMARY KEY NOT NULL" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableTransfer() {
        try {
            Statement st = util.con().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tb_transfer(" +
                    "kind_transfer STRING  NOT NULL," +
                    "origin STRING  NOT NULL," +
                    "ori_branch INTEGER  NOT NULL," +
                    "ori_number INTEGER  NOT NULL," +
                    "value DOUBLE  NOT NULL," +
                    "destiny STRING  NOT NULL," +
                    "des_branch INTEGER  NOT NULL," +
                    "des_number INTEGER  NOT NULL" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
    }
}
