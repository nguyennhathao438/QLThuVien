package BLL;

import DAL.TacGiaDAL;
import java.util.ArrayList;

import Model.TacGia;
import javax.swing.JOptionPane;

public class DSTacGiaBLL {
	static ArrayList<TacGia> dstg = new ArrayList<>();
        
    public DSTacGiaBLL() {
    }
    public ArrayList<TacGia> layAllTacGia(){ 
        dstg=TacGiaDAL.layDSTacGia();
        return dstg ; 
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    public void themTG(TacGia tg){ 
        dstg=layAllTacGia();
        if(tg.getMaTacGia().isEmpty()){ 
            showMess("Mã tác giả không đươc để trống");
            return ;
        }
        for(TacGia a:dstg){ 
            if(a.getMaTacGia().equals(tg.getMaTacGia())){ 
                showMess("Mã tác giả đã tồn tại ");
                return ;
            }
        }
        if(tg.getNamSinh()>2010 || tg.getNamSinh()<1950){
            showMess("Năm sinh không hợp lệ");
            return ;
        }
        if(TacGiaDAL.themTacGia(tg) >0){
            showMess("Thêm tác giả thành công ");
            return ; 
        }
        showMess("Thêm tác giả thất bại ");
        
    }
    public void xoaTG(String maTG){ 
        if(TacGiaDAL.xoaTacGia(maTG) >0){ 
            showMess("Xoá tác giả thành công ");
            return ;
        }
        showMess("Xoá tác giả thất bại ");
    }
    public void suaTG(TacGia tg){ 
        dstg=layAllTacGia();
        if(tg.getMaTacGia().isEmpty()){ 
            showMess("Mã tác giả không đươc để trống");
            return ;
        }
        boolean kt=false ;
        for(TacGia a:dstg){ 
            if(a.getMaTacGia().equals(tg.getMaTacGia())){ 
                kt = true ;
                break ; 
            }
        }
        if(!kt){ 
            showMess("Không tìm thấy mã tác giả ");
            return ;
        }
        if(tg.getNamSinh()>2010 || tg.getNamSinh()<1950){
            showMess("Năm sinh không hợp lệ");
            return ;
        }
        if(TacGiaDAL.suaTacGia(tg) >0){
            showMess("Sửa tác giả thành công ");
            return ; 
        }
        showMess("Sửa tác giả thất bại ");
        
    }
    public TacGia getTacGia(String matg){ 
        return TacGiaDAL.layTacGia(matg);
    }
}
