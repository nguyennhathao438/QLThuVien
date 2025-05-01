
package BLL;

import DAL.CTPhieuNhapDAL;
import DAL.PhieuNhapDAL;
import DAL.SachDAL;
import MODEL.CTPhieuNhap;
import MODEL.PhieuNhap;
import Model.Sach;
import UI.Component.Validation;
import UI.Panel.TaoPhieuNhapPanel;
import static UI.Panel.TaoPhieuNhapPanel.money;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

public class DSPhieuNhap {
    private static ArrayList<String> idArray = new ArrayList<>();
    private static ArrayList<PhieuNhap> dsPN = new ArrayList(); 
    private PhieuNhapDAL pnDAL = new PhieuNhapDAL();
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private CTPhieuNhapDAL ctpnDAL = new CTPhieuNhapDAL();
    private SachDAL sachDAL = new SachDAL();
    private int soluongPN = pnDAL.getSoLuongPN();
    
    public static ArrayList<CTPhieuNhap> dsCTPN;
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
    
    public boolean delete(String maPN) {
        boolean check = pnDAL.delete(maPN);
        if (check) {
            int index = getIndexByMaPN(maPN);
            if (index != -1) {
                dsPN.remove(index);
            }
            check = ctpnDAL.delete(maPN);
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
        public ArrayList<PhieuNhap> advancedSearch(String ncc,String tt, Date fromDate, Date toDate, String fromMoneyStr, String toMoneyStr){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fromDateStr = "";
            if(fromDate != null){
                fromDateStr = sdf.format(fromDate);
            }
            String toDateStr = "";
            if(toDate != null){
                toDateStr = sdf.format(toDate);
            }
            LocalDateTime fromDateParsed = null;
            LocalDateTime toDateParsed = null;
            Double fromMoneyParsed = null;
            Double toMoneyParsed = null;
            try {
                if(!fromDateStr.isEmpty()){
                    fromDateParsed = LocalDateTime.parse(fromDateStr + " 00:00:00",formatTime);
                }
                if(!toDateStr.isEmpty()){
                    toDateParsed = LocalDateTime.parse(toDateStr + " 23:59:59", formatTime);
                }
                
                if(!fromMoneyStr.isEmpty()){                    
                    fromMoneyParsed = Double.valueOf(fromMoneyStr);             
                }
                
                
                if(!toMoneyStr.isEmpty()){                   
                    toMoneyParsed = Double.valueOf(toMoneyStr);                    
                }
                
            } catch (NumberFormatException err) {
                err.printStackTrace();
            }
            return filteredList(ncc, tt, fromDateParsed, toDateParsed, fromMoneyParsed, toMoneyParsed);
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
        
        public boolean checkInfoNhapHang(String ma,String gianhap,String soluong){
            if(Validation.isEmpty(ma)){
                JOptionPane.showMessageDialog(null, "Nhập mã phiếu nhập trước khi thêm", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }else if(!TaoPhieuNhapPanel.mark){
                boolean check = DSPhieuNhap.checkValidMaPN(ma);
                if(!check){
                    return check;
                }
//                txtMaPhieu.getTxtInput().setEditable(false);
                TaoPhieuNhapPanel.mark = true;
            }

            if(Validation.isEmpty(gianhap)){
                JOptionPane.showMessageDialog(null, "Giá nhập không được rỗng", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(Validation.isEmpty(soluong)){
                JOptionPane.showMessageDialog(null, "Số lượng không được rỗng", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(!Validation.isNumber(gianhap)){
                JOptionPane.showMessageDialog(null, "Giá nhập phải là số!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(!Validation.isNumber(soluong)){
                JOptionPane.showMessageDialog(null, "Số lượng phải là số!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        }  
        public int getSoLuongPN(){
            return pnDAL.getSoLuongPN();
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
        
        public String setMaPhieuNhap(){                        
            String maPhieuNhap = "PN";
            this.soluongPN++;
            if(soluongPN < 10){
                maPhieuNhap = maPhieuNhap + "00" + soluongPN;
            }else if(soluongPN >= 10 && soluongPN <= 99){
                maPhieuNhap += "0" + soluongPN;
            }else{
                maPhieuNhap += soluongPN;
            } 
                return maPhieuNhap;
            
        }
        public boolean ctpnExist(String ma){
            for(CTPhieuNhap ctpn : this.dsCTPN){
                if(ctpn.getMaSach().equals(ma)){
//                    System.out.println("check exist");                   
                    showMessage("Sách đã được thêm, bạn có thể chỉnh sửa","CẢNH BÁO" ,JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }           
            return false;
        }
         public void showMessage(String message, String title, int messageType) {
            JOptionPane.showMessageDialog(null, message, title, messageType);
        }
        
        public String updateTotal(double tien,String type){
            switch (type) {
                case "add":
                    money += tien;
                    break;
                case "delete":
                    money -= tien;
                    break;            
            }
            NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
            formatter.setMinimumFractionDigits(0); // hien thi it nhat 22 chu so thap phan
            formatter.setMaximumFractionDigits(0); // hien thi toi da 2 chu so thap phan

            String formattedMoney = formatter.format(money);
            return formattedMoney + "Đ";
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
