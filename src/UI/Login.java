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
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class Login extends JFrame {
    private JPanel leftPanel, rightPanel;
    private JLabel lbProject, lbTitle, lbUsername, lbPass, lbLogin;
    private JTextField txtUsername;
    private JPasswordField txtPass;
    private JPanel btnLogin;
    private JPanel inputFieldPanel;

    public Login() {
        this.setTitle("Đăng nhập");
        this.setLayout(new BorderLayout());
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    public void init() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(1, 0, 60));
        leftPanel.setPreferredSize(new Dimension(350, 500));
        leftPanel.setBackground(Color.decode("#33FFFF"));

        FlatSVGIcon libraryIcon = new FlatSVGIcon(getClass().getResource("/img/library.svg")).derive(220, 220);
        JLabel library = new JLabel(libraryIcon, JLabel.CENTER);
        leftPanel.add(library);

        lbProject = new JLabel("QUẢN LÝ THƯ VIỆN", JLabel.CENTER);
        lbProject.setFont(new Font("Segoe UI", Font.BOLD, 26));
        leftPanel.add(lbProject);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(450, 500));
        rightPanel.setBackground(Color.white);

        lbTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG", JLabel.CENTER);
        // lbTitle.setVerticalAlignment(SwingConstants.BOTTOM);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbTitle.setPreferredSize(new Dimension(450, 150));
        // lbTitle.setBorder(BorderFactory.createLineBorder(Color.red));
        rightPanel.add(lbTitle, BorderLayout.NORTH);

        inputFieldPanel = new JPanel();
        inputFieldPanel.setLayout(null);
        inputFieldPanel.setBackground(null);
        inputFieldPanel.setPreferredSize(new Dimension(450, 200));
        // inputFieldPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        rightPanel.add(inputFieldPanel, BorderLayout.CENTER);

        lbUsername = new JLabel("Tên đăng nhập");
        lbUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbUsername.setBounds(40, 0, 150, 30);
        inputFieldPanel.add(lbUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBounds(40, 35, 350, 40);
        // txtUsername.setForeground(Color.GRAY);
        inputFieldPanel.add(txtUsername);

        lbPass = new JLabel("Mật khẩu");
        lbPass.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbPass.setBounds(40, 80, 100, 30);
        inputFieldPanel.add(lbPass);

        txtPass = new JPasswordField();
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPass.setBounds(40, 115, 350, 40);
        inputFieldPanel.add(txtPass);

        lbLogin = new JLabel("Đăng nhập");
        lbLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbLogin.setForeground(Color.WHITE);

        btnLogin = new JPanel();
        // btnLogin.putClientProperty(FlatClientProperties.STYLE, "arc: 100");
        btnLogin.setBackground(Color.decode("#404040"));
        btnLogin.setLayout(new FlowLayout(1, 0, 10));
        btnLogin.add(lbLogin);
        btnLogin.setBounds(40, 190, 350, 50);
        inputFieldPanel.add(btnLogin);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);

        btnLogin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Login");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnLogin.setBackground(Color.decode("#808080"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                btnLogin.setBackground(Color.decode("#404040"));
            }

        });
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public JLabel getLbProject() {
        return lbProject;
    }

    public void setLbProject(JLabel lbProject) {
        this.lbProject = lbProject;
    }

    public JLabel getLbTitle() {
        return lbTitle;
    }

    public void setLbTitle(JLabel lbTitle) {
        this.lbTitle = lbTitle;
    }

    public JLabel getLbUsername() {
        return lbUsername;
    }

    public void setLbUsername(JLabel lbUsername) {
        this.lbUsername = lbUsername;
    }

    public JLabel getLbPass() {
        return lbPass;
    }

    public void setLbPass(JLabel lbPass) {
        this.lbPass = lbPass;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JPasswordField txtPass) {
        this.txtPass = txtPass;
    }

    public JPanel getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JPanel btnLogin) {
        this.btnLogin = btnLogin;
    }

    public JPanel getInputFieldPanel() {
        return inputFieldPanel;
    }

    public void setInputFieldPanel(JPanel inputFieldPanel) {
        this.inputFieldPanel = inputFieldPanel;
    }
}

