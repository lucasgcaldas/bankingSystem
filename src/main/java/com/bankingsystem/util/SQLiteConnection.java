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
            util = new SQLiteUtil(Settings.appPath, "db/bankingSystem.db");
            createTableAccount();
//            createTableTransfer();
            createTableExtract();
            createTableUser();
            createTableGroupMessage();
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
                    "pk_id INTEGER PRIMARY KEY," +
                    "kind_account STRING  NOT NULL," +
                    "balance DOUBLE," +
                    "branch INTEGER NOT NULL," +
                    "number INTEGER NOT NULL," +
                    "user STRING NOT NULL" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void createTableTransfer() {
//        try {
//            Statement st = util.con().createStatement();
//            st.execute("CREATE TABLE IF NOT EXISTS tb_transfer(" +
//                    "kind_transfer STRING  NOT NULL," +
//                    "origin STRING  NOT NULL," +
//                    "ori_branch INTEGER  NOT NULL," +
//                    "ori_number INTEGER  NOT NULL," +
//                    "value DOUBLE  NOT NULL," +
//                    "destiny STRING  NOT NULL," +
//                    "des_branch INTEGER  NOT NULL," +
//                    "des_number INTEGER  NOT NULL" +
//                    ");");
//            st.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void createTableExtract() {
        try {
            Statement st = util.con().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tb_extract(" +
                    "kind_transfer STRING  NOT NULL," +
                    "user STRING  NOT NULL," +
//                    "origin STRING  NOT NULL," +
                    "ori_branch INTEGER  NOT NULL," +
                    "ori_number INTEGER  NOT NULL," +
                    "value DOUBLE  NOT NULL," +
                    "user_destiny STRING  NOT NULL," +
//                    "destiny STRING  NOT NULL," +
                    "des_branch INTEGER  NOT NULL," +
                    "des_number INTEGER  NOT NULL," +
                    "date STRING  NOT NULL" +
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
                    "birthdate STRING  NOT NULL," +
                    "account1 INTEGER REFERENCES tb_account (number)," +
                    "account2 INTEGER REFERENCES tb_account (number)," +
                    "password STRING  NOT NULL" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableGroupMessage() {
        try {
            Statement st = util.con().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tb_messages(" +
                    "pk_id INTEGER PRIMARY KEY," +
                    "name STRING  NOT NULL," +
                    "account INTEGER  NOT NULL," +
                    "hour STRING  NOT NULL," +
                    "message STRING  NOT NULL" +
                    ");");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
