package MODEL;

import java.util.ArrayList;
import java.util.List;

public class CTPhieuNhap {
    private String maPNhap;
    private String maSach;   
    private int soLuong;
    private double donGia;


    public CTPhieuNhap() {
    }

    public CTPhieuNhap(String maPNhap, String maSach, int soLuong, double donGia) {
        this.maPNhap = maPNhap;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getter và Setter
    public String getMaPNhap() {
        return maPNhap;
    }

    public void setMaPNhap(String maPNhap) {
        this.maPNhap = maPNhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    // Tính thành tiền cho chi tiết phiếu nhập
    public double getThanhTien() {
        return soLuong * donGia;
    }
}