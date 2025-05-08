package Model;

public class TheLoai {
	private String maTheLoai;
	private String theLoai;
        int trangThai;

	public TheLoai(){
	}

	public TheLoai(String maTheLoai, String theLoai, int trangngThai){
		this.maTheLoai = maTheLoai;
		this.theLoai = theLoai;
                this.trangThai = trangngThai;
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

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	@Override
	public String toString(){
		return theLoai;
	}
}
