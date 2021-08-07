package sample;

abstract class NhanVien { // Lop truu tuong NhanVien
    protected String tenNV;
    protected String maNV;
    protected String soDT;
    protected String thoiGianBDTL;
    protected float heSoLuong;
    protected String kNV;
    protected int luong;
    protected int them;
    // Phuong thuoc khoi tao khong doi so cua lop NhanVien
    public NhanVien(){};
    //Nap chong phuong thuc khoi tao lop NhanVien
    public NhanVien(String maNV,String tenNV,String kNV,String soDT,String thoiGianBDTL,float heSoLuong){
        this.setTenNV(tenNV);
        this.setMaNV(maNV);
        this.setSoDT(soDT);
        this.setThoiGianBDTL(thoiGianBDTL);
        this.setHeSoLuong(heSoLuong);
        this.setkNV(kNV);
    }
    // Cac phuong thuc get, set cua cac thuoc tinh lop NhanVien
    public void setthem(int them){
        this.them=them;
    }
    public int getthem(){return this.them;};
    public  String getkNV(){
        return this.kNV;
    }
    public abstract int getLuong();
    public String getTenNV(){
        return this.tenNV;
    }
    public void setTenNV(String tenNV){
        this.tenNV=tenNV;
    }
    public String getMaNV(){
        return this.maNV;
    }
    public void setMaNV(String maNV){
        this.maNV=maNV;
    }
    public String getSoDT(){
        return this.soDT;
    }
    public void setSoDT(String soDT){
        this.soDT=soDT;
    }
    public String getThoiGianBDTL(){
        return this.thoiGianBDTL;
    }
    public void setThoiGianBDTL(String thoiGianBDTL){
        this.thoiGianBDTL=thoiGianBDTL;
    }
    public float getHeSoLuong(){
        return this.heSoLuong;
    }
    public void setHeSoLuong(float heSoLuong){ this.heSoLuong=heSoLuong; }
    public void setkNV(String kNV){this.kNV=kNV;}
    public abstract int luong(); // lop truu tuong luong se duoc cai de boi cac lop con
}
