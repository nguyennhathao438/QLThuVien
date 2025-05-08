package Model;

import UI.Component.Displayable;

public class ThuThu implements Displayable{
	private String maThuThu;
	private String tenThuThu;
	private String taiKhoan;
	private String matKhau;
	private String soDienThoai;

	public ThuThu(){
	}

	public ThuThu(String maThuThu, String tenThuThu, String taiKhoan, String matKhau, String soDienThoai){
		this.maThuThu = maThuThu;
		this.tenThuThu = tenThuThu;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.soDienThoai = soDienThoai;
	}

	public String getMaThuThu() {
		return maThuThu;
	}

	public void setMaThuThu(String maThuThu) {
		this.maThuThu = maThuThu;
	}

	public String getTenThuThu() {
		return tenThuThu;
	}

	public void setTenThuThu(String tenThuThu) {
		this.tenThuThu = tenThuThu;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@Override
	public String toString(){
		return tenThuThu;
	}

    @Override
    public String[] toRowData() {
        return new String[]{maThuThu,tenThuThu,soDienThoai};
    }
}
