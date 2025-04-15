/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class CTPhieuMuon {
    private String maPMuon;
    private String maSach;
    private int soLuong;
    private int trangThai;

    public CTPhieuMuon() {
    }

    public CTPhieuMuon(String maPMuon, String maSach, int soLuong, int trangThai) {
        this.maPMuon = maPMuon;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMaPMuon() {
        return maPMuon;
    }

    public void setMaPMuon(String maPMuon) {
        this.maPMuon = maPMuon;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
}
