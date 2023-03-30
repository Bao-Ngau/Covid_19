package com.example.covid_19.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class covid19Helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "covid19.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NEWS = "news";
    private static final String TABLE_STATISTICS = "statistics";
    public static String TB_USER = "USER";
    public static String TB_ARTICLE = "Article";
    private static final String TABLE_IMAGES = "images";

    // Columns for table news
    private static final String COL_NEWS_ID = "id";
    private static final String COL_NEWS_TITLE = "title";
    private static final String COL_NEWS_CATEGORY = "category";
    private static final String COL_NEWS_CONTENT = "content";
    private static final String COL_NEWS_ASSOCIATED ="associated" ;
    private static final String COL_NEWS_IMAGE_ID = "image_id";
    private static final String COL_NEWS_CREATED_AT = "created_at";

    // Columns for table statistics
    private static final String COL_STATISTICS_ID = "id";
    private static final String COL_STATISTICS_DATE = "date";
    private static final String COL_STATISTICS_TOTAL_CASES = "total_cases";
    private static final String COL_STATISTICS_TOTAL_DEATHS = "total_deaths";
    private static final String COL_STATISTICS_TOTAL_RECOVERED = "total_recovered";
    private static final String COL_STATISTICS_CREATED_AT = "created_at";

    // Columns for table users
    public static String MA_USER= "MA";
    public static String TDN_USER = "TENDN";
    public static String MK_USER = "MATKHAU";
    public static String GT_USER = "GIOITINH";
    public static String NS_USER = "NGAYSINH";
    public static String CMND_USER = "CMND";
    // Columns for table article;
    public static String MA_ARTICLE = "MA_ARTICLE";
    public static String TITLE_ARTICLE = "TITLE_ARTICLE";
    public static String CONTENT_ARTICLE = "CONTENT_ARTICLE";
    public static String EYE_ARTICLE = "EYE_ARTICLE";
    // Columns for table images
    private static final String COL_IMAGES_ID = "id";
    private static final String COL_IMAGES_IMAGE_URL = "image_url";

    // SQL statement to create table news
    private static final String CREATE_TABLE_NEWS = "CREATE TABLE " + TABLE_NEWS + "("
            + COL_NEWS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_NEWS_TITLE + " TEXT NOT NULL,"
            + COL_NEWS_CATEGORY + " TEXT NOT NULL,"
            + COL_NEWS_CONTENT + " TEXT NOT NULL,"
            + COL_NEWS_ASSOCIATED + " TEXT NOT NULL,"
            + COL_NEWS_IMAGE_ID + " INTEGER,"
            + COL_NEWS_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + "FOREIGN KEY(" + COL_NEWS_IMAGE_ID + ") REFERENCES " + TABLE_IMAGES + "(" + COL_IMAGES_ID + ")"
            + ");";

    // SQL statement to create table statistics
    private static final String CREATE_TABLE_STATISTICS = "CREATE TABLE " + TABLE_STATISTICS + "("
            + COL_STATISTICS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_STATISTICS_DATE + " DATE NOT NULL,"
            + COL_STATISTICS_TOTAL_CASES + " INTEGER NOT NULL,"
            + COL_STATISTICS_TOTAL_DEATHS + " INTEGER NOT NULL,"
            + COL_STATISTICS_TOTAL_RECOVERED + " INTEGER NOT NULL,"
            + COL_STATISTICS_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ");";

    // SQL statement to create table users
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TB_USER + " ( "
            + MA_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TDN_USER + " TEXT, "
            + MK_USER + " TEXT, "
            + GT_USER + " TEXT, "
            + NS_USER + " TEXT, "
            + CMND_USER + " TEXT)";

    private static final String CREATE_TABLE_ARTICLE = "CREATE TABLE " + TB_ARTICLE + " ("
            + MA_ARTICLE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EYE_ARTICLE + " INTEGER, "
            + CONTENT_ARTICLE + " TEXT, "
            + TITLE_ARTICLE + " TEXT)";

    // SQL statement to create table images
    private static final String CREATE_TABLE_IMAGES = "CREATE TABLE " + TABLE_IMAGES + "("
            + COL_IMAGES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_IMAGES_IMAGE_URL + " TEXT NOT NULL"
            + ");";

    public covid19Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NEWS);
        sqLiteDatabase.execSQL(CREATE_TABLE_STATISTICS);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGES);
        sqLiteDatabase.execSQL(CREATE_TABLE_ARTICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Thực hiện các hành động cần thiết khi cơ sở dữ liệu được nâng cấp
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_ARTICLE);

        onCreate(sqLiteDatabase);

    }
    public  SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
