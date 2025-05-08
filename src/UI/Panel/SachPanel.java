/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSSachBLL;
import BLL.DSTacGiaBLL;
import BLL.DSTheLoaiBLL;
import Model.Sach;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.chiTietSach;
import UI.Dialog.suaSach;
import UI.Dialog.themSach;
import UI.Dialog.thongBaoExcel;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author tung7
 */
public class SachPanel extends JPanel implements ItemListener,MouseListener{
    private JPanel headerPanel, content;
    private DefaultTableModel dtm;
    private JTable bangSach;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private JFrame parent;
    private DSSachBLL dsSach = new DSSachBLL();
    private DSTacGiaBLL dstg=new DSTacGiaBLL();
    private DSTheLoaiBLL dstl = new DSTheLoaiBLL();
    public SachPanel()
    {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponet();
        loadData(dsSach.layAllSach());
    }
    
    public void initComponet()
    {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);
        
        String[] function = {"create", "delete", "detail", "update", "exportexcel", "importexcel"};
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function)
        {
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        
        searchBar = new SearchBar(new String[] {"Tất cả", "Mã sách", "Tên sách", "Năm xuất bản"});
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyReleased(KeyEvent e)
                    {
                        String type = (String)searchBar.getCboChoose().getSelectedItem();
                        String text = searchBar.getTxtSearch().getText();
                        loadData(dsSach.searchSach(text,type));
                    }
                });
        
        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] hearder = {"Mã sách", "Tên sách", "Năm xuất bản", "Số lượng", "Đơn giá"};
        dtm = new DefaultTableModel(hearder,0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        bangSach = new JTable();
        bangSach.setFillsViewportHeight(true);
        bangSach.setModel(dtm);
        
        JTableHeader headers = bangSach.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = bangSach.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(400);
        column.getColumn(2).setPreferredWidth(120);
        column.getColumn(3).setPreferredWidth(300);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<bangSach.getColumnCount(); i++)
        {
            bangSach.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(bangSach);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }
    
    public void loadData(ArrayList<Sach> dsSach)
    {
        dtm.setRowCount(0);
        for(Sach s : dsSach)
        {
            if(s.getTrangThai()!=0)
            {
                dtm.addRow(new Object[]
                {
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getNamXuatBan(),
                    s.getSoLuong(),
                    s.getDonGia(),
                });
            }
        }
    }
    
    public DSSachBLL getSachBLL()
    {
        return this.dsSach;
    }
    
    public DSTacGiaBLL getTacGiaBLL()
    {
        return this.dstg;
    }
    
    public DSTheLoaiBLL getTheLoaiBLL()
    {
        return this.dstl;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("create"))
        {
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            new themSach((Frame) parentWindow,true,this).setVisible(true);
        } else if(e.getSource() == mainFunc.getLstBtn().get("update"))
        {
            int row = bangSach.getSelectedRow();
            if(row == -1)
            {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sách ");
            } else 
            {
                Window parentWindow = SwingUtilities.getWindowAncestor(this);
                String maS = (String)bangSach.getValueAt(row, 0);
                new suaSach((Frame) parentWindow,true,maS,this).setVisible(true);
            }
        } else if(e.getSource() == mainFunc.getLstBtn().get("delete"))
        {
            int row = bangSach.getSelectedRow();
            if(row == -1)
            {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần xóa ");
            } else
            {
                int conf = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ");
                if(conf == JOptionPane.YES_OPTION)
                {
                    String maS = (String)bangSach.getValueAt(row, 0);
                    dsSach.xoaSach(maS);
                    loadData(dsSach.layAllSach());
                }
            }
        } else if(e.getSource() == searchBar.getBtnRefesh())
        {
            searchBar.getCboChoose().setSelectedItem(0);
            searchBar.getTxtSearch().setText("");
            loadData(dsSach.layAllSach());
        }else if(e.getSource() == mainFunc.getLstBtn().get("exportexcel"))
        {
            xuatExcel();
        }else if(e.getSource() == mainFunc.getLstBtn().get("importexcel"))
        {
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            new thongBaoExcel((Frame) parentWindow,true).setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("detail"))
        {
            int index = bangSach.getSelectedRow();
            if(index == -1)
            {
                JOptionPane.showMessageDialog(this,"Vui lòng chọn sách");
            } else
            {
                String maS = (String) bangSach.getValueAt(index, 0);
                Window parentWindow = SwingUtilities.getWindowAncestor(this);
                new chiTietSach((Frame) parentWindow,true,maS,this).setVisible(true);
            }
        }
    }
    
    private void xuatExcel()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn noi lưu file");
        int luaChon = fileChooser.showSaveDialog(this);
        
        if(luaChon == JFileChooser.APPROVE_OPTION)
        {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if(!filePath.endsWith(".xlsx"))
            {
                filePath += ".xlsx";
            }
            ArrayList<Sach> ds1 = dsSach.layAllSach();
            ArrayList<Sach> ds2 = new ArrayList<>();
            for(Sach s : ds1)
            {
                if(s.getTrangThai() == 1)
                {
                    ds2.add(s);
                }
            }
            EXCEL.ExportExcel.xuatExcel(ds2, filePath);
            JOptionPane.showMessageDialog(this,"Xuất file thành công");
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
