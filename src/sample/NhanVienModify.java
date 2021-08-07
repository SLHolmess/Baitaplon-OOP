package sample;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import static java.sql.DriverManager.*;

public class NhanVienModify {
    //Phuong thuc findAll() de lay het du lieu NhanVien luu tru trong Databases
    public static NhanVienList findAll() {
        NhanVienList list = new NhanVienList(); // khoi tao danh sach NhanVien
        Connection connection = null;
        Statement statement = null;

        try {
            //lấy tất cả danh sách sinh viên
            //query
            connection =ConnectDatabase.TestConnextion();  // Ket noi voi databases
            String sql = "select * from dbo.A"; // cau lenh query su dung trong tim kiem cua sql server
            statement = connection.createStatement(); // khoi tao Statement de thuc hien cac cau lenh truy van voi co so du lieu sql
            ResultSet resultSet = statement.executeQuery(sql); // thuc hien truy van select va tra ve doi tuong cua resultSet
                                                               //resultSet tro den cac phan tu trong tableView

            //Doc cac du lieu trong bang cua sql Server
            while (resultSet.next()){
                String maNV=resultSet.getString("maNV");  //maNV luu tru gia tri String cua cot maNV
                String tenNV=resultSet.getString("tenNV"); //tenNV luu tru gia tri String cua cot tenNV
                String kNV=resultSet.getString("kNV"); // kNV luu tru gia tri String cua cot kNV
                String soDT=resultSet.getString("soDT"); //soDT luu tru gia tri String cua cot soDT
                String thoigianBDTL=resultSet.getString("thoigianBDTL"); //thoigianBDTL luu tru gia tri String cua cot thoigianBDTL
                float heSoLuong=resultSet.getFloat("heSoLuong"); //heSoLuong luu tru gia tri Float cua cot heSoLuong
                int them=resultSet.getInt("them"); // them luu tru gia tri integer cua cot them
                if (kNV == null){ // neu kNV la null thi bo qua
                    continue;
                }
                if (kNV.equals("Tester")){ // neu kNV la Tester khoi tao doi tuong Tester
                    NhanVien std = new Tester(maNV, tenNV,"Tester", soDT, thoigianBDTL, heSoLuong,them);
                    std.setkNV("Tester");
                    list.add(std); // them vao mang list
                } else if (kNV.equals("Dev")){
                    NhanVien std = new Dev(maNV, tenNV, "Dev", soDT, thoigianBDTL, heSoLuong,them);
                    std.setkNV("Dev");
                    list.add(std); // them vao mang list
                };
            }
        } catch (SQLException ex) { // xu ly ngoai le
            Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list; // tra ve danh sach list cac NhanVien trong bang table cua sql
    }

    public static void insert (NhanVien std){ //phuong thuc them NhanVien
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectDatabase.TestConnextion(); // ket noi voi database
            //tao cau lenh cho query
            String sql = "insert into A(maNV,tenNV,kNV,soDT,thoigianBDTL,hesoluong,luong,them) values(?,?,?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);
            statement.setString(1, std.getMaNV());  //them MaNV vao cot 1 cua table
            statement.setString(2, std.getTenNV()); //them tenNV vao cot 2 cua table
            statement.setString(3, std.getkNV()); //them kNV vao cot 3 cua table
            statement.setString(4, std.getSoDT()); //them soDT vao cot 4 cua table
            statement.setString(5, std.getThoiGianBDTL()); //them thoigianBDTL vao cot 5 cua table
            statement.setFloat(6, std.getHeSoLuong()); //them heSoLuong vao cot 6 cua table
            statement.setInt(7,std.getLuong()); //them luong vao cot 7 cua table
            statement.setInt(8,std.getthem()); //them them vao cot 8 cua table
            statement.execute();

        } catch (Exception ex) {
            Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static NhanVienList tinhLuong(NhanVienList listnv) { // tinh luong cac NhanVien
        for (int i = 0; i < listnv.nextIndex; i++) {
            listnv.vitri(i).luong = listnv.vitri(i).luong();
        }
        return listnv;
    }
    public static int delete(String id){ // ham xoa 1 phan tu trong table cua database
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //lấy tất cả danh sách sinh viên
            connection = ConnectDatabase.TestConnextion(); // ket noi Database
            //cau lenh query
            String sql = "delete from A where maNV=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, id);


            statement.execute(); // thuc hien truy van tra ve nhieu ket qua
            return 1;

        } catch (Exception ex) { // xu ly ngoai le
            Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static NhanVienList search (String field, String value){ // ham tim kiem
        NhanVienList list= new NhanVienList(); //tao danh sach NhanVien
        Connection connection = null;
        PreparedStatement statement = null;
        switch (field) {
            case "Mã NV":
                field = "maNV";
                break;
            case  "Tên NV":
                field = "tenNV";
                break;
            case  "số ĐT":
                field = "soDT";
                break;
            case "Thời gian BĐ trả lương":
                field = "thoigianBDTL";
                break;
            default:

        }

        try {
            connection = ConnectDatabase.TestConnextion(); // ket noi database
            //cau lenh query
            String sql = "select * from dbo.A where " + field + "=?";
            statement = connection.prepareCall(sql);
            statement.setString(1,value);
            ResultSet resultSet=statement.executeQuery(); // thuc hien truy van Select va tra ve doi tuong resultSet
            while (resultSet.next()){
                String maNV=resultSet.getString("maNV");
                String tenNV=resultSet.getString("tenNV");
                String kNV=resultSet.getString("kNV");
                String soDT=resultSet.getString("soDT");
                String thoigianBDTL=resultSet.getString("thoigianBDTL");
                float heSoLuong=resultSet.getFloat("heSoLuong");
                int them=resultSet.getInt("them");
                if (kNV == null){
                    continue;
                }
                if (kNV.equals("Tester")){
                    NhanVien std = new Tester(maNV, tenNV,"Tester", soDT, thoigianBDTL, heSoLuong,them);
                    std.setkNV("Tester");
                    list.add(std);
                } else if (kNV.equals("Dev")){
                    NhanVien std = new Dev(maNV, tenNV, "Dev", soDT, thoigianBDTL, heSoLuong,them);
                    std.setkNV("Dev");
                    list.add(std);
                };
            }
            statement.execute();

        } catch (Exception ex) { // xu ly ngoai le
            Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list; // tra ve danh sach mang NhanVien
    }
}
