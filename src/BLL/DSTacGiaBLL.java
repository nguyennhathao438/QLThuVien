package BLL;

import java.util.ArrayList;

import Model.TacGia;

public class DSTacGiaBLL {
	ArrayList<TacGia> dsTacGia = new ArrayList<>();

	public void themTG(TacGia tg){
		dsTacGia.add(tg);
	}

	public ArrayList<TacGia> getDSTacGia(){
		return dsTacGia;
	}
}
