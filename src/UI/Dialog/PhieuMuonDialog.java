/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Dialog;

import BLL.DSDocGiaBLL;
import BLL.DSSachBLL;
import BLL.DSThuThuBLL;
import BLL.LoginBLL;
import MODEL.CTPhieuMuon;
import MODEL.SachMuon;
import Model.PhieuMuon;
import Model.Sach;
import UI.Panel.PhieuMuonPanel;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.*;

/**
 *
 * @author NGOC TUYEN
 */
public class PhieuMuonDialog extends javax.swing.JDialog {
    private DefaultTableModel tblModel;
    private DefaultTableModel tblModel1;
    private PhieuMuonPanel panel;
    private DSSachBLL sachBLL = new DSSachBLL();
    private ArrayList<SachMuon> dssm = new ArrayList<>();
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DSDocGiaBLL dgbll = new DSDocGiaBLL();
    private DSThuThuBLL ttbll = new DSThuThuBLL();
    
    private JFrame parent;
    
    public PhieuMuonDialog(JFrame parent, PhieuMuonPanel panel) {
        super(parent,"Thêm phiếu mượn", true);
        this.panel = panel;
        initComponents();
        init();
       
        this.setLocationRelativeTo(null);
    }

    public void init(){
        tblModel = new DefaultTableModel();  
        JTableHeader headers = tblSach.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        String[] columnNames = {"Mã sách", "Tên sách", "Số lượng"};
        tblModel = new DefaultTableModel(columnNames, 0){
             @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        }; 
        tblSach.setModel(tblModel);   
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tblSach.getColumnCount(); i++){
            tblSach.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        loadDataTblSach(DSSachBLL.layAllSach());
        
        
        tblModel1 = new DefaultTableModel();
        headers = tblSachMuon.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        String[] columnNames1 = {"Mã sách", "Tên sách", "Số lượng", "Trạng thái"};
        tblModel1 = new DefaultTableModel(columnNames1, 0){
             @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        }; 
        tblSachMuon.setModel(tblModel1);
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tblSachMuon.getColumnCount(); i++){
            tblSachMuon.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        LoginBLL login = new LoginBLL();
        String maThuThu = login.getMaThuThu();
        txtThuThu.setText(DSThuThuBLL.getTenThuThuByMa(maThuThu));
        txtThuThu.setEnabled(false);
        
        jdcNgMuon.setDate(new java.util.Date());
        
    }
    
    public void capNhatDG(String TenDG, int gh){
        txtDG.setText(TenDG);
        txtGHMuon.setText(String.valueOf(gh));
        txtGHMuon.setEnabled(false);
    }
    
    public void loadDataTblSach(ArrayList<Sach> dsSach){
        tblModel.setRowCount(0);
        if(!dsSach.isEmpty()){
                for(Sach s : dsSach){
                if(s.getTrangThai() != 0){
                    tblModel.addRow(new Object[]{
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getSoLuong()

                    });
                }
            }   
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSachMuon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtMaPMuon = new javax.swing.JTextField();
        txtThuThu = new javax.swing.JTextField();
        jdcNgMuon = new com.toedter.calendar.JDateChooser();
        jdcNgTra = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        txtDG = new javax.swing.JTextField();
        btnDG = new javax.swing.JButton();
        txtGHMuon = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblSachMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng", "Trạng thái"
            }
        ));
        tblSachMuon.setFillsViewportHeight(true);
        tblSachMuon.setGridColor(new java.awt.Color(0, 0, 0));
        tblSachMuon.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblSachMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSachMuonMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblSachMuon);
        if (tblSachMuon.getColumnModel().getColumnCount() > 0) {
            tblSachMuon.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Bảng chi tiết phiếu mượn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jLabel1)
                .addContainerGap(239, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnSave.setBackground(new java.awt.Color(91, 202, 106));
        btnSave.setText("Lưu");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(245, 112, 112));
        btnCancel.setText("Hủy");
        btnCancel.setToolTipText("");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtMaPMuon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtThuThu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thủ thư", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jdcNgMuon.setBackground(new java.awt.Color(255, 255, 255));
        jdcNgMuon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jdcNgMuon.setDateFormatString("dd/MM/yyyy");

        jdcNgTra.setBackground(new java.awt.Color(255, 255, 255));
        jdcNgTra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jdcNgTra.setDateFormatString("dd/MM/yyyy");

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setFillsViewportHeight(true);
        tblSach.setGridColor(new java.awt.Color(0, 0, 0));
        tblSach.setRowSelectionAllowed(true);
        tblSach.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSachMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSach);

        txtDG.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Độc giả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnDG.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDG.setText(". . .");
        btnDG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDG.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDGActionPerformed(evt);
            }
        });

        txtGHMuon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giới hạn sách được mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtThuThu)
                                .addComponent(txtMaPMuon)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtDG, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                    .addComponent(btnDG, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtGHMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jdcNgMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcNgTra, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaPMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtThuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDG, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGHMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jdcNgMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgTra, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        PhieuMuon pm = new PhieuMuon();
        pm.setMaPhieuMuon(txtMaPMuon.getText());
        pm.setNgayMuon((jdcNgMuon.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
        if(jdcNgTra.getDate() == null){
            JOptionPane.showMessageDialog(null,"Ngay tra khong duoc bo trong");
            return ;
        }
        pm.setNgayTra((jdcNgTra.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));                                                                              
        pm.setMaThuThu(DSThuThuBLL.getMaThuThuByTen(txtThuThu.getText()));
        pm.setMaDocGia(DSDocGiaBLL.getMaDGByTen(txtDG.getText()));
        pm.setTrangThai(1);
        ArrayList<CTPhieuMuon> dsCTPM = new ArrayList<>();
        for (int i = 0; i < tblModel1.getRowCount(); i++) {
            String maSach = tblModel1.getValueAt(i, 0).toString();
            int sl = Integer.parseInt(tblModel1.getValueAt(i, 2).toString());
            CTPhieuMuon ct = new CTPhieuMuon(pm.getMaPhieuMuon(), maSach, sl, 1);
            dsCTPM.add(ct);
        }
        
        if(panel.getDSPMuonBLL().themPM(pm, dsCTPM)){
            if(panel.getDSPMuonBLL().updateSoLuongSach(dsCTPM)){
                JOptionPane.showMessageDialog(null,"Thêm phiếu mượn thành công");
                dispose();
                panel.loadData(panel.getDSPMuonBLL().layAllPhieuMuon());
            }else{
                JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thất bại");
                
            }
        }
        

            
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMousePressed
        int index = tblSach.getSelectedRow();
        if(index >= 0){
            String maSach = tblModel.getValueAt(index, 0).toString();
            String tenSach = tblSach.getValueAt(index, 1).toString();
            int slCon = Integer.parseInt(tblModel.getValueAt(index, 2).toString());
            String input = JOptionPane.showInputDialog(null, "Sách " + tenSach + "\n Nhập số lượng mượn");
            int slMuon = Integer.parseInt(input);
            if(slMuon <= 0 || slMuon > slCon){
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ");
            }else{
                tblModel1.addRow(new Object[]{maSach, tenSach, slMuon, "Chưa trả"});
                tblSach.setValueAt(slCon - slMuon, index, 2);
            }
        }
    }//GEN-LAST:event_tblSachMousePressed

    private void tblSachMuonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMuonMousePressed
        int index = tblSachMuon.getSelectedRow();
        if(index >= 0){
            String maSach = tblSachMuon.getValueAt(index, 0).toString();
            String tenSach = tblSachMuon.getValueAt(index, 1).toString();
            int slMuonCu = Integer.parseInt(tblSachMuon.getValueAt(index, 2).toString());
            String input = JOptionPane.showInputDialog(null, "Sach " + tenSach + "\nSửa số lượng mượn");
            int slMuonMoi = Integer.parseInt(input);
            if(slMuonMoi <= 0){
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ");
            }
            // Tìm hàng tương ứng trong tblSach theo mã sách
            for (int i = 0; i < tblSach.getRowCount(); i++) {
                String maSachInTblSach = tblSach.getValueAt(i, 0).toString();
                if (maSachInTblSach.equals(maSach)) {
                    int slConHienTai = Integer.parseInt(tblSach.getValueAt(i, 2).toString());
                    int slConSauCapNhat = slConHienTai + slMuonCu - slMuonMoi;
                    if (slConSauCapNhat < 0) {
                        JOptionPane.showMessageDialog(null, "Không đủ sách để mượn");
                    }
                    // Cập nhật lại số lượng còn trong bảng sách
                    tblSach.setValueAt(slConSauCapNhat, i, 2);
                    tblSachMuon.setValueAt(slMuonMoi, index, 2);
                    break;
                }
            }
        }
    }//GEN-LAST:event_tblSachMuonMousePressed

    private void btnDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDGActionPerformed
        parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        BangDocGiaDialog dialog = new BangDocGiaDialog(parent, this);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnDGActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDG;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcNgMuon;
    private com.toedter.calendar.JDateChooser jdcNgTra;
    private javax.swing.JTable tblSach;
    private javax.swing.JTable tblSachMuon;
    private javax.swing.JTextField txtDG;
    private javax.swing.JTextField txtGHMuon;
    private javax.swing.JTextField txtMaPMuon;
    private javax.swing.JTextField txtThuThu;
    // End of variables declaration//GEN-END:variables
}
