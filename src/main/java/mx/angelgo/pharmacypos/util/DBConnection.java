package mx.angelgo.pharmacypos.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection(String url, String user, String passwd) throws Exception {
        return DriverManager.getConnection(url, user, passwd
        );
    }
}
