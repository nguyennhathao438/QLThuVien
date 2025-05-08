/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Nghia0605
 */
public class TKTongChiTheoQuy {
    private String tenSach;
    private double q1, q2, q3, q4, tong;

    public TKTongChiTheoQuy(String tenSach, double q1, double q2, double q3, double q4, double tong) {
        this.tenSach = tenSach;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.tong = tong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public double getQ1() {
        return q1;
    }

    public double getQ2() {
        return q2;
    }

    public double getQ3() {
        return q3;
    }

    public double getQ4() {
        return q4;
    }

    public double getTong() {
        return tong;
    }
}
