package com.bankingsystem.controller;

import com.bankingsystem.model.Message;
import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageController extends SQLiteConnection {

    private AccountController ac = new AccountController();
    private UserController uc = new UserController();
    private List<Message> messagesList = new ArrayList<>();

    public void saveMessage(Message message) {
        try {
            String sql = "INSERT INTO tb_messages VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(2, message.getUser().getName());
            preparedStatement.setInt(3, message.getAccount().getNumber());
            preparedStatement.setString(4, message.getHour());
            preparedStatement.setString(5, message.getMessageDescription());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> checkIfExistMessages() {
        try {
            String sql = "SELECT * FROM tb_messages";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 1;
            while (rs.next()) {
                if (rs.getInt("pk_id") == i) {
                    Message message = new Message();
                    message.setUser(uc.checkIfExistUserToMessage(rs.getString("name")));
                    message.setAccount(ac.checkIfExistAccount(rs.getInt("account")));
                    message.setHour(rs.getString("hour"));
                    message.setMessageDescription(rs.getString("message"));
                    messagesList.add(message);
                    i++;
                }
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }
}
