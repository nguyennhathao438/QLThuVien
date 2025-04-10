package Model;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String soDienThoai;
        private int trangThai;

	public NhaCungCap(){
	}

	public NhaCungCap(String maNCC, String tenNCC, String soDienThoai,int trangThai){
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.soDienThoai = soDienThoai;
                this.trangThai = trangThai;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
        
        public int getTrangThai() {
        return trangThai;
        }

        public void setTrangThai(int trangThai) {
            this.trangThai = trangThai;
        }
	@Override
	public String toString(){
		return tenNCC;
	}
}
