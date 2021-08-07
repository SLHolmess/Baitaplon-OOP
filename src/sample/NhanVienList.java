package sample;

public class NhanVienList { // lop NhanVienList tao 1 mang de luu tru cac NhanVien
    public NhanVien[] nhanvien = new NhanVien[1000]; // khoi tao va cap phat bo nho cho mang
    public int nextIndex=0;  // tong so luong nhan vien cua mang
    //Phuong thuc them 1 doi tuong NhanVien vao mang
    public void add(NhanVien a){
        if (nextIndex< nhanvien.length){
            nhanvien[nextIndex]=a;  // them doi tuong a vao mang tai vi tri nextIndex(vi tri cuoi cua mang)
            nextIndex++; // tang nextIndex
        }
    }
    public NhanVien vitri(int i){ // Phuong thuc tra ve nhan vien tai vi tri i
        return nhanvien[i];
    }
}
