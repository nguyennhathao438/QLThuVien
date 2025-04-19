package Model;

public class Sach {
	private String maSach;
	private String tenSach;
	private int namXuatBan;
	private int soLuong;
	private double donGia;
	private int trangThai;

	public Sach(){
	}

	public Sach(String maSach, String tenSach, int namXuatBan, int soLuong, double donGia, int trangThai){
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXuatBan = namXuatBan;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.trangThai = trangThai;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString(){
		return tenSach;
	}


}
