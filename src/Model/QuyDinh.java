/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author ADMIN
 */
public class QuyDinh {
    private int maQuyDinh;
    private String noiDung;
    private double soTien;
    private int trangThai;

    public QuyDinh() {
    }
    
    public QuyDinh(int maQuyDinh, String noiDung, double soTien,int trangThai) {
        this.maQuyDinh = maQuyDinh;
        this.noiDung = noiDung;
        this.soTien = soTien;
        this.trangThai=trangThai;
    }

    public int getMaQuyDinh() {
        return maQuyDinh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setMaQuyDinh(int maQuyDinh) {
        this.maQuyDinh = maQuyDinh;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
