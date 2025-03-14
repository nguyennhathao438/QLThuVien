
package BLL;

import MODEL.PhieuNhap;
import java.util.ArrayList;

public class DSPhieuNhap {
    private ArrayList<PhieuNhap> dspn = new ArrayList();

    public DSPhieuNhap() {
    }
    
    public ArrayList<PhieuNhap> getDspn() {
        return dspn;
    }

    public void setDspn(ArrayList<PhieuNhap> dspn) {
        this.dspn = dspn;
    }
    public void themPhieuNhap(PhieuNhap pn){ 
        dspn.add(pn);
    }
}
