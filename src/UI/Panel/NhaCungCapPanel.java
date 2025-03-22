/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

/**
 *
 * @author Nghia0605
 */
import UI.Component.MainFunction;
import UI.Component.SearchBar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.*;

public class NhaCungCapPanel extends JPanel {
    private JPanel headerPanel, content;
    private DefaultTableModel tableModel;
    private JTable tableNCC;
    private JScrollPane scrollPane;
    private SearchBar searchBar;
    private MainFunction mainFunc;

    public NhaCungCapPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        initComponent();
    }

    public void initComponent() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setLayout(new FlowLayout(0, 0, 4));
        headerPanel.setBackground(Color.white);

        mainFunc = new MainFunction(
                new String[] { "create", "delete", "update", "detail", "exportexcel", "importexcel" });
        headerPanel.add(mainFunc);

        searchBar = new SearchBar(new String[] { "Tất cả", "Mã NCC", "Tên NCC", "Số điện thoại" });
        headerPanel.add(searchBar);
        this.add(headerPanel, BorderLayout.NORTH);

        content = new JPanel();
        content.setLayout(new BorderLayout());
        String[] header = { "Mã NCC", "Tên nhà cung cấp", "Số điện thoại" };
        tableModel = new DefaultTableModel(header, 0);
        tableModel.addRow(new Object[] {
                "1", "Nghia", "0938710605"
        });
        tableNCC = new JTable();
        tableNCC.setModel(tableModel);

        JTableHeader headers = tableNCC.getTableHeader();
        headers.setReorderingAllowed(false);
        headers.setResizingAllowed(false);
        headers.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headers.setBackground(Color.decode("#66B2FF"));

        TableColumnModel column = tableNCC.getColumnModel();
        column.getColumn(0).setPreferredWidth(80);
        column.getColumn(1).setPreferredWidth(500);
        column.getColumn(2).setPreferredWidth(320);

        scrollPane = new JScrollPane(tableNCC);
        // scrollPane.setBorder(Color.WHITE);
        // scrollPane.setBackground(Color.white);
        content.add(scrollPane, BorderLayout.CENTER);
        this.add(content, BorderLayout.CENTER);
    }
}

