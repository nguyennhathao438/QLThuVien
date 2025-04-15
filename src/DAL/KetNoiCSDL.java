
package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

public class KetNoiCSDL {
    SQLServerDataSource ds= new SQLServerDataSource();
    public KetNoiCSDL(){ 
        var server ="DESKTOP-SAC9NS5";
        var user="sa";
        var password="quencmnr";
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
