
package BLL;

import DAL.ThongKeDAL;
import MODEL.TKThuThu;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeBLL {
    ThongKeDAL tkdal = new ThongKeDAL();
//    public static ArrayList<TKThuThu> dsTKThuThu = new ArrayList<>();
    public double[] tkHoatDongThang(int thang ,int nam){ 
        double data[]=tkdal.tkHoatDongThang(thang, nam);
        return data;
    }
    public double[] tkHoatDongQuy(int quy,int nam){ 
        double data[]=tkdal.tkHoatDongQuy(quy, nam);
        return data;
    }
    public double[] tkHoatDongNgay(Date fromdate , Date todate){ 
        
        double data[] =tkdal.tkHoatDongNgay(fromdate, todate);
        return data;
    }
    
    public ArrayList<TKThuThu> getTKThuThu(int nam){
        return tkdal.getTKThuThu(nam);
    }
    
    public String[] getArrNam(int startYear){
        int currentYear = LocalDate.now().getYear();        
        String[] year = new String[currentYear - startYear + 1];
        for(int i = 0; i < year.length; i++){
            year[i] = String.valueOf(startYear + i);
        }
        return year;
    }
}
