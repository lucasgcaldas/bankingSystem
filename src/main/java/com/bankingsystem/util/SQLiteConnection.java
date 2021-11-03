package com.bankingsystem.util;

import java.sql.*;

public class SQLiteConnection {

    private Connection connection;

    public boolean connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:D:/ESTAGIO/TotalCross/November/bankingSystem/db/bankingdatabase.db");
            System.out.println("Connected");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean disconnect() {
        try {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
            System.out.println("desconectado");
        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Statement createStatement() {
        try {
            return this.connection.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }

    public PreparedStatement createPreparedStatement(String pSQL, int RETURN_GENERATED_KEYS) {
        try {
            System.out.println("Executando");
            return connection.prepareStatement(pSQL, RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
