package com.bankingsystem.view;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.main.Main;
import com.bankingsystem.model.Account;
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
import totalcross.util.UnitsConverter;

import java.util.Objects;

public class ConfTrans extends Window {

    private Container bar, cont1, cont2;
    private Button btnImage, btnCont;
    private Label destLabel, qualValorLabel, ageLabel, contLabel, valLabel, setValLabel, nameLabel, cpfLabel;
    private Label setNameLabel, setCpfLabel, setAgeLabel, setContLabel;
    private Integer agencia, conta;
    private BigDecimal valor;
    private String kindTransfer;
    private AccountController aC = new AccountController();
    private User user;
    private MessageBox mb;

    private int GAP = UnitsConverter.toPixels(DP + 20);

    public ConfTrans() {
    }

    public ConfTrans(String kindTransfer) {
        this.kindTransfer = kindTransfer;
    }

    public ConfTrans(Integer agencia, Integer conta, BigDecimal valor, User user, String kindTransfer) {
        this.agencia = agencia;
        this.conta = conta;
        this.valor = valor;
        this.user = user;
        this.kindTransfer = kindTransfer;
    }

    public void onPopup() {
        setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
        setInsets(0, 0, 0, UnitsConverter.toPixels(Control.DP + 8));

        try {
            bar = new Container();
            bar.setBackColor(Colors.PRIMARY);
            add(bar, LEFT, TOP, PARENTSIZE, DP + 70);

            Image img = new Image("images/back.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
            btnImage = new Button(img.getHwScaledInstance(UnitsConverter.toPixels(DP + 16), UnitsConverter.toPixels(DP + 16)), Button.BORDER_NONE);
            btnImage.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            bar.add(btnImage, LEFT, CENTER);

            Label confTrans = new Label("Confirmar Transferência", LEFT);
            confTrans.setFont(Font.getFont("Roboto", true, 19));
            confTrans.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            bar.add(confTrans, LEFT + 50, TOP, PARENTSIZE + 50, FILL);
            bar.resizeHeight();

            destLabel = new Label("Qual a conta de destino?");
            destLabel.transparentBackground = true;
            destLabel.setFont(Font.getFont("Roboto", true, 18));
            destLabel.setForeColor(Color.DARK);

            nameLabel = new Label("Nome:");
            nameLabel.setFont(Font.getFont("Roboto", true, 16));
            nameLabel.setForeColor(Color.DARK);

            setNameLabel = new Label(user.getName());
            setNameLabel.setFont(Font.getFont("Roboto", true, 15));
            setNameLabel.setForeColor(Color.DARK);

            cpfLabel = new Label("CPF:");
            cpfLabel.setFont(Font.getFont("Roboto", true, 16));
            cpfLabel.setForeColor(Color.DARK);

            setCpfLabel = new Label(user.getCpf());
            setCpfLabel.setFont(Font.getFont("Roboto", true, 15));
            setCpfLabel.setForeColor(Color.DARK);

            ageLabel = new Label("Agência:");
            ageLabel.setFont(Font.getFont("Roboto", true, 16));
            ageLabel.setForeColor(Color.DARK);

            setAgeLabel = new Label(agencia.toString());
            setAgeLabel.setFont(Font.getFont("Roboto", true, 15));
            setAgeLabel.setForeColor(Color.DARK);

            contLabel = new Label("Conta:");
            contLabel.setFont(Font.getFont("Roboto", true, 16));
            contLabel.setForeColor(Color.DARK);

            setContLabel = new Label(conta.toString());
            setContLabel.setFont(Font.getFont("Roboto", true, 15));
            setContLabel.setForeColor(Color.DARK);

            cont1 = new Container();
            cont1.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            cont1.setBorderStyle(BORDER_SIMPLE);
            cont1.borderColor = 0xEBEBEB;
            cont1.setBorderRadius(9);
            add(cont1, CENTER, AFTER + GAP, 328, 268);

            cont1.add(destLabel, CENTER, TOP + 10);
            cont1.add(nameLabel, LEFT + 10, AFTER + 20);
            cont1.add(setNameLabel, SAME, AFTER);
            cont1.add(cpfLabel, SAME, AFTER + 10);
            cont1.add(setCpfLabel, SAME, AFTER);
            cont1.add(ageLabel, SAME, AFTER + 10);
            cont1.add(setAgeLabel, SAME, AFTER);
            cont1.add(contLabel, SAME, AFTER + 10);
            cont1.add(setContLabel, SAME, AFTER);

            cont2 = new Container();
            cont2.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            cont2.setBorderStyle(BORDER_SIMPLE);
            cont2.borderColor = 0xEBEBEB;
            cont2.setBorderRadius(9);
            add(cont2, CENTER, AFTER + GAP, 328, 107);

            qualValorLabel = new Label("Qual o valor?");
            qualValorLabel.setFont(Font.getFont("Roboto", true, 18));
            qualValorLabel.setForeColor(Color.DARK);
            qualValorLabel.transparentBackground = true;

            valLabel = new Label("Valor:");
            valLabel.setFont(Font.getFont("Roboto", true, 16));
            valLabel.setForeColor(Color.DARK);

            setValLabel = new Label("R$: " + valor);
            setValLabel.setFont(Font.getFont("Roboto", true, 15));
            setValLabel.setForeColor(Color.DARK);

            cont2.add(qualValorLabel, CENTER, AFTER + 10);
            cont2.add(valLabel, LEFT + 10, AFTER + 20);
            cont2.add(setValLabel, SAME, AFTER);

            btnCont = new Button("Confirmar");
            btnCont.setFont(Font.getFont("Roboto", true, 19));
            btnCont.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            btnCont.setBorder(BORDER_ROUNDED);

            add(btnCont, CENTER, BOTTOM - 21, 252, 50);
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnImage) {
                ConfTrans confTrans = new ConfTrans();
                confTrans.unpop();
            }
        }
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnCont) {
                try {
                    if (Main.origin.getBalance().compareTo(valor) >= 0) {
                        Main.origin.sendTransfer(kindTransfer, valor);
                        Home.lSaldo.setText("R$ " + Main.origin.getBalance().toString());
                        Home.lSaldo.repaintNow();
                        this.unpop();
                        if (Objects.equals(kindTransfer, "TransferSav")) {
                            TransferSav transferSav = new TransferSav();
                            transferSav.unpop();
                        } else {
                            TransferChe transferChe = new TransferChe();
                            transferChe.unpop();
                        }
                    } else {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e) {
                    String message = "Saldo menor do que a quantidade que está querendo transferir";
                    mb = new MessageBox("Saldo insuficiente!", message, new String[]{"Ok!"});
                    mb.setRect(CENTER, CENTER, SCREENSIZE + 70, SCREENSIZE + 50);
                    mb.setBackForeColors(Colors.BACKGROUND, Colors.ON_P_300);
                    mb.popup();
                }
            }
        }
    }
}
