package UI.Dialog;

import Model.TheLoai;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ChonTheLoai extends JDialog {
    private JTable table;
    private DefaultTableModel tableModel;
    private TheLoai selectTheLoai;
    private JButton xacNhanButton;

    public ChonTheLoai(JFrame parent, ArrayList<TheLoai> danhSach) {
        super(parent, "Chọn thể loại", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Mã thể loại", "Thể loại"});

        for (TheLoai tl : danhSach) {
            tableModel.addRow(new Object[]{
                tl.getMaTheLoai(),
                tl.getTheLoai()
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
                    selectTheLoai = danhSach.get(row);
                    dispose();
                } 
            }
        });
    }

    public TheLoai getSelectTheLoai() {
        return selectTheLoai;
    }
}
