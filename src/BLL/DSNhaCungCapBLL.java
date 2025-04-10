package BLL;

import DAL.NhaCungCapDAL;
import java.util.ArrayList;

import Model.NhaCungCap;
import javax.swing.JOptionPane;

public class DSNhaCungCapBLL {
	private static ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
        private NhaCungCapDAL nccDAL = new NhaCungCapDAL();
        private static ArrayList<String> idArray = new ArrayList<>();
        
        public DSNhaCungCapBLL(){
            this.dsNCC = nccDAL.selectAll();
            this.idArray = nccDAL.getAllMaNCC();
        }
//        public ArrayList<NhaCungCap> getAll(){
//            return this.dsNCC;  
//        }
	public boolean themNCC(NhaCungCap ncc){
            if(checkValidationForm(ncc) && checkValidationData(ncc)){
                if(nccDAL.insert(ncc)){
                    dsNCC.add(ncc);
                    idArray.add(ncc.getMaNCC());
                    return true;
                }
            }
            return false;
        }
        
	public boolean xoaNCC(String id){            
            if(nccDAL.delete(id)){
                int index = getIndexByMaNCC(id);                
                if(index != -1){                    
                    dsNCC.remove(index);  
                    return true;
                }                
            }
            return false;
        }
        
        public boolean capnhatNCC(NhaCungCap ncc){            
            if(checkValidationForm(ncc)){
                if(nccDAL.update(ncc)){
                    int index = getIndexByMaNCC(ncc.getMaNCC());      
                    int indexMa = getIndexMa(ncc.getMaNCC());
                    if(index != -1 && indexMa != -1){
                        dsNCC.set(index, ncc);     
                        idArray.set(index, ncc.getMaNCC());
                        return true;
                    }                                             
                }
            }
            return false;
        }
        
        public int getIndexByMaNCC(String id){
            for(int i = 0; i < dsNCC.size(); i++){
                if(dsNCC.get(i).getMaNCC().equals(id)){
                    return i;
                }                
            }
            return -1;
        }
        public int getIndexMa(String id){
            for(int i = 0; i < idArray.size(); i++){
                if(id.equals(idArray.get(i))){
                    return i;
                }
            }
            return -1;
        }
        public int getNextID(){
            return nccDAL.getAutoIncremnt();
        }
        
        public static String getTenNCCByMa(String ma){
            for(int i = 0; i < dsNCC.size(); i++){
                NhaCungCap ncc = dsNCC.get(i);
                if(ncc.getMaNCC().equals(ma)){
                    return ncc.getTenNCC();
                }
            }
            return "";
        }
        public boolean checkValidationForm(NhaCungCap ncc){
            if(ncc.getMaNCC().isEmpty()){
                JOptionPane.showMessageDialog(null, "Mã không được rỗng!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(ncc.getTenNCC().isEmpty()){
                JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được rỗng !", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(ncc.getSoDienThoai().isEmpty()){
                JOptionPane.showMessageDialog(null, "Số điện thoại cung cấp không được rỗng !", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }        
            if(!validationPhone(ncc.getSoDienThoai())){
                JOptionPane.showConfirmDialog(null, "Số điện thoại không hợp lệ!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        }
        public boolean checkValidationData(NhaCungCap ncc){
            switch (checkMaNCC(ncc.getMaNCC(), idArray)) {
                case 2:
                    JOptionPane.showConfirmDialog(null, "Sai định dạng!(Ví dụ: NCC101)", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                    return false;                    
                case 3:
                    JOptionPane.showConfirmDialog(null, "Mã đã tồn tại!", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
                    return false;                                
            }
            
            return true;
        }
        public boolean validationPhone(String phone){
            // Xóa các dấu cách, dấu gạch hoặc dấu chấm (nếu có)
            phone = phone.replaceAll("[\\s\\-\\.]", "");
            String regex = "^0\\d{9}$";
            return phone.matches(regex);
        }
        public int checkMaNCC(String ma, ArrayList<String> existingCode){
            String regex = "^NCC\\d{3,}"; // ma co it nhat 3 chu so
            if(!ma.matches(regex)){
                return 2;                
            }
            if(existingCode.contains(ma)){
                return 3;
            }
            return 1;
        }
        public ArrayList<NhaCungCap> search(String text,String type){
            ArrayList<NhaCungCap> result = new ArrayList<>();
            text = text.toLowerCase();
            switch (type) {
                case "Tất cả":
                    for(NhaCungCap ncc : dsNCC){
                        if(ncc.getMaNCC().toLowerCase().contains(text) ||
                            ncc.getTenNCC().toLowerCase().contains(text) ||
                            ncc.getSoDienThoai().toLowerCase().contains(text)){
                            result.add(ncc);
                        }
                    }
                    break;
                    
                case "Mã NCC":
                    for(NhaCungCap ncc : dsNCC){
                        if(ncc.getMaNCC().toLowerCase().contains(text)){
                            result.add(ncc);
                        }
                    }
                    break;
                    
                case "Tên NCC":
                    for(NhaCungCap ncc : dsNCC){
                        if(ncc.getTenNCC().toLowerCase().contains(text)){
                            result.add(ncc);
                        }
                    }
                    break;
                    
                case "Số điện thoại":
                    for(NhaCungCap ncc : dsNCC){
                        if(ncc.getSoDienThoai().toLowerCase().contains(text)){
                            result.add(ncc);
                        }
                    } 
                    break;
                default:
                    throw new AssertionError();
            }
            return result;
        }
        public static ArrayList<NhaCungCap> getdsNCC(){
		return dsNCC;
	}
}
