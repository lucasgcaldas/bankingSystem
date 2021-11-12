package com.bankingsystem.view;

import com.bankingsystem.controller.MessageController;
import com.bankingsystem.util.SlideMenu;
import com.bankingsystem.model.Message;
import com.bankingsystem.util.Colors;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChatGroup extends Window {

    private Container bar, bar2, bar3, mesContainer;
    private Button btnImage, btnSendMes;
    private Edit messageEdit;
    private Label chatLabel, nameLabel, accNumberLabel, hourLabel, dateLabel, messageLabel;
    private Image imgSendMes;
    private String messageDescription;
    private MessageController mc = new MessageController();
    private List<Message> messagesList = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private Date date = new Date();

    @Override
    public void onPopup() {
        setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
        setInsets(0, 0, 0, UnitsConverter.toPixels(Control.DP + 8));

        try {
            bar = new Container();
            bar.setBackColor(Colors.PRIMARY);
            add(bar, CENTER, TOP, PARENTSIZE, DP + 70);

            Image img = new Image("images/back.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
            btnImage = new Button(img.getHwScaledInstance(UnitsConverter.toPixels(DP + 16), UnitsConverter.toPixels(DP + 16)), Button.BORDER_NONE);
            btnImage.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            bar.add(btnImage, LEFT, CENTER);

            chatLabel = new Label("Chat em Grupo", LEFT);
            chatLabel.setFont(Font.getFont("Roboto", true, 19));
            chatLabel.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            bar.add(chatLabel, LEFT + 50, TOP, PARENTSIZE + 50, FILL);
            bar.resizeHeight();

            bar3 = new Container();
            bar3.setBackColor(Colors.PRIMARY);
            add(bar3, CENTER, BOTTOM, PARENTSIZE, 60);

            messageEdit = new Edit();
            messageEdit.caption = "Digite sua menssagem";
            messageEdit.setMode(Edit.NORMAL, false);
            messageEdit.setBackForeColors(Color.DARK, Color.BLACK);

            imgSendMes = new Image("images/sendMessage.png").getHwScaledInstance(UnitsConverter.toPixels(25), UnitsConverter.toPixels(25));
            btnSendMes = new Button(imgSendMes);
            btnSendMes.transparentBackground = true;
            btnSendMes.setBorder(BORDER_NONE);

            bar3.add(messageEdit, LEFT + 20, CENTER, 288, 50);
            bar3.add(btnSendMes, RIGHT, CENTER);

            messagesList = mc.checkIfExistMessages();

            bar2 = new ScrollContainer();
            bar2.setBackColor(Colors.BACKGROUND);
            add(bar2, CENTER, TOP + 80, 328, FILL - 60);

            if (messagesList != null) {
                for (Message message : messagesList) {
                    nameLabel = new Label(message.getUser().getName());
                    nameLabel.setFont(Font.getFont("Roboto", true, 15));

                    accNumberLabel = new Label(message.getAccount().getNumber().toString());
                    accNumberLabel.setFont(Font.getFont("Roboto", true, 15));

                    hourLabel = new Label(message.getHour().substring(11));
                    hourLabel.setFont(Font.getFont("Roboto", true, 15));

                    dateLabel = new Label(message.getHour().substring(0, 11));
                    dateLabel.setFont(Font.getFont("Roboto", true, 15));

                    messageLabel = new Label(message.getMessageDescription());
                    messageLabel.setFont(Font.getFont("Roboto", true, 15));
                    messageDescription = messageEdit.getText();

                    if (Objects.equals(message.getUser().getName(), Login.user.getName())) {
                        mesContainer = new Container();
                        mesContainer.setBackForeColors(Colors.PRIMARY, Colors.ON_SURFACE);
                        mesContainer.setBorderStyle(BORDER_ROUNDED);
                        mesContainer.borderColor = 0xD1D1D1;
                        nameLabel.setForeColor(Color.WHITE);
                        accNumberLabel.setForeColor(Color.WHITE);
                        hourLabel.setForeColor(Color.WHITE);
                        dateLabel.setForeColor(Color.WHITE);
                        messageLabel.setForeColor(Color.WHITE);
                    } else {
                        mesContainer = new Container();
                        mesContainer.setBackForeColors(0xD1D1D1, Colors.ON_SURFACE);
                        mesContainer.setBorderStyle(BORDER_ROUNDED);
                        mesContainer.borderColor = 0xD1D1D1;
                        nameLabel.setForeColor(Color.DARK);
                        accNumberLabel.setForeColor(Color.DARK);
                        hourLabel.setForeColor(Color.DARK);
                        dateLabel.setForeColor(Color.DARK);
                        messageLabel.setForeColor(Color.BLACK);
                    }

                    bar2.add(mesContainer, CENTER, AFTER + 10, 330, PARENTSIZE);

                    mesContainer.add(nameLabel, LEFT + 5, AFTER);
                    mesContainer.add(accNumberLabel, RIGHT - 5, SAME);
                    mesContainer.add(messageLabel, LEFT + 5, AFTER);
                    mesContainer.add(hourLabel, RIGHT - 5, AFTER);
                    mesContainer.add(dateLabel, LEFT + 5, SAME);
                    mesContainer.resizeHeight();
                }
            }
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnImage) {
                this.unpop();
            }
        }
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnSendMes) {
                messageDescription = messageEdit.getText();
                messageEdit.clear();

                Login.message = new Message();
                Login.message.setUser(Login.user);
                Login.message.setAccount(Login.origin);
                Login.message.setHour(sdf.format(date));
                Login.message.setMessageDescription(messageDescription);
                mc.saveMessage(Login.message);

                mesContainer = new Container();
                mesContainer.setBackForeColors(Colors.PRIMARY, Colors.ON_SURFACE);
                mesContainer.setBorderStyle(BORDER_ROUNDED);
                mesContainer.borderColor = 0xD1D1D1;

                nameLabel = new Label(Login.message.getUser().getName());
                nameLabel.setFont(Font.getFont("Roboto", true, 15));
                nameLabel.setForeColor(Color.WHITE);

                accNumberLabel = new Label(Login.message.getAccount().getNumber().toString());
                accNumberLabel.setFont(Font.getFont("Roboto", true, 15));
                accNumberLabel.setForeColor(Color.WHITE);

                hourLabel = new Label(Login.message.getHour().substring(11));
                hourLabel.setFont(Font.getFont("Roboto", true, 15));
                hourLabel.setForeColor(Color.WHITE);

                dateLabel = new Label(Login.message.getHour().substring(0, 11));
                dateLabel.setFont(Font.getFont("Roboto", true, 15));
                dateLabel.setForeColor(Color.WHITE);

                messageLabel = new Label(messageDescription);
                messageLabel.setFont(Font.getFont("Roboto", true, 15));
                messageLabel.setForeColor(Color.WHITE);

                bar2.add(mesContainer, CENTER, AFTER + 10, PARENTSIZE, PARENTSIZE);

                mesContainer.add(nameLabel, LEFT + 5, TOP);
                mesContainer.add(accNumberLabel, RIGHT - 5, SAME);
                mesContainer.add(messageLabel, LEFT + 5, AFTER);
                mesContainer.add(hourLabel, RIGHT - 5, AFTER);
                mesContainer.add(dateLabel, LEFT + 5, SAME);
                mesContainer.resizeHeight();
            }
        }
    }
}

