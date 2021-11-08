package com.bankingsystem.main;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.model.Account;
import com.bankingsystem.model.CheckingAccount;
import com.bankingsystem.model.SavingsAccount;
import com.bankingsystem.util.Colors;
import com.bankingsystem.util.SQLiteConnection;
import com.bankingsystem.view.Initial;
import totalcross.sys.InvalidNumberException;
import totalcross.sys.Settings;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.icon.MaterialIcons;
import totalcross.ui.image.Image;
import totalcross.util.BigDecimal;
import totalcross.util.UnitsConverter;

public class Main extends MainWindow {

    SideMenuContainer sideMenu;
    private Button btnImage1;
    private Button btnImage2;
    public static Account origin;
    public static Account destiny;
    public static Account destiny2;

    public Main() {
        super("TotalCross Showcase", NO_BORDER);

        setUIStyle(Settings.MATERIAL_UI);
        uiAdjustmentsBasedOnFontHeightIsSupported = false;
        setBackForeColors(com.bankingsystem.util.Colors.BACKGROUND, com.bankingsystem.util.Colors.SURFACE);
    }

    @Override
    public void initUI() {
        try {
            SQLiteConnection.getInstance();
            MainWindow.getMainWindow().addTimer(100);

            SideMenuContainer.Item home = new SideMenuContainer.Item("TotalCross", MaterialIcons._HOME, Color.BLACK, false, () -> {
                try {
                    origin = new CheckingAccount(new BigDecimal("15000.00"), 1111, 12345);

                    destiny = new SavingsAccount(new BigDecimal("30000.00"), 2222, 56789);
                    destiny2 = new CheckingAccount(new BigDecimal("75000.00"), 3333, 10111);

                    return new Initial();
                } catch (InvalidNumberException e) {
                    e.printStackTrace();
                }
                return null;
            });
            home.setFont(Font.getFont("Roboto", true, 19));
            SideMenuContainer.Sub uiGroup = createUISubGroup();

            sideMenu = new SideMenuContainer(null,
                    home,
                    uiGroup);

            sideMenu.topMenu.header = new Container() {
                @Override
                public void initUI() {
                    setBackColor(Colors.PRIMARY);

                    Label title = new Label("Menu", LEFT, Color.WHITE, false);
                    title.setFont(Font.getFont("Lato Bold", false, this.getFont().size + 5));
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

    private SideMenuContainer.Sub createUISubGroup() {
        return new SideMenuContainer.Sub("TotalCross",
                new SideMenuContainer.Item("TotalCross", MaterialIcons._LOOKS, Color.BLACK, Initial::new));
    }
}
