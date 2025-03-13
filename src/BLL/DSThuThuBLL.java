package BLL;

import java.util.ArrayList;

import Model.ThuThu;

public class DSThuThuBLL {
	ArrayList<ThuThu> dsThuThu = new ArrayList<>();

	public void themThuThu(ThuThu t){
		dsThuThu.add(t);
	}

	public ArrayList<ThuThu> getDSThuThu(){
		return dsThuThu;
	}
}
