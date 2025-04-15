
package MODEL;

import java.util.ArrayList;

public class CTPhat {
    private String maPhuThu;
    private double thanhTien ;
    ArrayList<QuyDinh> dsqd= new ArrayList();

   
    public String getMaPhuThu() {
        return maPhuThu;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
   
    public ArrayList<QuyDinh> getDsqd() {
        return dsqd;
    }
    public void setMaPhuThu(String maPhuThu) {
        this.maPhuThu = maPhuThu;
    }

    public void setDsqd(ArrayList<QuyDinh> dsqd) {
        this.dsqd = dsqd;
    }

    
}
