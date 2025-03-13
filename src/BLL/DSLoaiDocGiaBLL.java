package BLL;

import java.util.ArrayList;

import Model.LoaiDocGia;

public class DSLoaiDocGiaBLL {
	ArrayList<LoaiDocGia> dsLoaiDG = new ArrayList<>();

	public void themLoaiDG(LoaiDocGia ldg){
		dsLoaiDG.add(ldg);
	}

	public ArrayList<LoaiDocGia> getdsLoaiDG(){
		return dsLoaiDG;
	}
}
