/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.Panel;

import BLL.ThongKeBLL;
import MODEL.TKSach;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;


public class TKSachPanel extends javax.swing.JPanel {
    ThongKeBLL tkbll = new ThongKeBLL();
    ArrayList<TKSach> dstkSach = new ArrayList<>();
    boolean rbthoigian = false;
    public TKSachPanel() {
        initComponents();
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.setPreferredSize(new java.awt.Dimension(675, 300));
        dstkSach = tkbll.gettkSachThang(2025);
        hienThiBieuDoThang(dstkSach);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        todate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangtkSach = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nam = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        choice = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();

        todate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                todatePropertyChange(evt);
            }
        });

        bangtkSach.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(bangtkSach);

        jLabel3.setText("Năm:");

        nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2024", "2023", " ", " " }));

        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Thời gian:");

        choice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Quý" }));
        choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void todatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_todatePropertyChange
        
    }//GEN-LAST:event_todatePropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int a = choice.getSelectedIndex();
        int namtk = Integer.parseInt((String) nam.getSelectedItem());
        if(a==0)
        {
            dstkSach = tkbll.gettkSachThang(namtk);
            hienThiBieuDoThang(dstkSach);
        } else if(a==1)
        {
            dstkSach = tkbll.gettkSachQuy(namtk);
            hienThiBieuDoQuy(dstkSach);
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void choiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choiceActionPerformed

    public void hienThiBieuDoThang(ArrayList<TKSach> dstkSach)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int i=0;
        while(i<12)
        {
            TKSach tks = dstkSach.get(i);
            int thang = (i+1);
            dataset.setValue(tks.getSoLuongMuon(), "Sách mượn", String.valueOf(thang));
            dataset.setValue(tks.getSoLuongTra(), "Sách trả", String.valueOf(thang));
            i++;
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ sách mượn và trả theo tháng",
                "Tháng",
                "Tổng trả và mượn",
                dataset);
        jPanel2.removeAll();
        jPanel2.add(new ChartPanel(barChart), BorderLayout.CENTER);
        jPanel2.validate();
    }

    public void hienThiBieuDoQuy(ArrayList<TKSach> dstkSach)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i=0;
        while(i<=3)
        {
            TKSach tks = dstkSach.get(i);
            String quy = "Quý" + (i+1);
            dataset.setValue(tks.getSoLuongMuon(), "Sách mượn", quy);
            dataset.setValue(tks.getSoLuongTra(), "Sách trả", quy);
            i++;
        }
        
                System.out.println("hasgash");

        
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ sách mượn và sách trả",
                "Quý",
                "Tổng trả và mượn",
                dataset);
        jPanel2.removeAll();
        jPanel2.add(new ChartPanel(barChart), BorderLayout.CENTER);
        jPanel2.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangtkSach;
    private javax.swing.JComboBox<String> choice;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> nam;
    private com.toedter.calendar.JDateChooser todate;
    // End of variables declaration//GEN-END:variables
}
