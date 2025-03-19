/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import MODEL.QuyDinh;
import java.util.ArrayList;

public class DSQuyDinh {
    ArrayList<QuyDinh> dsqd=new ArrayList();

    public DSQuyDinh() {
    }
    public void themQuyDinh(QuyDinh qd){ 
        dsqd.add(qd);
    }

    public ArrayList<QuyDinh> getDsqd() {
        return dsqd;
    }

    
}
