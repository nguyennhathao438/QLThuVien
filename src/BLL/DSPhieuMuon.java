
package BLL;

import DAL.CTPhieuMuonDAL;
import DAL.PhieuMuonDAL;
import DAL.SachDAL;
import MODEL.CTPhieuMuon;
import MODEL.SachMuon;
import Model.PhieuMuon;
import Model.Sach;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DSPhieuMuon {
    private static ArrayList<PhieuMuon> dspm = new ArrayList<>();
    private static ArrayList<CTPhieuMuon> dsctpm = new ArrayList<>();
    private CTPhieuMuonDAL ctpmdal = new CTPhieuMuonDAL();
    private PhieuMuonDAL pmdal = new PhieuMuonDAL();
    private SachDAL sachdal = new SachDAL();
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DSDocGiaBLL dgbll = new DSDocGiaBLL();
    
    public DSPhieuMuon() {
        this.dspm = pmdal.getAllPhieuMuon();
    }
    public ArrayList<PhieuMuon> layAllPhieuMuon() {
        return dspm;
    }

    public ArrayList<CTPhieuMuon> layAllCTPN(PhieuMuon pm){
        return ctpmdal.getAllCTPMuon(pm.getMaPhieuMuon());
    }
    
    public PhieuMuon getPMuonByMa(String ma){
            for(PhieuMuon pm : dspm){
                if(pm.getMaPhieuMuon().equals(ma)){
                    return pm;
                }
            }
            return null;
        }
    
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    
    public boolean themPM(PhieuMuon pm, ArrayList<CTPhieuMuon> dsCTPM){
        if(pm.getMaPhieuMuon().isEmpty() || pm.getMaThuThu().isEmpty() || pm.getMaDocGia().isEmpty()){
            showMess("Thông tin không được bỏ trống");
            return false;
        }
        
        String regex="^PM\\d{3,}";
        if(!pm.getMaPhieuMuon().matches(regex)){ 
            showMess("Mã phiếu mượn không hợp lệ Vd:PM007");
            return false;
        }
        for(PhieuMuon a : dspm){
            if(a.getMaPhieuMuon().equals(pm.getMaPhieuMuon())){
                showMess("Mã phiếu mượn đã tồn tại");
                return false;
            }
        }
        int tongSLSachMuon = 0;
        for(int i = 0; i < dsCTPM.size(); i++){
            tongSLSachMuon += dsCTPM.get(i).getSoLuong();
        }
        if(tongSLSachMuon > DSLoaiDocGiaBLL.getGHByMa(DSDocGiaBLL.getDocGiaByMA(pm.getMaDocGia()).getMaLoaiDG())){
            showMess("Không được mượn quá số lượng cho phép");
            return false;
        }
        if (pm.getNgayTra().isBefore(pm.getNgayMuon())){
            showMess("Ngày trả phải lớn hơn ngày mượn");
            return false;
        }
        
        boolean kt = pmdal.themPM(pm);
        if(kt){
            dspm.add(pm);
            kt = ctpmdal.themCTPM(dsCTPM);
        }
        return kt;
    }
    
    public boolean updateSoLuongSach(ArrayList<CTPhieuMuon> dsCTPM){
        boolean kt = sachdal.updateSoLuongSachMuon(dsCTPM);
        if(kt){
            for(CTPhieuMuon ctpm : dsCTPM){
                for(Sach s : DSSachBLL.getDsSach()){
                    if(s.getMaSach().equals(ctpm.getMaSach())){
                        s.setSoLuong(s.getSoLuong() - ctpm.getSoLuong());
                        break;
                    }
                }
            }
        }
        return kt;
    }
    
    public int getIndexByMa(String maPM){
        for(int i = 0; i < dspm.size(); i++){
            if(dspm.get(i).getMaPhieuMuon().equals(maPM))
                return i;
        }
        return -1;
    }
    
    public boolean xoaPM(String maPM){
        if(getPMuonByMa(maPM).getTrangThai() != 2){
            showMess("Không thể xóa phiếu do chưa trả sách");
            return false;
        }
        boolean pmXoa = pmdal.xoaPM(maPM);
        boolean ctXoa = ctpmdal.xoaCTPM(maPM);
        if(pmXoa && ctXoa){
            int index = getIndexByMa(maPM);
            if(index != -1){
                dspm.get(index).setTrangThai(0); // đánh dấu đã xóa trên danh sách
            }
        }
        return pmXoa && ctXoa;
    }

    public String getTrangThaiPM(int trangThai){ 
        if(trangThai == 1){ 
            return "Chưa trả";
        }
        if(trangThai == 2){ 
            return "Đã trả";
        }
        return "";
    }
    
    public ArrayList<PhieuMuon> searchPM(String text, String type){
        ArrayList<PhieuMuon> result = new ArrayList<>();
        text = text.toLowerCase();
        switch(type){
            case "Tất cả":
                for(PhieuMuon pm : dspm){
                    if(pm.getMaPhieuMuon().toLowerCase().contains(text)
                        || DSThuThuBLL.getTenThuThuByMa(pm.getMaThuThu()).toLowerCase().contains(text)
                        || DSDocGiaBLL.getTenDocGiabyMa(pm.getMaDocGia()).toLowerCase().contains(text)
                        || pm.getNgayMuon().format(formatTime).contains(text)
                        || pm.getNgayTra().format(formatTime).contains(text)){
                        
                        result.add(pm);
                    }
                }
                break;
            case "Mã phiếu mượn":
                for(PhieuMuon pm : dspm){
                    if(pm.getMaPhieuMuon().toLowerCase().contains(text)){
                        result.add(pm);
                    }
                }
                break;
            case "Ngày mượn":
                for(PhieuMuon pm : dspm){
                    if(pm.getNgayMuon().format(formatTime).contains(text)){
                        result.add(pm);
                    }
                }
                break;
            case "Ngày trả": 
                for(PhieuMuon pm : dspm){
                    if(pm.getNgayTra().format(formatTime).contains(text)){
                        result.add(pm);
                    }
                }
                break;
            case "Tên đọc giả":
                for(PhieuMuon pm : dspm){
                    if(DSDocGiaBLL.getTenDocGiabyMa(pm.getMaDocGia()).toLowerCase().contains(text)){
                        result.add(pm);
                    }
                }
                break;
            case "Tên thủ thư":
                for(PhieuMuon pm : dspm){
                    if(pm.getMaPhieuMuon().toLowerCase().contains(text)){
                        result.add(pm);
                    }
                }
                break;
            case "Trạng thái":
                for(PhieuMuon pm : dspm){
                    if(getTrangThaiPM(pm.getTrangThai()).toLowerCase().contains(text)){
                        result.add(pm);
                    }
                }
            default:
                break;
        }
        return result;
    }
}
