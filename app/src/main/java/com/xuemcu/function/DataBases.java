package com.xuemcu.function;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Wuzhiwen on 2017/8/18.
 */
class DataBases extends SQLiteOpenHelper {

    private Context mContext;


    //***数据库名称
    private static  final String DATABASE_NAME = "DataBase.db";
    //数据库版本号
    private static final int DATABASE_VERSION=1;

    public static String Users = "create table Users ("
            + "ID integer primary key autoincrement, "
            + "User text, "         //账户
            + "Passwd text, "       //密码
            + "Security text, "     //密保问题
            + "Answer text, "       //密保答案
            + "Orders text, "       //头像
            + "Sex text, "          //性别
            + "Nickname text)";     //昵称

    public static String Notes = "create table Notes ("
            + "ID integer primary key autoincrement, "
            + "User text,"         //保存的是账户
            + "title text,"         //保存的是标题
            + "context text,"       //保存的是主文
            + "path text,"          //图片的路径
            + "time varchar(20))";  //时间

    public static String Login = "create table Login ("
            + "ID integer primary key autoincrement, "
            + "logins text,"
            + "login text)";  //记录当前登录账号

    public static String Collects = "create table Collects (" //记录账号的收藏
            + "ID integer primary key autoincrement, "
            + "User text,"
            + "name text,"
            + "number text,"
            + "picture text,"
            + "introduce text,"
            + "expenses text,"
            + "explain text)";

    public static String Shopps = "create table Shopps (" //记录账号的购物车
            + "ID integer primary key autoincrement, "
            + "User text,"
            + "name text,"
            + "number text,"
            + "picture text,"
            + "introduce text,"
            + "expenses text,"
            + "explain text)";




    public DataBases (Context context)
    {

        this(context,DATABASE_NAME,null,DATABASE_VERSION);
        mContext = context;

    }

    public DataBases(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBases(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Users);
        db.execSQL(Notes);
        db.execSQL(Login);
        db.execSQL(Collects);
        db.execSQL(Shopps);
        Toast.makeText(mContext,"Create User Succeeded",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+ Users);
            db.execSQL("DROP TABLE IF EXISTS "+ Notes);
            db.execSQL("DROP TABLE IF EXISTS "+ Login);
            onCreate(db);

        }
    }
}