package com.bankingsystem.controller;

import com.bankingsystem.main.Main;
import com.bankingsystem.model.Extract;
import com.bankingsystem.model.Message;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExtractAccountController extends SQLiteConnection {

    private UserController uc = new UserController();
    private AccountController ac = new AccountController();
    private List<Extract> extractList = new ArrayList<>();

    public void saveTransfer(Extract extract, String kindTransfer, Integer conta) {
        updateTableAccount();
        try {
            String sql = "INSERT INTO tb_extract VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            if (Objects.equals(kindTransfer, "TransferSav")) {
                preparedStatement.setString(1, "Transferência Poupança");
            } else {
                preparedStatement.setString(1, "Transferência Corrente");
            }
            preparedStatement.setString(2, extract.getUser().getName());
            preparedStatement.setInt(3, Main.origin.getBranch());
            preparedStatement.setInt(4, Main.origin.getNumber());
            preparedStatement.setBigDecimal(5, extract.getValue());
            preparedStatement.setString(6, extract.getUserDestiny().getName());
            Main.destiny = ac.checkIfExistAccount(conta);
            preparedStatement.setInt(7, Main.destiny.getBranch());
            preparedStatement.setInt(8, Main.destiny.getNumber());
            preparedStatement.setString(9, extract.getDate());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTableAccount() {
        try {
            String sql = "UPDATE tb_account" +
                    " SET " +
                    " balance = ?" +
                    " WHERE number = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setBigDecimal(1, Main.origin.getBalance());
            preparedStatement.setInt(2, Main.origin.getNumber());

            preparedStatement.executeUpdate();

            preparedStatement.setBigDecimal(1, Main.destiny.getBalance());
            preparedStatement.setInt(2, Main.destiny.getNumber());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Extract> checkIfExistExtract() {
        try {
            String sql = "SELECT * FROM tb_extract";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (Objects.equals(rs.getString("user"), Main.user.getName()) && rs.getInt("ori_number") == Main.origin.getNumber()) {
                    Extract extract = new Extract();
                    extract.setUser(uc.checkIfExistUserToMessage(rs.getString("user")));
                    extract.setUserDestiny(uc.checkIfExistUserToMessage(rs.getString("user_destiny")));
                    extract.setValue(rs.getBigDecimal("value"));
                    extract.setDate(rs.getString("date"));
                    extractList.add(extract);
                }
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extractList;
    }
}
