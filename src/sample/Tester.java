package sample;

public class Tester extends NhanVien { // lop Tester ke thua tu lop NhanVien
    int soLoiPhatHien;
    public Tester(){} // phuong thuc khoi tao khong doi so cua lop Tester
    //Nap chong phuong thuc khoi tao cua lop Tester
    public Tester(String maNV,String tenNV,String kNV, String soDT,String thoiGianBDTL,float heSoLuong,int soLoiPhatHien){
        super(maNV,tenNV,kNV,soDT,thoiGianBDTL,heSoLuong); // goi den phuong thuc khoi tao lop NhanVien
        this.soLoiPhatHien=soLoiPhatHien;
        this.them=soLoiPhatHien;
    }
    public void setThem(int soLoiPhatHien){
        setthem(soLoiPhatHien);
        this.soLoiPhatHien=soLoiPhatHien;
    }
    // Phuong thuc tinh luong lop Tester
    public int luong(){
        this.luong=(int)(this.heSoLuong*500000+this.getthem()*20000);
        return this.luong;
    }
    public int getLuong(){

        return this.luong();
    }

}
