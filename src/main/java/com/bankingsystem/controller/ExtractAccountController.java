package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.util.SQLiteConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ExtractAccountController extends SQLiteConnection {

    private Account origin;
    private Account destiny;
    private BigDecimal value;

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestiny() {
        return destiny;
    }

    public void setDestiny(Account destiny) {
        this.destiny = destiny;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void saveTransfer() {
        connect();
        String sql = "INSERT INTO tb_transfer(" +
                "origin, " +
                "value, " +
                "destiny, " +
                "new_origin_balance, " +
                "new_received_balance) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = createPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            preparedStatement.setString(1, this.origin.getClass().getName().substring(24));
            preparedStatement.setBigDecimal(2, this.value);
            preparedStatement.setString(3, this.destiny.getClass().getName().substring(24));
            preparedStatement.setBigDecimal(4, this.origin.getBalance());
            preparedStatement.setBigDecimal(5, this.destiny.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        disconnect();
    }
}
