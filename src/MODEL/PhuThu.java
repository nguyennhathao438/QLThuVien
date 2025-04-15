
package MODEL;


public class PhuThu {
    private int maPhuThu;
    private int maPhieuTra;
    private double tienPhat;
    public PhuThu(){ 
        
    }
    public PhuThu(int maPhuThu, int maPhieuTra, double tienPhat) {
        this.maPhuThu = maPhuThu;
        this.maPhieuTra = maPhieuTra;
        this.tienPhat = tienPhat;
    }
    public int getMaPhuThu() {
        return maPhuThu;
    }

    public int getMaPhieuTra() {
        return maPhieuTra;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public void setMaPhuThu(int maPhuThu) {
        this.maPhuThu = maPhuThu;
    }

    public void setMaPhieuTra(int maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }

    
    
}
