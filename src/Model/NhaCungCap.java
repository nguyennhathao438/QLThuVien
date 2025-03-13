package Model;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String soDienThoai;

	public NhaCungCap(){
	}

	public NhaCungCap(String maNCC, String tenNCC, String soDienThoai){
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.soDienThoai = soDienThoai;
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

	@Override
	public String toString(){
		return tenNCC;
	}
}
