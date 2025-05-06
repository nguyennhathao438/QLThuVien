
package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

public class KetNoiCSDL {
    SQLServerDataSource ds= new SQLServerDataSource();
    public KetNoiCSDL(){ 
<<<<<<< HEAD
        var server ="THANHTUNG\\MSSQLSERVER2";
        var user="sa";
        var password="7579100";
=======
        var server ="DESKTOP-1JQESS2\\SQLEXPRESS";
        var user="sa";
        var password="123";
>>>>>>> 41ab6e0431129ed168b3f872baf1ad4085bbd274
        var db="QLThuVien";
        var port=1433;
        
        ds.setUser(user);
        ds.setPassword(password);
        ds.setServerName(server);
        ds.setPortNumber(port);
        ds.setDatabaseName(db);        
        ds.setTrustServerCertificate(true);
    }
    public Connection getConnection() throws SQLServerException{ 
        return ds.getConnection();       
    }
}
