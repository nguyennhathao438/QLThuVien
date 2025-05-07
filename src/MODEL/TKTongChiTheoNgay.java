/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Nghia0605
 */
public class TKTongChiTheoNgay {
    private Date date;
    private double tongChi;
    
    public TKTongChiTheoNgay(Date date, double tongChi) {
        this.date = date;
        this.tongChi = tongChi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTongChi() {
        return tongChi;
    }

    public void setTongChi(double tongChi) {
        this.tongChi = tongChi;
    }
    
    
}
