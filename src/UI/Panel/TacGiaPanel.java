
package UI.Panel;

import BLL.DSNhaCungCapBLL;
import BLL.DSTacGiaBLL;
import Model.NhaCungCap;
import Model.TacGia;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.NhaCungCapDialog;
import UI.Dialog.suaTacGia;
import UI.Dialog.themTacGia;
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


public class TacGiaPanel extends JPanel implements ItemListener,MouseListener {
    private JPanel headerPanel, content;
    private DefaultTableModel dtm;
    private JTable bangTacGia;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private JFrame parent;
    private DSTacGiaBLL dstg=new DSTacGiaBLL();
    public TacGiaPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponent();                
        loadData(DSTacGiaBLL.layAllTacGia());
        
    }
    public void initComponent() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);

        String[] function = { "create", "delete", "update" }; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        searchBar = new SearchBar(new String[] { "Tất cả", "Mã Tác Giả", "Tên Tác Giả", "Năm Sinh","Số Điện Thoại" });
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText();
                loadData(dstg.searchQuyDinh(text, type));
            }
        });

        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] header = { "Mã Tác Giả", "Tên Tác Giả", "Năm Sinh" ,"Số Điện Thoại"};
        dtm = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        bangTacGia = new JTable();
        bangTacGia.setFillsViewportHeight(true);      
        bangTacGia.setModel(dtm);

        JTableHeader headers = bangTacGia.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = bangTacGia.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(400);
        column.getColumn(2).setPreferredWidth(120);
        column.getColumn(3).setPreferredWidth(300);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < bangTacGia.getColumnCount(); i++){
            bangTacGia.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(bangTacGia);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }   
    public void loadData(ArrayList<TacGia> dstg){
        dtm.setRowCount(0);   
        for(TacGia a: dstg){
            if(a.getTrangThai()!= 0)
             dtm.addRow(new Object[]{
                a.getMaTacGia(),
                 a.getTenTacGia(),
                 a.getNamSinh(),
                 a.getSoDienThoai(),
            });              
        }
    }
    public DSTacGiaBLL getTacGiaBLL(){ 
        return this.dstg;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("create")){            
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
        new themTacGia((Frame) parentWindow,true,this).setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("update")){
            int row = bangTacGia.getSelectedRow();
        if(row == -1 ){ 
            JOptionPane.showMessageDialog(this," Vui lòng chọn tác giả ");
        }else{
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            String matg=(String) bangTacGia.getValueAt(row, 0);
                new suaTacGia((Frame) parentWindow,true,matg,this).setVisible(true);
        }
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete")){
            int row = bangTacGia.getSelectedRow();
        if(row == -1 ){ 
            JOptionPane.showMessageDialog(this,"Chọn Tác Giả cần xoá");
        }else{ 
            int conf = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xoá");
            if(conf == JOptionPane.YES_OPTION){ 
                String matg=(String) bangTacGia.getValueAt(row, 0);
                dstg.xoaTG(matg);
                loadData(dstg.layAllTacGia());
            }
        }
        }else if(e.getSource() == searchBar.getBtnRefesh()){            
  searchBar.getCboChoose().setSelectedIndex(0);
           searchBar.getTxtSearch().setText("");
           loadData(dstg.layAllTacGia());
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
