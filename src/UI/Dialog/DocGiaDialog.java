/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;

/**
 *
 * @author NGOC TUYEN
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

public class DocGiaDialog extends JDialog{
    private JTextField txtMaDG, txtTenDG, txtSDT, txtDiaChi;
    private JComboBox cboLoaiDG;
    private JButton btnSave, btnCancel;
    private JPanel panelInput, panelBTN;
    private boolean confirmed = false;
    
    public DocGiaDialog(JFrame parent, boolean modal){
        super(parent,"Thông tin độc giả", modal);
        this.setSize(500, 400);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        initEvents();
    }

    public void initComponents(){
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

        String[] tenLDG = new String[]{"LDG001-Thường", "LDDG002-VIP", "LDG003-VVIP", "Khác"};
        cboLoaiDG = cbochoose("Loại độc giả", tenLDG);
        panelInput.add(cboLoaiDG);

        // Panel chứa nút
        panelBTN = new JPanel();
        panelBTN.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
        panelBTN.setBackground(Color.WHITE);

        btnSave = btnFunc("Lưu", "#4ec85a");
        btnCancel = btnFunc("Hủy", "#db4444");
        panelBTN.add(btnSave);
        panelBTN.add(btnCancel);

        panelInput.add(panelBTN);
        this.add(panelInput);
        
    }

       
    public void initEvents(){
        //Khi nhấn "Lưu", đặt confirmed thành true cho biết đã xác nhận và đồng ý với các thay đổi.
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();//Đóng dialog khi nhấn "Lưu".
            }
        });

        // Đóng dialog khi người dùng nhấn "Hủy".
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        //Chuyển đến LoạiDGDialog khi chọn loại độc giả khác trong comboBox
        cboLoaiDG.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String selectedItem = (String) cboLoaiDG.getSelectedItem();
                if("Khác".equals(selectedItem)){
                    openLoaiDGDialog();
                    // Sau khi dialog đóng, bạn có thể cập nhật lại ComboBox trong DocGiaDialog
                    // Giả sử trong LoaiDGDialog, bạn đã thêm loại độc giả mới vào ComboBox
//                    String newLoai = LoaiDGDialog.getNewLoai();  // Giả sử phương thức này trả về loại độc giả vừa thêm
//                    if (newLoai != null && !newLoai.trim().isEmpty()) {
//                        // Thêm loại mới vào ComboBox trong DocGiaDialog
//                        cboLoaiDG.addItem(newLoai);
//                        cboLoaiDG.setSelectedItem(newLoai); // Chọn loại độc giả vừa thêm
//                    }
                }
            }
        });
    }
    
    public void openLoaiDGDialog(){
        LoaiDGDialog dialog = new LoaiDGDialog((JFrame) SwingUtilities.getWindowAncestor(DocGiaDialog.this), true);
                dialog.setVisible(true);
               
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
  
    //ktra nguoi dung luu du lieu hay chua
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public String getMaDG() {
        return txtMaDG.getText();
    }

    public String getTenDG() {
        return txtTenDG.getText();
    }

    public String getSoDienThoai() {
        return txtSDT.getText();
    }

    public String getDiaChi() {
        return txtDiaChi.getText();
    }
    
    public String getTenLDG() {
        return (String) cboLoaiDG.getSelectedItem();
    }
    
    
}
