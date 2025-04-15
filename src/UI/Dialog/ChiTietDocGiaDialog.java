/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author NGOC TUYEN
 */
public class ChiTietDocGiaDialog extends JDialog{
    private DefaultTableModel tblModel;
    private JTable tblChiTiet;
    private JScrollPane scrollPane;
    private JButton btnCancel;
    private JPanel tblPanel;
    
    public ChiTietDocGiaDialog(JFrame parent, boolean modal){
        super(parent,"Chi tiết độc giả", modal);
        this.setSize(700, 300);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        //initEvents();
    }

    public void initComponents(){
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã độc giả", "Tên độc giả", "Mã loại","Tên loại", "Giới hạn", "Mô tả"};
        tblModel = new DefaultTableModel(header, 0);
        tblChiTiet = new JTable();
        tblChiTiet.setModel(tblModel);
        
        //Lấy header của bảng.
        JTableHeader headers = tblChiTiet.getTableHeader();
        headers.setReorderingAllowed(false);//Không cho thay đổi thứ tự các cột.
        headers.setResizingAllowed(false);// Không cho thay đổi kích thước cột.
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        //Lấy thông tin mô hình cột của bảng.
        TableColumnModel columns = tblChiTiet.getColumnModel();
        columns.getColumn(0).setPreferredWidth(100);
        columns.getColumn(1).setPreferredWidth(100);
        columns.getColumn(2).setPreferredWidth(100);
        columns.getColumn(3).setPreferredWidth(100);
        columns.getColumn(4).setPreferredWidth(100);
        columns.getColumn(5).setPreferredWidth(100);
        
        scrollPane = new JScrollPane(tblChiTiet);
        tblPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(tblPanel, BorderLayout.CENTER);
    
        
    }   
        
    public void initEvents(){
        // Đóng dialog khi người dùng nhấn "Hủy".
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    public JTextField txtInfor(String title){
        JTextField infor = new JTextField();
        infor.setBorder(BorderFactory.createTitledBorder(title));  
        return infor;
    }

    public JButton btnFunc(String title, String color){
        JButton btn = new JButton(title);
        btn.setPreferredSize(new Dimension(150,40));
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        btn.setBackground(Color.decode(color));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);// khong hien thi vien khi click
        return btn;
    }
    
    public JComboBox cbochoose(String title, String[] str){
        JComboBox cbo = new JComboBox<>(str);
        cbo.setBorder(BorderFactory.createTitledBorder(title)); 
        cbo.setModel(new DefaultComboBoxModel<>(str));
        cbo.setFocusable(false);
        cbo.setOpaque(false);
        cbo.setBackground(Color.WHITE);
        cbo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return cbo;    
    }
  
    
}
    