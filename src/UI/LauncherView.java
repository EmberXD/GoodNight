package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LauncherView extends JFrame {
    public static void main(String[] args) {
        LauncherView j = new LauncherView();
        j.setVisible(true);
    }

    private int xOld = 0;
    private int yOld = 0;

    private LauncherView() {
        this.setLayout(null);
        this.setTitle("Good Night");
        this.setUndecorated(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
                yOld = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                LauncherView.this.setLocation(xx, yy);
            }
        });

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setOpaque(false);
        layeredPane.setBounds(0, 0, 380, 286);
        this.add(layeredPane);

        JPanel bgPanel = new JPanel();
        bgPanel.setOpaque(false);
        bgPanel.setBounds(0, 0, 380, 286);

        JLabel bgLabel = new JLabel(new ImageIcon("src/img/gn.png"));
        bgPanel.add(bgLabel);

        layeredPane.add(bgPanel, new Integer(0));

        JPanel miniPanel = new JPanel();
        miniPanel.setBounds(0, 0, 380, 286);
        miniPanel.setLayout(null);
        miniPanel.setOpaque(false);
        layeredPane.add(miniPanel, new Integer(1));

        JButton closeButton = new JButton(new ImageIcon("src/img/btn_close_normal.png"));
        closeButton.setBounds(355, 8, 20, 20);
        closeButton.setRolloverIcon(new ImageIcon("src/img/btn_close_highlight.png"));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
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


        JButton loginButton = new JButton(new ImageIcon("src/img/btn_login_normal.png"));
        loginButton.setBounds(160, 222, 80, 30);
        loginButton.setRolloverIcon(new ImageIcon("src/img/btn_login_highlight.png"));
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setContentAreaFilled(false);
        miniPanel.add(loginButton);
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
                            socket = null;
                            System.out.println("客户端 finally 异常:" + e4.getMessage());
                        }
                    }
                }
        });

        this.setBounds(0, 0, 380, 286);
        this.setBackground(new Color(0, 0, 0,0));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
    }
}
