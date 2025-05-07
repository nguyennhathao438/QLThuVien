/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Dialog;

import BLL.DSDocGiaBLL;
import BLL.DSLoaiDocGiaBLL;
import Model.DocGia;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author NGOC TUYEN
 */
public class BangDocGiaDialog extends javax.swing.JDialog {
    private PhieuMuonDialog pmDialog;
    private DefaultTableModel tblModel;
    private DSDocGiaBLL dgbll = new DSDocGiaBLL();
    private DSLoaiDocGiaBLL ldgbll = new DSLoaiDocGiaBLL();
    
    public BangDocGiaDialog(javax.swing.JFrame parent, PhieuMuonDialog pmDialog) {
        super(parent,"Thông tin độc giả", true);
        this.pmDialog = pmDialog;
        initComponents();
        init();
        loadData(DSDocGiaBLL.layDSDocGia());
        this.setLocationRelativeTo(parent);
    }

    public void init(){
        tblModel = new DefaultTableModel();  
        JTableHeader headers = tblDG.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        String[] columnNames = {"Mã độc giả", "Tên độc giả", "Loại độc giả", "Giới hạn mượn"};
        tblModel = new DefaultTableModel(columnNames, 0){
             @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        }; 
        tblDG.setModel(tblModel);   
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tblDG.getColumnCount(); i++){
            tblDG.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
    }
    
    public void loadData(ArrayList<DocGia> dsDG){
        tblModel.setRowCount(0);
        if(!dsDG.isEmpty()){
                for(DocGia s : dsDG){
                if(s.getTrangThai() != 0){
                    tblModel.addRow(new Object[]{
                    s.getMaDocGia(),
                    s.getTenDocGia(),
                    DSLoaiDocGiaBLL.getTenLoaiDGByMa(s.getMaLoaiDG()),
                    DSLoaiDocGiaBLL.getGHByMa(s.getMaLoaiDG())
                    });
                }
            }
        } 
        else{
            System.out.println("rong");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDG = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblDG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Loại độc giả", "Giới hạn mượn"
            }
        ));
        tblDG.setFillsViewportHeight(true);
        tblDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDG);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDGMouseClicked
        if(evt.getClickCount() == 2 && tblDG.getSelectedRow() != -1){
            int row = tblDG.getSelectedRow();
            pmDialog.capNhatDG(tblDG.getValueAt(row, 1).toString(), Integer.parseInt(tblDG.getValueAt(row, 3).toString()));
            dispose();
        }
        
    }//GEN-LAST:event_tblDGMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDG;
    // End of variables declaration//GEN-END:variables
}
