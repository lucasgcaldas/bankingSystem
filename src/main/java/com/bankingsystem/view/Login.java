package com.bankingsystem.view;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.controller.UserController;
import com.bankingsystem.model.Account;
import com.bankingsystem.model.Message;
import com.bankingsystem.model.User;
import com.bankingsystem.util.SlideMenu;
import com.bankingsystem.util.Colors;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sys.InvalidNumberException;
import totalcross.sys.Settings;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

import java.util.Objects;

public class Login extends MainWindow {

    public static Account origin, destiny;
    public static User user;
    public static Message message;
    private int GAP = UnitsConverter.toPixels(DP + 20);
    private ImageControl imageControl;
    private Container bar1, bar2;
    private Edit ageEdit, contEdit, passwordEdit;
    private Label label, acessarLabel, ageLabel, contLabel, passwordLabel;
    private Button btnCadas, btnConf, btnChangePassword;
    private AccountController ac = new AccountController();
    private UserController uc = new UserController();
    private Integer agencia, conta;
    private MessageBox mb;
    private String senha;

    public Login() {
//        setUIStyle(Settings.HOLO_UI);
        setUIStyle(Settings.MATERIAL_UI);
        setBackForeColors(Colors.BACKGROUND, Colors.SURFACE);
    }

    @Override
    public void initUI() {
        setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
        setInsets(0, 0, 0, UnitsConverter.toPixels(Control.DP + 8));

        SQLiteConnection.getInstance();

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

            bar2 = new Container();
            bar2.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            bar2.setBorderStyle(BORDER_ROUNDED);
            bar2.borderColor = 0xEBEBEB;
            bar2.setBorderRadius(24);
            add(bar2, CENTER, AFTER + GAP, 328, 310);

            acessarLabel = new Label("Acessar sua Conta");
            acessarLabel.transparentBackground = true;
            acessarLabel.setFont(Font.getFont("Roboto", true, 18));
            acessarLabel.setForeColor(Color.DARK);

            ageLabel = new Label("Agência:");
            ageLabel.setFont(Font.getFont("Roboto", true, 15));
            ageLabel.setForeColor(Color.DARK);

            contLabel = new Label("Conta:");
            contLabel.setFont(Font.getFont("Roboto", true, 15));
            contLabel.setForeColor(Color.DARK);

            passwordLabel = new Label("Senha:");
            passwordLabel.setFont(Font.getFont("Roboto", true, 15));
            passwordLabel.setForeColor(Color.DARK);

            ageEdit = new Edit();
            ageEdit.setMode(Edit.CURRENCY);
            ageEdit.setKeyboard(Edit.KBD_NUMERIC);
            ageEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            contEdit = new Edit();
            contEdit.setMode(Edit.CURRENCY);
            contEdit.setKeyboard(Edit.KBD_NUMERIC);
            contEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            passwordEdit = new Edit();
            passwordEdit.setMode(Edit.PASSWORD_ALL);
            passwordEdit.setBackForeColors(Color.DARK, Color.BLACK);

            btnChangePassword = new Button("Trocar Senha");
            btnChangePassword.setFont(Font.getFont("Roboto", true, 15));
            btnChangePassword.setForeColor(Color.DARK);
            btnChangePassword.transparentBackground = true;
            btnChangePassword.setBorder(BORDER_NONE);

            bar2.add(acessarLabel, CENTER, TOP + 10);
            bar2.add(ageLabel, LEFT + 20, TOP + 50);
            bar2.add(ageEdit, SAME, AFTER + 10, 288, 38);
            bar2.add(contLabel, SAME, AFTER + 10);
            bar2.add(contEdit, SAME, AFTER + 10, 288, 38);
            bar2.add(passwordLabel, SAME, AFTER + 10);
            bar2.add(passwordEdit, SAME, AFTER + 10, 288, 38);
            bar2.add(btnChangePassword, CENTER, AFTER);

            btnCadas = new Button("Cadastro");
            btnCadas.setFont(Font.getFont("Roboto", true, 19));
            btnCadas.setBackForeColors(Color.DARK, Color.WHITE);
            btnCadas.setBorder(BORDER_ROUNDED);
            btnCadas.roundBorderFactor = 1;

            btnConf = new Button("Confirmar");
            btnConf.setFont(Font.getFont("Roboto", true, 19));
            btnConf.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            btnConf.setBorder(BORDER_ROUNDED);
            btnConf.roundBorderFactor = 1;

            add(btnCadas, LEFT + 24, BOTTOM - 21, 151, 50);
            add(btnConf, RIGHT - 24, BOTTOM - 21, 151, 50);

        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnConf) {
                try {
                    if (!ageEdit.getText().matches("") || !contEdit.getText().matches("") || !passwordEdit.getText().matches("")) {
                        agencia = Integer.parseInt(ageEdit.getText());
                        conta = Integer.parseInt(contEdit.getText());
                        senha = String.valueOf(passwordEdit.getText());
                    }
                    Login.origin = ac.checkIfExistAccountToTransfer(agencia, conta);
                    Login.user = uc.checkIfExistUserWithPassword(senha, conta);
                    if (Login.user != null) {
                        if (Login.origin != null) {
                            if (Objects.equals(senha, Login.user.getPassword())) {
                                SlideMenu slideMenu = new SlideMenu();
                                slideMenu.popup();
                            }
                        } else {
                            throw new NullPointerException();
                        }
                    } else {
                        throw new InvalidNumberException();
                    }
                } catch (NullPointerException e) {
                    String message = "Tente inserir corretamente os dados da conta";
                    mb = new MessageBox("Conta não encontrada!", message, new String[]{"Ok!"});
                    mb.setRect(CENTER, CENTER, SCREENSIZE + 70, SCREENSIZE + 50);
                    mb.setBackForeColors(Colors.BACKGROUND, Colors.ON_P_300);
                    mb.popup();
                } catch (InvalidNumberException e) {
                    String message = "Tente inserir corretamente a senha ou altere a senha";
                    mb = new MessageBox("Senha inválida!", message, new String[]{"Ok!"});
                    mb.setRect(CENTER, CENTER, SCREENSIZE + 70, SCREENSIZE + 50);
                    mb.setBackForeColors(Colors.BACKGROUND, Colors.ON_P_300);
                    mb.popup();
                } catch (NumberFormatException e) {
                    String message = "Preencha todos os campos com seus dados!";
                    mb = new MessageBox("Campos vazios!", message, new String[]{"Ok!"});
                    mb.setRect(CENTER, CENTER, SCREENSIZE + 70, SCREENSIZE + 50);
                    mb.setBackForeColors(Colors.BACKGROUND, Colors.ON_P_300);
                    mb.popup();
                }
            }
        }
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnCadas) {
                Cadastro cadastro = new Cadastro();
                cadastro.popup();
            }
        }
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnChangePassword) {
                Password password = new Password();
                password.popup();
            }
        }
    }
}
