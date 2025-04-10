/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSQuyDinh;
import MODEL.QuyDinh;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.suaQuyDinh;
import UI.Dialog.themQuyDinh;
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
public class QuyDinhPanel extends JPanel implements ItemListener,MouseListener{
    private JPanel headerPanel, content;
    private DefaultTableModel dtm;
    private JTable bangQuyDinh;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private JFrame parent;
    private DSQuyDinh dsqd=new DSQuyDinh();
    public QuyDinhPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponent();                
        loadData(DSQuyDinh.layAllQuyDinh());
        
    }
    public void initComponent() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);

        String[] function = { "create", "delete", "update"}; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        searchBar = new SearchBar(new String[] { "Tất cả", "Mã Quy Định", "Nội dung", "Tiền Phạt" });
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText();
                
            }
        });

        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] header = { "Mã Quy Định", "Nội dung", "Tiền Phạt" };
        dtm = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        bangQuyDinh = new JTable();
        bangQuyDinh.setFillsViewportHeight(true);      
        bangQuyDinh.setModel(dtm);

        JTableHeader headers = bangQuyDinh.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = bangQuyDinh.getColumnModel();
        column.getColumn(0).setPreferredWidth(100);
        column.getColumn(1).setPreferredWidth(600);
        column.getColumn(2).setPreferredWidth(200);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < bangQuyDinh.getColumnCount(); i++){
            bangQuyDinh.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(bangQuyDinh);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }   
    public void loadData(ArrayList<QuyDinh> dsqd){
        dtm.setRowCount(0);   
        for(QuyDinh a: dsqd){
            if(a.getTrangThai()!= 0)
             dtm.addRow(new Object[]{
                a.getMaQuyDinh(),
                 a.getNoiDung(),
                 a.getSoTien()
            });              
        }
    }
    public DSQuyDinh getQuyDinhBLL(){ 
        return this.dsqd;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("create")){            
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
        new themQuyDinh((Frame) parentWindow,true,this).setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("update")){
            int row=bangQuyDinh.getSelectedRow();
        if(row==-1 ){ 
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quy định");
        }else{ 
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        String ma=(String) bangQuyDinh.getValueAt(row, 0);
        new suaQuyDinh((Frame) parentWindow,true,ma,this).setVisible(true);
                }
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete")){
            int row = bangQuyDinh.getSelectedRow();
        if(row ==  -1 ){ 
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quy định");
        }else{ 
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá");
            if(confirm == JOptionPane.YES_OPTION){ 
                String ma=(String) bangQuyDinh.getValueAt(row, 0);
                dsqd.xoaQD(ma);
                loadData(DSQuyDinh.layAllQuyDinh());
            }
        }
        }else if(e.getSource() == searchBar.getBtnRefesh()){            
  
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
