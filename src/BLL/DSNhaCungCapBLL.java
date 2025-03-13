package BLL;

import java.util.ArrayList;

import Model.NhaCungCap;

public class DSNhaCungCapBLL {
	ArrayList<NhaCungCap> dsNCC = new ArrayList<>();

	public void themNCC(NhaCungCap ncc){
		dsNCC.add(ncc);
	}

	public ArrayList<NhaCungCap> getdsNCC(){
		return dsNCC;
	}
}
