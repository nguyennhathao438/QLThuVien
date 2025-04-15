package BLL;

import DAL.DocGiaDAL;
import java.util.ArrayList;

import Model.DocGia;
import javax.swing.JOptionPane;

public class DSDocGiaBLL {
	public static ArrayList<DocGia> dsdg ;
        
        public DSDocGiaBLL(){
            
        }
        
        public void docDSDG(){
            DocGiaDAL data = new DocGiaDAL();
            if (dsdg == null)
                dsdg = new ArrayList<DocGia>();
            dsdg = data.getAllDocGia();
        }
        
        public void showmess(String s){
            JOptionPane.showMessageDialog(null,s);
        }
        
	public int themDG(DocGia dg){
            DocGiaDAL data = new DocGiaDAL();
            if(dg.getMaDocGia().isEmpty() || dg.getTenDocGia().isEmpty() || dg.getSoDienThoai().isEmpty() || dg.getDiaChi().isEmpty() || dg.getMaLoaiDG().isEmpty()){
                showmess("Thông tin không được bỏ trống");
                return -1;
            }
            
            for(DocGia d : dsdg){
                if(d.getMaDocGia().trim().equals(dg.getMaDocGia().trim())){
                    showmess("Mã độc giả đã có");
                    return -1;
                }
            }
            
            if(data.ThemDG(dg) > 0){
                dsdg.add(dg);
                showmess("Thêm độc giả thành công");
                return -1;
            }
            
            showmess("Thêm độc giả thất bại");
            return -1;
            
	}
        
        public int suaDG(DocGia dg){
            DocGiaDAL data = new DocGiaDAL();
            if(dg.getMaDocGia().isEmpty() || dg.getTenDocGia().isEmpty() || dg.getSoDienThoai().isEmpty() || dg.getDiaChi().isEmpty() || dg.getMaLoaiDG().isEmpty()){
                showmess("Thông tin không được bỏ trống");
                return -1;
            }
            
            for(DocGia d : dsdg){
                if(d.getMaDocGia().trim().equals(dg.getMaDocGia().trim())){
                    showmess("Mã độc giả đã có");
                    return -1;
                }
            }
            
            if(data.SuaDG(dg) > 0){
                //dsdg
                showmess("Sửa độc giả thành công");
                return 1;
            }
            showmess("Sửa độc giả thất bại");
            return -1;
        }

        public int xoaDG(String ma){
            DocGiaDAL data = new DocGiaDAL();
            if(ma.isEmpty()){
                showmess("Thông tin không được bỏ trống");
                return -1;
            }
            
            if(data.XoaDG(ma) > 0){
                //dsdg
                showmess("Xóa độc giả thành công");
                return -1;
            }
            showmess("Xóa độc giả thất bại");
            return -1;
        }    
        
     
        
        
	
}
