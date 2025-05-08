/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSDocGiaBLL;
import BLL.DSLoaiDocGiaBLL;
import Model.DocGia;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.ChiTietDocGiaDialog;
import UI.Dialog.DocGiaDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGOC TUYEN
 */
public class DocGiaPanel extends JPanel implements ItemListener, MouseListener{
    private JPanel headerPanel, tblPanel;
    private DefaultTableModel tblModel;
    private JTable tblDG;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private JFrame parent;
    private DSDocGiaBLL dgbll = new DSDocGiaBLL();
    private DocGiaDialog dgDialog = null;
    private ChiTietDocGiaDialog ctDialog = null;
    private DSLoaiDocGiaBLL ldgbll = new DSLoaiDocGiaBLL();
    
    public DocGiaPanel(){
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        initComponent();
        loadDG(dgbll.layDSDocGia());
        
    }
    
    public void initComponent(){
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0,0,4));
        headerPanel.setBackground(Color.WHITE);
        
        String[] function = {"create", "delete", "update", "detail"};
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        
        searchBar = new SearchBar(new String[] {"Tất cả", "Mã ĐG", "Tên ĐG", "Số điện thoại", "Loại ĐG"});
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText().trim();
                loadDG(dgbll.searchDocGia(text, type));
            }
        });
        
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã độc giả", "Tên độc giả", "Số điện thoại", "Địa chỉ", "Loại độc giả"};
        tblModel = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        tblDG = new JTable();
        tblDG.setFillsViewportHeight(true);      
        tblDG.setModel(tblModel);
        
        //Lấy header của bảng.
        JTableHeader headers = tblDG.getTableHeader();
        headers.setReorderingAllowed(false);//Không cho thay đổi thứ tự các cột.
        headers.setResizingAllowed(false);// Không cho thay đổi kích thước cột.
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        //Lấy thông tin mô hình cột của bảng.
        TableColumnModel columns = tblDG.getColumnModel();
        columns.getColumn(0).setPreferredWidth(150);
        columns.getColumn(1).setPreferredWidth(200);
        columns.getColumn(2).setPreferredWidth(200);
        columns.getColumn(3).setPreferredWidth(200);
        columns.getColumn(4).setPreferredWidth(150);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tblDG.getColumnCount(); i++){
            tblDG.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(tblDG);
        tblPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(tblPanel, BorderLayout.CENTER);
        
    }
    
    public void loadDG(ArrayList<DocGia> dsdg){
        tblModel.setRowCount(0);
        for(DocGia dg : dsdg){
            if(dg.getTrangThai() != 0 ){   
                tblModel.addRow(new Object[]{
                    dg.getMaDocGia(), 
                    dg.getTenDocGia(), 
                    dg.getSoDienThoai(), 
                    dg.getDiaChi(), 
                    ldgbll.getTenLoaiDGByMa(dg.getMaLoaiDG())
                });
            }
        }
    }

    public DSDocGiaBLL getDSDocGiaBLL(){ 
        return dgbll;
    }
    
    public DSLoaiDocGiaBLL getLoaiDGBLL(){
        return ldgbll;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        String text = searchBar.getTxtSearch().getText().trim();
        String type = (String)searchBar.getCboChoose().getSelectedItem();
        loadDG(dgbll.searchDocGia(text, type));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("create")){            
            dgDialog = new DocGiaDialog(parent, "Thêm độc giả", "create", this);
            dgDialog.setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("update")){
            int row=tblDG.getSelectedRow();
            if(row==-1 ){ 
                JOptionPane.showMessageDialog(this, "Vui lòng chọn độc giả");
            }else{ 
                String ma=(String) tblDG.getValueAt(row, 0);
                dgDialog = new DocGiaDialog(parent, "Cập nhật độc giả", "update", dgbll.getDocGiaByMA(ma), this);
                dgDialog.setVisible(true);
            }
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete")){
            int row = tblDG.getSelectedRow();
            if(row ==  -1 ){ 
                JOptionPane.showMessageDialog(this, "Vui lòng chọn độc giả");
            }else{ 
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá");
                if(confirm == JOptionPane.YES_OPTION){ 
                    String ma=(String) tblDG.getValueAt(row, 0);
                    dgbll.xoaDG(ma);
                    loadDG(dgbll.layDSDocGia());
                }
            }
        }else if(e.getSource() == mainFunc.getLstBtn().get("detail")){
            int row = tblDG.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn độc giả");
            }else{
                String ma=(String) tblDG.getValueAt(row, 0);
                ctDialog = new ChiTietDocGiaDialog(parent, dgbll.getDocGiaByMA(ma),this);
                ctDialog.setVisible(true);
            }
        }else if(e.getSource() == searchBar.getBtnRefesh()){            
            searchBar.getCboChoose().setSelectedIndex(0);
            searchBar.getTxtSearch().setText("");
            loadDG(dgbll.layDSDocGia());
        }
    }   
    

    @Override
    public void mousePressed(MouseEvent e) {
        
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
    
}
