package Model;

import java.time.LocalDate;

public class PhieuMuon {
    private String maPhieuMuon;
    private LocalDate ngayMuon;
    private LocalDate ngayTra;
    private String maDocGia;
    private String maThuThu;
    private int trangThai;

    public PhieuMuon() {
    }
     
    public PhieuMuon(String maPhieuMuon, LocalDate ngayMuon, LocalDate ngayTra, String maDocGia, String maThuThu, int trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.maDocGia = maDocGia;
        this.maThuThu = maThuThu;
        this.trangThai = trangThai;
    }
    
    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
