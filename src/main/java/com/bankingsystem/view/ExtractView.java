package com.bankingsystem.view;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.controller.ExtractAccountController;
import com.bankingsystem.main.Main;
import com.bankingsystem.model.Account;
import com.bankingsystem.model.Extract;
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

import java.util.ArrayList;
import java.util.List;

public class ExtractView extends Window {

    private Container bar, cont1, cont2, cont3;
    private Button btnImage;
    private Label oriLabel, destLabel, ageLabel, contLabel, valLabel, setValLabel, nameLabel, cpfLabel, dateLabel;
    private Label setNameLabel, setCpfLabel, setAgeLabel, setContLabel, setDateLabel;
    private ExtractAccountController eac = new ExtractAccountController();
    private List<Extract> extractList = new ArrayList<>();

    private int GAP = UnitsConverter.toPixels(DP + 20);

    public ExtractView() {
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

            Label confTrans = new Label("Extrato", LEFT);
            confTrans.setFont(Font.getFont("Roboto", true, 19));
            confTrans.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            bar.add(confTrans, LEFT + 50, TOP, PARENTSIZE + 50, FILL);
            bar.resizeHeight();

            extractList = eac.checkIfExistExtract();

            cont3 = new ScrollContainer();
            cont3.setBackColor(Colors.BACKGROUND);
            add(cont3, CENTER, AFTER + GAP, 360, FILL);

            oriLabel = new Label("Conta Origem");
            oriLabel.transparentBackground = true;
            oriLabel.setFont(Font.getFont("Roboto", true, 18));
            oriLabel.setForeColor(Color.DARK);

            nameLabel = new Label("Nome:");
            nameLabel.setFont(Font.getFont("Roboto", true, 16));
            nameLabel.setForeColor(Color.DARK);

            setNameLabel = new Label(Main.user.getName());
            setNameLabel.setFont(Font.getFont("Roboto", true, 15));
            setNameLabel.setForeColor(Color.DARK);

            cpfLabel = new Label("CPF:");
            cpfLabel.setFont(Font.getFont("Roboto", true, 16));
            cpfLabel.setForeColor(Color.DARK);

            setCpfLabel = new Label(Main.user.getCpf());
            setCpfLabel.setFont(Font.getFont("Roboto", true, 15));
            setCpfLabel.setForeColor(Color.DARK);

            ageLabel = new Label("Agência:");
            ageLabel.setFont(Font.getFont("Roboto", true, 16));
            ageLabel.setForeColor(Color.DARK);

            setAgeLabel = new Label(Main.origin.getBranch().toString());
            setAgeLabel.setFont(Font.getFont("Roboto", true, 15));
            setAgeLabel.setForeColor(Color.DARK);

            contLabel = new Label("Conta:");
            contLabel.setFont(Font.getFont("Roboto", true, 16));
            contLabel.setForeColor(Color.DARK);

            setContLabel = new Label(Main.origin.getNumber().toString());
            setContLabel.setFont(Font.getFont("Roboto", true, 15));
            setContLabel.setForeColor(Color.DARK);

            cont1 = new Container();
            cont1.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            cont1.setBorderStyle(BORDER_SIMPLE);
            cont1.borderColor = 0xEBEBEB;
            cont1.setBorderRadius(9);

            cont3.add(cont1, CENTER, AFTER, 328, PARENTSIZE);

            cont1.add(oriLabel, CENTER, TOP);
            cont1.add(nameLabel, LEFT + 10, AFTER + 20);
            cont1.add(setNameLabel, SAME, AFTER);
            cont1.add(cpfLabel, SAME, AFTER + 10);
            cont1.add(setCpfLabel, SAME, AFTER);
            cont1.add(ageLabel, SAME, AFTER + 10);
            cont1.add(setAgeLabel, SAME, AFTER);
            cont1.add(contLabel, SAME, AFTER + 10);
            cont1.add(setContLabel, SAME, AFTER);
            cont1.resizeHeight();

            for (Extract extract : extractList) {
                destLabel = new Label("Conta Destino");
                destLabel.transparentBackground = true;
                destLabel.setFont(Font.getFont("Roboto", true, 18));
                destLabel.setForeColor(Color.DARK);

                nameLabel = new Label("Nome:");
                nameLabel.setFont(Font.getFont("Roboto", true, 16));
                nameLabel.setForeColor(Color.DARK);

                setNameLabel = new Label(extract.getUserDestiny().getName());
                setNameLabel.setFont(Font.getFont("Roboto", true, 15));
                setNameLabel.setForeColor(Color.DARK);

                cpfLabel = new Label("CPF:");
                cpfLabel.setFont(Font.getFont("Roboto", true, 16));
                cpfLabel.setForeColor(Color.DARK);

                setCpfLabel = new Label(extract.getUserDestiny().getCpf());
                setCpfLabel.setFont(Font.getFont("Roboto", true, 15));
                setCpfLabel.setForeColor(Color.DARK);

                ageLabel = new Label("Agência:");
                ageLabel.setFont(Font.getFont("Roboto", true, 16));
                ageLabel.setForeColor(Color.DARK);

                setAgeLabel = new Label(extract.getUserDestiny().getAccount1().getBranch().toString());
                setAgeLabel.setFont(Font.getFont("Roboto", true, 15));
                setAgeLabel.setForeColor(Color.DARK);

                contLabel = new Label("Conta:");
                contLabel.setFont(Font.getFont("Roboto", true, 16));
                contLabel.setForeColor(Color.DARK);

                setContLabel = new Label(extract.getUserDestiny().getAccount1().getNumber().toString());
                setContLabel.setFont(Font.getFont("Roboto", true, 15));
                setContLabel.setForeColor(Color.DARK);

                valLabel = new Label("Valor:");
                valLabel.setFont(Font.getFont("Roboto", true, 16));
                valLabel.setForeColor(Color.DARK);

                setValLabel = new Label("R$: " + extract.getValue());
                setValLabel.setFont(Font.getFont("Roboto", true, 15));
                setValLabel.setForeColor(Color.DARK);

                dateLabel = new Label("Data e hora:");
                dateLabel.setFont(Font.getFont("Roboto", true, 16));
                dateLabel.setForeColor(Color.DARK);

                setDateLabel = new Label(extract.getDate());
                setDateLabel.setFont(Font.getFont("Roboto", true, 15));
                setDateLabel.setForeColor(Color.DARK);

                cont2 = new Container();
                cont2.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
                cont2.setBorderStyle(BORDER_SIMPLE);
                cont2.borderColor = 0xEBEBEB;
                cont2.setBorderRadius(9);

                cont3.add(cont2, CENTER, AFTER + GAP, 328, PARENTSIZE);

                cont2.add(destLabel, CENTER, TOP + 10);
                cont2.add(nameLabel, LEFT + 10, AFTER + 20);
                cont2.add(setNameLabel, SAME, AFTER);
                cont2.add(cpfLabel, SAME, AFTER + 10);
                cont2.add(setCpfLabel, SAME, AFTER);
                cont2.add(ageLabel, SAME, AFTER + 10);
                cont2.add(setAgeLabel, SAME, AFTER);
                cont2.add(contLabel, SAME, AFTER + 10);
                cont2.add(setContLabel, SAME, AFTER);
                cont2.add(valLabel, LEFT + 10, AFTER + 10);
                cont2.add(setValLabel, SAME, AFTER);
                cont2.add(dateLabel, LEFT + 10, AFTER + 10);
                cont2.add(setDateLabel, SAME, AFTER);
                cont2.resizeHeight();
            }
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnImage) {
                ExtractView extractView = new ExtractView();
                extractView.unpop();
            }
        }
    }
}
