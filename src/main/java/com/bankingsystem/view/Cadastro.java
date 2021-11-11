package com.bankingsystem.view;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.controller.UserController;
import com.bankingsystem.main.Main;
import com.bankingsystem.model.Account;
import com.bankingsystem.model.CheckingAccount;
import com.bankingsystem.model.SavingsAccount;
import com.bankingsystem.model.User;
import com.bankingsystem.util.Colors;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.BigDecimal;
import totalcross.util.Date;
import totalcross.util.UnitsConverter;

import java.math.BigInteger;
import java.util.*;

public class Cadastro extends Window {

    private int GAP = UnitsConverter.toPixels(DP + 20);
    private ImageControl imageControl;
    private Container bar1;
    private ScrollContainer bar2;
    private Edit nameEdit, cpfEdit, dateEdit, passwordEdit, kindAccountEdit;
    private Label label, cadastro, name, cpf, date, kindAccount, password;
    private Button btnLogin, btnConf;
    private ComboBox kindAccountBox;
    private AccountController ac = new AccountController();
    private UserController uc = new UserController();

    @Override
    public void onPopup() {
        setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
        setInsets(0, 0, 0, UnitsConverter.toPixels(Control.DP + 8));

        try {
            imageControl = new ImageControl(new Image("images/bankIcon.png").getHwScaledInstance((int) 30.79, (int) 28.26));

            label = new Label("TotalBank");
            label.setFont(Font.getFont("Roboto", true, 23));
            label.setForeColor(Color.WHITE);

            bar1 = new Container();
            bar1.setBackColor(Colors.PRIMARY);
            add(bar1, LEFT, TOP, PARENTSIZE, 102);

            bar1.add(label, LEFT + 148, CENTER);
            bar1.add(imageControl, BEFORE - 5, SAME, label);

            bar2 = new ScrollContainer();
            bar2.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            bar2.setBorderStyle(BORDER_ROUNDED);
            bar2.borderColor = 0xEBEBEB;
            bar2.setBorderRadius(24);
            add(bar2, CENTER, AFTER + GAP, 328, 382);

            cadastro = new Label("Cadastre sua Conta");
            cadastro.transparentBackground = true;
            cadastro.setFont(Font.getFont("Roboto", true, 18));
            cadastro.setForeColor(Color.DARK);

            name = new Label("Nome Completo:");
            name.setFont(Font.getFont("Roboto", true, 15));
            name.setForeColor(Color.DARK);

            cpf = new Label("CPF:");
            cpf.setFont(Font.getFont("Roboto", true, 15));
            cpf.setForeColor(Color.DARK);

            date = new Label("Data de Nascimento:");
            date.setFont(Font.getFont("Roboto", true, 15));
            date.setForeColor(Color.DARK);

            kindAccount = new Label("Tipo de Conta:");
            kindAccount.setFont(Font.getFont("Roboto", true, 15));
            kindAccount.setForeColor(Color.DARK);

            password = new Label("Senha:");
            password.setFont(Font.getFont("Roboto", true, 15));
            password.setForeColor(Color.DARK);

            nameEdit = new Edit();
            nameEdit.setMode(Edit.NORMAL);
            nameEdit.setBackForeColors(Color.DARK, Color.BLACK);

            cpfEdit = new Edit("999.999.999-99");
            cpfEdit.setMode(Edit.NORMAL, true);
            cpfEdit.setValidChars(Edit.numbersSet);
            nameEdit.setKeyboard(Edit.KBD_NUMERIC);
            cpfEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            dateEdit = new Edit("99/99/99");
            dateEdit.setMode(Edit.DATE, true);
            dateEdit.setKeyboard(Edit.KBD_NUMERIC);
            dateEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            kindAccountBox = new ComboBox(new String[] {"Corrente", "Poupança", "Ambas"});
            kindAccountBox.setBackColor(0xD1D1D1);

            passwordEdit = new Edit();
            passwordEdit.setMode(Edit.PASSWORD_ALL);
            passwordEdit.setBackForeColors(Color.DARK, Color.BLACK);

            bar2.add(cadastro, CENTER, TOP + 10);
            bar2.add(name, LEFT + 20, TOP + 50);
            bar2.add(nameEdit, SAME, AFTER + 10, 288, 40);
            bar2.add(cpf, SAME, AFTER + 10);
            bar2.add(cpfEdit, SAME, AFTER + 10, 288, 40);
            bar2.add(date, SAME, AFTER + 10);
            bar2.add(dateEdit, SAME, AFTER + 10, 288, 40);
            bar2.add(kindAccount, SAME, AFTER + 10);
            bar2.add(kindAccountBox, SAME, AFTER + 10, 288, 40, kindAccountEdit);
            bar2.add(password, SAME, AFTER + 10);
            bar2.add(passwordEdit, SAME, AFTER + 10, 288, 40);

            btnLogin = new Button("Login");
            btnLogin.setFont(Font.getFont("Roboto", true, 19));
            btnLogin.setBackForeColors(Color.DARK, Color.WHITE);
            btnLogin.setBorder(BORDER_ROUNDED);
            btnLogin.roundBorderFactor = 1;

            btnConf = new Button("Confirmar");
            btnConf.setFont(Font.getFont("Roboto", true, 19));
            btnConf.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            btnConf.setBorder(BORDER_ROUNDED);
            btnConf.roundBorderFactor = 1;

            add(btnLogin, LEFT + 24, BOTTOM - 21, 151, 50);
            add(btnConf, RIGHT - 24, BOTTOM - 21, 151, 50);

        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnLogin) {
                this.unpop();
            }
        }
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnConf) {
                Main.user = new User();
                Main.user.setName(nameEdit.getText());
                Main.user.setCpf(cpfEdit.getText());
                Main.user.setBirthDate(dateEdit.getText());

                if (Objects.equals(kindAccountBox.getValue(), "Poupança")){
                    SavingsAccount sA = new SavingsAccount();
                    sA.setBalance(new BigDecimal(1500));
                    sA.setNumber(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 6)));
                    sA.setBranch(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 4)));

                    Main.user.setAccount1(sA);
                    ac.saveAccount(sA, Main.user.getName());
                } else if (Objects.equals(kindAccountBox.getValue(), "Corrente")) {
                    CheckingAccount cA = new CheckingAccount();
                    cA.setBalance(new BigDecimal(1000));
                    cA.setNumber(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 6)));
                    cA.setBranch(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 4)));

                    Main.user.setAccount1(cA);
                    ac.saveAccount(cA, Main.user.getName());
                } else {
                    SavingsAccount sA = new SavingsAccount();
                    sA.setBalance(new BigDecimal(1500));
                    sA.setNumber(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 6)));
                    sA.setBranch(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 4)));

                    Main.user.setAccount1(sA);
                    ac.saveAccount(sA, Main.user.getName());

                    CheckingAccount cA = new CheckingAccount();
                    cA.setBalance(new BigDecimal(1000));
                    cA.setNumber(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 6)));
                    cA.setBranch(Integer.parseInt(String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 4)));

                    Main.user.setAccount2(cA);
                    ac.saveAccount(cA, Main.user.getName());
                }
                Main.user.setPassword(passwordEdit.getText());
                uc.saveUser(Main.user);

                this.unpop();
            }
        }
    }
}
