package sample;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    Connection conn = null;

    public static Connection TestConnextion() {
        try {
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=NhanVien;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(dbURL);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
