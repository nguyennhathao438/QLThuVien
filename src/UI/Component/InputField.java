/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Nghia0605
 */
public class InputField extends JPanel{
    private JTextField txtInput;
    private JLabel lbContent,lbData;
    private Font font = new Font("Segoe UI", Font.BOLD, 15);
    private Font txtfont = new Font("Segoe UI", Font.PLAIN, 15);
    public InputField(String content,int w,int h){
        this.setLayout(new GridLayout(2, 1));
        this.lbContent = new JLabel(content);
        this.txtInput = new JTextField();
        this.txtInput.setFont(txtfont);
        this.add(lbContent);
        this.add(txtInput);
        this.init(w, h);
    }
    public InputField(String content,String data,int w,int h){
        this.setLayout(new FlowLayout(0, 7, 0));
        this.lbContent = new JLabel(content);
        this.lbData = new JLabel(data);
        lbData.setPreferredSize(new Dimension(150,30));
        this.add(lbContent);
        this.add(lbData);
        this.init(w, h);
    }
    public void init(int width,int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setBorder(new EmptyBorder(0,10,5,10));
        this.setBackground(Color.WHITE);
        this.lbContent.setFont(font);
        
    }

    public JTextField getTxtInput() {
        return txtInput;
    }

    public void setTxtInput(String txtInput) {
        this.txtInput.setText(txtInput);
    }
    
    
}
