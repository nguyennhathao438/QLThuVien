
package UI.Dialog;

import BLL.DSPhieuTraBLL;
import MODEL.CTPhieuMuon;
import MODEL.CTPhieuTra;
import MODEL.SachTra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ChiTietPhieuTraDialog extends JDialog{
    private DefaultTableModel tblModel;
    private JTable tblCTPT;
    private JScrollPane scrollPane;
    private JPanel tblPanel;
    private DSPhieuTraBLL ptbll = new DSPhieuTraBLL();
    private CTPhieuTra ctpt = new CTPhieuTra() ;
    public ChiTietPhieuTraDialog(JFrame parent, boolean modal ,String maPhieuMuon){
        super(parent,"Chi tiết phiếu mượn", modal);
        this.setSize(600, 300);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        ctpt = ptbll.layChiTietPhieuTra(maPhieuMuon);
        loadData();
       
    }
    
    public void initComponents(){
        tblPanel = new JPanel();
        tblPanel.setLayout(new BorderLayout());
        String[] header = {"Mã phiếu mượn", "Mã sách", "số lượng"};
        tblModel = new DefaultTableModel(header, 0);
        tblCTPT = new JTable();
        tblCTPT.setModel(tblModel);
       
        //Lấy header của bảng.
        JTableHeader headers = tblCTPT.getTableHeader();
        headers.setReorderingAllowed(false);//Không cho thay đổi thứ tự các cột.
        headers.setResizingAllowed(false);// Không cho thay đổi kích thước cột.
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        //Lấy thông tin mô hình cột của bảng.
        TableColumnModel columns = tblCTPT.getColumnModel();
        columns.getColumn(0).setPreferredWidth(200);
        columns.getColumn(1).setPreferredWidth(200);
        columns.getColumn(2).setPreferredWidth(150);

        
        scrollPane = new JScrollPane(tblCTPT);
        tblPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(tblPanel, BorderLayout.CENTER);
    
        
    }
    public void loadData(){ 
        for(SachTra a:ctpt.getDsst()){ 
            tblModel.addRow(new Object[]{ctpt.getMaPhieuTra(),a.getMaSach(),a.getSoLuong()});
        }
    }
}
