package BLL;

import DAL.TacGiaDAL;
import java.util.ArrayList;

import Model.TacGia;
import javax.swing.JOptionPane;

public class DSTacGiaBLL {
	static ArrayList<TacGia> dstg = new ArrayList<>();
        TacGiaDAL tgdal = new TacGiaDAL();
    public DSTacGiaBLL() {
        if(dstg.size() == 0){
            this.dstg=tgdal.layDSTacGia();
        }
    }
    public static ArrayList<TacGia> layAllTacGia(){ 
        return dstg ; 
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    public boolean themTG(TacGia tg){ 
        dstg=layAllTacGia();
        if(tg.getMaTacGia().isEmpty()){ 
            showMess("Mã tác giả không đươc để trống");
            return false;
        }
        String regex = "^TG\\d{3,}";
        if(!tg.getMaTacGia().matches(regex)){ 
            showMess("Mã nhập không hợp lệ (Ví dụ:TG017)");
            return false;
        }
        for(TacGia a:dstg){ 
            if(a.getMaTacGia().equals(tg.getMaTacGia())){ 
                showMess("Mã tác giả đã tồn tại ");
                return false;
            }
        }
        if(tg.getNamSinh()>2010 || tg.getNamSinh()<1950){
            showMess("Năm sinh không hợp lệ");
            return false;
        }
        if(tg.getSoDienThoai().length()!=10 && tg.getSoDienThoai().length()!=11 ){ 
            showMess("Số điện thoại không hợp lệ");
            return false; 
        }
        if(tgdal.themTacGia(tg) >0){
            dstg.add(tg);
            showMess("Thêm tác giả thành công ");
            return true; 
        }
        showMess("Thêm tác giả thất bại ");
        return false ;
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
    public boolean suaTG(TacGia tg){ 
        dstg=layAllTacGia();
        if(tg.getMaTacGia().isEmpty()){ 
            showMess("Mã tác giả không đươc để trống");
            return false;
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
            return false;
        }
        if(tg.getSoDienThoai().length()!=10 && tg.getSoDienThoai().length()!=11 ){ 
            showMess("Số điện thoại không hợp lệ");
            return false; 
        }
        if(tg.getNamSinh()>2010 || tg.getNamSinh()<1950){
            showMess("Năm sinh không hợp lệ");
            return false;
        }
        if(tgdal.suaTacGia(tg) >0){
            int index = getIndexbyMaTG(tg.getMaTacGia());
            dstg.set(index, tg);
            
            showMess("Sửa tác giả thành công ");
            return true; 
        }
        showMess("Sửa tác giả thất bại ");
        return false;
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
    public ArrayList<TacGia> searchQuyDinh(String text,String type){ 
        ArrayList<TacGia> dssearch = new ArrayList();
        text=text.toLowerCase();
        switch(type){ 
            case "Tất cả":
                for(TacGia a:dstg){ 
                    if(a.getMaTacGia().toLowerCase().contains(text) || String.valueOf(a.getNamSinh()).contains(text) || a.getSoDienThoai().contains(text) || a.getTenTacGia().toLowerCase().contains(text)){
                        dssearch.add(a);
                    }
                }
                break ;
            case "Mã Tác Giả":
                for(TacGia a:dstg){ 
                    if(a.getMaTacGia().toLowerCase().contains(text)){
                        dssearch.add(a);
                    }
                }
                break ;
                case "Tên Tác Giả":
                    for(TacGia a:dstg){ 
                    if(a.getTenTacGia().toLowerCase().contains(text)){
                        dssearch.add(a);
                    }
                }
                    break;
                case "Năm Sinh":
                    for(TacGia a:dstg){ 
                    if(String.valueOf(a.getNamSinh()).contains(text)){
                        dssearch.add(a);
                    }
                }
                    break;
                case "Số Điện Thoại":
                    for(TacGia a:dstg){ 
                    if(a.getSoDienThoai().contains(text)){
                        dssearch.add(a);
                    }
                }
                    break;
                
        }
        return dssearch;
    }
    public String getTenTacGiabyMa(String maTG){ 
        for(TacGia a:dstg){ 
            if(a.getMaTacGia().equals(maTG) && a.getTrangThai() !=0){ 
                return a.getTenTacGia();
            }
        }
        return "";
    }
}
