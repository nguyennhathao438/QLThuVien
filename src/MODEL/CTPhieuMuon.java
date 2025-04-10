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
public class CTPhieuMuon extends PhieuMuon{
    private String maThuThu;
    private String maDocGia;

    public CTPhieuMuon(String maThuThu, String maDocGia, int maPhieuMuon, Date ngayMuon, Date ngayTra, String tenDocGia, String tenThuThu) {
        super(maPhieuMuon, ngayMuon, ngayTra, tenDocGia, tenThuThu);
        this.maThuThu = maThuThu;
        this.maDocGia = maDocGia;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }
    
   
}
