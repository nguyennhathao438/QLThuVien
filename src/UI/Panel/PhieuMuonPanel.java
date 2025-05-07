/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSDocGiaBLL;
import BLL.DSPhieuMuon;
import BLL.DSThuThuBLL;
import Model.PhieuMuon;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.ChiTietPhieuMuonDialog;
import UI.Dialog.PhieuMuonDialog;
import UI.Dialog.lapPhieuTra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGOC TUYEN
 */
public class PhieuMuonPanel extends JPanel implements ItemListener, MouseListener{
    private JPanel headerPanel, tblPanel;
    private DefaultTableModel tblModel;
    private JTable tblPhMuon;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DSPhieuMuon pmbll = new DSPhieuMuon();
    DSThuThuBLL ttbll = new DSThuThuBLL();
    DSDocGiaBLL dgbll = new DSDocGiaBLL();
    private JFrame parent;
    
    public PhieuMuonPanel(){
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0,0));
        this.setBackground(Color.WHITE);
        initcomponent();
        loadData(pmbll.layAllPhieuMuon());
    }
        
    public void initcomponent(){
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900,120));
        headerPanel.setLayout(new FlowLayout(0,0,4));
        headerPanel.setBackground(Color.WHITE);
        
        String[] function = {"create", "delete", "detail","return"};
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);

        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        
        searchBar = new SearchBar(new String[] {"Tất cả", "Mã phiếu mượn", "Ngày mượn", "Ngày trả", "Tên đọc giả", "Tên thủ thư", "Trạng thái"});
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText();
                loadData(pmbll.searchPM(text, type));
            }
        });
        
        
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã phiếu", "Ngày mượn", "Ngày trả", "Tên độc giả", "Tên thủ thư","Trạng Thái"};
        tblModel = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        tblPhMuon = new JTable();
        tblPhMuon.setFillsViewportHeight(true);
        tblPhMuon.setModel(tblModel);
        
        //Lấy header của bảng.
        JTableHeader headers = tblPhMuon.getTableHeader();
        headers.setReorderingAllowed(false);//Không cho thay đổi thứ tự các cột.
        headers.setResizingAllowed(false);// Không cho thay đổi kích thước cột.
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        //Lấy thông tin mô hình cột của bảng.
        TableColumnModel columns = tblPhMuon.getColumnModel();
        columns.getColumn(0).setPreferredWidth(150);
        columns.getColumn(1).setPreferredWidth(175);
        columns.getColumn(2).setPreferredWidth(175);
        columns.getColumn(3).setPreferredWidth(150);
        columns.getColumn(4).setPreferredWidth(150);
        columns.getColumn(4).setPreferredWidth(100);
         
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tblPhMuon.getColumnCount(); i++){
            tblPhMuon.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        scrollPane = new JScrollPane(tblPhMuon);
        tblPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(tblPanel, BorderLayout.CENTER);
        
    }
    public void loadData(ArrayList<PhieuMuon> dspm){ 
        tblModel.setRowCount(0);   
        for(PhieuMuon a:dspm){
            if(a.getTrangThai()!= 0)
                tblModel.addRow(new Object[]{
                    a.getMaPhieuMuon(),
                    a.getNgayMuon().format(formatTime),
                    a.getNgayTra().format(formatTime),
                    dgbll.getTenDocGiabyMa(a.getMaDocGia()),
                    ttbll.getTenThuThuByMa(a.getMaThuThu()),
                    pmbll.getTrangThaiPM(a.getTrangThai())
            });              
        }
    }

    public DSPhieuMuon getDSPMuonBLL(){ 
        return pmbll;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String)searchBar.getCboChoose().getSelectedItem();
        String text = searchBar.getTxtSearch().getText().trim();
        loadData(pmbll.searchPM(text, type));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("return")){
            int row = tblPhMuon.getSelectedRow();
                if(row == -1 ){
                    JOptionPane.showMessageDialog(null,  "Click vào phiếu mươn");
                }else { 
                    String trangThai = (String) tblPhMuon.getValueAt(row, 5);
                    if(trangThai.equals("Đã trả")){ 
                        JOptionPane.showMessageDialog(null,  "Phiếu mượn này đã trả đủ");
                    }else{ 
                        String mapm = (String) tblPhMuon.getValueAt(row, 0);
                        String ma=(String) tblPhMuon.getValueAt(row, 0);
                        new lapPhieuTra(parent,true,ma).setVisible(true);
                    }
                    
                }
        } else if(e.getSource() == searchBar.getBtnRefesh()){            
            searchBar.getCboChoose().setSelectedIndex(0);
            searchBar.getTxtSearch().setText("");
            loadData(pmbll.layAllPhieuMuon());
        } else if(e.getSource() == mainFunc.getLstBtn().get("detail")){
            int row = tblPhMuon.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(null,  "Vui lòng chọn phiếu mượn");
            } else{
                String ma = (String) tblPhMuon.getValueAt(row, 0);
                ChiTietPhieuMuonDialog dialog = new ChiTietPhieuMuonDialog(parent, pmbll.getPMuonByMa(ma));
                dialog.setVisible(true);
            }
        } else if(e.getSource() == mainFunc.getLstBtn().get("create")){
            PhieuMuonDialog dialog = new PhieuMuonDialog(parent, this);
            dialog.setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete")){
            int index = tblPhMuon.getSelectedRow();
            if(index != -1){
                String maPM = tblPhMuon.getValueAt(index, 0).toString();
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu nhập không?");
                if(input == JOptionPane.OK_OPTION){
                    if(pmbll.xoaPM(maPM)){
                        JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công!");
                        loadData(pmbll.layAllPhieuMuon());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thất bại!");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(parent, "Chọn phiếu mượn cần xóa");
            }
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
