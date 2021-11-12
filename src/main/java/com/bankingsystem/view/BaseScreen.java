package com.bankingsystem.view;

import com.bankingsystem.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

public abstract class BaseScreen extends Container {

    protected ScrollContainer content;
    protected boolean allowVerticalScroll = true;
    protected boolean allowHorizontalScroll = false;
    private Container bar;
    boolean showBar;
    private Button btnImage1;
    private Button btnImage2;

    public BaseScreen() {

    }

    @Override
    public void initUI() {
        try {
            if (showBar) {
                bar = new Container();
                bar.setBackColor(Colors.PRIMARY);

                add(bar, LEFT, TOP, PARENTSIZE, DP + 52);

                Image img1 = new Image("images/notification.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
                btnImage1 = new Button(img1.getHwScaledInstance(UnitsConverter.toPixels(DP + 16), UnitsConverter.toPixels(DP + (int) 19.5)), Button.BORDER_NONE);
                btnImage1.setBackForeColors(Colors.PRIMARY, Color.WHITE);
                bar.add(btnImage1, RIGHT - 60, CENTER);

                Image img2 = new Image("images/goout.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
                btnImage2 = new Button(img2.getHwScaledInstance(UnitsConverter.toPixels(DP + 20), UnitsConverter.toPixels(DP + 18)), Button.BORDER_NONE);
                btnImage2.setBackForeColors(Colors.PRIMARY, Color.WHITE);
                bar.add(btnImage2, RIGHT - 18, CENTER);

                bar.resizeHeight();
            }
            content = new ScrollContainer() {
                @Override
                public void initUI() {
                    setScrollBars(allowHorizontalScroll, allowVerticalScroll);
                    super.initUI();
                }
            };
            // Content
            add(content, LEFT, AFTER, PARENTSIZE, FILL);
            onContent(content);
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public abstract void onContent(ScrollContainer content);
}
