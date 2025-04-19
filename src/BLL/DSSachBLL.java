package BLL;

import DAL.SachDAL;
import java.util.ArrayList;

import Model.Sach;
import java.util.Locale;
import javax.swing.JOptionPane;

public class DSSachBLL {
	static ArrayList<Sach> dsSach = new ArrayList<>();
        SachDAL sachDAL = new SachDAL();
        
        public DSSachBLL()
        {
            this.dsSach = sachDAL.layDSSach();
        }
        
        public static ArrayList<Sach> layAllSach()
        {
            return dsSach;
        }
        
        public void showMess(String s)
        {
            JOptionPane.showMessageDialog(null,s);
        }
        
	public void themSach(Sach s){
            dsSach=layAllSach();
            if(s.getMaSach().isEmpty())
            {
                showMess("Mã tác giả không được trống");
                return;
            }
            String themMS = "^SACH\\d{3,}";
            if(!s.getMaSach().matches(themMS))
            {
                showMess("Mã sách không hợp lệ. Ví dụ:S001");
                return;
            }
            
            for(Sach sach : dsSach)
            {
                if(sach.getMaSach().equals(s.getMaSach()))
                {
                    showMess("Mã sách đã tồn tại");
                    return;
                }
            }
            
            if(s.getNamXuatBan()<1800 || s.getNamXuatBan()>=2026)
            {
                showMess("Năm xuất bản không hợp lệ");
                return;
            }
            if(sachDAL.themSach(s)>0)
            {
                dsSach.add(s);
                showMess("Thêm sách thành công");
                return;
            }
            showMess("Thêm sách thất bại");
	}
        
        public void xoaSach(String maS)
        {
            if(sachDAL.xoaSach(maS) > 0)
            {
                int index = getIndexbyMaSach(maS);
                if(index != -1)
                {
                    dsSach.get(index).setTrangThai(0);
                }
                showMess("Xoá sách thành công");
                return;
            }
            showMess("Xóa sách thất bại");
        }
        
        public void suaSach(Sach s)
        {
            dsSach=layAllSach();
            if(s.getMaSach().isEmpty())
            {
                showMess("Mã sách không được để trống");
                return;
            }
            boolean kt = false;
            for(Sach sach : dsSach)
            {
                if(sach.getMaSach().equals(s.getMaSach()))
                {
                    kt=true;
                    break;
                }
            }
            if(!kt)
            {
                showMess("Không tìm thấy mã sách");
                return;
            }
            if(s.getNamXuatBan()<1800 || s.getNamXuatBan()>=2026)
            {
                showMess("Năm xuất bản không hợp lệ");
                return;
            }
            if(sachDAL.suaSach(s) > 0)
            {
                int index = getIndexbyMaSach(s.getMaSach());
                dsSach.set(index,s);
                showMess("Sửa sách thành công");
                return;
            }
            showMess("Sủa sách thất bại");
        }
        
        public int getIndexbyMaSach(String maS)
        {
            for(int i=0; i<dsSach.size(); i++)
            {
                if(dsSach.get(i).getMaSach().equals(maS))
                {
                    return i;
                }
            }
            return -1;
        }
        
       public ArrayList<Sach> searchSach(String text, String type)
       {
           ArrayList<Sach> dsSearch = new ArrayList();
           text = text.toLowerCase();
           switch(type)
           {
               case "Tất cả":
                   for(Sach sach : dsSach)
                   {
                       if(sach.getMaSach().toLowerCase().contains(text) || sach.getTenSach().toLowerCase().contains(text) || String.valueOf(sach.getNamXuatBan()).contains(text))
                       {
                           dsSearch.add(sach);
                       }
                   }
                   break;
               case "Mã sách":
                   for(Sach sach:dsSach)
                   {
                       if(sach.getMaSach().toLowerCase().contains(text))
                       {
                           dsSearch.add(sach);
                       }
                   }
                   break;
               case "Tên sách":
                   for(Sach sach:dsSach)
                   {
                       if(sach.getTenSach().toLowerCase().contains(text))
                       {
                           dsSearch.add(sach);
                       }
                   }
                   break;
               case "Năm xuất bản":
                   for(Sach sach:dsSach)
                   {
                       if(String.valueOf(sach.getNamXuatBan()).contains(text))
                       {
                           dsSearch.add(sach);
                       }
                   }
                   break;
           }
           return dsSearch;
       }
       
       public Sach getSach(String maS)
       {
           return sachDAL.laySach(maS);
       }
       
       public String getSachbyMa(String maS)
       {
           for(Sach sach:dsSach)
           {
              if(sach.getMaSach().equals(maS) && sach.getTrangThai() != 0)
              {
                  return sach.getTenSach();
              }
           }
           return "";
       }
}
