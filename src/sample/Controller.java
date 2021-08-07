package sample;
import com.sun.javafx.fxml.ParseTraceElement;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;

public class Controller implements Initializable {
    @FXML
    private TextField tenNV;
    @FXML
    private TextField maNV;
    @FXML
    private TextField soDT;

    @FXML
    private DatePicker thoiGianBDTL;
    @FXML
    private TextField heSoluong;
    @FXML
    private TextField deleteMaNV;
    @FXML
    private TextField soLoiTimDuoc;
    @FXML
    private TextField soGioLamThem;
    @FXML
    private TextField timkiem;

    @FXML
    private TextField maNVsua;
    @FXML
    private TextField tenmoi;
    @FXML
    private TextField sdtmoi;
    @FXML
    private DatePicker thoigianTLmoi;
    @FXML
    private TextField hesoluongmoi;
    @FXML
    private TextField sogiolamthemmoi;
    @FXML
    private TextField soloitimduocmoi;


    @FXML
    private TableView<NhanVien> table;
    @FXML
    private TableColumn<NhanVien, String> ma;
    @FXML
    private TableColumn<NhanVien, String> ten;
    @FXML
    private TableColumn<NhanVien, String> phone;
    @FXML
    private TableColumn<NhanVien, String> time;
    @FXML
    private TableColumn<NhanVien, Float> hsl;
    @FXML
    private TableColumn<NhanVien, Integer> luongColumn;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> kieuNV;
    @FXML
    private ComboBox<String> kieunvmoi;

    ObservableList<String> list1 = FXCollections.observableArrayList("Dev", "Tester");


    private ObservableList<NhanVien> nvList = FXCollections.observableArrayList();
    public void show(ActionEvent actionEvent){
        allNV(NhanVienModify.findAll());
    } // nut hien thi
    public void add(ActionEvent actionEvent){ // nut them
        NhanVienList listnv=NhanVienModify.findAll();
        String kieuNhanVien=kieuNV.getValue().toString();
        if(kieuNhanVien.equals("Tester")){
            NhanVien nv = new Tester();
            String s=heSoluong.getText();
            if(maNV.getText().equals("")) nv.setMaNV(null); else nv.setMaNV(maNV.getText());
            if(tenNV.getText().equals("")) nv.setTenNV(null);
            else nv.setTenNV(tenNV.getText());
            nv.setkNV("Tester");
            if(soDT.getText().equals("")) nv.setSoDT(null);
            else nv.setSoDT(soDT.getText());
            if(heSoluong.getText().equals("")) nv.setHeSoLuong(0);
            else nv.setHeSoLuong(parseFloat(s));
            nv.setThoiGianBDTL(thoiGianBDTL.getValue().toString());
            nv.setthem(Integer.parseInt(soLoiTimDuoc.getText()));
            NhanVienModify.insert(nv) ;
            listnv.add(nv);
            allNV(NhanVienModify.findAll());
            home();
        } else {
            NhanVien nv = new Dev();
            String s=heSoluong.getText();
            if(maNV.getText().equals("")) nv.setMaNV(null); else nv.setMaNV(maNV.getText());
            if(tenNV.getText().equals("")) nv.setTenNV(null);
            else nv.setTenNV(tenNV.getText());
            nv.setkNV("Dev");
            if(soDT.getText().equals("")) nv.setSoDT(null);
            else nv.setSoDT(soDT.getText());
            if(heSoluong.getText().equals("")) nv.setHeSoLuong(0);
            else nv.setHeSoLuong(java.lang.Float.parseFloat(s));
            nv.setThoiGianBDTL(thoiGianBDTL.getValue().toString());
            nv.setthem(Integer.parseInt(soGioLamThem.getText()));
            NhanVienModify.insert(nv);
            listnv.add(nv);
            allNV(NhanVienModify.findAll());
            home();
        }
    }
    public void allNV(NhanVienList listnv) { // hien thi danh sach ra bang trong giao dien javafx
        table.getItems().clear();
        listnv = NhanVienModify.tinhLuong(listnv);
        for(int i=0;i< listnv.nextIndex;i++) {
            nvList.add(listnv.vitri(i));
        }
        ma.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("maNV"));
        ten.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("tenNV"));
        phone.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("soDT"));
        time.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("thoiGianBDTL"));
        hsl.setCellValueFactory(new PropertyValueFactory<NhanVien,Float>("heSoLuong"));
        luongColumn.setCellValueFactory(new PropertyValueFactory<NhanVien,Integer>("luong"));
        table.setItems(nvList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NhanVienList listnv= NhanVienModify.findAll();
        allNV(listnv);
        kieuNV.setItems(list1);
        comboBox.setItems(list);
        kieunvmoi.setItems(list1);
    }
    public void home(){ // cai dat cac textField ve ban dau
        tenNV.setText("");
        maNV.setText("");
        heSoluong.setText("");
        soDT.setText("");
        kieuNV.setValue("");
        thoiGianBDTL.setValue(null);
        soLoiTimDuoc.setText("");
        soGioLamThem.setText("");
    }
    public void delete(ActionEvent actionEvent){ // nut xoa
        String s=deleteMaNV.getText(); // doc du lieu tu TextField co id la deleteMaNV
        NhanVienModify.delete(s);
        allNV(NhanVienModify.findAll());
    }

    public void update(ActionEvent actionEvent){ // nut cap nhat
        NhanVienList listnv=NhanVienModify.findAll(); // doc cac du lieu trong table cua sql server
        String manv = maNVsua.getText(); // doc du lieu tu TextField co id maNVsua
        NhanVienModify.delete(manv);
        String kieuNhanVien=kieunvmoi.getValue().toString(); // doc combobox co id kieuNhanVien va chuyen sang String
        // doc du lieu tu cac TextField va tao ca doi tuong tuong ung
        if(kieuNhanVien.equals("Tester")){
            NhanVien nv = new Tester();
            String s=hesoluongmoi.getText();
            if(tenmoi.getText().equals("")) nv.setTenNV(null);
            else nv.setTenNV(tenmoi.getText());
            nv.setkNV("Tester");
            if(sdtmoi.getText().equals("")) nv.setSoDT(null);
            else nv.setSoDT(sdtmoi.getText());
            if(hesoluongmoi.getText().equals("")) nv.setHeSoLuong(0);
            else nv.setHeSoLuong(parseFloat(s));
            nv.setThoiGianBDTL(thoigianTLmoi.getValue().toString());
            nv.setthem(Integer.parseInt(soloitimduocmoi.getText()));
            nv.setMaNV(manv);
            NhanVienModify.insert(nv);
            listnv.add(nv); // them vao listnv
            allNV(NhanVienModify.findAll());// hien thi
            home();
        } else {
            NhanVien nv = new Dev();
            String s=hesoluongmoi.getText();
            if(tenmoi.getText().equals("")) nv.setTenNV(null);
            else nv.setTenNV(tenmoi.getText());
            nv.setkNV("Dev");
            if(sdtmoi.getText().equals("")) nv.setSoDT(null);
            else nv.setSoDT(sdtmoi.getText());
            if(hesoluongmoi.getText().equals("")) nv.setHeSoLuong(0);
            else nv.setHeSoLuong(java.lang.Float.parseFloat(s));
            nv.setThoiGianBDTL(thoigianTLmoi.getValue().toString());
            nv.setthem(Integer.parseInt(sogiolamthemmoi.getText()));
            nv.setMaNV(manv);
            NhanVienModify.insert(nv);
            listnv.add(nv);
            allNV(NhanVienModify.findAll());
            home();
        }

    }

    ObservableList<String> list = FXCollections.observableArrayList("Mã NV", "Tên NV", "số ĐT", "Thời gian BĐ trả lương");

    public void timkiem(ActionEvent actionEvent){ // nut tim kiem
        String comboBoxValue = comboBox.getValue(); // doc du lieu tu comboBox
        String value = timkiem.getText(); // doc du lieu tu TextField co id timkiem
        NhanVienList listnv = NhanVienModify.search(comboBoxValue, value );
        allNV(listnv); // hien thi
    }

    public void luongTB(){
        NhanVienList listnv=NhanVienModify.findAll(); // lay du lieu tu database
        int sum=0;
        for(int i=0;i<listnv.nextIndex;i++){
            sum+= listnv.vitri(i).luong();
        }
        // hien thi thong bao
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // loai thong bao
        alert.setTitle("Thông báo"); // nhan cua thong bao
        alert.setHeaderText("Thực hiện tính lương trung bình thành công"); // tieu de thong bao
        alert.setContentText("Lương trung bình là: "+sum/listnv.nextIndex); // noi dung chinh
        ButtonType buttonTypeYes = new ButtonType("OK", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes);
        alert.show(); // hien thi thong bao
    }
    public void thongke(){ // ham thong ke nhan vien co muc luong duoi muc trung binh
        int sum=0;
        NhanVienList listnv=NhanVienModify.findAll(); // lay nhan vien trong database
        NhanVienList result=new NhanVienList(); // tao danh sach luu tru cac NhanVien
        for(int i=0;i<listnv.nextIndex;i++){
            sum+= listnv.vitri(i).luong();
        }
        float tb=sum/listnv.nextIndex;
        for(int i=0;i<listnv.nextIndex;i++){
            if(listnv.vitri(i).luong<tb) result.add(listnv.vitri(i));
        }
        allNV(result);
        // hien thi thong bao
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // loai thong bao
        alert.setTitle("Thông báo"); // nhan cua thong bao
        alert.setHeaderText("Thống kê "); // tieu de
        alert.setContentText("Nhân Viên có lương thấp hơn trung bình :");  // noi dung
        ButtonType buttonTypeYes = new ButtonType("OK", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes);
        alert.show(); // hien thi thong bao
    }
}
