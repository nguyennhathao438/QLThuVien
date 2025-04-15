package Model;

import java.util.Date;

public class PhieuMuon {
    private int maPhieuMuon;
    private Date ngayMuon;
    private Date ngayTra;
    private String maDocGia;
    private String maThuThu;
    private int trangThai;
    
    private PhieuMuon(){ 
        
    }
    public PhieuMuon(int maPhieuMuon, Date ngayMuon, Date ngayTra, String maDocGia, String maThuThu, int trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.maDocGia = maDocGia;
        this.maThuThu = maThuThu;
        this.trangThai = trangThai;
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

    public String getMaDocGia() {
        return maDocGia;
    }

    public String getMaThuThu() {
        return maThuThu;
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

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
