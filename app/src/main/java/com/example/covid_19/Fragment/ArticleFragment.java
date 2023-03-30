package com.example.covid_19.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.covid_19.R;

public class ArticleFragment extends Fragment {

    private int REQUEST_CODE = 888;
    @Nullable
    @Override
    //tạo ra một View bằng cách inflate layout và trả về nó.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //nap view vào xong găn cho container
        View view = inflater.inflate(R.layout.fragment_article,container,false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBaiViet1 = menu.add(1,R.id.itThem,2,R.string.thembaiviet);
        itThemBaiViet1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem itThemBaiViet2 = menu.add(1,R.id.itSua,3,R.string.suabaiviet);
        itThemBaiViet1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itThem:
                Intent iThemBaiViet = new Intent(getActivity(),com.example.covid_19.ThemBaiVietActivity.class);
                startActivityForResult(iThemBaiViet,REQUEST_CODE);
                break;
        }
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                Log.d("ArticleFragment", "onActivityResult: requestCode = " + requestCode + ", resultCode = " + resultCode);
                Intent intent = data;
                boolean ktra = intent.getBooleanExtra("ketquathem",false);
                if(ktra){
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
