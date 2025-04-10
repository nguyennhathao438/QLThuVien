package BLL;

import DAL.ThuThuDAL;
import java.util.ArrayList;

import Model.ThuThu;

public class DSThuThuBLL {
    private static ThuThuDAL ttDAL = new ThuThuDAL();
    static ArrayList<ThuThu> dsThuThu = new ArrayList<>();
    
    public DSThuThuBLL(){
        this.dsThuThu = ttDAL.selectAll();
    }

    public ThuThuDAL getTtDAL() {
        return ttDAL;
    }

    public void setTtDAL(ThuThuDAL ttDAL) {
        this.ttDAL = ttDAL;
    }

    
    public static String getTenThuThuByMa(String ma){
        for(int i = 0; i < dsThuThu.size(); i++){
            ThuThu tt = dsThuThu.get(i);
            if(tt.getMaThuThu().equals(ma)){
                return tt.getTenThuThu();
            }
        }
        return null;
    }
    public static ArrayList<ThuThu> getDsThuThu() {
        return dsThuThu;
    }

    public static void setDsThuThu(ArrayList<ThuThu> dsThuThu) {
        DSThuThuBLL.dsThuThu = dsThuThu;
    }
        
        
        
}
