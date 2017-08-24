package com.xuemcu.function;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Wuzhiwen on 2017/8/18.
 */

public class DataBases extends SQLiteOpenHelper {
    public static String Users = "create table Users ("
            + "ID integer primary key autoincrement, "
            + "User text, "         //账户
            + "Passwd text, "       //密码
            + "Security text, "     //密保问题
            + "Answer text, "       //密保答案
            + "Orders integer, "    //头像
            + "Nickname text)";     //昵称

    public static String Notes = "create table Notes ("
            + "ID integer primary key autoincrement, "
            + "title text,"         //保存的是标题
            + "context text,"       //保存的是主文
            + "path text,"          //图片的路径
            + "time varchar(20))";  //时间

    private Context mContext;

    public DataBases(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Users);
        db.execSQL(Notes);
        Toast.makeText(mContext,"Create User Succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}