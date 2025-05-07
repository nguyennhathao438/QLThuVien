
package BLL;

import DAL.PhieuTraDAL;
import DAL.SachDAL;
import MODEL.CTPhat;
import MODEL.CTPhieuTra;
import MODEL.PhieuTra;
import MODEL.SachTra;
import Model.Sach;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DSPhieuTraBLL {
    PhieuTraDAL ptdal=new PhieuTraDAL();
    static ArrayList<PhieuTra> dspt =new ArrayList();
    DSThuThuBLL ttbll = new DSThuThuBLL();
    ArrayList<SachTra> dsst=new ArrayList();
    SachDAL sachdal = new SachDAL();
    DSPhieuMuon pmbll =new DSPhieuMuon();
    public DSPhieuTraBLL(){ 
        this.dspt = ptdal.layDSPTra();
    }
    public static ArrayList<PhieuTra> getAllPhieuTra(){ 
        return dspt;
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    public boolean taoPhieuPhat(String maPhieuTra,String maPhuThu,String[] dsmqd,double tienPhat){
        String regex = "^PHUTHU\\d{3,}";
        if(maPhuThu.matches(regex)){ 
            showMess("Mã nhập không hợp lệ (Ví dụ:PHUTHU017)");
            return false;
        }
        if(maPhuThu.equals("")){ 
            showMess("Mã phụ thu không được để trống");
            return false;
        }
        if(ptdal.ktDuyNhatMaPhuThu(maPhuThu)){ 
            showMess("Mã phụ thu đã tồn tại ");
            return false;
        }
        if(dsmqd.length == 0){ 
            showMess("Vui lòng chọn quy định ");
            return false;
        }
        try {
            int kq = ptdal.taoPhieuPhat(maPhieuTra, maPhuThu,dsmqd, tienPhat);
            if(kq >=0){
                showMess("Tạo phiếu phạt thành công");
                int index = getIndexbyMaPT(maPhieuTra);
                dspt.get(index).setMaPhuThu(maPhuThu);
                return true;
            }else{
                showMess("Tạo phiếu phạt thất bại ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuTraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public CTPhat getChiTietPhat(String maPhieuTra){ 
        return ptdal.getCTPhat(maPhieuTra);
    }
    public int getIndexbyMaPT(String maPT){
        for(int i=0;i<dspt.size();i++){ 
            if(dspt.get(i).getMaPhieuTra().equals(maPT)){ 
                return i;
            }
        }
        return -1 ;
    }
    public void xoaPhieuTra(String maPT){ 
        
        if(ptdal.xoaPhieuTra(maPT)>0){ 
            int index =getIndexbyMaPT(maPT);
            dspt.get(index).setTrangThai(0);
            showMess("Xoá phiếu trả thành công ");
        }
    }
    public ArrayList<PhieuTra> searchQuyDinh(String text,String type){ 
        ArrayList<PhieuTra> dssearch = new ArrayList();
        text=text.toLowerCase();
        switch(type){ 
            case "Tất cả":
                for(PhieuTra a:dspt){ 
                    if(a.getMaPhieuMuon().toLowerCase().contains(text) ||
                        a.getMaPhieuTra().toLowerCase().contains(text)||
                             ttbll.getTenThuThuByMa(a.getMaThuThu()).toLowerCase().contains(text)){ 
                        dssearch.add(a);
                    }
                }
                break ;
            case "Mã Phiếu Mượn":
                for(PhieuTra a:dspt){ 
                    if(a.getMaPhieuMuon().toLowerCase().contains(text)){ 
                        dssearch.add(a);
                    }
                }
                break ;
                case "Mã Phiếu Trả":
                    for(PhieuTra a:dspt){ 
                    if(
                        a.getMaPhieuTra().toLowerCase().contains(text)){ 
                        dssearch.add(a);
                    }
                }
                
                    break;
                case "Tên Thủ Thư":
                    for(PhieuTra a:dspt){ 
                    if( ttbll.getTenThuThuByMa(a.getMaThuThu()).toLowerCase().contains(text)){ 
                        dssearch.add(a);
                    }
                }
                    break;
                }
                
        return dssearch;
    }
    public ArrayList<SachTra> getSachChuaTra(String mapm){ 
        this.dsst = ptdal.laySachChuaTra(mapm);
        return dsst;
    }
    public boolean taoPhieuTra(PhieuTra pt,ArrayList<SachTra> dsdt,ArrayList<SachTra> dsct){ 
        String regex="^PTRA\\d{3,}";
        this.dspt = getAllPhieuTra();
        if(!pt.getMaPhieuTra().matches(regex)){ 
            showMess("Mã phiếu trả không hợp lệ Vd:PTRA014");
            return false;
        }
        for(PhieuTra a: dspt){ 
            if(a.getMaPhieuTra().equals(pt.getMaPhieuTra())){ 
                showMess("Mã phiếu trả đã tồn tại");
            return false;
            }
        }
        if(dsdt.size()==0){ 
            showMess("Không tìm thấy sách trả");
            return false;
        }
        if(dsct.size()==0){ 
            showMess("Không tìm thấy sách chưa trả");
            return false ;
        }
        try {
            if(ptdal.taoPhieuTra(pt, dsdt, dsct)>0 && sachdal.updateSoLuongSachTra(dsdt)){
                updateSoLuongSach(dsdt);
                showMess("Tạo phiếu trả thành công");
                
            }else{ 
                showMess("Tạo phiếu trả thất bại");
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(DSPhieuTraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean updateSoLuongSach(ArrayList<SachTra> dsdt){
        boolean check = sachdal.updateSoLuongSachTra(dsdt);
        if(check){
            for(SachTra st : dsdt){
                for(Sach s : DSSachBLL.getDsSach()){
                    if(s.getMaSach().equals(st.getMaSach())){
                        s.setSoLuong(s.getSoLuong() + st.getSoLuong());
                        break;
                    }
                }
            }
        }
        return check;
    }
    public CTPhieuTra layChiTietPhieuTra(String maPhieuTra){ 
        return ptdal.getCTPhieuTra(maPhieuTra);
    }
    public double getTienPhat(String maPhieuTra){ 
        CTPhat ct = ptdal.getCTPhat(maPhieuTra);
        return ct.getThanhTien();
    }
}
