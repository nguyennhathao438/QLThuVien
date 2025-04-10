
package MODEL;

import java.util.Date;

public class PhieuMuon {
    private int maPhieuMuon;
    private Date ngayMuon;
    private Date ngayTra;
    private String tenDocGia;
    private String tenThuThu;
    private int trangThai;
    private PhieuMuon(){ 
        
    }
    public PhieuMuon(int maPhieuMuon, Date ngayMuon, Date ngayTra, String tenDocGia, String tenThuThu) {
        this.maPhieuMuon = maPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tenDocGia = tenDocGia;
        this.tenThuThu = tenThuThu;
    }
    
    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }
    
}
