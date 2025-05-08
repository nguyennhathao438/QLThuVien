/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.QuyDinhDAL;
import MODEL.QuyDinh;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DSQuyDinh {
    QuyDinhDAL qddal=new QuyDinhDAL();
    static ArrayList<QuyDinh> dsqd=new ArrayList();
    public DSQuyDinh(){ 
        dsqd = qddal.layDSQuyDinh();
    }
    public static ArrayList<QuyDinh> layAllQuyDinh(){ 
        return dsqd;
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    
    public boolean themQD(QuyDinh qd){ 
        dsqd= layAllQuyDinh();
        String regex = "^QD\\d{3,}";
        if(!qd.getMaQuyDinh().matches(regex)){ 
            showMess("Mã nhập không hợp lệ (Ví dụ:QD017)");
            return false; 
        }
      if(qd.getMaQuyDinh().isEmpty()){ 
            showMess("Mã quy định không được để trống ");
            return false;
        }
        for(QuyDinh a:dsqd){ 
            if(a.getMaQuyDinh().equals(qd.getMaQuyDinh())){ 
                showMess("Mã quy định đã tồn tại ");
                return false;
            }
        }
        if(qd.getNoiDung().isEmpty()){ 
            showMess("Nội dung không được để trống ");
            return false;
        }
        if(qd.getSoTien()<0){ 
            showMess("Số tiền phải lớn hơn 0");
            return false; 
        }
        if(qddal.themQuyDinh(qd)> 0){ 
            dsqd.add(qd);
            showMess("Thêm quy định thành công ");
            return true;
        }
        showMess("Thêm quy định thất bại ");
        return false ;
    }
    public void xoaQD(String maqd){ 
        if(qddal.xoaQuyDinh(maqd)>0){ 
            int index = getIndexbyMaQD(maqd);
            dsqd.get(index).setTrangThai(0);
            showMess("Xoá quy định thành công ");
            
            return ;
        }
        showMess("Xoá qưy định thất bại ");
    }
    public boolean suaQD(QuyDinh qd){ 
        dsqd= layAllQuyDinh();
      if(qd.getMaQuyDinh().isEmpty()){ 
            showMess("Mã quy định không được để trống ");
            return false;
        }
      boolean kt=false;
        for(QuyDinh a:dsqd){ 
            if(a.getMaQuyDinh().equals(qd.getMaQuyDinh())){ 
                kt=true;
                break;
            }
        }
        if(!kt){ 
            showMess("Mã quy định không tồn tại ");
            return false;
        }
        if(qd.getNoiDung().isEmpty()){ 
            showMess("Nội dung không được để trống ");
            return false;
        }
        if(qd.getSoTien()<0){ 
            showMess("Số tiền phải lớn hơn 0");
            return false; 
        }
        if(qddal.suaQuyDinh(qd)> 0){ 
            int index = getIndexbyMaQD(qd.getMaQuyDinh());
            dsqd.set(index,qd);
            showMess("Sửa quy định thành công ");
            return true;
        }
        showMess("Sửa quy định thất bại ");
        return false ;
    }
    public int getIndexbyMaQD(String maqd){ 
        for(int i=0;i<dsqd.size();i++){
            if(dsqd.get(i).getMaQuyDinh().equals(maqd))
                return i;
        }
        return -1;
    }
    public QuyDinh getQuyDinh(String maqd){ 
        return qddal.layQuyDinh(maqd);
    }
    public ArrayList<QuyDinh> searchQuyDinh(String text,String type){ 
        ArrayList<QuyDinh> dssearch = new ArrayList();
        text=text.toLowerCase();
        switch(type){ 
            case "Tất cả":
                for(QuyDinh a:dsqd){ 
                    if(a.getMaQuyDinh().toLowerCase().contains(text) || a.getNoiDung().toLowerCase().contains(text) || String.valueOf(a.getSoTien()).contains(text)){
                        dssearch.add(a);
                    }
                }
                break ;
            case "Mã Quy Định":
                for(QuyDinh a:dsqd){ 
                    if(a.getMaQuyDinh().toLowerCase().contains(text) ){ 
                        dssearch.add(a);
                    }
                }
                break ;
                case "Nội Dung":
                    for(QuyDinh a:dsqd){ 
                    if( a.getNoiDung().toLowerCase().contains(text)){ 
                        dssearch.add(a);
                    }
                }
                    break;
                case "Tiền Phạt":
                    for(QuyDinh a:dsqd){ 
                    if(String.valueOf(a.getSoTien()).contains(text)){ 
                        dssearch.add(a);
                    }
                    break;
                }
                
        }
        return dssearch;
    }
}
