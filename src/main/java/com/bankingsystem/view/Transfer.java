package com.bankingsystem.view;

import com.bankingsystem.util.Colors;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

public class Transfer extends Window {

    private Container bar;
    private Container cont1;
    private Container cont2;
    private Button btnImage;
    private Edit ageEdit;
    private Edit contEdit;
    private Edit valEdit;
    private Label destLabel;
    private Label wValueLabel;
    private Label ageLabel;
    private Label contLabel;
    private Label valLabel;
    private Label dinLabel;
    private Button btnCont;

    private int GAP = UnitsConverter.toPixels(DP + 20);

    public Transfer() {
        super();
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

            Label tP = new Label("Transferência Poupança", LEFT);
            tP.setFont(Font.getFont("Roboto", true, 19));
            tP.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            bar.add(tP, LEFT + 50, TOP, PARENTSIZE + 50, FILL);
            bar.resizeHeight();

            ageEdit = new Edit();
//            ageEdit.caption = "Agência";
            ageEdit.setMode(Edit.CURRENCY);
            ageEdit.setKeyboard(Edit.KBD_NUMERIC);
            ageEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            ageLabel = new Label("Agência:");
            ageLabel.setFont(Font.getFont("Roboto", true, 15));
            ageLabel.setForeColor(Color.DARK);

            contEdit = new Edit();
//            contEdit.caption = "Conta";
            contEdit.setMode(Edit.CURRENCY);
            contEdit.setKeyboard(Edit.KBD_NUMERIC);
            contEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            contLabel = new Label("Conta:");
            contLabel.setFont(Font.getFont("Roboto", true, 15));
            contLabel.setForeColor(Color.DARK);

            valEdit = new Edit();
//            valEdit.caption = "Valor";
            valEdit.setMode(Edit.CURRENCY);
            valEdit.setKeyboard(Edit.KBD_NUMERIC);
            valEdit.setBackForeColors(Color.DARK, Color.BLACK);
            Edit.useNativeNumericPad = true;

            valLabel = new Label("Valor:");
            valLabel.setFont(Font.getFont("Roboto", true, 15));
            valLabel.setForeColor(Color.DARK);

            dinLabel = new Label("R$");
            dinLabel.setFont(Font.getFont("Roboto", true, 15));
            dinLabel.setForeColor(Color.DARK);

            cont1 = new Container();
            cont1.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            cont1.setBorderStyle(BORDER_SIMPLE);
            cont1.borderColor = 0xEBEBEB;
            cont1.setBorderRadius(9);
            add(cont1, LEFT + GAP, AFTER + GAP, 328, 134);

            destLabel = new Label("Qual a conta de destino?");
            destLabel.transparentBackground = true;
            destLabel.setFont(Font.getFont("Roboto", true, 18));
            destLabel.setForeColor(Color.DARK);

            cont1.add(destLabel, CENTER, TOP + 10);
            cont1.add(ageEdit, LEFT + 10, AFTER + 46, 100, 38);
            cont1.add(contEdit, RIGHT - 10, SAME, 178, 38);
            cont1.add(ageLabel, LEFT + 10, BEFORE - 10);
            cont1.add(contLabel, LEFT + 135, SAME);

            cont2 = new Container();
            cont2.setBackForeColors(Color.WHITE, Colors.ON_SURFACE);
            cont2.setBorderStyle(BORDER_SIMPLE);
            cont2.borderColor = 0xEBEBEB;
            cont2.setBorderRadius(9);
            add(cont2, LEFT + GAP, AFTER + GAP, 328, 134);

            wValueLabel = new Label("Qual o valor?");
            wValueLabel.setFont(Font.getFont("Roboto", true, 18));
            wValueLabel.setForeColor(Color.DARK);
            wValueLabel.transparentBackground = true;

            cont2.add(wValueLabel, CENTER, AFTER + 10);
            cont2.add(valEdit, LEFT + 37, AFTER + 46, 271, 38);
            cont2.add(valLabel, LEFT + 10, BEFORE - 10);
            cont2.add(dinLabel, LEFT + 10, SAME + 10, valEdit);

            btnCont = new Button("Confirmar");
            btnCont.setFont(Font.getFont("Roboto", true, 19));
            btnCont.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            btnCont.setBorder(BORDER_ROUNDED);
//            btnCont.roundBorderFactor = 20;

            add(btnCont, CENTER, BOTTOM - 21, 252, 50);
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnImage) {
                Transfer transfer = new Transfer();
                transfer.unpop();
            }
        }
    }
}
