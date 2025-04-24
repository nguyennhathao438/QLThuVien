package Model;

import java.util.ArrayList;

public class Sach {
	private String maSach;
	private String tenSach;
	private int namXuatBan;
	private int soLuong;
	private double donGia;
	private int trangThai;
        private String maTheLoai;
        private String maTacGia;
        private ArrayList<Sach> dss = new ArrayList<>();
	public Sach(){
	}

        public Sach(String maSach, String tenSach, int namXuatBan, int soLuong, double donGia, int trangThai, String maTheLoai, String maTacGia) {
            this.maSach = maSach;
            this.tenSach = tenSach;
            this.namXuatBan = namXuatBan;
            this.soLuong = soLuong;
            this.donGia = donGia;
            this.trangThai = trangThai;
            this.maTheLoai = maTheLoai;
            this.maTacGia = maTacGia;
        }

	public Sach(String maSach, String tenSach, int namXuatBan, int soLuong, double donGia, int trangThai){
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXuatBan = namXuatBan;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.trangThai = trangThai;
	}
        
        public ArrayList<Sach> getDSSach()
        {
            return dss;
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

        public String getMaTheLoai() {
            return maTheLoai;
        }

        public void setMaTheLoai(String maTheLoai) {
            this.maTheLoai = maTheLoai;
        }

        public String getMaTacGia() {
            return maTacGia;
        }

        public void setMaTacGia(String maTacGia) {
            this.maTacGia = maTacGia;
        }

        
	@Override
	public String toString(){
		return tenSach;
	}


}
