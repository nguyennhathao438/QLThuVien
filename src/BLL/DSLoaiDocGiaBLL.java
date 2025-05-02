package BLL;

import DAL.LoaiDGDAL;
import java.util.ArrayList;

import Model.LoaiDocGia;
import javax.swing.JOptionPane;

public class DSLoaiDocGiaBLL {
    LoaiDGDAL ldgdal = new LoaiDGDAL();
    static ArrayList<LoaiDocGia> dsldg = new ArrayList<>();

    public DSLoaiDocGiaBLL(){ 
        dsldg = ldgdal.getAllLoaiDG();
    }
    
    public static ArrayList<LoaiDocGia> layDSLoaiDG(){ 
        return dsldg;
    }
        
    public void showmess(String s){
        JOptionPane.showMessageDialog(null,s);
    }
        
    public int themLoaiDG(LoaiDocGia ldg){
        dsldg = layDSLoaiDG();
        if(ldg.getMaLoaiDG().isEmpty() || ldg.getTenLoaiDG().isEmpty() || ldg.getMoTa().isEmpty()){
            showmess("Thông tin không được bỏ trống");
            return -1;
        }
        if(ldg.getGioiHanMuon() <= 0){
            showmess("Giới hạn mượn không hợp lệ");
            return -1;
        }
          
        for(LoaiDocGia loai : dsldg){
            if(loai.getMaLoaiDG().trim().equals(ldg.getMaLoaiDG().trim())){
                showmess("Mã loại độc giả đã tồn tại");
                return -1;
            }
        }
        String regex = "^LDG\\d{3,}";
        if(!ldg.getMaLoaiDG().matches(regex)){
            showmess("Mã nhập không hợp lệ (Ví dụ: LDG004)");
            return -1;
        }
        if(ldgdal.ThemLoaiDG(ldg) > 0){
            dsldg.add(ldg);
            showmess("Thêm loại độc giả thành công");
            return 1;
        }
        showmess("Thêm loại độc giả thất bại");
        return -1;
    }

    public static String getTenLoaiDGByMa(String ma){
        for(LoaiDocGia ldg : dsldg){
            if(ldg.getMaLoaiDG().equals(ma))
                return ldg.getTenLoaiDG();              
        }
        return null;
    }  
    
    public static ArrayList<String> getdsTenLDG(){
        ArrayList<String> dsTenLoai = new ArrayList<>();
            for(LoaiDocGia ldg : dsldg){
                String ten = ldg.getTenLoaiDG();
                dsTenLoai.add(ten);
            }
        return dsTenLoai;
    }
        
    public static String getMaLoaiDGByTen(String ten){
        for(LoaiDocGia ldg : dsldg){
            if(ldg.getTenLoaiDG().toLowerCase().equals(ten.toLowerCase()))
                return ldg.getMaLoaiDG();              
        }
        return null;
    }
    
    public static LoaiDocGia getLoaiDGByMa(String ma){
        for(LoaiDocGia ldg : dsldg){
            if(ldg.getMaLoaiDG().equals(ma))
                return ldg;  
        }
        return null;
    }
}
