/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import UI.Component.InputField;
import UI.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
    private JPanel pnInput,pn1,pn2,pn2_1, pn2_2,pn2_3, pn2_4;
    private InputField masach,tensach,soluong,gianhap,tacgia,theloai;
    private JTable phieuTable, sachTable;
    private JScrollPane scrollPNTable, scrollSachTable;
    private DefaultTableModel phieuTableModel, sachTableModel;
    private JButton btnAdd,btnNhapHang;
    private InputField txtMaPhieu,txtThuThu,txtMaSach,txtTenSach,txtSoluong,txtGiaNhap;
    private JTextField txtSearch;
    private JLabel lbTotal;
    MainFrame m;
    
    public TaoPhieuNhapPanel(MainFrame m){
        this.setBackground(Color.decode("#ccc"));
        this.setLayout(new BorderLayout(5,5));
        initComponent();
    }
    
    
    public void initComponent(){
        left = new JPanel();
        left.setBackground(Color.white);
        left.setPreferredSize(new Dimension(690,800));
        left.setLayout(new BorderLayout(5,0));
        left.setBorder(new EmptyBorder(10, 5, 0, 5));
        
        pnInput = new JPanel();
        pnInput.setPreferredSize(new Dimension(650, 600));
        pnInput.setLayout(new BorderLayout(0, 5));
        pnInput.setBackground(Color.white);
        pnInput.setBorder(new EmptyBorder(0, 0, 5, 0));
        left.add(pnInput, BorderLayout.CENTER);
        
        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout(2, 7));
        pn1.setBackground(Color.white);
        pn1.setPreferredSize(new Dimension(390, 600));        
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
//        pnSachTable.setBackground(Color.white);
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
        
        JPanel pnBtnAdd = new JPanel();
        pnBtnAdd.setLayout(new FlowLayout(1,0,0));
        pnBtnAdd.setBackground(Color.white);
        pn1.add(pnBtnAdd, BorderLayout.SOUTH);
        
        btnAdd = new JButton("Thêm sản phẩm");
        btnAdd.setBackground(Color.decode("#00994C"));    
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAdd.setForeground(Color.white);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorder(null);
        btnAdd.setPreferredSize(new Dimension(150, 35));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
        pnBtnAdd.add(btnAdd);
        
        pn2 = new JPanel();
        pn2.setBackground(Color.white);
        pn2.setPreferredSize(new Dimension(290, 500));   
        pnInput.add(pn2, BorderLayout.EAST);
        
        pn2_1 = new JPanel();
        pn2_1.setLayout(new FlowLayout());
        pn2_1.setBackground(Color.white);
        pn2_1.setPreferredSize(new Dimension(290, 60));
        pn2.add(pn2_1);
        
        masach = new InputField("Mã sách", 90, 60);
        masach.getTxtInput().setText("SACH001");
        masach.getTxtInput().setEditable(false);
        masach.getTxtInput().setForeground(Color.BLACK);
        pn2_1.add(masach);
        
        tensach = new InputField("Tên sách", 190, 60);
        tensach.getTxtInput().setEditable(false);
        tensach.getTxtInput().setForeground(Color.BLACK);
        pn2_1.add(tensach);
        
        pn2_2 = new JPanel();
        pn2_2.setLayout(new FlowLayout());
        pn2_2.setBackground(Color.white);
        pn2_2.setPreferredSize(new Dimension(290, 60));
        pn2.add(pn2_2);
        
        theloai = new InputField("Thể loại", 280, 60);
        theloai.getTxtInput().setEditable(false);
        pn2_2.add(theloai);
                     
        pn2_4 = new JPanel();
        pn2_4.setLayout(new FlowLayout());
        pn2_4.setBackground(Color.white);
        pn2_4.setPreferredSize(new Dimension(290, 60));
        pn2.add(pn2_4);
        
        tacgia = new InputField("Tên tác giả", 280, 60);
        tacgia.getTxtInput().setEditable(false);
        pn2_4.add(tacgia);
        
        pn2_3 = new JPanel();
        pn2_3.setLayout(new FlowLayout());
        pn2_3.setBackground(Color.white);
        pn2_3.setPreferredSize(new Dimension(290, 60));
        pn2.add(pn2_3);
        
        gianhap = new InputField("Giá nhập", 190, 60);
        pn2_3.add(gianhap);
        
        soluong = new InputField("Số lượng", 90, 60);
        pn2_3.add(soluong);
        
        
        String[] colnamePhieuTable = {"Mã sách","Tên sách", "Đơn giá","Số lượng","Thành tiền"};
        phieuTableModel = new DefaultTableModel(colnamePhieuTable, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        
        pnPhieuTable = new JPanel();
        pnPhieuTable.setPreferredSize(new Dimension(400, 290));
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
        right.setPreferredSize(new Dimension(200, 800));        
        
        this.add(left, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
    }
}
