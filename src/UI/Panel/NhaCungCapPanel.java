/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSNhaCungCapBLL;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import Model.NhaCungCap;
import UI.Dialog.NhaCungCapDialog;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;

public class NhaCungCapPanel extends JPanel implements ItemListener,MouseListener{
    private JPanel headerPanel, content;
    private DefaultTableModel tableModel;
    private JTable tableNCC;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private DSNhaCungCapBLL nccBLL = new DSNhaCungCapBLL();
    private NhaCungCapDialog nccDialog = null;
    private JFrame parent;
    
    public NhaCungCapPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponent();                
        loadData(nccBLL.getdsNCC());
        
    }

    public void initComponent() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);

        String[] function = { "create", "delete", "update" }; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        searchBar = new SearchBar(new String[] { "Tất cả", "Mã NCC", "Tên NCC", "Số điện thoại" });
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText().trim();
                loadData(nccBLL.search(text, type));
            }
        });

        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] header = { "Mã NCC", "Tên nhà cung cấp", "Số điện thoại" };
        tableModel = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        tableNCC = new JTable();
        tableNCC.setFillsViewportHeight(true);      
        tableNCC.setModel(tableModel);

        JTableHeader headers = tableNCC.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = tableNCC.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(500);
        column.getColumn(2).setPreferredWidth(320);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tableNCC.getColumnCount(); i++){
            tableNCC.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(tableNCC);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }    
    public void loadData(ArrayList<NhaCungCap> dsNCC){
        tableModel.setRowCount(0);   
        for(NhaCungCap ncc : dsNCC){
             tableModel.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getSoDienThoai()
            });              
        }
    }

    public DSNhaCungCapBLL getNccBLL() {
        return nccBLL;
    }

    public void setNccBLL(DSNhaCungCapBLL nccBLL) {
        this.nccBLL = nccBLL;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {       
        parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("create")){            
            nccDialog = new NhaCungCapDialog(parent, "Thêm nhà cung cấp", "create",this);  
            nccDialog.setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("update")){
            int index = tableNCC.getSelectedRow();
            if(index != -1){
                nccDialog = new NhaCungCapDialog(parent, "Cập nhật nhà cung cấp", "update", nccBLL.getdsNCC().get(index),this);
                nccDialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(parent, "Hãy chọn dòng cần sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete")){
            int index = tableNCC.getSelectedRow();
            if(index != -1){
                int check = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa nhà cung cấp ?", "Xóa nhà cung cấp",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(check == 0){
                    nccBLL.xoaNCC(nccBLL.getdsNCC().get(index).getMaNCC());
                    loadData(nccBLL.getdsNCC());
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(e.getSource() == mainFunc.getLstBtn().get("detail")){
            int index = tableNCC.getSelectedRow();
            if(index != -1){
                nccDialog = new NhaCungCapDialog(parent, "CHI TIẾT", "update", nccBLL.getdsNCC().get(index), this);
                nccDialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(parent, "Hãy chọn dòng cần xem chi tiết!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else if(e.getSource() == searchBar.getBtnRefesh()){            
           searchBar.getCboChoose().setSelectedIndex(0);
           searchBar.getTxtSearch().setText("");
            loadData(DSNhaCungCapBLL.getdsNCC());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String text = searchBar.getTxtSearch().getText().trim();
        String type = (String)searchBar.getCboChoose().getSelectedItem();
        loadData(nccBLL.search(text, type));
    }
}

