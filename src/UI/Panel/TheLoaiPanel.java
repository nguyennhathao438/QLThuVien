/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSTheLoaiBLL;
import Model.TheLoai;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.suaTacGia;
import UI.Dialog.suaTheLoai;
import UI.Dialog.themTacGia;
import UI.Dialog.themTheLoai;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author tung7
 */
public class TheLoaiPanel extends JPanel implements ItemListener,MouseListener{
    private JPanel headerPanel, content;
    private JFrame parent;
    private DefaultTableModel dtm;
    private JTable bangTheLoai;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    DSTheLoaiBLL dsTheLoai = new DSTheLoaiBLL();
    
    public TheLoaiPanel()
    {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        initComponet();
        loadData(dsTheLoai.layALLTheLoai());
    }
    
    public void initComponet()
    {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);
        
        String[] function = {"create", "delete", "update", "exportexcel", "importexcel"};
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for(String func : function)
        {
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        
        searchBar = new SearchBar(new String[] {"Tất cả", "Mã thể loại", "Tên thể loại"});
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
                        loadData(dsTheLoai.searchTheLoai(type,text));
                    }
                });
        
        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] hearder = {"Mã thể loại", "Thể loại"};
        dtm = new DefaultTableModel(hearder,0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        bangTheLoai = new JTable();
        bangTheLoai.setFillsViewportHeight(true);
        bangTheLoai.setModel(dtm);
        
        JTableHeader headers = bangTheLoai.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = bangTheLoai.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(400);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<bangTheLoai.getColumnCount(); i++)
        {
            bangTheLoai.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(bangTheLoai);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }

    public void loadData(ArrayList<TheLoai> dsTL)
    {
        dtm.setRowCount(0);
        for(TheLoai tl : dsTL)
        {
            if(tl.getTrangThai() != 0 )
            {
                dtm.addRow(new Object[]
                {
                    tl.getMaTheLoai(),
                    tl.getTheLoai(),
                });
            }
        }
    }
    
    public DSTheLoaiBLL getTheLoaiBLL()
    {
        return this.dsTheLoai;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        if(e.getSource() == mainFunc.getLstBtn().get("create"))
        {            
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            new themTheLoai((Frame) parentWindow,true,this).setVisible(true);
        }else if(e.getSource() == mainFunc.getLstBtn().get("update"))
        {
            int row = bangTheLoai.getSelectedRow();
        if(row == -1 )
        { 
            JOptionPane.showMessageDialog(this," Vui lòng chọn thể loại ");
        }else
        {
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            String matl=(String) bangTheLoai.getValueAt(row, 0);
            new suaTheLoai((Frame) parentWindow,true,matl,this).setVisible(true);
        }
        }else if(e.getSource() == mainFunc.getLstBtn().get("delete"))
        {
            int row = bangTheLoai.getSelectedRow();
        if(row == -1 )
        { 
            JOptionPane.showMessageDialog(this,"Chọn thể loại cần xoá");
        }else
        { 
            int conf = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xoá");
            if(conf == JOptionPane.YES_OPTION)
            { 
                String matl=(String) bangTheLoai.getValueAt(row, 0);
                dsTheLoai.xoaTheLoai(matl);
                loadData(dsTheLoai.layALLTheLoai());
            }
        }
        }else if(e.getSource() == searchBar.getBtnRefesh())
        {            
           searchBar.getCboChoose().setSelectedIndex(0);
           searchBar.getTxtSearch().setText("");
           loadData(dsTheLoai.layALLTheLoai());
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
