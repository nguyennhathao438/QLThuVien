
package BLL;

import DAL.ThongKeDAL;
import MODEL.TKDocGia;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeBLL {
    private static ArrayList<TKDocGia> dstk = new ArrayList();
    ThongKeDAL tkdal = new ThongKeDAL();
    public ThongKeBLL() {
        dstk = tkdal.getTKDocGia();
    }
    public ArrayList<TKDocGia> gettkDocGia(){ 
        dstk = tkdal.getTKDocGia();
        return dstk;
    }
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
