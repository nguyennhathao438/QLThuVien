
package MODEL;

public class SachTra {
    private String maSach;
    private int soLuong;

    public SachTra() {
    }

    public SachTra(String maSach, int soLuong) {
        this.maSach = maSach;
        this.soLuong = soLuong;
    }
    
    public String getMaSach() {
        return maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
