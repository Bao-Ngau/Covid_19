package com.example.covid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class OnboardingFragment3 extends Fragment {

    private View mview;
    private Button btnGetStarted;


    public OnboardingFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        mview dùng để ánh xạ trung gian

        mview = inflater.inflate(R.layout.fragment_onboarding_3, container, false);
        btnGetStarted = mview.findViewById(R.id.btn_get_start);
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                nếu nhấn vào button thì sẽ chuyển qua màn hình khác
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
//                lúc này sẽ khởi tạo màn hình khác
                startActivity(intent);
            }
        });
        return mview;
    }
}