/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSThuThuBLL;
import BLL.ThongKeBLL;
import MODEL.TKThuThu;
import UI.Component.SelectInput;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Nghia0605
 */
public class TKThuThuPanel extends JPanel{
    private SelectInput cboTT,cboYear;
    private JPanel topPanel,contentPanel;
    private DSThuThuBLL ttBLL = new DSThuThuBLL();    
    private ThongKeBLL tkBLL = new ThongKeBLL();
    private ArrayList<TKThuThu> dsTK;
    private JLabel lbNotification;
    
    public TKThuThuPanel(){
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout(0,0));        
        init();
        this.dsTK = tkBLL.getTKThuThu(2025);
        if (!dsTK.isEmpty()) {
            this.displayChart(dsTK.get(0));
        } else {
            this.notification(); // Call notification if initial dsTK is empty
        }
    }
    
    public void init(){
        topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        topPanel.setPreferredSize(new Dimension(900, 80));
        topPanel.setLayout(new FlowLayout(0, 30, 10));
        topPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.lightGray));
        this.add(topPanel, BorderLayout.NORTH);
        
        String[] arr = ttBLL.getArrTenThuThu();
        cboTT = new SelectInput("Thủ thư", arr, 200, 60);   
        cboTT.getCboChoose().addActionListener(e ->{
            String selectedThuThu = (String) cboTT.getCboChoose().getSelectedItem();
            
            for(TKThuThu tk : dsTK){
                if(tk.getTenThuThu().equals(selectedThuThu)){
                    this.displayChart(tk);
                    return;
                }
                this.notification();
            }
        });
        topPanel.add(cboTT);
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(0,0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        contentPanel.setBackground(Color.white);
        contentPanel.setPreferredSize(new Dimension(890, 607));
        this.add(contentPanel, BorderLayout.CENTER);
        
        String[] arrYear = tkBLL.getArrNam(2020);
        cboYear = new SelectInput("Năm", arrYear, 200, 60);
        cboYear.getCboChoose().setSelectedIndex(arrYear.length - 1);
        topPanel.add(cboYear);
        cboYear.getCboChoose().addActionListener(e -> {
            int nam = Integer.valueOf((String)cboYear.getCboChoose().getSelectedItem());
//            System.out.println(nam);
            this.dsTK = tkBLL.getTKThuThu(nam);                       
             if (!dsTK.isEmpty()) {
//                System.out.println("Danh sach thong ke: " + dsTK.size());
                String tenThuThu = (String)cboTT.getCboChoose().getSelectedItem();
                String maThuThu = ttBLL.getMaThuThuByTen(tenThuThu); 
                for(TKThuThu tk : dsTK){
                    if(tk.getMaThuThu().equals(maThuThu)){
                        displayChart(tk);
                        return;
                    }
                }
                this.notification();
            }
             else{
//                 System.out.println("Danh sach thong ke: " + dsTK.size());
                 this.notification();
             }
        });                                      
    }
    
    private void displayChart(TKThuThu tk){
        contentPanel.removeAll();
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Phiếu mượn", tk.getPhanTramMuon());
        dataset.setValue("Phiếu trả", tk.getPhanTramTra());
        dataset.setValue("Phiếu nhập", tk.getPhanTramNhap());
        
        JFreeChart chart = ChartFactory.createPieChart("Tỷ lệ tham vào các phiếu của " + tk.getTenThuThu() + 
                "\nNăm" + cboYear.getCboChoose().getSelectedItem()
                , dataset, true, true, false);
        //(ten bieu do,du lieu, chu thich, thong tin tung phan khi re chuot qua, tao lien ket khi click vao bieu do)                
        chart.setBackgroundPaint(Color.white);
        PiePlot plot = (PiePlot) chart.getPlot();

        // Đổi màu từng phần tử (theo key trong dataset)
        plot.setSectionPaint("Phiếu mượn", Color.RED);
        plot.setSectionPaint("Phiếu trả", Color.BLUE);
        plot.setSectionPaint("Phiếu nhập", Color.GREEN);

        // Đổi font nhãn
        plot.setLabelFont(new Font("Tahoma", Font.PLAIN, 12));

        // Hiển thị nhãn theo định dạng: tên - % giá trị
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} - {1} ({2})"));

        // Bỏ đường viền giữa các phần
        plot.setSectionOutlinesVisible(false); // Ẩn viền giữa các phần


        // Đổi màu nền
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null); // Không tô viền cho toàn bộ biểu đồ


        // Đổi font tiêu đề
        chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 18));

        
        ChartPanel cp = new ChartPanel(chart);
        cp.setBackground(Color.white);
        contentPanel.add(cp, BorderLayout.CENTER);
        contentPanel.validate();
    }
    private void notification(){
        lbNotification = new JLabel("Không có dữ liệu", JLabel.CENTER);
        lbNotification.setBackground(Color.white);
        lbNotification.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        contentPanel.removeAll();
        contentPanel.add(lbNotification, BorderLayout.CENTER);
        contentPanel.validate();
    }
}
