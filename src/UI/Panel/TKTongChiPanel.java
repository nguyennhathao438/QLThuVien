/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import UI.Component.InputField;
import UI.Component.SelectInput;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nghia0605
 */
public class TKTongChiPanel extends JPanel{    
    private String[] labels = {"Theo ngày", "Theo quý"};
    private JPanel toolBarPanel,contentPanel;    
    private JPanel[] btnPanels;
    private Color defaultColor = new Color(255, 255, 255);
    private Color selectedColor = new Color(100, 149, 237); 
    
    public TKTongChiPanel(){
        setBackground(Color.white);
        setLayout(new BorderLayout(0, 0));
        
        init();
    }
    
    public void init(){
        
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.white);
        contentPanel.setPreferredSize(new Dimension(890, 660));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        contentPanel.setLayout(new BorderLayout(10, 0));
        this.add(contentPanel, BorderLayout.CENTER);
        
        
        toolBarPanel = new JPanel(new FlowLayout(0,10,20));
        toolBarPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.LIGHT_GRAY));
        toolBarPanel.setBackground(Color.white);
        toolBarPanel.setPreferredSize(new Dimension(890,70));                      
        this.add(toolBarPanel, BorderLayout.NORTH);
        btnPanels = new JPanel[labels.length];
        createToolBar();        
        
    }
    
    public void setContentPanel(JPanel pn){
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel.add(pn, BorderLayout.CENTER);              
    }
    
    public void createToolBar(){
        for (int i = 0; i < labels.length; i++) {
            JPanel p = new JPanel();
//            p.setPreferredSize(new Dimension(100, 30));
            p.setBackground(defaultColor);
//            p.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
            p.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JLabel label = new JLabel(labels[i], JLabel.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            label.setForeground(Color.BLACK);  
            p.add(label);

            int index = i;
            p.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setSelectedPanel(index);
                }
            });

            btnPanels[i] = p;
            toolBarPanel.add(p);
        }        

        setSelectedPanel(0);
    }
    private void setSelectedPanel(int selectedIndex) {
        for (int i = 0; i < btnPanels.length; i++) {
            if (i == selectedIndex) {
                btnPanels[i].setBackground(Color.white);
                btnPanels[i].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, selectedColor));
            } else {
                btnPanels[i].setBackground(defaultColor);
                btnPanels[i].setBorder(null);
            }
        }  
//        contentPanel.removeAll();
        JPanel selectedPanel = null;
        switch(selectedIndex){ 
            case 0: 
                selectedPanel= new TKTongChiTheoNgayPanel();
                break;
            case 1:
                selectedPanel= new TKTongChiTheoQuyPanel();
                break;

            default:
                selectedPanel= new TKTongChiTheoNgayPanel();
                break;

        }
        setContentPanel(selectedPanel);
    }
}
