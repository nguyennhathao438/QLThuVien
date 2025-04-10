package Model;

public class DocGia {
	private String maDocGia;
	private String tenDocGia;
	private String soDienThoai;
	private String diaChi;

	public DocGia(){
	}

	public DocGia(String maDocGia, String tenDocGia, String soDienThoai, String diaChi){
		this.maDocGia = maDocGia;
		this.tenDocGia = tenDocGia;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
	}

	public String getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(String maDocGia) {
		this.maDocGia = maDocGia;
	}

	public String getTenDocGia() {
		return tenDocGia;
	}

	public void setTenDocGia(String tenDocGia) {
		this.tenDocGia = tenDocGia;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String toString(){
		return tenDocGia;
	}
}
