package com.bankingsystem.util;

import com.bankingsystem.view.Home;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.icon.MaterialIcons;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

public class SlideMenu extends Window {

    public static SideMenuContainer sideMenu;
    private Button btnImage1, btnImage2;
    private MessageBox mb;

    @Override
    public void onPopup() {
        try {
            MainWindow.getMainWindow().addTimer(100);

            SideMenuContainer.Item home = new SideMenuContainer.Item("Home", MaterialIcons._HOME, Color.BLACK, false, Home::new);
            home.setFont(Font.getFont("Roboto", true, 19));

            sideMenu = new SideMenuContainer(null,
                    home);

            sideMenu.topMenu.header = new Container() {
                @Override
                public void initUI() {
                    setBackColor(Colors.PRIMARY);

                    Label title = new Label("Menu", LEFT, Color.WHITE, false);
                    title.setFont(Font.getFont("Roboto", false, 19));
                    title.setForeColor(Color.WHITE);
                    add(title, LEFT + UnitsConverter.toPixels(Control.DP + 10), BOTTOM - UnitsConverter.toPixels(Control.DP + 10), FILL, DP + 56);
                }
            };

            sideMenu.setBarFont(Font.getFont(Font.getDefaultFontSize() + 5));
            sideMenu.setBackColor(Colors.PRIMARY);
            sideMenu.setForeColor(Color.WHITE);
            sideMenu.setItemForeColor(Color.BLACK);
            sideMenu.topMenu.drawSeparators = false;
            sideMenu.topMenu.itemHeightFactor = 3;

            add(sideMenu, LEFT, TOP, PARENTSIZE, PARENTSIZE);

            Image img1 = new Image("images/notification.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
            btnImage1 = new Button(img1.getHwScaledInstance(UnitsConverter.toPixels(DP + 16), UnitsConverter.toPixels(DP + (int) 19.5)), Button.BORDER_NONE);
            btnImage1.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            add(btnImage1, RIGHT - 60, TOP + 10);

            Image img2 = new Image("images/goout.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
            btnImage2 = new Button(img2.getHwScaledInstance(UnitsConverter.toPixels(DP + 20), UnitsConverter.toPixels(DP + 18)), Button.BORDER_NONE);
            btnImage2.setBackForeColors(Colors.PRIMARY, Color.WHITE);
            add(btnImage2, RIGHT - 18, TOP + 10);

        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void onEvent(Event event) {
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnImage1) {
                String message = "Você ainda não possui notificações!";
                mb = new MessageBox("Sem notificações!", message, new String[]{"Ok!"});
                mb.setRect(CENTER, CENTER, SCREENSIZE + 70, SCREENSIZE + 50);
                mb.setBackForeColors(Colors.BACKGROUND, Colors.ON_P_300);
                mb.popup();
            }
        }
        if (event.type == ControlEvent.PRESSED) {
            if (event.target == btnImage2) {
                this.unpop();
            }
        }
    }
}
