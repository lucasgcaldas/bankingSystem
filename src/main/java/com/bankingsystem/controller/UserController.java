package com.bankingsystem.controller;

import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController extends SQLiteConnection {

    public boolean saveUser(User user) {
        connect();

        String sql = "INSERT INTO tb_user(" +
                "name, " +
                "cpf, " +
                "account) " +
                "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = createPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getCpf());
            preparedStatement.setInt(3, user.getAccount().getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        disconnect();
        return true;
    }
}
