package BLL;

import java.util.ArrayList;

import Model.Sach;

public class DSSachBLL {
	ArrayList<Sach> dsSach = new ArrayList<>();

	public void themSach(Sach s){
		dsSach.add(s);
	}

	public ArrayList<Sach> getdsSach(){
		return dsSach;
	}
}
