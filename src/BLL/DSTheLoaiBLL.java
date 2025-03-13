package BLL;

import java.util.ArrayList;

import Model.TheLoai;

public class DSTheLoaiBLL {
	ArrayList<TheLoai> dsTheLoai = new ArrayList<>();

	public void themTheLoai(TheLoai tl){
		dsTheLoai.add(tl);
	}

	public ArrayList<TheLoai> getDSTheLoai(){
		return dsTheLoai;
	}
}
