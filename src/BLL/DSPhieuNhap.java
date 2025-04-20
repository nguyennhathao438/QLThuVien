
package BLL;

import DAL.CTPhieuNhapDAL;
import DAL.PhieuNhapDAL;
import DAL.SachDAL;
import MODEL.CTPhieuNhap;
import MODEL.PhieuNhap;
import Model.Sach;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DSPhieuNhap {
    private static ArrayList<String> idArray = new ArrayList<>();
    private static ArrayList<PhieuNhap> dsPN = new ArrayList(); 
    private PhieuNhapDAL pnDAL = new PhieuNhapDAL();
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private CTPhieuNhapDAL ctpnDAL = new CTPhieuNhapDAL();
    private SachDAL sachDAL = new SachDAL();
    public DSPhieuNhap(){
        if(dsPN.isEmpty()){
           this.dsPN = pnDAL.selectAll();
            this.idArray = getAllMaPN(); 
        }
    }
    
    
    public boolean add(PhieuNhap pn, ArrayList<CTPhieuNhap> dsCTPN){
        boolean check = pnDAL.insert(pn);
        if(check){
            dsPN.add(pn);
            check = ctpnDAL.insert(dsCTPN);
            
        }
        return check;
    }
    
    public boolean updateSoLuongSach(ArrayList<CTPhieuNhap> dsCTPN){
        boolean check = sachDAL.updateSoLuongSach(dsCTPN);
        if(check){
            for(CTPhieuNhap ctpn : dsCTPN){
                for(Sach s : DSSachBLL.getDsSach()){
                    if(s.getMaSach().equals(ctpn.getMaSach())){
                        s.setSoLuong(s.getSoLuong() + ctpn.getSoLuong());
                        break;
                    }
                }
            }
        }
        return check;
    }
    public ArrayList<CTPhieuNhap> getCTPN(PhieuNhap pn){
        return ctpnDAL.selectAll(pn.getMaPhieuNhap());
    }
//    public boolean delete(PhieuNhap pn){        
//    }
        
    public int getIndexByMaPN(String ma){
        for(int i = 0; i < dsPN.size(); i++){
            if(dsPN.get(i).getMaPhieuNhap().equals(ma)){
                return i;
            }
        }
        return -1;
    }
        public ArrayList<PhieuNhap> search(String text,String type){
            ArrayList<PhieuNhap> result  = new ArrayList<>();
            text = text.toLowerCase();
            switch (type) {
                case "Tất cả":
                    for(int i = 0; i < dsPN.size(); i++){
                        if(dsPN.get(i).getMaPhieuNhap().toLowerCase().contains(text)
                           || DSNhaCungCapBLL.getTenNCCByMa(dsPN.get(i).getMaNCC()).toLowerCase().contains(text)
                           || DSThuThuBLL.getTenThuThuByMa(dsPN.get(i).getMaThuThu()).toLowerCase().contains(text)
                           || dsPN.get(i).getThoiGian().format(formatTime).contains(text)
                           || String.valueOf(dsPN.get(i).getTongTien()).contains(text)){
                                result.add(dsPN.get(i));
                        }                                   
                    } 
                    break;
                case "Tên NCC":
                    for(int i = 0; i < dsPN.size(); i++){
                        if(DSNhaCungCapBLL.getTenNCCByMa(dsPN.get(i).getMaNCC()).toLowerCase().contains(text)){
                            result.add(dsPN.get(i));
                        }
                    }
                    break;
                case "Thủ thư":
                    for(int i = 0; i < dsPN.size(); i++){
                        if(DSThuThuBLL.getTenThuThuByMa(dsPN.get(i).getMaThuThu()).toLowerCase().contains(text)){
                            result.add(dsPN.get(i));
                        }
                    }
                    break;
                case "Thời gian":
                    for(int i = 0; i < dsPN.size(); i++){
                        if(dsPN.get(i).getThoiGian().format(formatTime).contains(text)){
                            result.add(dsPN.get(i));
                        }
                    }
                    break;
                case "Tổng tiền":
                    for(int i = 0; i < dsPN.size(); i++){
                        if(String.valueOf(dsPN.get(i).getTongTien()).contains(text)){
                            result.add(dsPN.get(i));
                        }
                    }
                    break;
                case "Mã phiếu":
                    for(int i = 0; i < dsPN.size(); i++){
                        if(dsPN.get(i).getMaPhieuNhap().toLowerCase().contains(text)){
                            result.add(dsPN.get(i));
                        }
                    }
                    break;
                default:
                    break;
            }
            return result;
        }
        
        public ArrayList<PhieuNhap> filteredList(String ncc, String tt, LocalDateTime fromDate, LocalDateTime toDate, Double fromMoney, Double toMoney){
            ArrayList<PhieuNhap> result = new ArrayList<>();
            if(!validationFilter(ncc, tt, fromDate, toDate, fromMoney, toMoney)) return null;
            for (PhieuNhap pn : getDsPN()) {
            boolean matches = true;

            // Kiểm tra nhà cung cấp
            if (!ncc.isEmpty() && !DSNhaCungCapBLL.getTenNCCByMa(pn.getMaNCC()).contains(ncc)) {
                matches = false;
            }
            
            // Kiểm tra thủ thư
            if (!tt.isEmpty() && !DSThuThuBLL.getTenThuThuByMa(pn.getMaThuThu()).contains(tt)) {
                matches = false;
            }

            // Kiểm tra ngày
            if (fromDate != null && pn.getThoiGian().isBefore(fromDate)) {
                matches = false;
            }
            if (toDate != null && pn.getThoiGian().isAfter(toDate)) {
                matches = false;
            }

            // Kiểm tra số tiền
            if (fromMoney != null && pn.getTongTien() < fromMoney) {
                matches = false;
            }
            if (toMoney != null && pn.getTongTien() > toMoney) {
                matches = false;
            }

            if (matches) {
                result.add(pn);
            }
        }
           return result;
    }
        public PhieuNhap getPNByMa(String ma){
            for(PhieuNhap pn : dsPN){
                if(pn.getMaPhieuNhap().equals(ma)){
                    return pn;
                }
            }
            return null;
        }        
        
        public boolean validationFilter(String ncc, String tt, LocalDateTime fromDate, LocalDateTime toDate, Double fromMoney, Double toMoney){
           if(fromDate != null && toDate != null && fromDate.isAfter(toDate)){
               JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
           }
           if(fromMoney != null && toMoney != null && fromMoney > toMoney){
               JOptionPane.showMessageDialog(null, "Khoảng tiền không hợp lệ!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
           }
           return true;
        }
        
        public static int checkMaPN(String ma, ArrayList<String> existingCode){
            String regex = "^PN\\d{3,}";
            if(!ma.matches(regex)){
                return 2;
            }
            if(existingCode.contains(ma)){
                return 3;
            }
            return 1;
        }
        
        public static boolean checkValidMaPN(String ma){
            int check = checkMaPN(ma, idArray);
            switch (check) {
                case 1: 
                    return true;
                case 2:
                    JOptionPane.showMessageDialog(null, "Sai định dạng!(Ví dụ: PN001)", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                    return false;                    
                case 3:
                    JOptionPane.showMessageDialog(null, "Mã phiếu nhập đã tồn tại!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                    return false; 
  
            }
            return true;
        }
        public static ArrayList<String> getAllMaPN(){
            ArrayList<String> result = new ArrayList<>();
            if(dsPN.size() > 0){
                for(PhieuNhap pn : dsPN){
                    result.add(pn.getMaPhieuNhap());
                }
            }
            return result;
        }
        
        
        public static ArrayList<PhieuNhap> getDsPN() {
            return dsPN;
        }

        public PhieuNhapDAL getPnDAL() {
            return pnDAL;
        }

        public static ArrayList<String> getIdArray() {
            return idArray;
        }
    
    
}
