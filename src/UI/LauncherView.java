package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LauncherView {
    private JFrame frame;

    private static JLayeredPane layeredPane;
    private static JPanel bgPanel;
    private static JPanel miniPanel;
    private static MyIconButton closeButton;
    private static MyIconButton loginButton;
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
                try {
                    LauncherView launcherView = new LauncherView();
                    launcherView.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    private LauncherView() {
        initialize();
    }

    private int xOld = 0;
    private int yOld = 0;
    /**
     * 初始化frame
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setBounds(ConstantsUI.LAUNCHER_VIEW_X, ConstantsUI.LAUNCHER_VIEW_Y, ConstantsUI.LAUNCHER_VIEW_WIDTH,
                ConstantsUI.LAUNCHER_VIEW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(ConstantsUI.APP_NAME);
        frame.setBackground(new Color(0, 0, 0, 0));

        layeredPane = new JLayeredPane();
        layeredPane.setOpaque(false);
        layeredPane.setBounds(ConstantsUI.LAUNCHER_VIEW_X, ConstantsUI.LAUNCHER_VIEW_Y, ConstantsUI.LAUNCHER_VIEW_WIDTH,
                ConstantsUI.LAUNCHER_VIEW_HEIGHT);
        frame.add(layeredPane);


        bgPanel = new JPanel();
        bgPanel.setOpaque(false);
        bgPanel.setBounds(ConstantsUI.LAUNCHER_VIEW_X, ConstantsUI.LAUNCHER_VIEW_Y, ConstantsUI.LAUNCHER_VIEW_WIDTH,
                ConstantsUI.LAUNCHER_VIEW_HEIGHT);

        JLabel bgLabel = new JLabel(ConstantsUI.ICON_BACKGROUND);
        bgPanel.add(bgLabel);

        layeredPane.add(bgPanel, new Integer(0));

        miniPanel = new JPanel();
        miniPanel.setBounds(ConstantsUI.LAUNCHER_VIEW_X, ConstantsUI.LAUNCHER_VIEW_Y, ConstantsUI.LAUNCHER_VIEW_WIDTH,
                ConstantsUI.LAUNCHER_VIEW_HEIGHT);
        miniPanel.setLayout(null);
        miniPanel.setOpaque(false);
        layeredPane.add(miniPanel, new Integer(1));

        closeButton = new MyIconButton(ConstantsUI.ICON_CLOSE_NORMAL, ConstantsUI.ICON_CLOSE_DOWN,
                ConstantsUI.ICON_CLOSE_HIGHLIGHT, "");
        closeButton.setBounds(355, 8, 20, 20);
        closeButton.addActionListener((e)-> System.exit(0));
        miniPanel.add(closeButton);

        final JLabel _username = new JLabel("1 + 1 = ");
        _username.setBounds(70, 180, 70, 22);
        _username.setFont(new Font("黑体", Font.PLAIN, 15));
        miniPanel.add(_username);
        final JTextField username = new JTextField();
        username.setBounds(140, 182, 120, 20);
        username.setFont(new Font("黑体", Font.PLAIN, 16));
        username.setVisible(true);
        miniPanel.add(username);

        loginButton = new MyIconButton(ConstantsUI.ICON_LOGIN_NORMAL, ConstantsUI.ICON_LOGIN_DOWN,
                ConstantsUI.ICON_LOGIN_HIGHLIGHT, "");
        loginButton.setBounds(160, 222, 80, 30);
        miniPanel.add(loginButton);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
                yOld = e.getY();
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                frame.setLocation(xx, yy);
            }
        });

        loginButton.addActionListener((e) ->{
            final String IP_ADDR = "139.199.12.213";
            final int PORT = 12345;
            Socket socket = null;
            try {
                socket = new Socket(IP_ADDR, PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String str = username.getText();
                out.writeUTF(str);
                String ret = input.readUTF();
                if (ret.equals("10")) {
                    JOptionPane.showMessageDialog(null, "success");
                } else {
                    JOptionPane.showMessageDialog(null, "fail");
                }

                out.close();
                input.close();

            } catch (Exception e2) {
                System.out.println("客户端异常：" + e2.getMessage());
            }finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e4) {
                        System.out.println("客户端 finally 异常:" + e4.getMessage());
                    }
                }
            }
        });
    }

}
