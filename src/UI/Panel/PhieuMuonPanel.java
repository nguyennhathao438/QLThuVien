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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGOC TUYEN
 */
public class PhieuMuonPanel extends JPanel{
    private JPanel headerPanel, tblPanel;
    private DefaultTableModel tblModel;
    private JTable tblPhMuon;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    DSPhieuMuon pmbll = new DSPhieuMuon();
    DSThuThuBLL ttbll = new DSThuThuBLL();
    DSDocGiaBLL dgbll = new DSDocGiaBLL();
    public PhieuMuonPanel(){
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0,0));
        this.setBackground(Color.WHITE);
        initcomponent();
        initEvents();
        loadData(pmbll.layAllPhieuMuon());
    }
        
    public void initcomponent(){
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900,120));
        headerPanel.setLayout(new FlowLayout(0,0,4));
        headerPanel.setBackground(Color.WHITE);
        mainFunc = new MainFunction(new String[] {"create", "delete", "update", "detail","return"});
        headerPanel.add(mainFunc);
        
        searchBar = new SearchBar(new String[] {"Tất cả", "Mã phiếu mượn", "Ngày mượn", "Tên đọc giả", "Tên thủ thư"});
        headerPanel.add(searchBar);

        this.add(headerPanel, BorderLayout.NORTH);
        
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã phiếu", "Ngày mượn", "Ngày trả", "Tên độc giả", "Tên thủ thư","Trạng Thái"};
        tblModel = new DefaultTableModel(header, 0);
        tblPhMuon = new JTable();
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
                 a.getNgayMuon(),
                 a.getNgayTra(),
                 dgbll.getTenDocGiabyMa(a.getMaDocGia()),
                 ttbll.getTenThuThuByMa(a.getMaThuThu()),
                 getTrangThaiPM(a.getTrangThai())
            });              
        }
    }
    public String getTrangThaiPM(int trangThai){ 
        if(trangThai == 1){ 
            return "Chưa trả";
        }
        if(trangThai == 2){ 
            return "Đã trả";
        }
        return "";
    }
    public void initEvents(){
        // Xử lý sự kiện cho nút "Thêm"
        mainFunc.getLstBtn().get("create").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Lấy cửa sổ (JFrame) chứa NhaCungCapPanel, đảm bảo dialog được hiển thị đúng trên cửa sổ cha.
                //true (modal):dialog sẽ ở chế độ modal,phải đóng dialog trước khi thao tác với cửa sổ chính.
                PhieuMuonDialog dialog = new PhieuMuonDialog((JFrame) SwingUtilities.getWindowAncestor(PhieuMuonPanel.this), true);
                dialog.setVisible(true);
//                if (dialog.isConfirmed()) { //Kiểm tra xem người dùng có nhấn nút xác nhận (OK) hay không.
//                    tableModel.addRow(new Object[]{
//                        dialog.getMaNCC(),
//                        dialog.getTenNCC(),
//                        dialog.getSoDienThoai()
//                   });
//                }
            }
        });
        
        // Xử lý sự kiện cho nút "Sửa"
        mainFunc.getLstBtn().get("update").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Lấy cửa sổ (JFrame) chứa NhaCungCapPanel, đảm bảo dialog được hiển thị đúng trên cửa sổ cha.
                //true (modal):dialog sẽ ở chế độ modal,phải đóng dialog trước khi thao tác với cửa sổ chính.
                PhieuMuonDialog dialog = new PhieuMuonDialog((JFrame) SwingUtilities.getWindowAncestor(PhieuMuonPanel.this), true);
                dialog.setVisible(true);
            }
        }); 
        
        mainFunc.getLstBtn().get("detail").addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ChiTietPhieuMuonDialog dialog = new ChiTietPhieuMuonDialog((JFrame) SwingUtilities.getWindowAncestor(PhieuMuonPanel.this), true);
                dialog.setVisible(true);
            }
        });
        //Sự kiện thêm phiếu trả của Hào
        mainFunc.getLstBtn().get("return").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblPhMuon.getSelectedRow();
                if(row == -1 ){
                    JOptionPane.showMessageDialog(null,  "Click vào phiếu mươn");
                }else { 
                    String trangThai = (String) tblPhMuon.getValueAt(row, 5);
                    if(trangThai.equals("Đã trả")){ 
                        JOptionPane.showMessageDialog(null,  "Phiếu mượn này đã trả đủ");
                    }else{ 
                        String mapm = (String) tblPhMuon.getValueAt(row, 0);
                        Window parentWindow = SwingUtilities.getWindowAncestor(PhieuMuonPanel.this);
                            String ma=(String) tblPhMuon.getValueAt(row, 0);
                        new lapPhieuTra((Frame) parentWindow,true,ma).setVisible(true);
                    }
                    
                }
            }
        }); 
    }
    
}
