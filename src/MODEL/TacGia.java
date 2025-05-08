package Model;

public class TacGia {
	private String maTacGia;
	private String tenTacGia;
	private int namSinh;
	private String soDienThoai;
        private int trangThai ;
	public TacGia(){
	}

	public TacGia(String maTacGia, String tenTacGia, int namSinh, String soDienThoai,int trangThai){
		this.maTacGia = maTacGia;
		this.tenTacGia = tenTacGia;
		this.namSinh = namSinh;
		this.soDienThoai = soDienThoai;
                this.trangThai= trangThai;
	}

	public String getMaTacGia() {
		return maTacGia;
	}

	public void setMaTacGia(String maTacGia) {
		this.maTacGia = maTacGia;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@Override
	public String toString(){
		return tenTacGia;
	}

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
        
}
