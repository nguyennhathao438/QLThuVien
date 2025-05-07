/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author tung7
 */
public class TKSach {
   private int soLuongMuon;
   private int soLuongTra;

    public TKSach() {
    }

    public TKSach(int soLuongMuon, int soLuongTra) {
        this.soLuongMuon = soLuongMuon;
        this.soLuongTra = soLuongTra;
    }

    public int getSoLuongTra() {
        return soLuongTra;
    }

    public void setSoLuongTra(int soLuongTra) {
        this.soLuongTra = soLuongTra;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }   
   
}
