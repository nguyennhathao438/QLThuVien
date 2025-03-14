/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.Date;

public class PhieuNhap {
    private int maPhieuNhap;
    private Date thoiGian;
    private Double tongTien;
    private String tenNCC;
    private String tenThuThu;
    private int trangThai;
   public PhieuNhap(){ 
       
   }
    public PhieuNhap(int maPhieuNhap, Date thoiGian, Double tongTien, String tenNCC) {
        this.maPhieuNhap = maPhieuNhap;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
        this.tenNCC = tenNCC;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    
}
