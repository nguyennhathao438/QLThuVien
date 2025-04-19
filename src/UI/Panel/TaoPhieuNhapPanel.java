/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSNhaCungCapBLL;
import BLL.DSPhieuNhap;
import BLL.DSSachBLL;
import BLL.DSTacGiaBLL;
import BLL.DSThuThuBLL;
import MODEL.CTPhieuNhap;
import MODEL.PhieuNhap;
import Model.Sach;
import UI.Component.InputField;
import UI.Component.PlaceHolderInput;
import UI.Component.SelectInput;
import UI.Component.Validation;
import UI.MainFrame;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nghia0605
 */
public class TaoPhieuNhapPanel extends JPanel{
    private JPanel left,right,pnPhieuTable,pnSachTable;
    private JPanel pnInput,pn1,pn2,pn2_1, pn2_2,pn2_3, pn2_4;
    private InputField masach,tensach,soluong,gianhap,tacgia,theloai, lbTotal;
    private JTable phieuTable, sachTable;
    private JScrollPane scrollPNTable, scrollSachTable;
    private DefaultTableModel phieuTableModel, sachTableModel;
    private JButton btnAdd,btnNhapHang,btnUpdate, btnDel;
    private InputField txtMaPhieu,txtThuThu;
    private PlaceHolderInput txtSearch;
    private SelectInput cboNCC;
    MainFrame m;    
    private JPanel pnBtnAdd;
    private JPanel pnBtnDelAndUpdate;
    private ArrayList<CTPhieuNhap> dsCTPN;
    private DSSachBLL sachBLL = new DSSachBLL();
    private DSTacGiaBLL tacgiaBLL = new DSTacGiaBLL();
    private static boolean mark = false;
    private static double money = 0;
    private DSPhieuNhap pnBLL = new DSPhieuNhap();
    public TaoPhieuNhapPanel(MainFrame m){
//        this.setBackground(Color.decode("#ccc"));
        this.m = m;
        this.dsCTPN = new ArrayList<>();
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout(5,5));
        initComponent();
    }
    
    
    public void initComponent(){
        left = new JPanel();
        left.setBackground(Color.white);
        left.setPreferredSize(new Dimension(670,800));
        left.setLayout(new BorderLayout(5,0));
        left.setBorder(new EmptyBorder(10, 5, 0, 5));
        
        pnInput = new JPanel();
        pnInput.setPreferredSize(new Dimension(630, 600));
        pnInput.setLayout(new BorderLayout(0, 5));
        pnInput.setBackground(Color.white);
        pnInput.setBorder(new EmptyBorder(0, 0, 5, 0));
        left.add(pnInput, BorderLayout.CENTER);
        
        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout(2, 7));
        pn1.setBackground(Color.white);
        pn1.setPreferredSize(new Dimension(380, 600));        
        pnInput.add(pn1, BorderLayout.CENTER);
        
        JPanel pnSearch = new JPanel();
        pnSearch.setPreferredSize(new Dimension(370, 100));
        pnSearch.setBorder(new EmptyBorder(0, 5, 0, 0));            
        txtSearch = new PlaceHolderInput("Nhập mã hoặc tên sách...");
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                String text = txtSearch.getText().trim();
                loadDataTblSach(sachBLL.searchByMaAndName(text));
                
            }
        });
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
        pnSachTable.setPreferredSize(new Dimension(380, 490));
        pnSachTable.setLayout(new BorderLayout(0,0));
//        pnSachTable.setBackground(Color.white);
        pn1.add(pnSachTable, BorderLayout.CENTER);
        
        sachTable = new JTable();
        sachTable.setFillsViewportHeight(true);      
        sachTable.setModel(sachTableModel);
        sachTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                tableSachClicked(e);
            }
        });
        TableColumnModel column = sachTable.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(220);
        column.getColumn(2).setPreferredWidth(80);
        
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
        loadDataTblSach(DSSachBLL.getDsSach());

        pn2 = new JPanel();
        pn2.setBackground(Color.white);
        pn2.setPreferredSize(new Dimension(280, 500));   
        pnInput.add(pn2, BorderLayout.EAST);
        
        pn2_1 = new JPanel();
        pn2_1.setLayout(new FlowLayout());
        pn2_1.setBackground(Color.white);
        pn2_1.setPreferredSize(new Dimension(280, 60));
        pn2.add(pn2_1);
        
        masach = new InputField("Mã sách", 90, 60);
//        masach.getTxtInput().setText("SACH001");
        masach.getTxtInput().setEditable(false);
        masach.getTxtInput().setForeground(Color.BLACK);
        pn2_1.add(masach);
        
        tensach = new InputField("Tên sách", 180, 60);
        tensach.getTxtInput().setEditable(false);
        tensach.getTxtInput().setForeground(Color.BLACK);
        pn2_1.add(tensach);
        
        pn2_2 = new JPanel();
        pn2_2.setLayout(new FlowLayout());
        pn2_2.setBackground(Color.white);
        pn2_2.setPreferredSize(new Dimension(280, 60));
        pn2.add(pn2_2);
        
        theloai = new InputField("Thể loại", 270, 60);
        theloai.getTxtInput().setEditable(false);
        pn2_2.add(theloai);
                     
        pn2_4 = new JPanel();
        pn2_4.setLayout(new FlowLayout());
        pn2_4.setBackground(Color.white);
        pn2_4.setPreferredSize(new Dimension(280, 60));
        pn2.add(pn2_4);
        
        tacgia = new InputField("Tên tác giả", 270, 60);
        tacgia.getTxtInput().setEditable(false);
        pn2_4.add(tacgia);
        
        pn2_3 = new JPanel();
        pn2_3.setLayout(new FlowLayout());
        pn2_3.setBackground(Color.white);
        pn2_3.setPreferredSize(new Dimension(280, 60));
        pn2.add(pn2_3);
        
        gianhap = new InputField("Giá nhập", 180, 60);
        pn2_3.add(gianhap);
        
        soluong = new InputField("Số lượng", 90, 60);
        pn2_3.add(soluong);
        
        pnBtnAdd = new JPanel();
        pnBtnAdd.setLayout(new FlowLayout(1,0,0));
        pnBtnAdd.setBackground(Color.white);
        pnBtnAdd.setBorder(new EmptyBorder(10, 0, 0, 0));
        pn2.add(pnBtnAdd);
        
        btnAdd = new JButton("Thêm sản phẩm");
        btnAdd.setBackground(Color.decode("#00994C"));    
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAdd.setForeground(Color.white);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorder(null);
        btnAdd.setPreferredSize(new Dimension(150, 35));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                BtnAddClicked(e);
            }
        });
        pnBtnAdd.add(btnAdd);
        
        pnBtnDelAndUpdate = new JPanel();
        pnBtnDelAndUpdate.setLayout(new FlowLayout(0,10,0));
        pnBtnDelAndUpdate.setBackground(Color.white);
        pnBtnDelAndUpdate.setBorder(new EmptyBorder(10, 0, 0, 0));
        pn2.add(pnBtnDelAndUpdate);
        
        btnUpdate = new JButton("Sửa");
        btnUpdate.setBackground(Color.decode("#00994C"));    
        btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnUpdate.setForeground(Color.white);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorder(null);
        btnUpdate.setPreferredSize(new Dimension(130, 35));
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                btnUpdateClicked(e);
            }
        });
        pnBtnDelAndUpdate.add(btnUpdate);
        
        btnDel = new JButton("Xóa");
        btnDel.setBackground(Color.decode("#00994C"));    
        btnDel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDel.setForeground(Color.white);
        btnDel.setFocusPainted(false);
        btnDel.setBorder(null);
        btnDel.setPreferredSize(new Dimension(130, 35));
        btnDel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                btnDeleteClicked(e);
            }
        });
        pnBtnDelAndUpdate.add(btnDel);
        
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
        phieuTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                tablePhieuClicked(e);
            }           
        });
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
        right.setBackground(Color.white);  
        right.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.LIGHT_GRAY));
        right.setPreferredSize(new Dimension(220, 800));        
        
        JPanel pnBack = new JPanel();
        pnBack.setBackground(Color.white);
        pnBack.setPreferredSize(new Dimension(210, 60));
        pnBack.setLayout(new FlowLayout(2,7,10));
        right.add(pnBack);

        FlatSVGIcon iconBack = new FlatSVGIcon(getClass().getResource("/img/back.svg")).derive(17, 17);
        JLabel lbBack = new JLabel("QUAY LẠI", iconBack, JLabel.CENTER);
        lbBack.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbBack.setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
        pnBack.add(lbBack);
        lbBack.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                resetStaticVariable();
                m.setRightPanel(new PhieuNhapPanel(m));
            }
        });
        
        txtMaPhieu = new InputField("Mã phiếu nhập", 210, 60);
        right.add(txtMaPhieu);
        
        txtThuThu = new InputField("Thủ thư", 210, 60);
        right.add(txtThuThu);
        
        String[] arrTenNCC = DSNhaCungCapBLL.getArrTenNCC();
        cboNCC = new SelectInput("Nhà cung cấp", arrTenNCC, 200, 60);
        right.add(cboNCC);
        
        lbTotal = new InputField("TỔNG TIỀN:", "0Đ",210, 50);
//        lbTotal.setBorder(BorderFactory.createLineBorder(Color.yellow));
        lbTotal.getLbContent().setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbTotal.getLbContent().setForeground(Color.red);
        lbTotal.getLbData().setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbTotal.getLbData().setForeground(Color.black);
        right.add(lbTotal);
        
        JPanel pnBtnNhapHang = new JPanel();
        pnBtnNhapHang.setPreferredSize(new Dimension(210, 50));
        pnBtnNhapHang.setBackground(Color.white);
        
        btnNhapHang = new JButton("Nhập hàng");
        btnNhapHang.setBackground(Color.decode("#00994C"));    
        btnNhapHang.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btnNhapHang.setForeground(Color.white);
        btnNhapHang.setFocusPainted(false);
        btnNhapHang.setBorder(null);
        btnNhapHang.setPreferredSize(new Dimension(210, 35));
        btnNhapHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNhapHang.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                btnNhapHangClicked(e);
            }
        });
        pnBtnNhapHang.add(btnNhapHang);
        right.add(pnBtnNhapHang);
        
        this.add(left, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
    }
    
        public void actBtn(String type) {
        boolean isAdd = type.equals("add");
        boolean isUpdate = type.equals("update");

        btnAdd.setEnabled(isAdd);
        btnUpdate.setEnabled(isUpdate);
        btnDel.setEnabled(isUpdate);
        
        btnAdd.setBackground(Color.lightGray);
        btnUpdate.setBackground(Color.lightGray);
        btnDel.setBackground(Color.lightGray);
        
        if (isAdd) {
            btnAdd.setBackground(Color.decode("#00994C")); 
        } else if (isUpdate) {
            btnUpdate.setBackground(Color.ORANGE);
            btnDel.setBackground(Color.RED);      
        }
    }

    public void loadDataTblSach(ArrayList<Sach> dsSach){
        sachTableModel.setRowCount(0);
        if(!dsSach.isEmpty()){
                for(Sach s : dsSach){
                sachTableModel.addRow(new Object[]{
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getSoLuong()

                });
            }
        }
    }
    
    
    private void tableSachClicked(MouseEvent e){
        int index = this.sachTable.getSelectedRow();
        if(index >= 0){
            resetForm();
            String ma = (String)sachTable.getValueAt(index, 0);
            actBtn("add");
            Sach s = DSSachBLL.getDsSach().get(sachBLL.getIndexbyMaSach(ma));            
            setForm(s);
        }
    }
    private void tablePhieuClicked(MouseEvent e){
        int index = this.phieuTable.getSelectedRow();
        if(index >= 0){
            actBtn("update");
            String ma = (String)sachTable.getValueAt(index, 0);            
            Sach s = DSSachBLL.getDsSach().get(sachBLL.getIndexbyMaSach(ma));            
            setForm(s);
            gianhap.getTxtInput().setText(String.valueOf(dsCTPN.get(index).getDonGia()));
            soluong.getTxtInput().setText(String.valueOf(dsCTPN.get(index).getSoLuong()));
        }
    }
    public boolean checkInfoNhapHang(){
        if(Validation.isEmpty(txtMaPhieu.getTxtInput().getText())){
            JOptionPane.showMessageDialog(null, "Nhập mã phiếu nhập trước khi thêm", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(!mark){
            boolean check = DSPhieuNhap.checkValidMaPN(txtMaPhieu.getTxtInput().getText());
            if(!check){
                return check;
            }
            txtMaPhieu.getTxtInput().setEditable(false);
            mark = true;
        }
        
        if(Validation.isEmpty(gianhap.getTxtInput().getText())){
            JOptionPane.showMessageDialog(null, "Giá nhập không được rỗng", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(Validation.isEmpty(soluong.getTxtInput().getText())){
            JOptionPane.showMessageDialog(null, "Số lượng không được rỗng", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!Validation.isNumber(gianhap.getTxtInput().getText())){
            JOptionPane.showMessageDialog(null, "Giá nhập phải là số!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!Validation.isNumber(soluong.getTxtInput().getText())){
            JOptionPane.showMessageDialog(null, "Số lượng phải là số!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }        
    public CTPhieuNhap getInfoCTPhieu(){
        String maPN = txtMaPhieu.getTxtInput().getText();
        String maSach = masach.getTxtInput().getText();
        int quantity = Integer.valueOf(soluong.getTxtInput().getText());
        double dongia = Double.valueOf(gianhap.getTxtInput().getText());
        return new CTPhieuNhap(maPN, maSach, quantity, dongia);
    }
    private void loadTableCTPN(ArrayList<CTPhieuNhap> dsCT){
        phieuTableModel.setRowCount(0);
        for(CTPhieuNhap ctpn : dsCT){
//            double thanhtien = ctpn.getThanhTien();
//            updateTotal(thanhtien);
            phieuTableModel.addRow(new Object[]{
                ctpn.getMaSach(),
                sachBLL.getSachbyMa(ctpn.getMaSach()),
                ctpn.getDonGia(),
                ctpn.getSoLuong(),
                ctpn.getThanhTien()
            });
        }
    }
    private void resetStaticVariable(){
        money = 0;
        mark = false;
    }
    private void setForm(Sach s){
        masach.getTxtInput().setText(s.getMaSach());
        tensach.getTxtInput().setText(s.getTenSach());
//      theloai.getTxtInput().setText(sachBLL.getTheLoaiByMa(s.getMaSach()));
        theloai.getTxtInput().setText(s.getMaTheLoai());
        tacgia.getTxtInput().setText(tacgiaBLL.getTenTacGiabyMa(s.getMaTacGia()));
    }
    private void updateTotal(double tien,String type){
        switch (type) {
            case "add":
                money += tien;
                break;
            case "delete":
                money -= tien;
                break;            
        }
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        formatter.setMinimumFractionDigits(0); // hien thi it nhat 22 chu so thap phan
        formatter.setMaximumFractionDigits(0); // hien thi toi da 2 chu so thap phan

        String formattedMoney = formatter.format(money);
        lbTotal.getLbData().setText(formattedMoney + "Đ");
    }
    private void resetForm(){
        masach.getTxtInput().setText("");
        tensach.getTxtInput().setText("");
        theloai.getTxtInput().setText("");
        tacgia.getTxtInput().setText("");
        gianhap.getTxtInput().setText("");
        soluong.getTxtInput().setText("");
    }
    private void BtnAddClicked(MouseEvent e){
        int index = sachTable.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(null, "Chọn sách cần nhập", "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(checkInfoNhapHang()){
            btnAdd.setEnabled(false);
            sachTable.clearSelection();
            CTPhieuNhap ctpn = getInfoCTPhieu();
            double thanhtien = ctpn.getThanhTien();
            updateTotal(thanhtien,"add");
            dsCTPN.add(ctpn);
            loadTableCTPN(dsCTPN);
            resetForm();
            Timer timer = new Timer(300, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    btnAdd.setEnabled(true); // Mở lại nút sau 300ms
                }
            });
            timer.setRepeats(false); // Chạy 1 lần duy nhất
            timer.start(); 
        }
    }     
    private void btnUpdateClicked(MouseEvent e){
        int index = phieuTable.getSelectedRow();
        if(index != -1){
            money -= dsCTPN.get(index).getThanhTien();
            CTPhieuNhap ctpn = getInfoCTPhieu();
            dsCTPN.set(index, ctpn);
            updateTotal(ctpn.getThanhTien(), "add");
            loadTableCTPN(dsCTPN);
            resetForm();
        }else{
            JOptionPane.showMessageDialog(null, "Chọn chi tiết phiếu cần sửa", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }    
    private void btnDeleteClicked(MouseEvent e){
        int index = phieuTable.getSelectedRow();
        if(index != -1){
            double thanhtien = dsCTPN.get(index).getThanhTien();
            updateTotal(thanhtien,"delete");
            dsCTPN.remove(index);
            phieuTableModel.removeRow(index);            
            resetForm();                        
            System.out.println(dsCTPN.size());
        }else{
            JOptionPane.showMessageDialog(null, "Chọn chi tiết phiếu cần xóa", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
    private void btnNhapHangClicked(MouseEvent e){
        if(dsCTPN.isEmpty()){
             JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào!", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        }else{
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu nhập!", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(input == JOptionPane.OK_OPTION){
                String maPNhap = txtMaPhieu.getTxtInput().getText();
                String maThuThu = DSThuThuBLL.getMaThuThuByTen(txtThuThu.getTxtInput().getText());
                LocalDateTime ngaytao = LocalDateTime.now();
                double tongtien = TaoPhieuNhapPanel.money;
                PhieuNhap pn = new PhieuNhap(maPNhap, ngaytao, tongtien, maPNhap, maThuThu);
                boolean result = pnBLL.add(pn, dsCTPN);
                if(result){
                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");                    
                    m.setRightPanel(new PhieuNhapPanel(m));
                } else {
                    JOptionPane.showMessageDialog(this, "Nhập hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
