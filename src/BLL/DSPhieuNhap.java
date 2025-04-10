
package BLL;

import DAL.PhieuNhapDAL;
import MODEL.PhieuNhap;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DSPhieuNhap {

   

    private static ArrayList<PhieuNhap> dsPN = new ArrayList(); 
    private PhieuNhapDAL pnDAL = new PhieuNhapDAL();
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public DSPhieuNhap(){
        this.dsPN = pnDAL.selectAll();
    }
    
    
//    public boolean insert(PhieuNhap pn){
//        
//    }
//    public boolean update(PhieuNhap pn){
//        
//    }
//    public boolean delete(PhieuNhap pn){        
//    }
    
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
                case "Tên thủ thư":
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
    public static ArrayList<PhieuNhap> getDsPN() {
        return dsPN;
    }

    public PhieuNhapDAL getPnDAL() {
        return pnDAL;
    }
    
    
}
