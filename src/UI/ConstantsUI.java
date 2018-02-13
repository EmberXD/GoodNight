package UI;

import javax.swing.*;
import java.awt.*;

public class ConstantsUI {
    /**
     * 软件名称，版本
     */
    public static final String APP_NAME = "GoodNight";
    public static final String APP_VERSION = "v_1.00_180212";

    /**
     * 启动窗口大小
     */
    public static final int LAUNCHER_VIEW_X = 0;
    public static final int LAUNCHER_VIEW_Y = 0;
    public static final int LAUNCHER_VIEW_WIDTH = 380;
    public static final int LAUNCHER_VIEW_HEIGHT = 286;

    /**
     * 主窗口大小
     */
    public static final int MAIN_WINDOW_X = 240;
    public static final int MAIN_WINDOW_Y = 100;
    public static final int MAIN_WINDOW_WIDTH = 885;
    public static final int MAIN_WINDOW_HEIGHT = 636;

    /**
     * 系统当前路径
     */
    public static final String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * 启动窗口图标
     */
    //background
    public static final ImageIcon ICON_BACKGROUND = new ImageIcon(
            LauncherView.class.getResource("/img/gn.png"));
    //close_button_normal
    public static final ImageIcon ICON_CLOSE_NORMAL = new ImageIcon(
            LauncherView.class.getResource("/img/btn_close_normal.png"));
    //close_button_down
    public static final ImageIcon ICON_CLOSE_DOWN = null;
    //close_button_highlight
    public static final ImageIcon ICON_CLOSE_HIGHLIGHT = new ImageIcon(
            LauncherView.class.getResource("/img/btn_close_highlight.png"));
    //login_button_normal
    public static final ImageIcon ICON_LOGIN_NORMAL = new ImageIcon(
            LauncherView.class.getResource("/img/btn_login_normal.png"));
    //login_button_down
    public static final ImageIcon ICON_LOGIN_DOWN = null;
    //login_button_highlight
    public static final ImageIcon ICON_LOGIN_HIGHLIGHT = new ImageIcon(
            LauncherView.class.getResource("/img/btn_login_highlight.png"));

    /**
     * 主窗口图标
     */
//    public static final Image IMAGE_ICON = Toolkit.getDefaultToolkit()
//            .getImage(AppMainWindow.class.getResource("/"));
}