package sample;

public class Dev extends NhanVien {  //lop Dev ke thua tu lop NhanVien
    int soGioLamThem;
    // Phuong thuc khoi tao khong doi so cua Dev
    public Dev(){};
    // Nap chong phuong thuc khoi tao cua Dev
    public Dev(String maNV,String tenNV,String kNV,String soDT,String thoiGianBDTL,float heSoLuong,int soGioLamThem){
        super(maNV,tenNV,kNV ,soDT,thoiGianBDTL,heSoLuong); // goi den phuong thuc khoi tao lop NhanVien
        this.soGioLamThem=soGioLamThem;
        this.them=soGioLamThem;
    }
    // Phuong thuc set cho thuoc tinh soGiaLamThem cua cac doi tuong Dev
    public void setThem(int soGioLamThem){
        setthem(soGioLamThem);
        this.soGioLamThem=soGioLamThem;
    }
    // Phuong thuc tinh luong cho cac doi tuong thuoc lop Dev
    public int luong(){
        this.luong=(int)(this.heSoLuong*1000000+this.getthem()*200000);
        return this.luong;
    }
    public int getLuong(){
        return this.luong();
    }

}
