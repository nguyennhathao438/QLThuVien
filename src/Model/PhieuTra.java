
package MODEL;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class PhieuTra {
    private int maPhieuTra;
    private int maPhieuMuon;
    private int maThuThu;
    private String tenThuThu;  
    private Date ngayThucTra;
    private int  trangThai;
    public PhieuTra(){ 
        
    }

    public PhieuTra(int maPhieuTra, int maPhieuMuon, int maThuThu, String tenThuThu, Date ngayThucTra) {
        this.maPhieuTra = maPhieuTra;
        this.maPhieuMuon = maPhieuMuon;
        this.maThuThu = maThuThu;
        this.tenThuThu = tenThuThu;
        this.ngayThucTra = ngayThucTra;
    }

    public int getMaPhieuTra() {
        return maPhieuTra;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public int getMaThuThu() {
        return maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public Date getNgayThucTra() {
        return ngayThucTra;
    }

    public void setMaPhieuTra(int maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaThuThu(int maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public void setNgayThucTra(Date ngayThucTra) {
        this.ngayThucTra = ngayThucTra;
    }
    
}
