/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CTPhuThu extends PhuThu{
    ArrayList<QuyDinh> dsqd=new ArrayList();

    public CTPhuThu() {
    }

    public CTPhuThu(int maPhuThu, int maPhieuTra, double tienPhat) {
        super(maPhuThu, maPhieuTra, tienPhat);
    }

    public ArrayList<QuyDinh> getDsqd() {
        return dsqd;
    }

    public void setDsqd(ArrayList<QuyDinh> dsqd) {
        this.dsqd = dsqd;
    }
    
}
