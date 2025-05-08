/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Panel;

import UI.Component.RoundedPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Nghia0605
 */
public class TrangChuPanel extends JPanel{
    private JPanel header,centerHeader,content,pn1,pn2,pn3;
    private JPanel pn1Header,pn1Content,pn2Header,pn2Content,pn3Header,pn3Content;
    private JLabel lbTitle,lbIcon,lbOpenDoorIcon,lbGhimIcon,lbRuleIcon;    
    
    public TrangChuPanel(){
//        this.setBorder(BorderFactory.createEmptyBorder(7, 6, 7, 6));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);            
        initComponent();  
    }
    
    public void initComponent(){
        header = new JPanel();
        header.setLayout(new BorderLayout(0,0));        
        header.setPreferredSize(new Dimension(900, 250));
//        header.setBackground(Color.decode("#96D6FF"));
        header.setBackground(Color.decode("#1FA4BB"));
        header.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        FlatSVGIcon icon = new FlatSVGIcon(getClass().getResource("/img/logo1.svg")).derive(150, 150);
        lbIcon = new JLabel(icon);        
        header.add(lbIcon, BorderLayout.NORTH);
        
        centerHeader = new JPanel();
        centerHeader.setLayout(new BorderLayout(0,0));
        centerHeader.setPreferredSize(new Dimension(900, 100));
//        centerHeader.setBackground(Color.decode("#96D6FF"));
        centerHeader.setBackground(Color.decode("#1FA4BB"));
        header.add(centerHeader, BorderLayout.CENTER);  
        
        lbTitle = new JLabel("<html><h1 style='text-align:center; color:white; font-family: Arial'>HỆ THỐNG QUẢN LÝ THƯ VIỆN</h1>"
        + "<span style='text-align:center;font-size: 13px;font-family: Arial'>--MỖI TRANG SÁCH,MỖI BƯỚC TIẾN TRONG HÀNH TRÌNH HỌC HỎI--</span></html>", JLabel.CENTER);
        centerHeader.add(lbTitle, BorderLayout.CENTER);
        
        
        content = new JPanel();
        content.setLayout(new FlowLayout(1,25,60));        
        content.setBackground(Color.decode("#1FA4BB"));
        content.setPreferredSize(new Dimension(900, 550));
        
        pn1 = new RoundedPanel(30);
        pn1.setBackground(Color.WHITE);        
        pn1.setPreferredSize(new Dimension(250, 400));       
        pn1.setLayout(new BorderLayout(0,0));        
        content.add(pn1);
        
        pn1Header = new RoundedPanel(30);
        pn1Header.setPreferredSize(new Dimension(250, 100)); 
        pn1Header.setBackground(Color.WHITE);       
//        pn1Header.setBorder(BorderFactory.createLineBorder(Color.yellow));
        FlatSVGIcon opendoorIcon = new FlatSVGIcon(getClass().getResource("/img/opendoor.svg")).derive(100, 100);
        lbOpenDoorIcon = new JLabel(opendoorIcon);
        lbOpenDoorIcon.setBackground(Color.WHITE);
        pn1Header.add(lbOpenDoorIcon);
        pn1.add(pn1Header, BorderLayout.NORTH);
        
        pn1Content = new RoundedPanel(30);
        pn1Content.setPreferredSize(new Dimension(250, 300));
        pn1Content.setLayout(new GridLayout(5, 1, 0, 0));
        pn1Content.setBackground(Color.white);
        pn1.add(pn1Content, BorderLayout.CENTER);
        
        JLabel pn1_Lb1 = new JLabel("Giờ mở cửa",JLabel.CENTER);
        pn1_Lb1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        pn1Content.add(pn1_Lb1);
        
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        JLabel pn1_Lb2 = new JLabel("Thứ Hai - Thứ Sáu: 8:00 - 17:00", JLabel.CENTER);
        pn1_Lb2.setFont(font);
        pn1Content.add(pn1_Lb2);
                
        JLabel pn1_Lb3 = new JLabel("Thứ Bảy - Chủ Nhật: 9:00 - 11:30", JLabel.CENTER);
        pn1_Lb3.setFont(font);
        pn1Content.add(pn1_Lb3);        
        
        JLabel pn1_Lb4 = new JLabel("Liên hệ: 0123-456-789", new FlatSVGIcon(getClass().getResource("/img/phone.svg")).derive(20, 20), JLabel.CENTER);
        pn1_Lb4.setFont(font);
        pn1Content.add(pn1_Lb4);      
        
        JPanel pnAddress = new RoundedPanel(30);
        pnAddress.setOpaque(false);
        pnAddress.setBackground(Color.white);        
        pnAddress.setLayout(new FlowLayout(1, 7, 0));
        
         
        FlatSVGIcon locationIcon = new FlatSVGIcon(getClass().getResource("/img/location.svg")).derive(20, 20);
        JLabel lbLocationIcon = new JLabel(locationIcon);
        pnAddress.add(lbLocationIcon); 
        
        JLabel pn1_Lb5 = new JLabel("<html><span>Địa chỉ: 273 An Dương Vương, Phường 3, Quận 5, TP.HCM</span></html>", JLabel.CENTER);        
        pn1_Lb5.setFont(font);
        pn1_Lb5.setPreferredSize(new Dimension(200, 50));
        pnAddress.add(pn1_Lb5);            
        pn1Content.add(pnAddress);
   
        pn2 = new RoundedPanel(30);
        pn2.setBackground(Color.white);
        pn2.setPreferredSize(new Dimension(250, 400));
        content.add(pn2);
        
        pn2Header = new RoundedPanel(30);
        pn2Header.setPreferredSize(new Dimension(250, 90)); 
        pn2Header.setBackground(Color.WHITE);   
        FlatSVGIcon ghimIcon = new FlatSVGIcon(getClass().getResource("/img/ghim.svg")).derive(80, 80);
        lbGhimIcon = new JLabel(ghimIcon);
        pn2Header.add(lbGhimIcon);
        pn2.add(pn2Header, BorderLayout.NORTH);
        
        pn2Content = new RoundedPanel(30);
        pn2Content.setPreferredSize(new Dimension(250, 300));
        pn2Content.setLayout(new FlowLayout(1,0,17));
        pn2Content.setBackground(Color.white);
        pn2.add(pn2Content, BorderLayout.CENTER);
        
        JLabel pn2_Lb1 = new JLabel("Nội quy chung",JLabel.CENTER);
        pn2_Lb1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        pn2Content.add(pn2_Lb1);
                
        JLabel pn2_Lb2 = new JLabel("<html><div style='width: 200px;text-align: center;'>Không mang tài liệu, sách ra khỏi thư viện khi chưa làm thủ tục mượn.</div></html>", JLabel.CENTER);
        pn2_Lb2.setFont(font);
        pn2_Lb2.setBorder(new EmptyBorder(17, 0, 0, 0));
        pn2Content.add(pn2_Lb2);

        JLabel pn2_Lb3 = new JLabel("<html><div style='width: 200px;text-align: center;'>Không mang thức ăn, nước uống vào thư viện</div></html>", JLabel.CENTER);
        pn2_Lb3.setFont(font);
        pn2_Lb3.setBorder(new EmptyBorder(8, 0, 0, 0));
        pn2Content.add(pn2_Lb3);

        JLabel pn2_Lb4 = new JLabel("<html><div style='width: 200px;text-align: center;'>Giữ gìn trật tự, không gây ồn ào</div></html>", JLabel.CENTER);
        pn2_Lb4.setFont(font);
        pn2_Lb4.setBorder(new EmptyBorder(8, 0, 0, 0));
        pn2Content.add(pn2_Lb4);

        JLabel pn2_Lb5 = new JLabel("<html><div style='width: 200px;text-align: center'>Giữ gìn vệ sinh chung, không vẽ bậy hoặc làm hư hỏng tài sản thư viện.</div></html>", JLabel.CENTER);
        pn2_Lb5.setFont(font);   
        pn2_Lb5.setBorder(new EmptyBorder(6, 0, 0, 0));
        pn2Content.add(pn2_Lb5);

        
        pn3 = new RoundedPanel(30);
        pn3.setBackground(Color.white);
        pn3.setPreferredSize(new Dimension(250, 400));
        content.add(pn3);
        
        this.add(header, BorderLayout.NORTH);
        this.add(content,BorderLayout.CENTER);
    }
}
