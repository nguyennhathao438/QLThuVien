/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author Nghia0605
 */
import javax.swing.*;
//import javax.swing.border.Border;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import UI.Component.MenuTaskBar;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel leftPanel, rightPanel, userInfoPanel;
    private JLabel lbUsername;
    private MenuTaskBar menuTaskBar;

    public MainFrame() {
        this.setTitle("Quản lý thư viện");
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    public void init() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(0, 0));
        leftPanel.setPreferredSize(new Dimension(300, 800));
        leftPanel.setBackground(Color.WHITE);

        userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new FlowLayout(0, 20, 40));
        userInfoPanel.setPreferredSize(new Dimension(300, 140));
        FlatSVGIcon iconUser = new FlatSVGIcon(getClass().getResource("/img/user.svg")).derive(50, 50);
        JLabel lbIconUser = new JLabel(iconUser);
        userInfoPanel.add(lbIconUser);

        lbUsername = new JLabel("Tên đăng nhập");
        lbUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbUsername.setPreferredSize(new Dimension(170, 30));
        userInfoPanel.add(lbUsername);
        leftPanel.add(userInfoPanel, BorderLayout.NORTH);

        menuTaskBar = new MenuTaskBar(this);
        leftPanel.add(menuTaskBar, BorderLayout.CENTER);

//        TrangChuPanel trangChu = new TrangChuPanel();
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setPreferredSize(new Dimension(900, 800));
        rightPanel.setLayout(new BorderLayout());
//        rightPanel.add(trangChu, BorderLayout.CENTER);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);

    }

    public void setRightPanel(JPanel pn) {
        rightPanel.removeAll();
        rightPanel.add(pn, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}