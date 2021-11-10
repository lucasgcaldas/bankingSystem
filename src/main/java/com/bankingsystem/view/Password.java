package com.bankingsystem.view;

import com.bankingsystem.controller.UserController;
import com.bankingsystem.main.Main;
import com.bankingsystem.util.Colors;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

public class Password extends Window {

    private int GAP = UnitsConverter.toPixels(DP + 20);
    private ImageControl imageControl;
    private Container bar1, bar2;
    private Edit nameEdit, antigaEdit, novaEdit;
    private Label label, nameLabel, alternarLabel, antigaLabel, novaLabel;
    private Button btnLogin, btnConf;
    private String senhaNova, nome, senhaAntiga;
    private UserController uc = new UserController();
    private MessageBox mb;

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

            bar2 = new Container();
            bar2.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            bar2.setBorderStyle(BORDER_ROUNDED);
            bar2.borderColor = 0xEBEBEB;
            bar2.setBorderRadius(24);
            add(bar2, CENTER, AFTER + GAP, 328, 310);

            nameLabel = new Label("Nome");
            nameLabel.transparentBackground = true;
            nameLabel.setFont(Font.getFont("Roboto", true, 18));
            nameLabel.setForeColor(Color.DARK);

            alternarLabel = new Label("Alterar senha");
            alternarLabel.transparentBackground = true;
            alternarLabel.setFont(Font.getFont("Roboto", true, 18));
            alternarLabel.setForeColor(Color.DARK);

            antigaLabel = new Label("Senha Antiga:");
            antigaLabel.setFont(Font.getFont("Roboto", true, 15));
            antigaLabel.setForeColor(Color.DARK);

            novaLabel = new Label("Nova Senha:");
            novaLabel.setFont(Font.getFont("Roboto", true, 15));
            novaLabel.setForeColor(Color.DARK);

            nameEdit = new Edit();
            nameEdit.setMode(Edit.NORMAL);
            nameEdit.setBackForeColors(Color.DARK, Color.BLACK);

            antigaEdit = new Edit();
            antigaEdit.setMode(Edit.PASSWORD_ALL);
            antigaEdit.setBackForeColors(Color.DARK, Color.BLACK);

            novaEdit = new Edit();
            novaEdit.setMode(Edit.PASSWORD_ALL);
            novaEdit.setBackForeColors(Color.DARK, Color.BLACK);

            bar2.add(alternarLabel, CENTER, TOP + 10);
            bar2.add(nameLabel, LEFT + 20, TOP + 50);
            bar2.add(nameEdit, SAME, AFTER + 10, 288, 40);
            bar2.add(antigaLabel, SAME, AFTER + 10);
            bar2.add(antigaEdit, SAME, AFTER + 10, 288, 40);
            bar2.add(novaLabel, SAME, AFTER + 10);
            bar2.add(novaEdit, SAME, AFTER + 10, 288, 40);

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
                try {
                    nome = nameEdit.getText();
                    senhaAntiga = antigaEdit.getText();
                    senhaNova = novaEdit.getText();
                    Main.user = uc.checkIfExistUser(nome, senhaAntiga, senhaNova);
                    if (Main.user == null) {
                        throw new NullPointerException();
                    } else {
                        this.unpop();
                    }
                } catch (NullPointerException e) {
                    String message = "Tente inserir corretamente os dados";
                    mb = new MessageBox("As senhas não conferem!", message, new String[]{"Ok!"});
                    mb.setRect(CENTER, CENTER, SCREENSIZE + 70, SCREENSIZE + 50);
                    mb.setBackForeColors(Colors.BACKGROUND, Colors.ON_P_300);
                    mb.popup();
                }
            }
        }
    }
}
