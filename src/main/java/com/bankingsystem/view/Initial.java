package com.bankingsystem.view;

import com.bankingsystem.util.Colors;
import totalcross.ui.*;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class Initial extends BaseScreen {

    private Container bar;
    private Container bar2;
    private Container bar3;
    private Button btnServices;
    private Button btnTransSav;
    private Button btnTransChe;
    private Button btnExtract;
    private Button btnPix;
    private Button btnComp;
    private Button btnRecar;

    public Initial() {
        super();
    }

    @Override
    public void onContent(ScrollContainer content) {
        try {
            content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
            content.setInsets(0, 0, 0, UnitsConverter.toPixels(Control.DP + 8));

            bar = new Container();
            bar.setBackColor(Colors.PRIMARY);
            add(bar, LEFT, TOP, PARENTSIZE, DP + 52);
            Label mSD = new Label("Meu saldo disponível", LEFT);
            mSD.setFont(Font.getFont("Roboto", true, 15));
            bar.add(mSD, LEFT + 16, TOP, PARENTSIZE + 50, PREFERRED);
            bar.resizeHeight();


            bar2 = new Container();
            bar2.setBackColor(Colors.PRIMARY);
            add(bar2, LEFT, AFTER, PARENTSIZE, DP + 45);

            Label cash = new Label("R$ 2.000,00", LEFT);
            cash.setFont(Font.getFont("Roboto", true, 17));
            bar2.add(cash, LEFT + 16, AFTER, PARENTSIZE + 50, PREFERRED);
//            bar2.resizeHeight();

            bar3 = new Container();
            add(bar3, LEFT, AFTER, 360, 490);
            btnServices = new Button("Meus Serviços");
            btnServices.setFont(Font.getFont("Roboto", true, 16));
            btnServices.setBackForeColors(Color.WHITE, Colors.PRIMARY);
            btnServices.roundBorderFactor = 14;

            btnTransSav = new Button("Transfer (Poupança)");
            btnTransSav.setFont(Font.getFont("Roboto", true, 16));
            btnTransSav.setBackForeColors(Color.WHITE, 0x908390);
            btnTransSav.roundBorderFactor = 14;

            btnTransChe = new Button("Transfer (Corrente)");
            btnTransChe.setFont(Font.getFont("Roboto", true, 16));
            btnTransChe.setBackForeColors(Color.WHITE, 0x908390);
            btnTransChe.roundBorderFactor = 14;


            btnExtract = new Button("Extrato");
            btnExtract.setFont(Font.getFont("Roboto", true, 16));
            btnExtract.setBackForeColors(Color.WHITE, 0x908390);
            btnExtract.roundBorderFactor = 14;

            btnPix = new Button("Pix");
            btnPix.setFont(Font.getFont("Roboto", true, 16));
            btnPix.setBackForeColors(Color.WHITE, 0x908390);
            btnPix.roundBorderFactor = 14;

            btnComp = new Button("Comprovantes");
            btnComp.setFont(Font.getFont("Roboto", true, 16));
            btnComp.setBackForeColors(Color.WHITE, 0x908390);
            btnComp.roundBorderFactor = 14;

            btnRecar = new Button("Recargas");
            btnRecar.setFont(Font.getFont("Roboto", true, 16));
            btnRecar.setBackForeColors(Color.WHITE, 0x908390);
            btnRecar.roundBorderFactor = 14;

            bar3.add(btnServices, CENTER, AFTER + UnitsConverter.toPixels(DP + 8), 328, 50);
            bar3.add(btnTransSav, LEFT + 16, AFTER + UnitsConverter.toPixels(DP + 8), 155, 108);
            bar3.add(btnTransChe, AFTER + UnitsConverter.toPixels(DP + 18), SAME, 155, 108);
            bar3.add(btnExtract, LEFT + 16, AFTER + UnitsConverter.toPixels(DP + 8), 155, 108);
            bar3.add(btnPix, AFTER + UnitsConverter.toPixels(DP + 18), SAME, 155, 108);
            bar3.add(btnComp, LEFT + 16, AFTER + UnitsConverter.toPixels(DP + 8), 155, 108);
            bar3.add(btnRecar, AFTER + UnitsConverter.toPixels(DP + 18), SAME, 155, 108);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnTransSav) {
                Transfer transfer = new Transfer();
                transfer.popup();
            }
        }
    }
}
