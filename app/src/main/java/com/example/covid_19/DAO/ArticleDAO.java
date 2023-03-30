package com.example.covid_19.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.covid_19.DTO.ArticleDTO;
import com.example.covid_19.helper.covid19Helper;

public class ArticleDAO {
    SQLiteDatabase database;
    public ArticleDAO(Context context){
        covid19Helper createDatabase = new covid19Helper(context);
        database = createDatabase.open();
    }
    public boolean ThemArticle(ArticleDTO articleDTO){
            ContentValues contentValues = new ContentValues();
            contentValues.put(covid19Helper.EYE_ARTICLE,articleDTO.getEYE_ARTICLE());
            contentValues.put(covid19Helper.TITLE_ARTICLE,articleDTO.getTITLE_ARTICLE());
            contentValues.put(covid19Helper.CONTENT_ARTICLE,articleDTO.getCONTENT_ARTICLE());
            long ktra = database.insert(covid19Helper.TB_ARTICLE,null,contentValues);
            if(ktra != 0){
                return true;
            }else {
                return false;
            }
    }
}
