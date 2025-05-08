/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Nghia0605
 */
public class TKThuThu {    
    private String maThuThu;
    private String tenThuThu;
    private int soPhieuMuon;
    private int soPhieuTra;
    private int soPhieuNhap;

    public TKThuThu(String ma, String ten, int muon, int tra, int nhap) {
        this.maThuThu = ma;
        this.tenThuThu = ten;
        this.soPhieuMuon = muon;
        this.soPhieuTra = tra;
        this.soPhieuNhap = nhap;
    }

    public int tongPhieu() {
        return soPhieuMuon + soPhieuTra + soPhieuNhap;
    }

    public double getPhanTramMuon() {
        return tongPhieu() == 0 ? 0 : (soPhieuMuon * 100.0 / tongPhieu());
    }

    public double getPhanTramTra() {
        return tongPhieu() == 0 ? 0 : (soPhieuTra * 100.0 / tongPhieu());
    }

    public double getPhanTramNhap() {
        return tongPhieu() == 0 ? 0 : (soPhieuNhap * 100.0 / tongPhieu());
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public int getSoPhieuMuon() {
        return soPhieuMuon;
    }

    public void setSoPhieuMuon(int soPhieuMuon) {
        this.soPhieuMuon = soPhieuMuon;
    }

    public int getSoPhieuTra() {
        return soPhieuTra;
    }

    public void setSoPhieuTra(int soPhieuTra) {
        this.soPhieuTra = soPhieuTra;
    }

    public int getSoPhieuNhap() {
        return soPhieuNhap;
    }

    public void setSoPhieuNhap(int soPhieuNhap) {
        this.soPhieuNhap = soPhieuNhap;
    }
        
    
}
