
package BLL;

import DAL.ThongKeDAL;
import MODEL.TKDocGia;
import MODEL.TKSach;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ThongKeBLL {
    private static ArrayList<TKDocGia> dstk = new ArrayList();
    private static ArrayList<TKSach> dstkSach = new ArrayList<>();
    ThongKeDAL tkdal = new ThongKeDAL();
    
    public ArrayList<TKDocGia> gettkDocGia(){ 
        dstk = tkdal.getTKDocGia();
        return dstk;
    }
    
    
    public void showMess(String s){
        JOptionPane.showMessageDialog(null, s);
    }
    
    public ArrayList<TKDocGia> gettkDocGia(int thang,int quy,int nam,Date fromdate,Date todate){
        
      if(thang !=0 && nam!=0){
          dstk =tkdal.getTKDocGiaThang(thang, nam);
      }else if(quy !=0 && nam !=0){
         dstk =tkdal.getTKDocGiaQuy(quy, nam);
      }else if(fromdate !=null && todate !=null){
          if(fromdate.after(todate)){ 
              showMess("Ngay bat dau phai lon hon ngay ket thuc");
              return dstk;
          }
          dstk = tkdal.getTKDocGiaJNgay(fromdate, todate);
      }else{ 
          dstk = tkdal.getTKDocGia();
      }
      return dstk;
    }
    
    public ArrayList<TKSach> gettkSachThang(int nam)
    {
        ArrayList<TKSach> dstkAll = new ArrayList<>();
        for(int i=1; i<=12; i++)
        {
            dstkSach = tkdal.getTongSoLuongThang(i, nam);
            dstkAll.addAll(dstkSach);
        }
        return dstkAll;
    }
    
    public ArrayList<TKSach> gettkSachQuy(int nam)
    {
        ArrayList<TKSach> dstkAll = new ArrayList<>();
        for(int i=1; i<=12; i+=3)
        {
            dstkSach = tkdal.getTongSoLuongQuy(i, i+2, nam);
            dstkAll.addAll(dstkSach);
        }
        return dstkAll;
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
