/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import UI.Component.InputField;
import UI.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nghia0605
 */
public class TaoPhieuNhapPanel extends JPanel{
    private JPanel left,right,pnPhieuTable,pnSachTable;
    private JPanel pnInput,pn1,pn2;
    private JTable phieuTable, sachTable;
    private JScrollPane scrollPNTable, scrollSachTable;
    private DefaultTableModel phieuTableModel, sachTableModel;
    private JButton btnAdd,btnNhapHang;
    private InputField txtMaPhieu,txtThuThu,txtMaSach,txtTenSach,txtSoluong,txtGiaNhap;
    private JTextField txtSearch;
    private JLabel lbTotal;
    MainFrame m;
    
    public TaoPhieuNhapPanel(MainFrame m){
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout(0,0));
        initComponent();
    }
    
    
    public void initComponent(){
        left = new JPanel();
        left.setBackground(Color.white);
        left.setPreferredSize(new Dimension(650,800));
        left.setLayout(new BorderLayout(5,7));
        left.setBorder(new EmptyBorder(10, 5, 0, 5));
        
        pnInput = new JPanel();
        pnInput.setPreferredSize(new Dimension(650, 600));
        pnInput.setLayout(new BorderLayout(0, 5));
        left.add(pnInput, BorderLayout.CENTER);
        
        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout(2, 7));
//        pn1.setBackground(Color.PINK);
        pn1.setPreferredSize(new Dimension(400, 600));
        pnInput.add(pn1, BorderLayout.CENTER);
        
        JPanel pnSearch = new JPanel();
        pnSearch.setBorder(new EmptyBorder(10, 0, 0, 0));
        txtSearch = new JTextField(20);
        pnSearch.add(txtSearch);
        
        pn1.add(txtSearch, BorderLayout.NORTH);
        
        String[] colname = {"Mã sách","Tên sách", "Số lượng"};
        sachTableModel = new DefaultTableModel(colname, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        
        pnSachTable = new JPanel();
        pnSachTable.setPreferredSize(new Dimension(400, 300));
        pnSachTable.setLayout(new BorderLayout(0,0));
        pn1.add(pnSachTable, BorderLayout.CENTER);
        
        sachTable = new JTable();
        sachTable.setFillsViewportHeight(true);      
        sachTable.setModel(sachTableModel);
        
        JTableHeader headers = sachTable.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < sachTable.getColumnCount(); i++){
            sachTable.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollSachTable = new JScrollPane(sachTable);
        pnSachTable.add(scrollSachTable, BorderLayout.CENTER);
        
        
        
        pn2 = new JPanel();
        pn2.setBackground(Color.red);
        pn2.setPreferredSize(new Dimension(250, 500));
        pnInput.add(pn2, BorderLayout.EAST);
        
        String[] colnamePhieuTable = {"Mã sách","Tên sách", "Đơn giá","Số lượng","Thành tiền"};
        phieuTableModel = new DefaultTableModel(colnamePhieuTable, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        
        pnPhieuTable = new JPanel();
        pnPhieuTable.setPreferredSize(new Dimension(400, 300));
        pnPhieuTable.setLayout(new BorderLayout(0,0));
        left.add(pnPhieuTable, BorderLayout.SOUTH);
        
        phieuTable = new JTable();
        phieuTable.setFillsViewportHeight(true);      
        phieuTable.setModel(phieuTableModel);
        
        JTableHeader header = phieuTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(Color.decode("#66B2FF"));
        
        for(int i = 0; i < phieuTable.getColumnCount(); i++){
            phieuTable.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollSachTable = new JScrollPane(phieuTable);
        pnPhieuTable.add(scrollSachTable, BorderLayout.CENTER);
        
        right = new JPanel();
        right.setBackground(Color.blue);
        right.setPreferredSize(new Dimension(250, 800));
        
        this.add(left, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
    }
}
