
package MODEL;

import java.util.ArrayList;
import java.util.Date;

public class CTPhieuNhap extends PhieuNhap{
    private int maNCC;
    private int maThuThu;
    private ArrayList<SachNhap> dssn=new ArrayList();

    public CTPhieuNhap() {
        
    }

    public CTPhieuNhap(int maNCC, int maThuThu, int maPhieuNhap, Date thoiGian, Double tongTien, String tenNCC) {
        super(maPhieuNhap, thoiGian, tongTien, tenNCC);
        this.maNCC = maNCC;
        this.maThuThu = maThuThu;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public int getMaThuThu() {
        return maThuThu;
    }

    public ArrayList<SachNhap> getDssn() {
        return dssn;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setMaThuThu(int maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setDssn(ArrayList<SachNhap> dssn) {
        this.dssn = dssn;
    }
    
    
}
