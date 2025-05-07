/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author ADMIN
 */
public class SachMuon {
    private String maSach;
    private String tenSach;
    private int soLuong;
    private int trangThai;

    public SachMuon() {
    }

    
    public SachMuon(String maSach, String tenSach, int soLuong, int trangThai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }
    
}
