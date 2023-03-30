package com.example.covid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19.DAO.UserDAO;
import com.example.covid_19.DTO.UserDTO;
import com.example.covid_19.Fragment.DateFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    EditText edTenDNDangKY, edMKDangKy, edNgaySinhDK, edCMNDDangKy;
    Button btnDongY, btnThoat;
    RadioGroup grGioiTinh;
    RadioButton rdNam;
    String GioiTinh;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDNDangKY = (EditText) findViewById(R.id.edTenDNDangKY);
        edMKDangKy = (EditText) findViewById(R.id.edMKDangKy);
        edNgaySinhDK = (EditText) findViewById(R.id.edNgaySinhDK);
        edCMNDDangKy = (EditText) findViewById(R.id.edCMNDDangKy);

        btnDongY = (Button) findViewById(R.id.btnDongY);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        grGioiTinh = (RadioGroup) findViewById(R.id.grGioiTinh);
        rdNam = (RadioButton) findViewById(R.id.rdNam);


        btnDongY.setOnClickListener(this);
        btnThoat.setOnClickListener(this);

        edNgaySinhDK.setOnFocusChangeListener(this);

        userDAO = new UserDAO(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDongY:
                String TenDangNhap = edTenDNDangKY.getText().toString();
                String MatKhau = edMKDangKy.getText().toString();
                if (grGioiTinh.getCheckedRadioButtonId()== R.id.rdNam) {
                    GioiTinh = "Nam";
                } else if (grGioiTinh.getCheckedRadioButtonId()== R.id.rdNu) {
                    GioiTinh = "Nữ";
                }
                String NgaySinh = edNgaySinhDK.getText().toString();
                String CMND = edCMNDDangKy.getText().toString();

                if(TenDangNhap==null || TenDangNhap.equals("")){
                    //Thông báo cho người dùng
                    Toast.makeText(DangKyActivity.this,"Vui lòng nhập tên đang nhâp!!",Toast.LENGTH_SHORT).show();
                }else if (MatKhau == null || MatKhau.equals("")) {
                    Toast.makeText(DangKyActivity.this,"Vui lòng nhập mật khẩu!!",Toast.LENGTH_SHORT).show();
                }else if (MatKhau.length()<6) {
                    Toast.makeText(DangKyActivity.this,"Vui lòng nhập mật khẩu có ít nhất 6 kí tự!!",Toast.LENGTH_SHORT).show();
                }else if (userDAO.KiemTraTenDK(TenDangNhap)) {
                    Toast.makeText(DangKyActivity.this,"Tên tài khoản này đã tại!!",Toast.LENGTH_SHORT).show();
                }
                else if (userDAO.KiemTraCMND(CMND)) {
                    Toast.makeText(DangKyActivity.this,"CMND này đã tồn tại!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setTENDN(TenDangNhap);
                    userDTO.setMK(MatKhau);
                    userDTO.setGIOITINH(GioiTinh);
                    userDTO.setNGAYSINH(NgaySinh);
                    userDTO.setCMND(CMND);

                    long ktra = userDAO.ThemUser(userDTO);
                    if(ktra != 0){
                        Toast.makeText(DangKyActivity.this,"Thêm thành công hãy bấm thoát để đăng nhập",Toast.LENGTH_SHORT).show();
                        edTenDNDangKY.setText("");
                        edMKDangKy.setText("");
                        edCMNDDangKy.setText("");
                        edNgaySinhDK.setText("");
                        rdNam.setChecked(true);
                    }else{
                        Toast.makeText(DangKyActivity.this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnThoat:
                Intent dangNhap = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(dangNhap);
                break;
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus){
        int id = v.getId();
        switch (id){
            case R.id.edNgaySinhDK:
                if(hasFocus){
                    DateFragment date = new DateFragment();
                    date.show(getSupportFragmentManager(),"Ngày Sinh");
                }
                break;
        }
    }
}