
package MODEL;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class PhieuTra {
    private String maPhieuTra;
    private String maPhieuMuon;
    private String maThuThu;
    private String tenThuThu;
    private String maPhuThu;
    private Date ngayThucTra;
    private int  trangThai;
    public PhieuTra(){ 
        
    }

    public PhieuTra(String maPhieuTra, String maPhieuMuon, String maThuThu, String tenThuThu, String maPhuThu, Date ngayThucTra, int trangThai) {
        this.maPhieuTra = maPhieuTra;
        this.maPhieuMuon = maPhieuMuon;
        this.maThuThu = maThuThu;
        this.tenThuThu = tenThuThu;
        this.maPhuThu = maPhuThu;
        this.ngayThucTra = ngayThucTra;
        this.trangThai = trangThai;
    }

    public String getMaPhieuTra() {
        return maPhieuTra;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public String getMaPhuThu() {
        return maPhuThu;
    }

    public Date getNgayThucTra() {
        return ngayThucTra;
    }

    public int getTrangThai() {
        return trangThai;
    }
    
    public void setMaPhieuTra(String maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public void setMaPhuThu(String maPhuThu) {
        this.maPhuThu = maPhuThu;
    }

    public void setNgayThucTra(Date ngayThucTra) {
        this.ngayThucTra = ngayThucTra;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

   
    
}
