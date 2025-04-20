/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;

/**
 *
 * @author Nghia0605
 */
import BLL.DSNhaCungCapBLL;
import BLL.DSPhieuNhap;
import BLL.DSSachBLL;
import BLL.DSTacGiaBLL;
import BLL.DSThuThuBLL;
import DAL.CTPhieuNhapDAL;
import MODEL.CTPhieuNhap;
import MODEL.PhieuNhap;
import Model.Sach;
import UI.Component.InputField;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class CTPhieuNhapDialog extends JDialog {

    private InputField txtMaPhieu, txtThuThu, txtNhaCungCap, txtNgayTao;
    private JTable table;
    private DefaultTableModel ctTableModel;
    private JScrollPane scrollPane;
    private PhieuNhap phieunhap;
    private DateTimeFormatter formattime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ArrayList<CTPhieuNhap> dsCTPN;
    private DSPhieuNhap pnBLL = new DSPhieuNhap();
    private DSTacGiaBLL tacGiaBLL = new DSTacGiaBLL();
    private DSSachBLL sachBLL = new DSSachBLL();
    
    public CTPhieuNhapDialog(Frame parent, PhieuNhap pn) {
        super(parent, "Chi Tiết Phiếu Nhập", true);
        this.phieunhap = pn;
        init();
        setSize(800, 500);
        setLocationRelativeTo(parent);
        dsCTPN = pnBLL.getCTPN(pn);
        loadTableCTPN(dsCTPN, DSSachBLL.getDsSach());
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        // Phần tiêu đề
        JLabel lblTitle = new JLabel("THÔNG TIN PHIẾU NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(0, 123, 255));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setPreferredSize(new Dimension(800, 80));
        add(lblTitle, BorderLayout.NORTH);

        // Panel chứa các ô nhập liệu
        JPanel panelInfo = new JPanel(new FlowLayout(0, 7, 10));
        panelInfo.setPreferredSize(new Dimension(780, 120));
        panelInfo.setBackground(Color.white);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));

        txtMaPhieu = new InputField("Mã phiếu", 100, 60);
        txtMaPhieu.getTxtInput().setEditable(false);
        txtMaPhieu.getTxtInput().setText(phieunhap.getMaPhieuNhap());
        
        txtThuThu = new InputField("Thủ thư", 200, 60);
        txtThuThu.getTxtInput().setEditable(false);
        txtThuThu.getTxtInput().setText(DSThuThuBLL.getTenThuThuByMa(phieunhap.getMaThuThu()));
        
        txtNhaCungCap = new InputField("Nhà cung cấp", 210, 60);
        txtNhaCungCap.getTxtInput().setEditable(false);
        txtNhaCungCap.getTxtInput().setText(DSNhaCungCapBLL.getTenNCCByMa(phieunhap.getMaNCC()));
        
        txtNgayTao = new InputField("Ngày tạo phiếu", 190, 60);
        txtNgayTao.getTxtInput().setEditable(false);
        txtNgayTao.getTxtInput().setText(phieunhap.getThoiGian().format(formattime));
        
        panelInfo.add(txtMaPhieu);
        panelInfo.add(txtThuThu);
        panelInfo.add(txtNhaCungCap);
        panelInfo.add(txtNgayTao);

        add(panelInfo, BorderLayout.CENTER);

        // Bảng chi tiết sách
        String[] columnNames = {"Mã sách", "Tên sách", "Tên tác giả", "Tên thể loại", "Năm XB", "Số lượng", "Đơn giá"};
        ctTableModel = new DefaultTableModel(columnNames, 0){
             @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        table = new JTable();
        table.setFillsViewportHeight(true);      
        table.setModel(ctTableModel);
        
        JTableHeader headers = table.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = table.getColumnModel();
        column.getColumn(0).setPreferredWidth(70);
        column.getColumn(1).setPreferredWidth(175);
        column.getColumn(2).setPreferredWidth(150);
        column.getColumn(3).setPreferredWidth(150);
        column.getColumn(4).setPreferredWidth(70);
        column.getColumn(5).setPreferredWidth(80);
        column.getColumn(6).setPreferredWidth(95);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        JPanel pnTable = new JPanel();
        pnTable.setBackground(Color.white);
        pnTable.setPreferredSize(new Dimension(780, 300));        
        pnTable.setLayout(new BorderLayout(0,0));
        pnTable.setBorder(BorderFactory.createEmptyBorder(0, 10, 7, 10));        
        
        scrollPane = new JScrollPane(table);        
        pnTable.add(scrollPane);
        
        add(pnTable, BorderLayout.SOUTH);
    }
    
    
    private void loadTableCTPN(ArrayList<CTPhieuNhap> dsCTPN,ArrayList<Sach> dsSach){
        ctTableModel.setRowCount(0);
        for(CTPhieuNhap ctpn : dsCTPN){
            for(Sach sach : dsSach){
                if(ctpn.getMaSach().equals(sach.getMaSach())){
                    ctTableModel.addRow(new Object[]{
                        sach.getMaSach(),
                        sach.getTenSach(),
                        sach.getMaTheLoai(),
                        tacGiaBLL.getTenTacGiabyMa(sach.getMaTacGia()),
                        sach.getNamXuatBan(),
                        ctpn.getSoLuong(),
                        ctpn.getDonGia()
                    });
                }
            }
        }
    }

}
