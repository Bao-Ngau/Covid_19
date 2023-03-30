package com.example.covid_19;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_19.Fragment.ArticleFragment;
import com.example.covid_19.Fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView TenNav;
    FragmentManager fragmentManager;

    @SuppressLint({"RestrictedApi", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view_trangchu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // truy cập vô header view
        View view = navigationView.getHeaderView(0);
        TenNav = (TextView) view.findViewById(R.id.TenNav);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);//bật nút <--
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//hiện thị nút <--

        //mới chỉ khai báo chưa bt cửa cái nào || chuyển <-- thanh ||| đồng add sự kiện luôn
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);//set drawerToggle cho drawerLayout
        drawerToggle.syncState();//chạy đồng bộ

        Intent intent = getIntent();
        String tendn = intent.getStringExtra("tenDN");
        TenNav.setText(tendn);

        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tranHienThiTrangChu = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        tranHienThiTrangChu.replace(R.id.content, homeFragment);
        tranHienThiTrangChu.commit();
        //yêu cầu quyền này từ người dùng
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
                return;
            }
        }
        //yêu cầu quyền truy cập SEND_SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.SEND_SMS }, 0);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                FragmentTransaction tranHienThiTrangChu = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                tranHienThiTrangChu.replace(R.id.content, homeFragment);
                tranHienThiTrangChu.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_article:
                FragmentTransaction tranHienThiBaiViet = fragmentManager.beginTransaction();
                ArticleFragment articleFragment = new ArticleFragment();
                tranHienThiBaiViet.replace(R.id.content, articleFragment);
                tranHienThiBaiViet.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }

}
