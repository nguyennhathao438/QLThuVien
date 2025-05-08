/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Dialog;

import Model.NhaCungCap;
import UI.Component.InputField;
import UI.Panel.NhaCungCapPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Nghia0605
 */
public class NhaCungCapDialog extends JDialog implements MouseListener{
    private InputField maNCC,tenNCC,sdtNCC,trangthaiNCC = null;
    private NhaCungCap nccModel;
    private JButton btnAdd,btnUpdate,btnExit;
    private JPanel pnCenter, pnBottom;
    private NhaCungCapPanel pnNCC;
    
    public NhaCungCapDialog(JFrame frame, String title,String type,NhaCungCapPanel pnNCC){
        super(frame,true);
        this.pnNCC = pnNCC;        
//        tenNCC = new InputField("Tên nhà cung cấp", 200, 40);
//        sdtNCC = new InputField("Số điện thoại", 200, 40);
//        trangthaiNCC = new InputField("Trạng thái", 200, 40);
        this.init(type, title);
        this.setLocationRelativeTo(frame);        
    }
    
    public NhaCungCapDialog(JFrame frame, String title,String type, NhaCungCap nccModel, NhaCungCapPanel pnNCC){
        super(frame,true);
        this.nccModel = nccModel;
        this.pnNCC = pnNCC;
        this.setLocationRelativeTo(frame); 
//        if(type.equals("update")){
//            maNCC = new InputField("Mã nhà cung cấp", String.valueOf(nccModel.getMaNCC()), 200, 40);
//            trangthaiNCC = new InputField("Trạng thái", nccModel.getTrangThai() == 1 ? "Đang hoạt động" : "Ngừng hoạt động" , 200, 40);
//        }
//        tenNCC = new InputField("Tên nhà cung cấp", 200, 40);
//        sdtNCC = new InputField("Số điện thoại", 200, 40);
//        trangthaiNCC = new InputField("Trạng thái", 200, 40);
        this.init(type, title);
        this.setLocationRelativeTo(frame);
    }
    
    public void init(String type,String title){
        this.setSize(new Dimension(400, 300));
        this.setTitle(title);
        this.setLayout(new BorderLayout(0, 0));
        
        pnCenter = new JPanel();
        pnCenter.setLayout(new GridLayout(3,1));
        pnCenter.setBackground(Color.WHITE);
        pnCenter.setPreferredSize(new Dimension(300, 220));
        
        maNCC = new InputField("Mã nhà cung cấp", 200, 40);
        if(type.equals("update")){
            maNCC.getTxtInput().setEditable(false);
        }
        tenNCC = new InputField("Tên nhà cung cấp", 200, 40);
        sdtNCC = new InputField("Số điện thoại", 200, 40);
        
        if(maNCC != null){
            pnCenter.add(maNCC);
        }
        pnCenter.add(tenNCC);
        pnCenter.add(sdtNCC);
        if(trangthaiNCC != null){
            pnCenter.add(trangthaiNCC);
        }
        
        pnBottom = new JPanel();
        pnBottom.setBackground(Color.white);
        pnBottom.setLayout(new FlowLayout(1, 10, 17));
        pnBottom.setPreferredSize(new Dimension(300, 80));
        pnBottom.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        Font font = new Font("Segoe UI",Font.BOLD, 16);
        
        btnAdd = new JButton("Thêm");
        btnAdd.setFocusPainted(false);
        btnAdd.setFont(font);
        btnAdd.addMouseListener(this);
        
        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFont(font);
        btnUpdate.addMouseListener(this);
        
        btnExit = new JButton("Thoát");
        btnExit.setFocusPainted(false);
        btnExit.setFont(font);
        btnExit.addMouseListener(this);
        
        switch (type) {
            case "create":
                pnBottom.add(btnAdd);
                break;
            case "update":        
                maNCC.getTxtInput().setText(nccModel.getMaNCC());
                tenNCC.setTxtInput(nccModel.getTenNCC());
                sdtNCC.setTxtInput(nccModel.getSoDienThoai());
//                trangthaiNCC.setTxtInput(String.valueOf(nccModel.getTrangThai()));
                pnBottom.add(btnUpdate);
                break;                         
            default:
                throw new AssertionError();
        }
        pnBottom.add(btnExit);
        
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnBottom, BorderLayout.SOUTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {  
        if(e.getSource() == btnExit){
            dispose();
        }
        else if(e.getSource() == btnAdd){     
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(maNCC.getTxtInput().getText());
            ncc.setTenNCC(tenNCC.getTxtInput().getText());
            ncc.setSoDienThoai(sdtNCC.getTxtInput().getText());
//            ncc.setTrangThai(Integer.parseInt(trangthaiNCC.getTxtInput().getText()));
            if(pnNCC.getNccBLL().themNCC(ncc)){
                dispose();
                JOptionPane.showMessageDialog(null, "Thêm thành công !", "THÔNG BÁ0", JOptionPane.INFORMATION_MESSAGE);
                this.pnNCC.loadData(pnNCC.getNccBLL().getdsNCC());
               
            }
        }
        else{
            if(e.getSource() == btnUpdate){
                NhaCungCap updateNCC = new NhaCungCap();
                updateNCC.setMaNCC(nccModel.getMaNCC().trim());
                updateNCC.setTenNCC(tenNCC.getTxtInput().getText().trim());
                updateNCC.setSoDienThoai(sdtNCC.getTxtInput().getText().trim());                 
                if(pnNCC.getNccBLL().capnhatNCC(updateNCC)){
                    dispose();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công !", "THÔNG BÁ0", JOptionPane.INFORMATION_MESSAGE);
                    this.pnNCC.loadData(pnNCC.getNccBLL().getdsNCC());
                    
                }
            }
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
