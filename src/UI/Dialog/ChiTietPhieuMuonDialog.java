/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Dialog;
import BLL.DSDocGiaBLL;
import BLL.DSPhieuMuon;
import BLL.DSSachBLL;
import BLL.DSThuThuBLL;
import MODEL.CTPhieuMuon;
import Model.PhieuMuon;
import java.awt.Color;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author NGOC TUYEN
 */
public class ChiTietPhieuMuonDialog extends javax.swing.JDialog {
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DefaultTableModel tblModel;
    private PhieuMuon pm;
    private ArrayList<CTPhieuMuon> dsCTPM;
    private DSPhieuMuon pmbll = new DSPhieuMuon();
    private DSSachBLL sachBLL = new DSSachBLL();
    
    public ChiTietPhieuMuonDialog(JFrame parent, PhieuMuon pm) {
        super(parent,"Chi tiết phiếu mượn", true);
        this.pm = pm;
        dsCTPM = pmbll.layAllCTPN(pm);
        initComponents();
        loadTxt();
        loadTableCTPM(dsCTPM);
        this.setLocationRelativeTo(parent);
        
    }

    public void loadTxt(){
        txtMaPMuon.setText(pm.getMaPhieuMuon());
        txtTenThuThu.setText(DSThuThuBLL.getTenThuThuByMa(pm.getMaThuThu()));
        txtTenDG.setText(DSDocGiaBLL.getTenDocGiabyMa(pm.getMaDocGia()));
        txtNgayMuon.setText(pm.getNgayMuon().format(formatTime));
        txtNgayTra.setText(pm.getNgayTra().format(formatTime));
    }
    
    public void loadTableCTPM(ArrayList<CTPhieuMuon> dsCTPM){
        JTableHeader headers = tblCTPM.getTableHeader();
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));      
        
        String[] columnNames = {"Mã sách", "Tên sách", "Số lượng", "Trạng thái"};
        tblModel = new DefaultTableModel(columnNames, 0){
             @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };     
        tblCTPM.setModel(tblModel);
        
        tblModel.setRowCount(0);
        for(CTPhieuMuon ctpm : dsCTPM){
            if(ctpm.getTrangThai() != 0){
                tblModel.addRow(new Object[]{
                    ctpm.getMaSach(),
                    sachBLL.getSachbyMa(ctpm.getMaSach()),
                    ctpm.getSoLuong(),
                    ctpm.getTrangThai() == 1 ? "Chưa trả" : "Đã trả"
                });
            }
        }
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tblCTPM.getColumnCount(); i++){
            tblCTPM.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtMaPMuon = new javax.swing.JTextField();
        txtTenThuThu = new javax.swing.JTextField();
        txtTenDG = new javax.swing.JTextField();
        txtNgayMuon = new javax.swing.JTextField();
        txtNgayTra = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTPM = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtMaPMuon.setEditable(false);
        txtMaPMuon.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã phiếu mượn"));

        txtTenThuThu.setEditable(false);
        txtTenThuThu.setBackground(new java.awt.Color(242, 242, 242));
        txtTenThuThu.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên thủ thư"));

        txtTenDG.setEditable(false);
        txtTenDG.setBackground(new java.awt.Color(242, 242, 242));
        txtTenDG.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên độc giả"));

        txtNgayMuon.setEditable(false);
        txtNgayMuon.setBackground(new java.awt.Color(242, 242, 242));
        txtNgayMuon.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày mượn"));

        txtNgayTra.setEditable(false);
        txtNgayTra.setBackground(new java.awt.Color(242, 242, 242));
        txtNgayTra.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày trả"));

        tblCTPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCTPM.setFillsViewportHeight(true);
        tblCTPM.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCTPM);
        if (tblCTPM.getColumnModel().getColumnCount() > 0) {
            tblCTPM.getColumnModel().getColumn(0).setResizable(false);
            tblCTPM.getColumnModel().getColumn(1).setResizable(false);
            tblCTPM.getColumnModel().getColumn(2).setResizable(false);
            tblCTPM.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaPMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtTenThuThu)
                            .addComponent(txtTenDG))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtNgayTra))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenThuThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCTPM;
    private javax.swing.JTextField txtMaPMuon;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtTenDG;
    private javax.swing.JTextField txtTenThuThu;
    // End of variables declaration//GEN-END:variables
}



