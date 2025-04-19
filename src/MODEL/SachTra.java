
package MODEL;

public class SachTra {
    private String maSach;
    private int soLuongChuaTra;

    public SachTra() {
    }

    public SachTra(String maSach, int soLuongChuaTra) {
        this.maSach = maSach;
        this.soLuongChuaTra = soLuongChuaTra;
    }
    
    public String getMaSach() {
        return maSach;
    }

    public int getSoLuongChuaTra() {
        return soLuongChuaTra;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setSoLuongChuaTra(int soLuongChuaTra) {
        this.soLuongChuaTra = soLuongChuaTra;
    }
   
    
}
