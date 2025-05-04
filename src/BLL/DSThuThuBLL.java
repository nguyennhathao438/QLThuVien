package BLL;

import DAL.ThuThuDAL;
import java.util.ArrayList;

import Model.ThuThu;

public class DSThuThuBLL {
    private static ThuThuDAL ttDAL = new ThuThuDAL();
    static ArrayList<ThuThu> dsThuThu = new ArrayList<>();
    
    public DSThuThuBLL(){
        if(dsThuThu.size() == 0){
            this.dsThuThu = ttDAL.selectAll();
        }
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
    public static String getMaThuThuByTen(String ten){
        for(ThuThu tt : dsThuThu){
            if(tt.getTenThuThu().equals(ten)){
                return tt.getMaThuThu();
            }               
        }
        return null;
    }
    public String[] getArrTenThuThu(){
        int size = dsThuThu.size();
        String[] arr = new String[size];
        for(int i = 0; i < size; i++){
            arr[i] = dsThuThu.get(i).getTenThuThu();
        }
        return arr;
    }
    public static ArrayList<ThuThu> getDsThuThu() {
        return dsThuThu;
    }

    public static void setDsThuThu(ArrayList<ThuThu> dsThuThu) {
        DSThuThuBLL.dsThuThu = dsThuThu;
    }
        
        
        
}
