package BLL;

import DAL.TheLoaiDAL;
import java.util.ArrayList;

import Model.TheLoai;
import javax.swing.JOptionPane;

public class DSTheLoaiBLL {
    static ArrayList<TheLoai> dsTheLoai = new ArrayList<>();
    TheLoaiDAL tlDAL = new TheLoaiDAL();
    public DSTheLoaiBLL()
    {
        this.dsTheLoai = tlDAL.layDSTheLoai();
    }
    
    public static ArrayList<TheLoai> layALLTheLoai()
    {
        return dsTheLoai;
    }
    
    public void showMess(String s)
    {
        JOptionPane.showMessageDialog(null,s);
    }
    
    public void themTheLoai(TheLoai tl)
    {
        dsTheLoai=layALLTheLoai();
        if(tl.getMaTheLoai().isEmpty())
        {
            showMess("Mã thể loại không được trống");
        }
        String themTL = "^TL\\d{3,}";
        if(!tl.getMaTheLoai().matches(themTL))
        {
            showMess("Mã thể loại không hợp lệ. Ví dụ:TL001");
            return;
        }
        for(TheLoai theLoai : dsTheLoai)
        {
            if(theLoai.getMaTheLoai().equals(tl.getMaTheLoai()))
            {
                showMess("Mã thể loại đã tồn tại");
                return;
            }
        }
        
        if(tlDAL.themTheLoai(tl) > 0)
        {
            dsTheLoai.add(tl);
            showMess("Thêm thể loại thành công");
            return;
        }
        showMess("Thêm thể loại thất bại");
    }
	
    public void suaTheLoai(TheLoai tl)
    {
        dsTheLoai = layALLTheLoai();
        if(tl.getMaTheLoai().isEmpty())
        {
            showMess("Mã thể loại không được trống");
            return;
        }
        boolean kt = false;
        for(TheLoai theLoai : dsTheLoai)
        {
            if(theLoai.getTheLoai().equals(tl.getMaTheLoai()))
            {
                kt=true;
                break;
            }
        }
        if(!kt)
        {
            showMess("Không tìm thấy mã thể loại");
            return;
        }
        if(tlDAL.suaTheLoai(tl) > 0)
        {
            int index = getIndexbyMaTL(tl.getMaTheLoai());
            dsTheLoai.set(index,tl);
            showMess("Sửa thể loại thành công");
            return;
        }
        showMess("Sửa thể loại thất bại");
    }
    
    public void xoaTheLoai(String maTL)
    {
        if(tlDAL.xoaTheLoai(maTL) > 0)
        {
            int index = getIndexbyMaTL(maTL);
            if(index != 0)
            {
               dsTheLoai.get(index).setTrangThai(0);
                showMess("Xóa thể looại thành công");
                return;
            }
        }
        showMess("Xóa thể loại thất bại");
    }
    
    public int getIndexbyMaTL(String maTL)
    {
        for(int i=0; i<dsTheLoai.size(); i++)
        {
            if(dsTheLoai.get(i).getMaTheLoai().equals(maTL))
            {
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<TheLoai> searchTheLoai(String type, String text)
    {
        ArrayList<TheLoai> dsSearch = new ArrayList<>();
        text = text.toLowerCase();
        switch(type)
        {
            case "Tất cả":
                for(TheLoai tl : dsTheLoai)
                {
                   if(tl.getMaTheLoai().toLowerCase().contains(text) || tl.getTheLoai().toLowerCase().contains(text))
                   {
                       dsSearch.add(tl);
                   }
                }
                break;
            case "Mã thể loại":
                for(TheLoai tl : dsTheLoai)
                {
                    if(tl.getMaTheLoai().toLowerCase().contains(text))
                    {
                        dsSearch.add(tl);
                    }
                }
                break;
            case "Thê loại":
                for(TheLoai tl : dsTheLoai)
                {
                    if(tl.getTheLoai().toLowerCase().contains(text))
                    {
                        dsSearch.add(tl);
                    }
                }
                break;
        }
        return dsSearch;
    }
    
    public TheLoai getTL(String maTL)
    {
        return tlDAL.layTheLoai(maTL);
    }
    
    public String getTheLoaiByMa(String maTL)
    {
        for(TheLoai tl : dsTheLoai)
        {
            if(tl.getMaTheLoai().equals(maTL))
            {
                return tl.getTheLoai();
            }
        }
        return "";
    }
}
