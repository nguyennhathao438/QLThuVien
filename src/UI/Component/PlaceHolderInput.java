/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Nghia0605
 */
public class PlaceHolderInput extends JTextField{
    
    
    public PlaceHolderInput(String placeholder){
        this.setText(placeholder);
        this.setForeground(Color.GRAY);
        this.setBorder(null);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
//        this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        
        // Thêm sự kiện focus
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                }
            }
        });
    }
        
    
}
