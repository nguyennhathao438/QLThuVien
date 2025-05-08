/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.LoginBLL;
import BLL.TKTongChiBLL;
import MODEL.TKTongChiTheoNgay;
import UI.Component.InputField;
import UI.Component.PDFExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nghia0605
 */
public class TKTongChiTheoNgayPanel extends JPanel{
    private InputField fromDate, toDate;
    private JButton btnConfirm,btnExportPDF;
    private JPanel topPanel, topContent, topFooter, pnNotification;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private TKTongChiBLL tktcBLL = new TKTongChiBLL();
    public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); ;
    private JLabel lbNotification;
    private LoginBLL loginBLL = new LoginBLL();
    public TKTongChiTheoNgayPanel(){
        setBackground(Color.white);
        setLayout(new BorderLayout(0, 10));
        init();
    }
    public void init(){                
        
        topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        topPanel.setLayout(new BorderLayout(0,0));
        topPanel.setPreferredSize(new Dimension(890, 110));
        topPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.lightGray));
//        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        this.add(topPanel,BorderLayout.NORTH);
        
        topContent = new JPanel();
        topContent.setBackground(Color.white);
        topContent.setLayout(new FlowLayout(1,10,10));
        topContent.setPreferredSize(new Dimension(890, 60));
        topPanel.add(topContent, BorderLayout.CENTER);
        
        fromDate = new InputField("Từ ngày", 200, 40, true);
        topContent.add(fromDate);
        
        toDate = new InputField("Đến ngày", 200, 40, true);
        topContent.add(toDate);
        
        topFooter = new JPanel();
        topFooter.setBackground(Color.white);
        topFooter.setLayout(new FlowLayout(1,10,10));
        topFooter.setPreferredSize(new Dimension(890, 40));
        
        topPanel.add(topFooter, BorderLayout.SOUTH);
        
        btnConfirm = new JButton("Xem");
        btnConfirm.setFocusPainted(false);
        btnConfirm.setPreferredSize(new Dimension(110, 30));
        btnConfirm.setBackground(Color.green);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnConfirm.addMouseListener(new MouseAdapter(){
            
           @Override
           public void mouseClicked(MouseEvent e){
               Date fromdate = fromDate.getDate();
               Date todate = toDate.getDate();
               if(tktcBLL.checkEmptyDate(fromdate, todate)) return;
               tktcBLL.TKTongChiTheoNgay(fromdate, todate);
               if(scrollPane != null){
                   remove(scrollPane);
               }
//               displayTCN(TKTongChiBLL.getDsTKTCN());                
               if(TKTongChiBLL.getDsTKTCN().isEmpty()){
                   notification();
               }
               else{
                   if(lbNotification != null){
                       remove(lbNotification);
                   }
                   displayTKTable();                   
                   loadTable(TKTongChiBLL.getDsTKTCN());
               }
               revalidate();            
               repaint();
           }
        });
        topFooter.add(btnConfirm);
        
        btnExportPDF = new JButton("Xuất PDF");
        btnExportPDF.setFocusPainted(false);
        btnExportPDF.setPreferredSize(new Dimension(110, 30));
        btnExportPDF.setBackground(Color.red);
        btnExportPDF.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnExportPDF.addActionListener(e -> {
            Date fromdate = fromDate.getDate();
            Date todate = toDate.getDate();
            if(tktcBLL.checkEmptyDate(fromdate, todate)) return;
            String fromDateStr = sdf.format(fromdate);
            String toDateStr = sdf.format(todate);
            String title = "Thống kê tổng chi theo ngày\n" + fromDateStr + "-" + toDateStr;
            PDFExporter.exportTableToPDF(this, table, title, loginBLL.getTenThuThu());
        });
        topFooter.add(btnExportPDF);
        
//         String[] header = {"STT","Ngày", "Tổng chi"};
//        tableModel = new DefaultTableModel(header, 0){
//            @Override            
//            public boolean isCellEditable(int row,int column){
//                return false;
//            }
//        };
//        
//        table = new JTable();
//        table.setFillsViewportHeight(true);      
//        table.setModel(tableModel);
//        table.setFont(new Font("Segoe UI", Font.PLAIN,14));
//        table.setRowHeight(30);
//        
//        JTableHeader headers = table.getTableHeader();
//        headers.setReorderingAllowed(false);
//        headers.setResizingAllowed(false);
//        headers.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        headers.setBackground(Color.decode("#66B2FF"));
//        
//        scrollPane = new JScrollPane(table);
//        scrollPane.setPreferredSize(new Dimension(890, 450));
//        this.add(scrollPane, BorderLayout.CENTER);
    }
        
    public void displayTKTable(){    
        
        String[] header = {"STT","Ngày", "Tổng chi"};
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
        headers.setBackground(Color.decode("#66B2FF"));
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(890, 450));
        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    public void loadTable(ArrayList<TKTongChiTheoNgay> dsTKTCN){
        tableModel.setRowCount(0);
        int i = 1;
        for(TKTongChiTheoNgay tktc : dsTKTCN){
            tableModel.addRow(new Object[]{
                i++,
                sdf.format(tktc.getDate()),
                tktc.getTongChi()
                
            });
        }
    }    
    public void notification(){
//        pnNotification = new JPanel();
        lbNotification = new JLabel("Không có phiếu nhập trong khoảng thời gian trên!", JLabel.CENTER);
        lbNotification.setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.add(lbNotification);
    }
    public void displayTCN(ArrayList<TKTongChiTheoNgay> dsTKTCN){
        for(TKTongChiTheoNgay tktc : dsTKTCN){
            System.out.println("Ngày " + tktc.getDate() + "tong cong " + tktc.getTongChi());
        }
    }
}
