
package BLL;

import DAL.ThongKeDAL;
import java.util.Date;

public class ThongKeBLL {
    ThongKeDAL tkdal = new ThongKeDAL();
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
}
