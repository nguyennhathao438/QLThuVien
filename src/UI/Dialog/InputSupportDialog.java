/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;

import UI.Component.Displayable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nghia0605
 */
public class InputSupportDialog<T extends Displayable> extends JDialog{
    private JButton btnChon;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JPanel pnTable, pnBtn;
    private T selectedItem;
    public InputSupportDialog(JFrame parent, String title,String[] header,ArrayList<T> ds){
        super(parent, title,true);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout(0, 0));
        this.setSize(500, 400);
        this.setLocationRelativeTo(parent);
     
        model = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        
        loadData(ds);
        table = new JTable();        
        table.setFillsViewportHeight(true);      
        table.setModel(model);
        
        JTableHeader headers = table.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(table);
        
        pnTable = new JPanel();
        pnTable.setBackground(Color.white);
        pnTable.setLayout(new BorderLayout(0, 0));
        pnTable.setPreferredSize(new Dimension(500, 320));
        pnTable.add(scrollPane);
        this.add(pnTable,BorderLayout.CENTER);
        
        
        pnBtn = new JPanel();
        pnBtn.setPreferredSize(new Dimension(500, 80));
        pnBtn.setBackground(Color.white);
        pnBtn.setBorder(new EmptyBorder(10, 0, 0, 0));
        this.add(pnBtn, BorderLayout.SOUTH);
        
        this.btnChon = new JButton("Chọn");
        this.btnChon.setBorder(null);
        this.btnChon.setFocusPainted(false);
        this.btnChon.setPreferredSize(new Dimension(100, 40));
        this.btnChon.setBackground(Color.decode("#00994C"));
        this.btnChon.setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.btnChon.setForeground(Color.white);
        pnBtn.add(btnChon);
        
        this.btnChon.addActionListener(new ActionListener(){                        
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1){
                    selectedItem = ds.get(selectedRow); // Lưu object thực
                    dispose();
                }
            }
        });
        
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    
    public void loadData(ArrayList<T> ds){
        this.model.setRowCount(0);
        for(T item : ds){
            this.model.addRow(item.toRowData());
        }
    }
}
