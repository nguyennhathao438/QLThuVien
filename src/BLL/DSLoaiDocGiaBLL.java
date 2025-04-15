package BLL;

import DAL.LoaiDGDAL;
import java.util.ArrayList;

import Model.LoaiDocGia;
import javax.swing.JOptionPane;

public class DSLoaiDocGiaBLL {
	static ArrayList<LoaiDocGia> dsLoaiDG ;

        public void docDSLoai(){
            LoaiDGDAL data = new LoaiDGDAL();
            if (dsLoaiDG == null)
                dsLoaiDG = new ArrayList<LoaiDocGia>();
            dsLoaiDG = data.getAllLoaiDG();
        }
        
        public void showmess(String s){
            JOptionPane.showMessageDialog(null,s);
        }
        
	public int themLoaiDG(LoaiDocGia ldg){
            LoaiDGDAL data = new LoaiDGDAL();
            //Kiem tra du lieu hop le
            if(ldg.getMaLoaiDG().isEmpty() || ldg.getTenLoaiDG().isEmpty() || ldg.getMoTa().isEmpty()){
                showmess("Thông tin không được bỏ trống");
                return -1;
            }
            if(ldg.getGioiHanMuon() <= 0){
                showmess("Giới hạn mượn không hợp lệ");
                return -1;
            }
            //Kiem tra ma loai duy nhat
            for(LoaiDocGia loai : dsLoaiDG){
                if(loai.getMaLoaiDG().trim().equals(ldg.getMaLoaiDG().trim())){
                    showmess("Mã loại độc giả đã tồn tại");
                    return -1;
                }
            }
            
            if(data.ThemLoaiDG(ldg) > 0){
                dsLoaiDG.add(ldg);
                showmess("Thêm loại độc giả thành công");
                return 1;
            }
                showmess("Thêm loại độc giả thất bại");
                return -1;
         
	}

        
}
