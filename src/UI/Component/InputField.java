/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.util.Date;
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
    private JDateChooser dateChooser;    
    private boolean isDateField;
    
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
        this.setLayout(new FlowLayout(0, 7, 10));
        this.lbContent = new JLabel(content, JLabel.CENTER);
        this.lbData = new JLabel(data, JLabel.CENTER);
//        this.lbData.setPreferredSize(new Dimension(w ,h));
        this.lbData.setFont(txtfont);
//        this.lbData.setBorder(BorderFactory.createLineBorder(Color.red));
        this.add(lbContent);
        this.add(lbData);
        this.init(w, h);
    }
    
    public InputField(String content, int w, int h, boolean isDateField) {
        this.isDateField = isDateField;
        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.white);
        lbContent = new JLabel(content);
        lbContent.setFont(font);
        this.add(lbContent);        
//        JPanel inputPanel = new JPanel(new BorderLayout());
        if (isDateField) {
            dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("dd/MM/yyyy");
            dateChooser.setPreferredSize(new Dimension(w - 10, h));
            dateChooser.setFont(txtfont);
            this.add(dateChooser, BorderLayout.CENTER);
        } else {
            txtInput = new JTextField();
            txtInput.setFont(txtfont);
            txtInput.setPreferredSize(new Dimension(w, h));
            this.add(txtInput, BorderLayout.CENTER);
        }       
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
    
     public Date getDate() {
        return dateChooser != null ? dateChooser.getDate() : null;
    }
     public void setDate(Date date) {
        if (isDateField && dateChooser != null) {
            dateChooser.setDate(date); // Thiết lập ngày cho JDateChooser
        }
    }

    public JLabel getLbData() {
        return lbData;
    }

    public void setLbData(JLabel lbData) {
        this.lbData = lbData;
    }

    public JLabel getLbContent() {
        return lbContent;
    }

    public void setLbContent(JLabel lbContent) {
        this.lbContent = lbContent;
    }
     
     

}
