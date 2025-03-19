/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import UI.QuyDinhPanel;
import javax.swing.JFrame;


public class QLThuVien {

    public static void main(String[] args) {
        JFrame frame =new JFrame();
        frame.setSize(700,500);
        QuyDinhPanel pn=new QuyDinhPanel();
        frame.add(pn);
        frame.setVisible(true);
    }
    
}
