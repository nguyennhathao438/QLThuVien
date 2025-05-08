package Model;

public class LoaiDocGia {
	private String maLoaiDG;
	private String tenLoaiDG;
	private int gioiHanMuon;
	private String moTa;
        private int trangThai;
        
	public LoaiDocGia(){
	}

	public LoaiDocGia(String maLoaiDG, String tenLoaiDG, int gioiHanMuon, String moTa, int trangThai){
		this.maLoaiDG = maLoaiDG;
		this.tenLoaiDG = tenLoaiDG;
		this.gioiHanMuon = gioiHanMuon;
		this.moTa = moTa;
                this.trangThai = trangThai;
	}

	public String getMaLoaiDG() {
		return maLoaiDG;
	}

	public void setMaLoaiDG(String maLoaiDG) {
		this.maLoaiDG = maLoaiDG;
	}

	public String getTenLoaiDG() {
		return tenLoaiDG;
	}

	public void setTenLoaiDG(String tenLoaiDG) {
		this.tenLoaiDG = tenLoaiDG;
	}

	public int getGioiHanMuon() {
		return gioiHanMuon;
	}

	public void setGioiHanMuon(int gioiHanMuon) {
		this.gioiHanMuon = gioiHanMuon;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

        public int getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(int trangThai) {
            this.trangThai = trangThai;
        }
        
	@Override
	public String toString(){
		return tenLoaiDG;
	} 
}
