package com.example.covid_19;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19.DAO.ArticleDAO;
import com.example.covid_19.DTO.ArticleDTO;

public class ThemBaiVietActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edTieuDeBaiViet,edNoiDungBaiViet;
    Button btnThemBaiViet;
    ArticleDAO articleDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_bai_viet);

        edNoiDungBaiViet = (EditText) findViewById(R.id.edNoiDungBaiViet);
        edTieuDeBaiViet =(EditText) findViewById(R.id.edTieuDeBaiViet);
        btnThemBaiViet = (Button) findViewById(R.id.btnThemBaiViet);

        btnThemBaiViet.setOnClickListener(this);
        articleDAO = new ArticleDAO(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThemBaiViet:
                String tieude = edTieuDeBaiViet.getText().toString();
                String noidung = edNoiDungBaiViet.getText().toString();
                if(tieude!=null && noidung!=null && !tieude.equals("") && !noidung.equals("")){
                    ArticleDTO articleDTO = new ArticleDTO();
                    articleDTO.setTITLE_ARTICLE(tieude);
                    articleDTO.setCONTENT_ARTICLE(noidung);
                    articleDTO.setEYE_ARTICLE(0);
                    boolean ktra =  articleDAO.ThemArticle(articleDTO);
                    Log.d("d", String.valueOf(ktra));
                    Intent intent = new Intent();
                    intent.putExtra("ketquathem",ktra);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
                break;
        }
    }
}
