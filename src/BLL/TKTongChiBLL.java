/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.TKTongChiDAL;
import MODEL.TKTongChiTheoNgay;
import MODEL.TKTongChiTheoQuy;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Nghia0605
 */
public class TKTongChiBLL {
    private TKTongChiDAL tktcDAL = new TKTongChiDAL();
    private static ArrayList<TKTongChiTheoNgay> dsTKTCN = new ArrayList<>();
    private static ArrayList<TKTongChiTheoQuy> dsTKTCQ = new ArrayList<>();
    public TKTongChiBLL(){}
    
    public boolean checkEmptyDate(Date fromDate, Date toDate){
        if(fromDate == null || toDate == null){
            JOptionPane.showMessageDialog(null, "Hãy chọn ngày trước", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(fromDate.after(toDate)){
             JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return true;
        }
            
        return false;
    }
    public void TKTongChiTheoNgay(Date fromDate, Date toDate){
        Timestamp fromDateParsed = null;
        Timestamp toDateParsed = null;  
        if(checkEmptyDate(fromDate, toDate)) return;
        fromDateParsed = new Timestamp(fromDate.getTime());     
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        toDateParsed = new Timestamp(cal.getTimeInMillis());  
        
//        System.out.println("fromdateparsed " + fromDateParsed + "toDateParsed " + toDateParsed);
        this.dsTKTCN = tktcDAL.TKTongChiTheoNgay(fromDateParsed, toDateParsed);
    }
    
    
    public void TKTongChiTheoQuy(int nam){
        this.dsTKTCQ = tktcDAL.TKTongChiTheoQuy(nam);
    }
    
    public static ArrayList<TKTongChiTheoNgay> getDsTKTCN() {
        return dsTKTCN;
    }
    
    
    public static void setDsTKTCN(ArrayList<TKTongChiTheoNgay> dsTKTCN) {
        TKTongChiBLL.dsTKTCN = dsTKTCN;
    }

    public static ArrayList<TKTongChiTheoQuy> getDsTKTCQ() {
        return dsTKTCQ;
    }

    public static void setDsTKTCQ(ArrayList<TKTongChiTheoQuy> dsTKTCQ) {
        TKTongChiBLL.dsTKTCQ = dsTKTCQ;
    }
    
    public double tongQ1(){
        double sum = 0;
        if(!dsTKTCQ.isEmpty()){
           for(TKTongChiTheoQuy tk : dsTKTCQ){
               sum += tk.getQ1();
           }
        }
        return sum;
    }
    
    
    public double tongQ2(){
        double sum = 0;
        if(!dsTKTCQ.isEmpty()){
           for(TKTongChiTheoQuy tk : dsTKTCQ){
               sum += tk.getQ2();
           }
        }
        return sum;
    }
    
    
    public double tongQ3(){
        double sum = 0;
        if(!dsTKTCQ.isEmpty()){
           for(TKTongChiTheoQuy tk : dsTKTCQ){
               sum += tk.getQ3();
           }
        }
        return sum;
    }
    
    
    public double tongQ4(){
        double sum = 0;
        if(!dsTKTCQ.isEmpty()){
           for(TKTongChiTheoQuy tk : dsTKTCQ){
               sum += tk.getQ4();
           }
        }
        return sum;
    }
    
    public double tongNam(){
        double sum = 0;
        for(TKTongChiTheoQuy tk : dsTKTCQ){
            sum += tk.getTong();
        }
        return sum;
    }
}
