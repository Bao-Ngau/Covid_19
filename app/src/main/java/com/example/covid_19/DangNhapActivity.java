package com.example.covid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19.DAO.UserDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener  {
    EditText edTaiKhoanDN, edMatKhauDN;
    Button btnDangNhap, btnDkDN;
    UserDAO userDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        edTaiKhoanDN = (EditText) findViewById(R.id.edTaiKhoanDN);
        edMatKhauDN = (EditText) findViewById(R.id.edMatKhauDN);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDkDN = (Button) findViewById(R.id.btnDkDN);

        btnDkDN.setVisibility(View.GONE);

        userDAO = new UserDAO(this);

        btnDangNhap.setOnClickListener(this);
        btnDkDN.setOnClickListener(this);
        hienThiBTNDangKy();
    }
    private void hienThiBTNDangKy(){
        boolean check = userDAO.kiemTraUser();
        if(check){
            btnDkDN.setVisibility(View.GONE);
            btnDangNhap.setVisibility(View.VISIBLE);
        }else {
            btnDkDN.setVisibility(View.VISIBLE);
            btnDangNhap.setVisibility(View.GONE);
        }
    }
    private void btnDangNhap(){
        String tenDangNhap = edTaiKhoanDN.getText().toString();
        String matKhau = edMatKhauDN.getText().toString();

        Intent trangchu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
        trangchu.putExtra("tenDN",tenDangNhap);
        trangchu.putExtra("CMND",userDAO.LayCMND(tenDangNhap,matKhau));
        if(tenDangNhap.equals("admin8888") & matKhau.equals("admin8888")) {
            startActivity(trangchu);
            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công admin", Toast.LENGTH_SHORT).show();
        }else {
            boolean check = userDAO.KiemTraDangNhap(tenDangNhap,matKhau);
            if(check){
                startActivity(trangchu);
                Toast.makeText(DangNhapActivity.this,"Đăng nhập thành công user" ,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void btnDangKy(){
        Intent dangKy = new Intent(this,DangKyActivity.class);
        startActivity(dangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hienThiBTNDangKy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNhap:
                btnDangNhap();
                break;
            case R.id.btnDkDN:
                btnDangKy();
                break;
        }
    }
}
