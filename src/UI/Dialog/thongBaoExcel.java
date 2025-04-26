/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Dialog;

import BLL.DSSachBLL;
import DAL.SachDAL;
import Model.Sach;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Model.Sach;
import Model.TheLoai;
import UI.Panel.SachPanel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author tung7
 */
public class thongBaoExcel extends javax.swing.JDialog {

    DSSachBLL dsSach = new DSSachBLL();
    DefaultTableModel dtm =new DefaultTableModel();
    SachPanel spn = new SachPanel();
    public thongBaoExcel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        bangSach.setModel(dtm);
        docExcel();
        setData();
    }
    public void setData()
    {
        if(dtm.getColumnCount() == 0){
            dtm.addColumn("Mã Sách");
            dtm.addColumn("Tên Sách");
            dtm.addColumn("Năm xuất bản");
            dtm.addColumn("Số lượng");
            dtm.addColumn("Đơn giá");
            dtm.addColumn("Mã tác giả");
            dtm.addColumn("Mã thể loại");
        }
        dtm.setRowCount(0);
        ArrayList<Sach> ds  =dsSach.getDSTam();
        for(Sach a: ds){ 
            if(a.getSoLuong()> 0)
            dtm.addRow(new Object[]{
                a.getMaSach(),
                a.getTenSach(),
                a.getNamXuatBan(),
                a.getSoLuong(),
                a.getDonGia(),
                a.getMaTacGia(),
                a.getMaTheLoai()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangSach = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        confim = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dữ liệu được thêm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        bangSach.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(bangSach);

        jLabel1.setText("Bạn muốn thêm dữ liệu này không");

        confim.setText("Xác nhận");
        confim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(confim)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(confim)
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

    private void confimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confimActionPerformed
        ArrayList<Sach> dsTam = dsSach.getDSTam();
        for(Sach s : dsTam)
        {
            dsSach.themSachExcel(s);
        }
        dsSach.xoaDSTam();
        this.dispose();
    }//GEN-LAST:event_confimActionPerformed

      private void docExcel()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file để đọc");
        int luaChon = fileChooser.showOpenDialog(this);
        
        if(luaChon == JFileChooser.APPROVE_OPTION)
        {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            ArrayList<Sach> dsSachExcel = EXCEL.ExportExcel.docExcel(filePath);
            
            for(Sach s1 : dsSachExcel)
            {
               boolean kt = true;
               for(Sach s2 : dsSach.layAllSach())
               {
                   if(s1.getMaSach().equals(s2.getMaSach()))
                   {
                       kt = false;
                   }
               }
               if(kt)
                {
                    dsSach.themSachVaoDSTam(s1);
                }
            }
            JOptionPane.showMessageDialog(this,"Đọc file thành công");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangSach;
    private javax.swing.JButton confim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
