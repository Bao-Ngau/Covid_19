package com.example.covid_19.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.covid_19.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    Button btnCall, btnSend;
//    hàm này dùng để khởi tạo nút call và send sms
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home,container,false);
        btnCall =(Button) view.findViewById(R.id.button);
        btnSend = (Button) view.findViewById(R.id.button2);
        btnCall.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                // Lấy số điện thoại cần gọi
                String phoneNumberCall = "0385078008";
                // Tạo một Intent với hành động ACTION_DIAL
                Intent intentSms = new Intent(Intent.ACTION_DIAL);
                // Thêm số điện thoại vào dữ liệu của Intent
                intentSms.setData(Uri.parse("tel:" + phoneNumberCall));
                // Bắt đầu một Activity với Intent đã tạo
                startActivity(intentSms);
                break;
            case R.id.button2:
                Intent sendsms = new Intent(getActivity(),com.example.covid_19.SendSmsActivity.class);
                startActivity(sendsms);
                break;
        }
    }
}
