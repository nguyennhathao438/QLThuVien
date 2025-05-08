package BLL;

import DAL.DocGiaDAL;
import java.util.ArrayList;

import Model.DocGia;
import Model.LoaiDocGia;
import javax.swing.JOptionPane;

public class DSDocGiaBLL {
    DocGiaDAL dgdal = new DocGiaDAL();
    static ArrayList<DocGia> dsdg = new ArrayList<>();
    DSLoaiDocGiaBLL ldgbll = new DSLoaiDocGiaBLL();
    
    public DSDocGiaBLL(){
        dsdg = dgdal.getAllDocGia();
    }
    
    public static ArrayList<DocGia> layDSDocGia(){
        return dsdg;
    }
    
    public void showmess(String s){
        JOptionPane.showMessageDialog(null,s);
    }
        
    public int themDG(DocGia dg){
        
        if(dg.getMaDocGia().isEmpty() || dg.getTenDocGia().isEmpty() || dg.getSoDienThoai().isEmpty() || dg.getDiaChi().isEmpty() || dg.getMaLoaiDG().isEmpty()){
            showmess("Thông tin không được bỏ trống");
            return -1;
        }
        String regex = "^DG\\d{3,}";
        if(!dg.getMaDocGia().matches(regex)){ 
            showmess("Mã nhập không hợp lệ (Ví dụ:DG017)");
            return -1; 
        }
        for(DocGia d : dsdg){
            if(d.getMaDocGia().trim().equals(dg.getMaDocGia().trim())){
                showmess("Mã độc giả đã tồn tại");
                return -1;
            }
        }    
        if(dgdal.ThemDG(dg) > 0){
            dsdg.add(dg);
            showmess("Thêm độc giả thành công");
            return 1;
        }
        showmess("Thêm độc giả thất bại");     
        return -1;         
    }
        
    public int suaDG(DocGia dg){
        //dsdg = layDSDocGia();
        if(dg.getMaDocGia().isEmpty() || dg.getTenDocGia().isEmpty() || dg.getSoDienThoai().isEmpty() || dg.getDiaChi().isEmpty() || dg.getMaLoaiDG().isEmpty()){
            showmess("Thông tin không được bỏ trống");
            return -1;
        }               
        if(dgdal.SuaDG(dg) > 0){
            int index = getIndexByMa(dg.getMaDocGia());
            dsdg.set(index, dg);
            showmess("Sửa độc giả thành công");
            return 1;
        }
        showmess("Sửa độc giả thất bại");
        return -1;
    }

    public int getIndexByMa(String ma){
        for(int i = 0; i < dsdg.size(); i++){
            if(dsdg.get(i).getMaDocGia().equals(ma))
                return i;
        }
        return -1;
    }
    
    public int xoaDG(String ma){
        if(dgdal.XoaDG(ma) > 0){
            int index = getIndexByMa(ma);
            if(index != -1){
                dsdg.get(index).setTrangThai(0);
                showmess("Xóa độc giả thành công");
                return 1;
            }
        }
        showmess("Xóa độc giả thất bại");
        return -1;
    }    
        
    public static String getTenDocGiabyMa(String madg){ 
        //dsdg = layDSDocGia();
        for(DocGia dg :dsdg){ 
            if(dg.getTrangThai() != 0 && dg.getMaDocGia().equals(madg)){ 
                return dg.getTenDocGia();
            }
        }
        return "";       
    }
        
    public ArrayList<DocGia> searchDocGia(String text, String type){
        ArrayList<DocGia> dssearch = new ArrayList<>();
        text = text.toLowerCase();
        switch(type){
            case "Tất cả":
                for(DocGia dg : dsdg){
                    if(dg.getMaDocGia().toLowerCase().contains(text) || dg.getTenDocGia().toLowerCase().contains(text) || dg.getSoDienThoai().toLowerCase().contains(text) || ldgbll.getTenLoaiDGByMa(dg.getMaLoaiDG()).toLowerCase().contains(text)){
                        dssearch.add(dg);
                    }
                }
                break;
            case "Mã ĐG":
                for(DocGia dg : dsdg){
                    if(dg.getMaDocGia().toLowerCase().contains(text)){
                        dssearch.add(dg);
                    }
                }
                break;
            case "Tên ĐG":
                for(DocGia dg : dsdg){
                    if(dg.getTenDocGia().toLowerCase().contains(text)){
                        dssearch.add(dg);
                    }
                }
                break;
            case "Số điện thoại":
                for(DocGia dg : dsdg){
                    if(dg.getSoDienThoai().toLowerCase().contains(text)){
                        dssearch.add(dg);
                    }
                }
                break;
            case "Loại ĐG":
                for(DocGia dg : dsdg){
                    if(ldgbll.getTenLoaiDGByMa(dg.getMaLoaiDG()).toLowerCase().contains(text)){
                        dssearch.add(dg);
                    }
                }
                break;
            }
            return dssearch;
        }    
	
    public static DocGia getDocGiaByMA(String ma){
        for(DocGia dg : dsdg){
            if(dg.getMaDocGia().trim().equals(ma)){
                return dg;
            }
        }
        return null;
    }
    
    public static String getMaDGByTen(String ten){
        for(DocGia dg : dsdg){
            if(dg.getTenDocGia().trim().equals(ten)){
                return dg.getMaDocGia();
            }
        }
        return null;
    }
}
