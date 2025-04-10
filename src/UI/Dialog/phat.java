/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Dialog;

import BLL.DSPhieuTraBLL;
import BLL.DSQuyDinh;
import MODEL.QuyDinh;
import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class phat extends javax.swing.JDialog {
    DSPhieuTraBLL dspt=new DSPhieuTraBLL();
    DefaultTableModel dtm=new DefaultTableModel(){
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) { 
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3; 
    }
    
};;
    DSQuyDinh kn= new DSQuyDinh();
    String maPT;
    public phat(java.awt.Frame parent, boolean modal,String maPhieuTra) {
        super(parent, modal);
        initComponents();
        this.maPT = maPhieuTra;
        maPTra.setText(String.valueOf(maPT));
        bangQD.setModel(dtm);
        setData();
      dtm.addTableModelListener(new TableModelListener() {
    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getColumn() == 3) {  
            int row = e.getFirstRow();
            double tienPhat = (double) dtm.getValueAt(row, 2);  

            
            double tongTienPhat = 0;
            for (int i = 0; i < dtm.getRowCount(); i++) {         
            boolean isSelected = (boolean) dtm.getValueAt(i, 3);  
            if (isSelected) {        
                Object value = dtm.getValueAt(i, 2); 
                if (value instanceof Double) {
                    tongTienPhat += (Double) value; 
                }
            }
        }
            tongTien.setText(String.valueOf(tongTienPhat));
        }
    }
});
    }
    public void setData(){ 
        ArrayList<QuyDinh> dsqd=kn.layAllQuyDinh();
        if(dtm.getColumnCount()==0){ 
            dtm.addColumn("Mã QĐ");
            dtm.addColumn("Nội dung");
            dtm.addColumn("Tiền phạt");
            dtm.addColumn("Chọn");
        }
        dtm.setRowCount(0);
        for(QuyDinh qd:dsqd){ 
            if(qd.getTrangThai() != 0){ 
                dtm.addRow(new Object[]{qd.getMaQuyDinh(),qd.getNoiDung(),qd.getSoTien(),false});
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        maPTra = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangQD = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        maPhuThu = new javax.swing.JTextField();
        tongTien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        confirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lập Phiếu Phạt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        maPTra.setEditable(false);

        jLabel1.setText("Mã Phiếu Trả");

        bangQD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(bangQD);

        jLabel3.setText("Mã Phụ Thu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(maPTra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maPhuThu))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maPTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(maPhuThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel2.setText("Tổng phạt");

        confirm.setText("Xác nhận");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(confirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        String dsmqd[] = new String[10];
        int index=0;
        String maPhieuTra=maPTra.getText();
        double tienPhat = Double.parseDouble(tongTien.getText());
        for(int i=0;i<bangQD.getRowCount();i++){ 
            boolean isSelected = (boolean) dtm.getValueAt(i, 3);
            if(isSelected){ 
                String maqd= (String) dtm.getValueAt(i,0);
                dsmqd[index]=maqd;
                index++;
            }
        }
        String maPhuT = maPhuThu.getText();
        dspt.taoPhieuPhat(maPhieuTra,maPhuT, dsmqd, Double.parseDouble(tongTien.getText()));
    }//GEN-LAST:event_confirmActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangQD;
    private javax.swing.JButton confirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maPTra;
    private javax.swing.JTextField maPhuThu;
    private javax.swing.JTextField tongTien;
    // End of variables declaration//GEN-END:variables
}
