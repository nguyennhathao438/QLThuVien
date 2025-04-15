/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSDocGiaBLL;
import Model.DocGia;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.ChiTietDocGiaDialog;
import UI.Dialog.DocGiaDialog;
import UI.Dialog.DocGiaDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class DocGiaPanel extends JPanel{
    private JPanel headerPanel, tblPanel;
    private DefaultTableModel tblModel;
    private JTable tblDG;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private ArrayList<DocGia> dsDG;
    public DocGiaPanel(){
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        initComponent();
        loadDG();
        initEvents();
    }
    
    public void initComponent(){
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0,0,4));
        headerPanel.setBackground(Color.WHITE);
        mainFunc = new MainFunction(new String[] {"create", "delete", "update", "detail"});
        headerPanel.add(mainFunc);
        
        searchBar = new SearchBar(new String[] {"Tất cả", "Mã ĐG", "Tên ĐG", "Số điện thoại", "Loại độc giả"});
        headerPanel.add(searchBar);

        this.add(headerPanel, BorderLayout.NORTH);
        
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã độc giả", "Tên độc giả", "Số điện thoại", "Địa chỉ", "Loại độc giả"};
        tblModel = new DefaultTableModel(header, 0);

        tblDG = new JTable();    
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
    
    public void initEvents(){
        // Xử lý sự kiện cho nút "Thêm"
        mainFunc.getLstBtn().get("create").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Lấy cửa sổ (JFrame) chứa NhaCungCapPanel, đảm bảo dialog được hiển thị đúng trên cửa sổ cha.
                //true (modal):dialog sẽ ở chế độ modal,phải đóng dialog trước khi thao tác với cửa sổ chính.
                DocGiaDialog dialog = new DocGiaDialog((JFrame) SwingUtilities.getWindowAncestor(DocGiaPanel.this), true);
                dialog.setVisible(true);
//                if (dialog.isConfirmed()) { //Kiểm tra xem người dùng có nhấn nút xác nhận (OK) hay không.
//                    tblModel.addRow(new Object[]{
//                        dialog.getMaDG(),
//                        dialog.getTenDG(),
//                        dialog.getSoDienThoai(),
//                        dialog.getDiaChi(),
//                        dialog.getTenLDG(),
//                            
//                   });
//                }
            }
        });
        
        //xu ly su kien nut "sửa"
        mainFunc.getLstBtn().get("update").addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DocGiaDialog dialog = new DocGiaDialog((JFrame) SwingUtilities.getWindowAncestor(DocGiaPanel.this), true);
                dialog.setVisible(true);
            }
        });

        
         //xu ly su kien nut "chi tiet"
        mainFunc.getLstBtn().get("detail").addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ChiTietDocGiaDialog dialog = new ChiTietDocGiaDialog((JFrame) SwingUtilities.getWindowAncestor(DocGiaPanel.this), true);
                dialog.setVisible(true);
            }
        });

    }
    
    public void loadDG(){
        DSDocGiaBLL dgbll = new DSDocGiaBLL();
        if(DSDocGiaBLL.dsdg == null){
            dgbll.docDSDG();
        }
        tblModel.setRowCount(0);
        if(DSDocGiaBLL.dsdg != null){
            for(DocGia dg : DSDocGiaBLL.dsdg){
                tblModel.addRow(new Object[]{dg.getMaDocGia(), dg.getTenDocGia(), dg.getSoDienThoai(), dg.getDiaChi(), dg.getMaLoaiDG()});
            }
            tblModel.fireTableDataChanged();
        }else{
            
            JOptionPane.showMessageDialog(this,"dl null");     
        }
    }
    
}
