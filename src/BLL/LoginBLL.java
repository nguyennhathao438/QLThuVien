
package BLL;

import DAL.LoginDAL;
import Model.ThuThu;
import javax.swing.JOptionPane;

public class LoginBLL {
    private LoginDAL loginDAL = new LoginDAL();
    private static ThuThu thuthu ;

    public LoginBLL() {
    }
    
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
        }
    public boolean ktDangNhap(String tk,String mk){ 
        
        if(tk.equals("")){ 
            showMess("Tài khoản không được để trống");
            return false;
        }
        if(mk.equals("")){ 
            showMess("Mật khẩu không được để trống");
            return false;
        }
        ThuThu tt =  loginDAL.ktraDangNhap(tk, mk);
        if(tt == null){ 
            showMess("Tài khoản hoặc mật khẩu không chính xác");
            return false;
        }
        this.thuthu = tt;
        return true;
    }
    public String getMaThuThu(){ 
        return thuthu.getMaThuThu();
    }
    public String getTenThuThu(){ 
        return thuthu.getTenThuThu();
    }
    public void dangXuat(){ 
        this.thuthu=null;
    }
    
}
