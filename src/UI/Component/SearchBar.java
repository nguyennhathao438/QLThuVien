/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

/**
 *
 * @author Nghia0605
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchBar extends JPanel {
    private JComboBox<String> cboChoose;
    private JTextField txtSearch;
    private JButton btnRefesh;

    public SearchBar(String[] str) {
        this.setPreferredSize(new Dimension(390, 110));
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(0, 2, 20));
        this.init(str);
        this.setVisible(true);
    }

    public void init(String[] str) {
        Border border = BorderFactory.createLineBorder(Color.decode("#A0A0A0"), 2);// tạo màu cho đường viền
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Tìm kiếm");
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 15));
        titledBorder.setTitleColor(Color.GRAY);
        this.setBorder(titledBorder);

        cboChoose = new JComboBox<>(str);
        cboChoose.setModel(new DefaultComboBoxModel<>(str));
        cboChoose.setPreferredSize(new Dimension(110, 30));
        cboChoose.setFocusable(false);
        cboChoose.setOpaque(false);
        cboChoose.setBackground(Color.WHITE);
        cboChoose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cboChoose.setBorder(null);

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(170, 32));
        // txtSearch.putClientProperty("JTextField.placeholderText", "Nhập nội dung tìm
        // kiếm...");
        // txtSearch.putClientProperty("JTextField.showClearButton", true);

        FlatSVGIcon icon = new FlatSVGIcon(getClass().getResource("/img/refresh.svg")).derive(22, 22);
        btnRefesh = new JButton("Làm mới", icon);
        btnRefesh.setBackground(Color.WHITE);
        btnRefesh.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        btnRefesh.setPreferredSize(new Dimension(90, 30));
        btnRefesh.setFocusPainted(false);// khong hien thi vien khi click
        btnRefesh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRefesh.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnRefeshHover(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRefeshDefault(e);
            }
        });

        this.add(cboChoose);
        this.add(txtSearch);
        this.add(btnRefesh);

    }


    private void btnRefeshHover(MouseEvent e) {
        btnRefesh.setBackground(Color.decode("#F0F8FF"));
    }

    private void btnRefeshDefault(MouseEvent e) {
        btnRefesh.setBackground(Color.WHITE);
    }

    public JComboBox<String> getCboChoose() {
        return cboChoose;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JButton getBtnRefesh() {
        return btnRefesh;
    }
}

