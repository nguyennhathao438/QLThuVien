/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;

/**
 *
 * @author NGOC TUYEN
 */
import BLL.DSLoaiDocGiaBLL;
import Model.DocGia;
import UI.Panel.DocGiaPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DocGiaDialog extends JDialog implements MouseListener, ItemListener{
    private JTextField txtMaDG, txtTenDG, txtSDT, txtDiaChi;
    private JComboBox cboLoaiDG;
    private JButton btnSave, btnUpdate, btnCancel;
    private JPanel panelInput, panelBTN;
    private DocGiaPanel pn;
    private DocGia dgModel;
    //private DSLoaiDocGiaBLL ldgbll = new DSLoaiDocGiaBLL();
    private LoaiDGDialog dialog = null;
    private JFrame parent;
    private ArrayList<String> dsTenLoai;
    public DocGiaDialog(JFrame parent, String title, String type, DocGiaPanel pn){
        super(parent, true);
        this.pn = pn;
        this.initComponents(type, title);
        this.setLocationRelativeTo(parent);
    }

    public DocGiaDialog(JFrame parent, String title, String type, DocGia dgModel, DocGiaPanel pn){
        super(parent, true);
        this.pn = pn;
        this.dgModel = dgModel;
        this.initComponents(type, title);
        this.setLocationRelativeTo(parent);
    }
    
    public void initComponents(String type, String title){
        this.setSize(500, 400);
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        // Panel nhập thông tin độc giả
        panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(6, 1, 2, 2));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.setBackground(Color.WHITE);

        txtMaDG = txtInfor("Mã độc giả");
        panelInput.add(txtMaDG);       
        txtTenDG = txtInfor("Tên độc giả");
        panelInput.add(txtTenDG);       
        txtSDT = txtInfor("Số điện thoại");
        panelInput.add(txtSDT);
        txtDiaChi = txtInfor("Địa chỉ");
        panelInput.add(txtDiaChi);

        dsTenLoai = pn.getLoaiDGBLL().getdsTenLDG();
        dsTenLoai.add("Khác");
        cboLoaiDG = cbochoose("Loại độc giả", dsTenLoai.toArray(new String[0]));
        cboLoaiDG.addItemListener(this);
        panelInput.add(cboLoaiDG);

        // Panel chứa nút
        panelBTN = new JPanel();
        panelBTN.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
        panelBTN.setBackground(Color.WHITE);

        btnSave = btnFunc("Lưu", "#4ec85a");
        btnSave.addMouseListener(this);
        btnUpdate = btnFunc("Cập nhật", "#4ec85a");
        btnUpdate.addMouseListener(this);
        btnCancel = btnFunc("Hủy", "#db4444");
        btnCancel.addMouseListener(this);

        panelInput.add(panelBTN);
        this.add(panelInput);
        
        switch (type) {
            case "create":
                panelBTN.add(btnSave);
                panelBTN.add(btnCancel);
                break;
            case "update":    
                panelBTN.add(btnUpdate);
                panelBTN.add(btnCancel);
                txtMaDG.setText(dgModel.getMaDocGia());
                txtMaDG.setText(dgModel.getMaDocGia());
                txtTenDG.setText(dgModel.getTenDocGia());
                txtSDT.setText(dgModel.getSoDienThoai());
                txtDiaChi.setText(dgModel.getDiaChi());
                cboLoaiDG.setSelectedItem(pn.getLoaiDGBLL().getTenLoaiDGByMa(dgModel.getMaLoaiDG())); // chuyển mã sang tên
                // Khóa Mã độc giả nếu không muốn chỉnh sửa
                txtMaDG.setEnabled(false);
                break;                         
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        String selected = (String) cboLoaiDG.getSelectedItem();
        if("Khác".equals(selected)){
            dialog = new LoaiDGDialog(parent, this);
            dialog.setVisible(true);
        }
    }
    
    public DSLoaiDocGiaBLL getLoaiDGBLL(){
        return pn.getLoaiDGBLL();
    }
    
    public void capNhatCboLoaiDG(String newLoai){
        dsTenLoai = pn.getLoaiDGBLL().getdsTenLDG();
        dsTenLoai.add("Khác");
        cboLoaiDG.setModel(new DefaultComboBoxModel<>(dsTenLoai.toArray(new String[0])));
        if(newLoai.isEmpty()){
            cboLoaiDG.setSelectedIndex(0);
        }else{
            cboLoaiDG.setSelectedItem(newLoai); // chọn lại newLoai sau khi thêm xong       
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == btnCancel){
            dispose();
        }
        
        if(e.getSource() == btnSave){
            DocGia dg = new DocGia();
                dg.setMaDocGia(txtMaDG.getText());
                dg.setTenDocGia(txtTenDG.getText());
                dg.setSoDienThoai(txtSDT.getText());
                dg.setDiaChi(txtDiaChi.getText());
                dg.setMaLoaiDG(pn.getLoaiDGBLL().getMaLoaiDGByTen(cboLoaiDG.getSelectedItem().toString()));
                dg.setTrangThai(1);
                if(pn.getDSDocGiaBLL().themDG(dg) == 1){
                    dispose();
                    pn.loadDG(pn.getDSDocGiaBLL().layDSDocGia());      
                }
        }
        
        if(e.getSource() == btnUpdate){
            DocGia dg = new DocGia();
                dg.setMaDocGia(txtMaDG.getText());
                dg.setTenDocGia(txtTenDG.getText());
                dg.setSoDienThoai(txtSDT.getText());
                dg.setDiaChi(txtDiaChi.getText());
                dg.setMaLoaiDG(pn.getLoaiDGBLL().getMaLoaiDGByTen(cboLoaiDG.getSelectedItem().toString()));
                dg.setTrangThai(1);
                if(pn.getDSDocGiaBLL().suaDG(dg) == 1){
                    dispose();
                    pn.loadDG(pn.getDSDocGiaBLL().layDSDocGia());
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

    public JTextField txtInfor(String title){
        JTextField infor = new JTextField();
        infor.setBorder(BorderFactory.createTitledBorder(title));  
        return infor;
    }

    public JButton btnFunc(String title, String color){
        JButton btn = new JButton(title);
        btn.setPreferredSize(new Dimension(150,30));
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