/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import UI.*;
import javax.swing.JFrame;


public class QLThuVien {

    public static void main(String[] args) {
        JFrame frame =new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        //TacGiaPanel pn=new TacGiaPanel();
        PhieuTraPanel pn=new PhieuTraPanel();
        frame.add(pn);
        frame.setVisible(true);
        
    }
    
}
