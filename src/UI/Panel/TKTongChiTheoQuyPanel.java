/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.LoginBLL;
import BLL.TKTongChiBLL;
import BLL.ThongKeBLL;
import MODEL.TKTongChiTheoQuy;
import UI.Component.InputField;
import UI.Component.PDFExporter;
import UI.Component.SelectInput;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nghia0605
 */
public class TKTongChiTheoQuyPanel extends JPanel{
    private SelectInput cboYear;
    private JButton btnConfirm,btnExportPDF;
    private JPanel topPanel, topContent, topFooter;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private ThongKeBLL tkBLL = new ThongKeBLL();
    private TKTongChiBLL tktcBLL = new TKTongChiBLL();
    private JLabel lbNotification;
    private LoginBLL loginBLL = new LoginBLL();
    
    public TKTongChiTheoQuyPanel(){
        setBackground(Color.white);
        setLayout(new BorderLayout(0, 10));
        init();
        tktcBLL.TKTongChiTheoQuy(Integer.valueOf((String)cboYear.getCboChoose().getSelectedItem()));
        loadData(TKTongChiBLL.getDsTKTCQ());
    }
    public void init(){
        topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        topPanel.setLayout(new BorderLayout(0,0));
        topPanel.setPreferredSize(new Dimension(890, 110));
        topPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.lightGray));
//        topPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.add(topPanel,BorderLayout.NORTH);
        
        topContent = new JPanel();
        topContent.setBackground(Color.white);
        topContent.setLayout(new FlowLayout(0,20,7));
        topContent.setPreferredSize(new Dimension(890, 60));
        topPanel.add(topContent, BorderLayout.CENTER);
        
        String[] arrYear = tkBLL.getArrNam(2020);
        cboYear = new SelectInput("Năm", arrYear, 200, 60);
        cboYear.getCboChoose().setSelectedIndex(arrYear.length - 1);
        cboYear.getCboChoose().addActionListener(e -> {
            int nam = Integer.valueOf((String) cboYear.getCboChoose().getSelectedItem());
            tktcBLL.TKTongChiTheoQuy(nam);
            loadData(TKTongChiBLL.getDsTKTCQ());
        });
        topContent.add(cboYear);
        
        topFooter = new JPanel();
        topFooter.setBackground(Color.white);
        topFooter.setLayout(new FlowLayout(1,10,10));
        topFooter.setPreferredSize(new Dimension(890, 40));
        
        topPanel.add(topFooter, BorderLayout.SOUTH);
        
//        btnConfirm = new JButton("Xác nhận");
//        btnConfirm.setFocusPainted(false);
//        btnConfirm.setPreferredSize(new Dimension(110, 30));
//        btnConfirm.setBackground(Color.green);
//        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        topFooter.add(btnConfirm);
        
        btnExportPDF = new JButton("Xuất PDF");
        btnExportPDF.setFocusPainted(false);
        btnExportPDF.setPreferredSize(new Dimension(110, 30));
        btnExportPDF.setBackground(Color.red);
        btnExportPDF.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        btnExportPDF.setForeground(Color.yellow);
//        btnExportPDF.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        btnExportPDF.addActionListener(e -> {
            String nam = (String)cboYear.getCboChoose().getSelectedItem();
            String title = "Thống kê theo quý năm " + nam;
            PDFExporter.exportTableToPDF(this, table, title, loginBLL.getTenThuThu());
        });
        topFooter.add(btnExportPDF);
        
        String[] header = {"", "Quý 1", "Quý 2", "Quý 3", "Quý 4", "Tổng cộng"};
        tableModel = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        
        table = new JTable();
        table.setFillsViewportHeight(true);      
        table.setModel(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN,15));
        table.setRowHeight(30);
        
        JTableHeader headers = table.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 17));

//        headers.setDefaultRenderer(new DefaultTableCellRenderer() {
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                boolean isSelected, boolean hasFocus, int row, int column) {
//
//            JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//            lbl.setHorizontalAlignment(SwingConstants.CENTER);
//            lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
//            lbl.setForeground(Color.BLACK); // Màu chữ
//
//            // Nếu là cột "Tổng cộng" (cuối cùng)
//            if (column == table.getColumnCount() - 1) {
//                lbl.setBackground(Color.RED); // Header cột Tổng cộng màu đỏ
//            } else {
//                lbl.setBackground(Color.decode("#66B2FF")); // Các header khác màu xanh
//            }
//             lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//            return lbl;
//        }
//    });

        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        // Custom cell renderer
//        TableCellRenderer customRenderer = new DefaultTableCellRenderer() {
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value,
//            boolean isSelected, boolean hasFocus, int row, int column) {
//
//        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//        int rowCount = table.getRowCount();
//        int colCount = table.getColumnCount();
//
//        // Xét hàng "Tổng cộng" (giả sử là hàng cuối)
//        boolean isTotalRow = row == rowCount - 1;
//
//        // Xét cột "Tổng cộng" (giả sử là cột cuối)
//        boolean isTotalCol = column == colCount - 1;
//
//        setHorizontalAlignment(SwingConstants.CENTER);
//         
//        if (isTotalRow && isTotalCol) {
//            c.setBackground(Color.ORANGE);
//            c.setFont(c.getFont().deriveFont(Font.BOLD));
//        } else if (isTotalRow) {
//            c.setBackground(Color.YELLOW);
//            c.setFont(c.getFont().deriveFont(Font.BOLD));
//        } else if (isTotalCol) {
//            c.setBackground(new Color(255, 230, 180));
//            c.setFont(c.getFont().deriveFont(Font.BOLD));
//        } else {
//            c.setBackground(Color.WHITE);
//            c.setFont(c.getFont().deriveFont(Font.PLAIN));
//        }
//
//        return c;
//            }
//        };

        // Gán renderer cho tất cả cột
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
//        }

        
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(890, 450));
        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    public void loadData(ArrayList<TKTongChiTheoQuy> dsTKTCQ){
        tableModel.setRowCount(0);
        for(TKTongChiTheoQuy tktc : dsTKTCQ){
            tableModel.addRow(new Object[]{
                tktc.getTenSach(),
                tktc.getQ1(),
                tktc.getQ2(),
                tktc.getQ3(),
                tktc.getQ4(),
                tktc.getTong()
            });
            
        }
        tableModel.addRow(new Object[]{
            "Tổng cộng",
            tktcBLL.tongQ1(),
            tktcBLL.tongQ2(),
            tktcBLL.tongQ3(),
            tktcBLL.tongQ4(),
            tktcBLL.tongNam(),            
        });
    }
    
    public void notification(){
        String nam = (String) cboYear.getCboChoose().getSelectedItem();
        String label = "Không có phiếu nhập nào trong năm " + nam; 
        lbNotification = new JLabel(label, JLabel.CENTER);
    }
    
//    public void loadDataIsEmpty(){
//        
//    }        
}
