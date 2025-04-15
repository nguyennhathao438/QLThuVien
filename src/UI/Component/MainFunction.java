/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

/**
 *
 * @author Nghia0605
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MainFunction extends JToolBar {
    private HashMap<String, ItemToolBar> lstBtn = new HashMap<>();

    public MainFunction(String[] btnArray) {
        Border border = BorderFactory.createLineBorder(Color.decode("#A0A0A0"), 2);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Chức năng");
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 15));
        titledBorder.setTitleColor(Color.GRAY);
        this.setBorder(titledBorder);
        this.setPreferredSize(new Dimension(480, 110));
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setRollover(true);
        this.setBackground(Color.WHITE);
        this.initData();
        this.initComponent(btnArray);
        assignEvent();
    }

    public void initData() {
        lstBtn.put("create", new ItemToolBar("/img/add.svg", "Thêm", "create"));
        lstBtn.put("delete", new ItemToolBar("/img/delete.svg", "Xóa", "delete"));
        lstBtn.put("update", new ItemToolBar("/img/update.svg", "Sửa", "update"));
        lstBtn.put("detail", new ItemToolBar("/img/detail.svg", "Chi tiết", "detail"));
        lstBtn.put("exportexcel", new ItemToolBar("/img/exportExcel.svg", "Xuất Excel", "exportexcel"));
        lstBtn.put("importexcel", new ItemToolBar("/img/importExcel.svg", "Nhập Excel", "importexcel"));
        lstBtn.put("punish", new ItemToolBar("/img/punish.svg", "Phạt", "punish"));
        lstBtn.put("return", new ItemToolBar("/img/add.svg", "Lập Phiếu Trả", "return"));
    }

    public void initComponent(String[] btnArray) {
        for (String btn : btnArray) {
            this.add(lstBtn.get(btn));
        }

    }

    private void assignEvent() {
        Component[] components = this.getComponents();
        for (Component comp : components) {
            if (comp instanceof ItemToolBar) {
                comp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnHover(e, comp);
                    }
                });
                comp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnDefaultColor(e, comp);
                    }
                });
            }
        }
    }

    private void btnHover(MouseEvent e, Component comp) {
        comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        comp.setBackground(Color.decode("#F0F8FF"));
    }

    private void btnDefaultColor(MouseEvent e, Component comp) {
        comp.setBackground(Color.WHITE);
    }

    public HashMap<String, ItemToolBar> getLstBtn() {
        return lstBtn;
    }

    public void setLstBtn(HashMap<String, ItemToolBar> lstBtn) {
        this.lstBtn = lstBtn;
    }
    
}

