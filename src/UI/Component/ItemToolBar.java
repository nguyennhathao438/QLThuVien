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

public class ItemToolBar extends JButton {
    private String act;

    private JLabel lbIcon, lbContent;

    public ItemToolBar(String linkIcon, String content, String act) {
        FlatSVGIcon icon = new FlatSVGIcon(getClass().getResource(linkIcon)).derive(34, 34);
        lbIcon = new JLabel(icon, JLabel.CENTER);
        lbContent = new JLabel(content, JLabel.CENTER);
        lbContent.setPreferredSize(new Dimension(80, 20));
        lbContent.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbContent.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        this.setPreferredSize(new Dimension(78, 70));
        this.add(lbIcon);
        this.add(lbContent);
        this.setBackground(Color.WHITE);
        this.setBorder(null);
        this.setLayout(new FlowLayout(1, 2, 7));
        // this.setHorizontalTextPosition(SwingConstants.CENTER);
        // this.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }
}
