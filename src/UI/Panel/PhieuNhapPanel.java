/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSNhaCungCapBLL;
import BLL.DSPhieuNhap;
import BLL.DSThuThuBLL;
import MODEL.PhieuNhap;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
/**
 *
 * @author Nghia0605
 */
public class PhieuNhapPanel extends JPanel implements ItemListener, MouseListener{
    private JPanel headerPanel, content, leftContent, rightContent;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JTable tablePN;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private DSPhieuNhap pnBLL = new DSPhieuNhap();
    private JFrame parent;
    private DSNhaCungCapBLL nccBLL = new DSNhaCungCapBLL();    
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private DSThuThuBLL ttBLL = new DSThuThuBLL();
    
    public PhieuNhapPanel(){
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponent();   
        loadData(DSPhieuNhap.getDsPN());
    }
    
    
    public void initComponent(){
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);
        
        String[] function = { "create", "delete", "update", "detail","exportexcel", "importexcel" }; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        
//        for(String func : function){
//            mainFunc.getLstBtn().get(func).addMouseListener(this);
//        }

        searchBar = new SearchBar(new String[] {"Tất cả","Mã phiếu","Tên NCC","Thủ thư","Thời gian","Tổng tiền"});
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){                
                String type = (String)searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText();
                loadData(pnBLL.search(text, type));
            }
        });

        content = new JPanel();
        content.setLayout(new BorderLayout(7,0));
        
        leftContent = new JPanel();
        leftContent.setBackground(Color.blue);
        leftContent.setPreferredSize(new Dimension(200, 680));
//        leftContent.setBorder(new EmptyBorder(0, 0, 0, 10));
        content.add(leftContent, BorderLayout.WEST);
        
        rightContent = new JPanel();
        rightContent.setLayout(new BorderLayout());
        rightContent.setBackground(Color.red);
        rightContent.setPreferredSize(new Dimension(700, 680));
        content.add(rightContent, BorderLayout.CENTER);
        
        String[] header = { "Mã phiếu", "Tên nhà cung cấp", "Thủ thư","Thời gian", "Tổng tiền"};
        tableModel = new DefaultTableModel(header, 0){
            @Override            
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        tablePN = new JTable();
        tablePN.setFillsViewportHeight(true);      
        tablePN.setModel(tableModel);
        
        JTableHeader headers = tablePN.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        
        TableColumnModel column = tablePN.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(230);
        column.getColumn(2).setPreferredWidth(130);
        column.getColumn(3).setPreferredWidth(135);
        column.getColumn(4).setPreferredWidth(125);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tablePN.getColumnCount(); i++){
            tablePN.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(tablePN);
        rightContent.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }
    public void loadData(ArrayList<PhieuNhap> dsPN){
        tableModel.setRowCount(0);       
        for(int i = 0; i < dsPN.size(); i++){
            PhieuNhap pn = dsPN.get(i);
            tableModel.addRow(new Object[]{
                pn.getMaPhieuNhap(),
                !DSNhaCungCapBLL.getTenNCCByMa(pn.getMaNCC()).equals("") ? DSNhaCungCapBLL.getTenNCCByMa(pn.getMaNCC()) : null,
                !DSThuThuBLL.getTenThuThuByMa(pn.getMaThuThu()).equals("") ? DSThuThuBLL.getTenThuThuByMa(pn.getMaThuThu()) : null,
                pn.getThoiGian().format(formatTime),
                pn.getTongTien()
            });
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String)searchBar.getCboChoose().getSelectedItem();
        String text = searchBar.getTxtSearch().getText().trim();
        loadData(pnBLL.search(text, type));
    }

    @Override
    public void mouseClicked(MouseEvent e) {        
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