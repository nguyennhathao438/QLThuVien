/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGOC TUYEN
 */
public class ChiTietPhieuMuonDialog extends JDialog {
    private DefaultTableModel tblModel;
    private JTable tblCTPM;
    private JScrollPane scrollPane;
    private JPanel tblPanel;
    
    public ChiTietPhieuMuonDialog(JFrame parent, boolean modal){
        super(parent,"Chi tiết phiếu mượn", modal);
        this.setSize(600, 300);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        //initEvents();
       
    }
    
    public void initComponents(){
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã phiếu mượn", "Mã sách", "số lượng", "Trạng thái"};
        tblModel = new DefaultTableModel(header, 0);
        tblCTPM = new JTable();
        tblCTPM.setModel(tblModel);
       
        //Lấy header của bảng.
        JTableHeader headers = tblCTPM.getTableHeader();
        headers.setReorderingAllowed(false);//Không cho thay đổi thứ tự các cột.
        headers.setResizingAllowed(false);// Không cho thay đổi kích thước cột.
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        //Lấy thông tin mô hình cột của bảng.
        TableColumnModel columns = tblCTPM.getColumnModel();
        columns.getColumn(0).setPreferredWidth(150);
        columns.getColumn(1).setPreferredWidth(150);
        columns.getColumn(2).setPreferredWidth(100);
        columns.getColumn(3).setPreferredWidth(150);

        
        scrollPane = new JScrollPane(tblCTPM);
        tblPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(tblPanel, BorderLayout.CENTER);
    
        
    }
}
