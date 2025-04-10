package BLL;

import DAL.TacGiaDAL;
import java.util.ArrayList;

import Model.TacGia;
import javax.swing.JOptionPane;

public class DSTacGiaBLL {
	static ArrayList<TacGia> dstg = new ArrayList<>();
        TacGiaDAL tgdal = new TacGiaDAL();
    public DSTacGiaBLL() {
        this.dstg=tgdal.layDSTacGia();
    }
    public static ArrayList<TacGia> layAllTacGia(){ 
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
        if(tgdal.themTacGia(tg) >0){
            dstg.add(tg);
            showMess("Thêm tác giả thành công ");
            return ; 
        }
        showMess("Thêm tác giả thất bại ");
        
    }
    public void xoaTG(String maTG){ 
        if(tgdal.xoaTacGia(maTG) >0){
            int index = getIndexbyMaTG(maTG);
            if(index != -1){
                dstg.get(index).setTrangThai(0);
            }
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
        if(tgdal.suaTacGia(tg) >0){
            int index = getIndexbyMaTG(tg.getMaTacGia());
            dstg.set(index, tg);
            
            showMess("Sửa tác giả thành công ");
            return ; 
        }
        showMess("Sửa tác giả thất bại ");
        
    }
    public int getIndexbyMaTG(String matg){ 
        for(int i=0 ; i<dstg.size();i++){ 
            if(dstg.get(i).getMaTacGia().equals(matg)){ 
                return i;
            }
        }
        return -1;
    }
    public TacGia getTacGia(String matg){ 
        return tgdal.layTacGia(matg);
    }
}
