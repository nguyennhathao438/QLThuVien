package UI.Dialog;

import Model.TacGia;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ChonTacGia extends JDialog {
    private JTable table;
    private TacGia selectedTacGia;
    private DefaultTableModel tableModel;
    private JButton xacNhanButton;

    public ChonTacGia(JFrame parent, ArrayList<TacGia> danhSach) {
        super(parent, "Chọn tác giả", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        // Khởi tạo model bảng
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Mã tác giả", "Tên tác giả", "Năm sinh", "SĐT"});

        for (TacGia tg : danhSach) {
            tableModel.addRow(new Object[]{
                tg.getMaTacGia(),
                tg.getTenTacGia(),
                tg.getNamSinh(),
                tg.getSoDienThoai()
            });
        }

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        xacNhanButton = new JButton("Xác nhận");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(xacNhanButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        xacNhanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedTacGia = danhSach.get(row);
                    dispose();
                } 
            }
        }); 
    }

    public TacGia getSelectedTacGia() {
        return selectedTacGia;
    }
}
