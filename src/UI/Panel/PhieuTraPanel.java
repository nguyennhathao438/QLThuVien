/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import BLL.DSPhieuTraBLL;
import BLL.DSThuThuBLL;
import MODEL.CTPhat;
import MODEL.PhieuTra;
import MODEL.QuyDinh;
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import UI.Dialog.CTPhieuTraDiaLog;
import UI.Dialog.chiTietPhat;
import UI.Dialog.phat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class PhieuTraPanel extends JPanel implements ItemListener, MouseListener {

    private JPanel headerPanel, content, panelChiTiet;
    private DefaultTableModel dtm, dtmChiTiet;
    private JTable bangPhieuTra, bangChiTiet;
    private JScrollPane scrollPane, scrollChiTiet;
    private SearchBar searchBar;
    private MainFunction mainFunc;
    private JFrame parent;
    private DSPhieuTraBLL ptbll = new DSPhieuTraBLL();
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

        String[] function = {"delete", "detail", "punish"}; //"detail", 
        mainFunc = new MainFunction(function);
        headerPanel.add(mainFunc);
        for (String func : function) {
            mainFunc.getLstBtn().get(func).addMouseListener(this);
        }
        searchBar = new SearchBar(new String[]{"Tất cả", "Mã Phiếu Trả", "Mã Phiếu Mượn", "Tên Thủ Thư"});
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);
        searchBar.getCboChoose().addItemListener(this);
        searchBar.getBtnRefesh().addMouseListener(this);
        searchBar.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) searchBar.getCboChoose().getSelectedItem();
                String text = searchBar.getTxtSearch().getText();
                loadData(ptbll.searchQuyDinh(text, type));
            }
        });

        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] header = {"Mã Phiếu Trả", "Mã Phiếu Mượn", "Ngày Thực Trả", "Tên Thủ Thư", "Phụ Thu"};
        dtm = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
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
        for (int i = 0; i < bangPhieuTra.getColumnCount(); i++) {
            bangPhieuTra.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        scrollPane = new JScrollPane(bangPhieuTra);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
        bangPhieuTra.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = bangPhieuTra.getSelectedRow();
                double tienphat = (double) bangPhieuTra.getValueAt(row,4);
                if(tienphat > 0){
                taoPanelQuyDinh();
                panelChiTiet.setVisible(true);
                String maqd=(String) bangPhieuTra.getValueAt(row, 0);
                loadDataQD(maqd);
                }else{ 
                    if(panelChiTiet !=  null){
                    panelChiTiet.setVisible(false);
                content.remove(panelChiTiet);
                content.revalidate();
                content.repaint();
                }
                }
            }
        });
    }

    public void taoPanelQuyDinh() {
        
        if (panelChiTiet != null) {
            content.add(panelChiTiet, BorderLayout.SOUTH);
            return;
        }
        panelChiTiet = new JPanel(new BorderLayout());
        panelChiTiet.setPreferredSize(new Dimension(900, 200));
        panelChiTiet.setVisible(false);
        bangChiTiet = new JTable();
        
        String[] headerChiTiet = {"Mã Quy Định", "Tên Quy Định", "Tiền Phạt"};
        dtmChiTiet = new DefaultTableModel(headerChiTiet, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        bangChiTiet = new JTable(dtmChiTiet);
        JTableHeader headers = bangChiTiet.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));
        bangChiTiet.setFillsViewportHeight(true);

        scrollChiTiet = new JScrollPane(bangChiTiet);
        panelChiTiet.add(scrollChiTiet, BorderLayout.CENTER);
        JButton btn = new JButton("Quay lại");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelChiTiet.setVisible(false);
                content.remove(panelChiTiet);
                content.revalidate();
                content.repaint();
            }

        });
        JPanel panelql = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelql.add(btn);
        panelChiTiet.add(panelql, BorderLayout.NORTH);
        content.add(panelChiTiet, BorderLayout.SOUTH);
        panelChiTiet.setVisible(true);
    }

    public void loadData(ArrayList<PhieuTra> dspt) {
        dtm.setRowCount(0);
        for (PhieuTra a : dspt) {
            if (a.getTrangThai() != 0) {
                dtm.addRow(new Object[]{
                    a.getMaPhieuTra(),
                    a.getMaPhieuMuon(),
                    a.getNgayThucTra(),
                    ttbll.getTenThuThuByMa(a.getMaThuThu()),
                    ptbll.getTienPhat(a.getMaPhieuTra())
                });
            }
        }
    }

    public void loadDataQD(String maPhieuTra) {
        CTPhat ctp = ptbll.getChiTietPhat(maPhieuTra);
        dtmChiTiet.setRowCount(0);
        for (QuyDinh a : ctp.getDsqd()) {
            dtmChiTiet.addRow(new Object[]{a.getMaQuyDinh(), a.getNoiDung(), a.getSoTien()});
        }
    }

    public DSPhieuTraBLL getPhieuTraBLL() {
        return this.ptbll;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (e.getSource() == mainFunc.getLstBtn().get("punish")) {
            int row = bangPhieuTra.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(bangPhieuTra, "Vui lòng chọn phiếu trả để phạt");
            } else {
                double kt = (double) bangPhieuTra.getValueAt(row, 4);
                if (kt > 0) {
                    JOptionPane.showMessageDialog(content, "Phiếu trả này đã phạt rồi ");
                } else {
                    String maPTra = (String) bangPhieuTra.getValueAt(row, 0);
                    Window parentWindow = SwingUtilities.getWindowAncestor(this);
                    new phat((Frame) parentWindow, true, maPTra, this).setVisible(true);
                }
            }
        } else if (e.getSource() == mainFunc.getLstBtn().get("delete")) {
            int row = bangPhieuTra.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(bangPhieuTra, "Vui lòng chọn phiếu trả để phạt");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá");
                if (confirm == JOptionPane.YES_OPTION) {
                    String maPtra = (String) bangPhieuTra.getValueAt(row, 0);
                    ptbll.xoaPhieuTra(maPtra);
                    loadData(ptbll.getAllPhieuTra());
                }
            }
        } else if (e.getSource() == searchBar.getBtnRefesh()) {
            searchBar.getCboChoose().setSelectedIndex(0);
            searchBar.getTxtSearch().setText("");
            loadData(ptbll.getAllPhieuTra());
        } else if (e.getSource() == mainFunc.getLstBtn().get("detail")) {
            int row = bangPhieuTra.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(bangPhieuTra, "Vui lòng chọn phiếu trả");
            } else {
                Window parentWindow = SwingUtilities.getWindowAncestor(this);
                PhieuTra pt =new PhieuTra();
                pt.setMaPhieuTra((String) bangPhieuTra.getValueAt(row, 0));
                pt.setMaPhieuMuon((String) bangPhieuTra.getValueAt(row, 1));
                pt.setNgayThucTra((Date) bangPhieuTra.getValueAt(row, 2));
                new CTPhieuTraDiaLog((JFrame) (Frame) parentWindow, true, pt).setVisible(true);
            }
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
