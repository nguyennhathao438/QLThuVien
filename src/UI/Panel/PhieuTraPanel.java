/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSPhieuTraBLL;
import BLL.DSThuThuBLL;
import MODEL.PhieuTra;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.chiTietPhat;
import UI.Dialog.phat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class PhieuTraPanel extends JPanel implements ItemListener,MouseListener{
     private JPanel headerPanel, content;
    private DefaultTableModel dtm;
    private JTable bangPhieuTra;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private JFrame parent;
    private DSPhieuTraBLL dspt=new DSPhieuTraBLL();
    private DSThuThuBLL ttbll = new DSThuThuBLL();
    public PhieuTraPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponent();                
        loadData(DSPhieuTraBLL.getAllPhieuTra());
        
    }
    public void initComponent() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);

        String[] function = { "delete" ,"punish"}; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        searchBar = new SearchBar(new String[] { "Tất cả", "Mã Phiếu Trả", "Mã Phiếu Mượn","Phụ Thu","Tên Thủ Thư" });
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText();
                loadData(dspt.searchQuyDinh(text, type));
            }
        });

        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] header = { "Mã Phiếu Trả", "Mã Phiếu Mượn", "Ngày Thực Trả","Tên Thủ Thư" ,"Phụ Thu"};
        dtm = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        bangPhieuTra = new JTable();
        bangPhieuTra.setFillsViewportHeight(true);      
        bangPhieuTra.setModel(dtm);

        JTableHeader headers = bangPhieuTra.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = bangPhieuTra.getColumnModel();
        column.getColumn(0).setPreferredWidth(150);
        column.getColumn(1).setPreferredWidth(150);
        column.getColumn(2).setPreferredWidth(200);
        column.getColumn(3).setPreferredWidth(300);
        column.getColumn(4).setPreferredWidth(100);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < bangPhieuTra.getColumnCount(); i++){
            bangPhieuTra.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(bangPhieuTra);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }   
    public void loadData(ArrayList<PhieuTra> dspt){
        dtm.setRowCount(0);   
        for(PhieuTra a: dspt){
            if(a.getTrangThai()!= 0){
             dtm.addRow(new Object[]{
                a.getMaPhieuTra(),
                 a.getMaPhieuMuon(),
                 a.getNgayThucTra(),
                 ttbll.getTenThuThuByMa(a.getMaThuThu()),
                 getPhuThu(a.getMaPhuThu())
            }); 
            }             
        }
    }
    
     public String getPhuThu(String s ){ 
        if(s== null )
            return "Không";
        return "Có";
    }
    public DSPhieuTraBLL getPhieuTraBLL(){ 
        return this.dspt;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("punish")){
            int row = bangPhieuTra.getSelectedRow();
        if(row == -1 ){ 
            JOptionPane.showMessageDialog(bangPhieuTra,"Vui lòng chọn phiếu trả để phạt");
        }else{ 
            String kt = (String) bangPhieuTra.getValueAt(row,4);
            if(kt.equals("Không")){
            String maPTra = (String) bangPhieuTra.getValueAt(row, 0);
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
        new phat((Frame) parentWindow,true,maPTra,this).setVisible(true);
            }else{ 
                int confirm = JOptionPane.showConfirmDialog(bangPhieuTra,"Phiếu trả này đã phạt rồi \n Bạn có muốn xem chi tiết ");
                if(confirm == JOptionPane.YES_OPTION){ 
                    String maPTra = (String) bangPhieuTra.getValueAt(row, 0);
                    Window parentWindow = SwingUtilities.getWindowAncestor(this);
        new chiTietPhat((Frame) parentWindow,true,maPTra).setVisible(true);
                }
            }
        }       
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete")){
            int row = bangPhieuTra.getSelectedRow();
            if(row == -1 ){ 
                JOptionPane.showMessageDialog(bangPhieuTra,"Vui lòng chọn phiếu trả để phạt");
            }else{ 
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá");
                if(confirm == JOptionPane.YES_OPTION){
                String maPtra = (String) bangPhieuTra.getValueAt(row,0);
                dspt.xoaPhieuTra(maPtra);
                loadData(dspt.getAllPhieuTra());
                }
            }
        }else if(e.getSource() == searchBar.getBtnRefesh()){            
               searchBar.getCboChoose().setSelectedIndex(0);
           searchBar.getTxtSearch().setText("");
           loadData(dspt.getAllPhieuTra());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
