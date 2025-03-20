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

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.*;

public class ItemTaskBar extends JPanel {
    private JLabel lbIcon, lbContent;

    public ItemTaskBar(String icon, String content) {
        this.setLayout(new FlowLayout(1, 10, 5));
        this.setPreferredSize(new Dimension(300, 55));
        this.setBackground(Color.white);

        lbIcon = new JLabel();
        lbIcon.setIcon(new FlatSVGIcon(getClass().getResource("/img/" + icon)).derive(30, 30));
        this.add(lbIcon);

        lbContent = new JLabel(content);
        lbContent.setPreferredSize(new Dimension(200, 40));
        lbContent.setForeground(Color.BLACK);
        lbContent.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 18));

        this.setVisible(true);
        this.add(lbContent);
    }
}
