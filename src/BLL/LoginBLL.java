
package BLL;

import DAL.LoginDAL;

public class LoginBLL {
    LoginDAL loginDAL = new LoginDAL();
    public String ktDangNhap(String tk,String mk){ 
        return loginDAL.ktraDangNhap(tk, mk);
    }
}
