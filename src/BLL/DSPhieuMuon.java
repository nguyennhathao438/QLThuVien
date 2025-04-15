
package BLL;

import DAL.PhieuMuonDAL;
import Model.PhieuMuon;
import java.util.ArrayList;

public class DSPhieuMuon {
    private static ArrayList<PhieuMuon> dspm=new ArrayList();
    PhieuMuonDAL pmdal = new PhieuMuonDAL();
    public DSPhieuMuon() {
        this.dspm = pmdal.getAllPhieuMuon();
    }
    public ArrayList<PhieuMuon> layAllPhieuMuon() {
        return dspm;
    }

    public void setDspm(ArrayList<PhieuMuon> dspm) {
        this.dspm = dspm;
    }
    public void themPhieuNhap(){ 
        
    }
    
    
}
