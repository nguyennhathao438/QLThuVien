package Model;

import java.util.Date;

public class PhieuMuon {
    private String maPhieuMuon;
    private Date ngayMuon;
    private Date ngayTra;
    private String maDocGia;
    private String tenDocGia;
    private String maThuThu;
    private String tenThuThu;
    private int trangThai;
    
    public PhieuMuon(){ 
        
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
