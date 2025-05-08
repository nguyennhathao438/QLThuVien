/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSNhaCungCapBLL;
import BLL.DSPhieuNhap;
import BLL.DSThuThuBLL;
import MODEL.PhieuNhap;
import Model.NhaCungCap;
import Model.ThuThu;
import UI.Component.InputField;
import UI.Component.InputSupportField;
import UI.Component.MainFunction;
import UI.Component.RoundedPanel;
import UI.Component.SearchBar;
import UI.Dialog.CTPhieuNhapDialog;
import UI.Dialog.InputSupportDialog;
import UI.MainFrame;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
    private MainFrame mainFrame;
    private InputField fromDate,toDate, fromMoney, toMoney;
    private InputSupportField nccField, ttField;
    private JButton btnFilter;    
    private JButton btnReset;
    private CTPhieuNhapDialog ctpnDialog;
    
    
    public PhieuNhapPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
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
        
        String[] function = { "create", "delete", "detail" }; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        
        for(String func : function){
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }

        searchBar = new SearchBar(new String[] {"Tất cả","Mã phiếu","Tên NCC","Thủ thư","Thời gian","Tổng tiền"});
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getBtnRefesh().addMouseListener(this);
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
        content.setBackground(Color.white);
        content.setLayout(new BorderLayout(7,0));
        
        
        leftContent = new RoundedPanel(17);
        leftContent.setPreferredSize(new Dimension(240, 680));        
        leftContent.setBackground(Color.white);
        content.add(leftContent, BorderLayout.WEST);
        
        JPanel topLeftContent = new JPanel();     
        topLeftContent.setPreferredSize(new Dimension(230, 500));
        topLeftContent.setLayout(new BoxLayout(topLeftContent, BoxLayout.Y_AXIS));
//        topLeftContent.setBorder(new EmptyBorder(0, 7, 0, 7)); 
        topLeftContent.setBackground(Color.white);       
        leftContent.add(topLeftContent);
        
        fromDate = new InputField("Từ ngày", 220, 40,true);
        toDate = new InputField("Đến ngày", 220, 40,true);        
        fromMoney = new InputField("Từ số tiền (VND)", 220, 40,false);          
        toMoney = new InputField("Đến số tiền (VND)", 220, 40,false); 
        nccField = new InputSupportField("Nhà cung cấp", 220, 40);   
        nccField.getBtnSupport().addMouseListener(this);
        ttField = new InputSupportField("Thủ thư", 220, 40);
        ttField.getBtnSupport().addMouseListener(this);
        
        topLeftContent.add(nccField);
        topLeftContent.add(Box.createVerticalStrut(15));
        topLeftContent.add(ttField);
        topLeftContent.add(fromDate);
        topLeftContent.add(Box.createVerticalStrut(15));
        topLeftContent.add(toDate);
        topLeftContent.add(Box.createVerticalStrut(15));
        topLeftContent.add(fromMoney);
        topLeftContent.add(Box.createVerticalStrut(15));
        topLeftContent.add(toMoney);        
        
        
        JPanel pnButton = new JPanel();
        pnButton.setPreferredSize(new Dimension(220, 50));
        pnButton.setBackground(Color.white);
        topLeftContent.add(Box.createVerticalStrut(15));
        topLeftContent.add(pnButton);
        
        
        btnFilter = new JButton("Lọc");
        btnFilter.setPreferredSize(new Dimension(100, 40));
        btnFilter.setFocusPainted(false);
        btnFilter.setBackground(Color.decode("#00994C"));
        btnFilter.setForeground(Color.WHITE);
        btnFilter.setFont(new Font("Segoe UI", Font.BOLD, 18));  
        btnFilter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFilter.addMouseListener(this);
        pnButton.add(btnFilter);
        
        btnReset = new JButton("Đặt lại");
        btnReset.setPreferredSize(new Dimension(100, 40));
        btnReset.setFocusPainted(false);
        btnReset.setBackground(Color.decode("#00994C"));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 18));  
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.addMouseListener(this);
        pnButton.add(btnReset);
        
        rightContent = new JPanel();
        rightContent.setLayout(new BorderLayout());
        rightContent.setBackground(Color.red);
        rightContent.setPreferredSize(new Dimension(650, 680));
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
        if(dsPN != null){
            for(int i = 0; i < dsPN.size(); i++){
                PhieuNhap pn = dsPN.get(i);
                String tenNCC = DSNhaCungCapBLL.getTenNCCByMa(pn.getMaNCC());
                String tenTT = DSThuThuBLL.getTenThuThuByMa(pn.getMaThuThu());
                tableModel.addRow(new Object[]{
                    pn.getMaPhieuNhap(),    
                    !tenNCC.equals("") ? tenNCC : null,
                    !tenTT.equals("") ? tenTT : null,
                    pn.getThoiGian().format(formatTime),
                    pn.getTongTien()
                });
            }
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
        Object obj = e.getSource();
        if(obj == searchBar.getBtnRefesh()){
            searchBar.getCboChoose().setSelectedIndex(0);
            searchBar.getTxtSearch().setText("");
            loadData(DSPhieuNhap.getDsPN());
        } else if(obj == mainFunc.getLstBtn().get("create")){            
            TaoPhieuNhapPanel taophieunhap = new TaoPhieuNhapPanel(mainFrame);
            mainFrame.setRightPanel(taophieunhap);
        } else if(obj == mainFunc.getLstBtn().get("detail")){
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            int index = tablePN.getSelectedRow();
            if(index != -1){
                String ma = (String)tableModel.getValueAt(index, 0);
                ctpnDialog = new CTPhieuNhapDialog(parent, pnBLL.getPNByMa(ma));
                ctpnDialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(parent, "Chọn phiếu nhập cần xem chi tiết", "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
            }                        
        } else if(obj == mainFunc.getLstBtn().get("delete")){
            int index = tablePN.getSelectedRow();
            if(index != -1){
                String maPN = tablePN.getValueAt(index, 0).toString();
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu nhập không?", "XÓA PHIẾU", 
                        JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(input == JOptionPane.OK_OPTION){
                    if(pnBLL.delete(maPN)){
                        JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thành công!", "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                        loadData(pnBLL.getDsPN());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thất bại!", "LỖI", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(parent, "Chọn phiếu nhập cần xóa", "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(obj == this.btnFilter){
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ncc = nccField.getTxtInput().getText().trim();
            String tt = ttField.getTxtInput().getText().trim();
            Date fromDate = null;
            if(this.fromDate.getDate() != null){
                fromDate = this.fromDate.getDate();
            }
            Date toDate = null;
            if(this.toDate.getDate() != null){
                toDate = this.toDate.getDate();
            }
            String fromMoneyStr = fromMoney.getTxtInput().getText().trim();
            String toMoneyStr = toMoney.getTxtInput().getText().trim();        
            ArrayList<PhieuNhap> result = pnBLL.advancedSearch(ncc, tt, fromDate, toDate, fromMoneyStr, toMoneyStr);
            loadData(result);          
        } else if(obj == nccField.getBtnSupport()){
            parent = (JFrame)SwingUtilities.getWindowAncestor(this);
            String[] header = {"Mã NCC", "Tên NCC", "Số điện thoại"};
            InputSupportDialog<NhaCungCap> dialog = new InputSupportDialog<>(parent,"Chọn nhà cung cấp", header, DSNhaCungCapBLL.getdsNCC());
            dialog.setVisible(true);
            NhaCungCap ncc = dialog.getSelectedItem();
            if(ncc != null){
                nccField.getTxtInput().setText(ncc.getTenNCC());
            }
        } else if(obj == ttField.getBtnSupport()){
            parent = (JFrame)SwingUtilities.getWindowAncestor(this);
            String[] header = {"Mã thủ thư", "Tên thủ thư", "Số điện thoại"};
            InputSupportDialog<ThuThu> dialog = new InputSupportDialog<>(parent,"Chọn nhà cung cấp", header, DSThuThuBLL.getDsThuThu());
            dialog.setVisible(true);
            ThuThu tt = dialog.getSelectedItem();
            if(tt != null){
                ttField.getTxtInput().setText(tt.getTenThuThu());
            }
        } else if(obj == this.btnReset){
            nccField.getTxtInput().setText("");
            ttField.getTxtInput().setText("");
            fromDate.setDate(null);
            toDate.setDate(null);
            fromMoney.getTxtInput().setText("");
            toMoney.getTxtInput().setText("");
            
            loadData(DSPhieuNhap.getDsPN());
        }
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