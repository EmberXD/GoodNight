package UI;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义按钮类，支持自定义默认图标、激活图标、失效图标和tips提示
 */
class MyIconButton extends JButton {
    private ImageIcon iconDown, iconHighlight;
    private String tip;

    MyIconButton(ImageIcon iconNormal, ImageIcon iconDown, ImageIcon iconHighlight, String tip) {
        super(iconNormal);

        this.iconDown = iconDown;
        this.iconHighlight = iconHighlight;
        this.tip = tip;

        initialize();
        setUp();
    }

    /**
     * 初始化，设置按钮属性：无边，无焦点渲染，无内容区，各边距0
     */
    private void initialize() {
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    /**
     * 设置按钮图标：鼠标移过、按压的图标 和设置按钮提示
     */
    private void setUp() {
        this.setRolloverIcon(iconHighlight);
        this.setPressedIcon(iconDown);

        if (!tip.equals("")) {
            this.setToolTipText(tip);
        }
    }
}