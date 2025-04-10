/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDateTime;

public class PhieuNhap {
    private String maPhieuNhap;
    private LocalDateTime thoiGian;  
    private double tongTien;
    private String maNCC;
    private String maThuThu;
    private int trangThai;
    
    public PhieuNhap() { 
       // Constructor mặc định
    }

    public PhieuNhap(String maPhieuNhap, LocalDateTime thoiGian, double tongTien, 
                    String maNCC, String maThuThu, int trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
        this.maNCC = maNCC;
        this.maThuThu = maThuThu;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public double getTongTien() {
        return tongTien;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}