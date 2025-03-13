package BLL;

import java.util.ArrayList;

import Model.DocGia;

public class DSDocGiaBLL {
	ArrayList<DocGia> dsDocGia = new ArrayList<>();

	public void themDG(DocGia dg){
		dsDocGia.add(dg);
	}

	public ArrayList<DocGia> getDSDocGia(){
		return dsDocGia;
	}
}
