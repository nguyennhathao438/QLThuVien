package Model;

public class TheLoai {
	private String maTheLoai;
	private String theLoai;

	public TheLoai(){
	}

	public TheLoai(String maTheLoai, String theLoai){
		this.maTheLoai = maTheLoai;
		this.theLoai = theLoai;
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
