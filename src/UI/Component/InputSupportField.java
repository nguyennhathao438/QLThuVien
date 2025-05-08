package UI.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Nghia0605
 */
public class InputSupportField extends JPanel {
    private JLabel lbContent;
    private JButton btnSupport;
    private JTextField txtInput;

    private Font font = new Font("Segoe UI", Font.BOLD, 15);
    private Font txtfont = new Font("Segoe UI", Font.PLAIN, 15);

    public InputSupportField(String content, int w, int h) {
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(w, h));

        // Label
        this.lbContent = new JLabel(content);
        this.lbContent.setFont(font);
        this.lbContent.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(lbContent);

        // Panel chứa input và nút hỗ trợ
        JPanel pnTxtInput = new JPanel();
        pnTxtInput.setPreferredSize(new Dimension(w, h));
        pnTxtInput.setBackground(Color.white);
        pnTxtInput.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        this.add(pnTxtInput);

        // Text field
        this.txtInput = new JTextField();
        this.txtInput.setFont(txtfont);
        this.txtInput.setPreferredSize(new Dimension(w - 20, h - 10)); // điều chỉnh width để chừa chỗ cho nút
        pnTxtInput.add(txtInput);

        // Nút hỗ trợ
        this.btnSupport = new JButton("...");
        this.btnSupport.setPreferredSize(new Dimension(17, 17));
        this.btnSupport.setFocusPainted(false);
        this.btnSupport.setContentAreaFilled(false);
        this.btnSupport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btnSupport.setBorder(new LineBorder(Color.RED, 1));
        pnTxtInput.add(btnSupport);
    }
    
    public JLabel getLbContent() {
        return lbContent;
    }

    public void setLbContent(JLabel lbContent) {
        this.lbContent = lbContent;
    }

    public JTextField getTxtInput() {
        return txtInput;
    }

    public void setTxtInput(JTextField txtInput) {
        this.txtInput = txtInput;
    }

    public JButton getBtnSupport() {
        return btnSupport;
    }

    // Cho phép gán sự kiện cho nút hỗ trợ
    public void setSupportButtonAction(ActionListener action) {
        btnSupport.addActionListener(action);
    }
}
