package com.example.covid_19.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.covid_19.DTO.UserDTO;
import com.example.covid_19.helper.covid19Helper;

public class UserDAO {
    SQLiteDatabase database;
    public UserDAO(Context context){
        covid19Helper createDatabase = new covid19Helper(context);
        database = createDatabase.open();
    }
    public long ThemUser(UserDTO userDTO){
        ContentValues contentValues = new ContentValues();

        contentValues.put(covid19Helper.TDN_USER, userDTO.getTENDN());
        contentValues.put(covid19Helper.MK_USER, userDTO.getMK());
        contentValues.put(covid19Helper.GT_USER, userDTO.getGIOITINH());
        contentValues.put(covid19Helper.NS_USER, userDTO.getNGAYSINH());
        contentValues.put(covid19Helper.CMND_USER, userDTO.getCMND());

        long ktra = database.insert(covid19Helper.TB_USER,null,contentValues);
        return  ktra;
    }
    public boolean kiemTraUser(){
        String sql = "SELECT * FROM " + covid19Helper.TB_USER;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean KiemTraDangNhap(String TenDangNhap, String MatKhau){
        String sql = "SELECT * FROM " + covid19Helper.TB_USER +" WHERE "+ covid19Helper.TDN_USER +" = '"+TenDangNhap+ "' AND "+covid19Helper.MK_USER +" = '"+MatKhau+"'";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.getCount() == 1){
            return true;
        }else {
            return false;
        }
    }
    @SuppressLint("Range")
    public String LayCMND(String TenDangNhap, String MatKhau){
        String sql = "SELECT "+covid19Helper.CMND_USER+ " FROM " + covid19Helper.TB_USER +" WHERE "+ covid19Helper.TDN_USER +" = '"+TenDangNhap+ "' AND "+covid19Helper.MK_USER +" = '"+MatKhau+"'";
        Cursor cursor = database.rawQuery(sql,null);

        String cmnd = null;
        if(cursor.moveToFirst()){//bỏ qua dòng tiên
            cmnd = cursor.getString(cursor.getColumnIndex(covid19Helper.CMND_USER));
        }
        cursor.close();
        return cmnd;
    }
    public boolean KiemTraTenDK(String TenDangKy){
        String sql = "SELECT * FROM " + covid19Helper.TB_USER +" WHERE "+ covid19Helper.TDN_USER +" = '"+TenDangKy+ "'";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.getCount() >= 1){
            return true;
        }else {
            return false;
        }
    }

    public boolean KiemTraCMND(String CMND){
        String sql = "SELECT * FROM " + covid19Helper.TB_USER +" WHERE "+ covid19Helper.CMND_USER +" = '"+CMND+ "'";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.getCount() >= 1){
            return true;
        }else {
            return false;
        }
    }
}
