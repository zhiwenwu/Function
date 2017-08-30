package com.xuemcu.function;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by Wuzhiwen on 2017/8/27.
 */

public class CXActivity extends Activity implements View.OnClickListener{


    private static final String TAG = "CXActivity";

    private ImageView imageView = null;
    private Button btnJieshao = null;
    private Button btnFeiyong = null;
    private Button btnShiyong = null;
    private Button btnZhixun   = null;
    private Button btnShoucang = null;
    private Button btnFenxiang = null;
    private Button btnDingzhi  = null;
    private TextView textView  = null;
    private TextView ming = null;

    private DataBases dataBases;


    private String[] name = new String[]{"大兴安岭三日日游","天柱山一日游","黄山一日游","长安古城一日游","泰山五日游","黄龙岗瀑布三日游","三峡一日游","华山一日游","南安古城一日游"};
    private String[] feiyong = new String[]{"43656元","20元","543元","456元","456元","456元","45645元","876元","456元","456450元"};
    private String[] jieshao = new String[]{"111111111","22222222222","3333333333333","4444444444","555555555555","66666666666666","777777777777777","88888888888888","9999999999999999999999"};
    private String[] shiyong = new String[]{"999999999999999999999999999999","888888888888","7777777777777777777","666666666666666666666666","555555555555555555555","4444444444444444444","333333333333333333333333333333333","2222222222222222222222222","11111111111111111111111111"};

    private int Num = 1;
    private String Indexs;
    private String AccountNumber;
    private String logins = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cx);


        InitViews();
    }

    private void InitViews(){

        imageView   = (ImageView) findViewById(R.id.imageView);
        btnJieshao  = (Button) findViewById(R.id.Jieshao);
        btnFeiyong  = (Button) findViewById(R.id.Feiyong);
        btnShiyong  = (Button) findViewById(R.id.Shiyong);
        btnZhixun   = (Button) findViewById(R.id.Zhixun);
        btnShoucang = (Button) findViewById(R.id.ShouCang);
        btnFenxiang = (Button) findViewById(R.id.Fenxiang);
        btnDingzhi  = (Button) findViewById(R.id.Dingzhis);
        textView    = (TextView) findViewById(R.id.textView);
        ming = (TextView) findViewById(R.id.name);

        btnJieshao.setOnClickListener(this);
        btnFeiyong.setOnClickListener(this);
        btnShiyong.setOnClickListener(this);
        btnZhixun.setOnClickListener(this);
        btnShoucang.setOnClickListener(this);
        btnFenxiang.setOnClickListener(this);
        btnDingzhi.setOnClickListener(this);


        Intent intent = getIntent();
        Indexs = intent.getStringExtra("Indexs");
        if(Indexs.length() == 0){

            Num = 0;
        }
        else{

            Log.d(TAG, "String:     " +Indexs);
            Num = Integer.valueOf(Indexs).intValue();
            Log.d(TAG, "onCreate: Num      "  +Num);

        }

        //textView.setText("产品介绍");
        BoundAry(Num);
        BoundAry_jieshao(Num);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Jieshao:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx1));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                BoundAry_jieshao(Num);
                Log.d(TAG, "onClick: 我点击了!");
                //textView.setText("产品介绍");
                break;
            case R.id.Feiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx1));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                BoundAry_feiyong(Num);
                //textView.setText("费用说明");
                break;
            case R.id.Shiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx1));
                BoundAry_shiyong(Num);
               // textView.setText("使用说明");
                break;
            case R.id.Zhixun:
                Toast.makeText(this, "咨询", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ShouCang:
                Collect();
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Fenxiang:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Dingzhis:
                Shopps();
                Toast.makeText(this, "订制", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void BoundAry_Name(int Num){

        switch (Num){
            case 0:
                textView.setText(name[Num]);
                break;
            case 1:
                textView.setText(name[Num]);
                break;
            case 2:
                textView.setText(name[Num]);
                break;
            case 3:
                textView.setText(name[Num]);
                break;
            case 4:
                textView.setText(name[Num]);
                break;
            case 5:
                textView.setText(name[Num]);
                break;
            case 6:
                textView.setText(name[Num]);
                break;
            case 7:
                textView.setText(name[Num]);
                break;
            case 8:
                textView.setText(name[Num]);
                break;

        }

    }


    private void BoundAry(int Num){

        switch (Num){

            case 0:
                imageView.setImageResource(R.drawable.qinqin);
                break;
            case 1:
                imageView.setImageResource(R.drawable.timg1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.timg2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.timg3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.timg4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.timg5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.timg6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.timg7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.timg8);
                break;
        }

    }

    private void BoundAry_jieshao(int Num){


        Log.d(TAG, "BoundAry_jieshao: 我也点击了");
        switch (Num){

            case 0:
                textView.setText(jieshao[Num]);
                break;
            case 1:
                textView.setText(jieshao[Num]);
                break;
            case 2:
                textView.setText(jieshao[Num]);
                break;
            case 3:
                textView.setText(jieshao[Num]);
                break;
            case 4:
                textView.setText(jieshao[Num]);
                break;
            case 5:
                textView.setText(jieshao[Num]);
                break;
            case 6:
                textView.setText(jieshao[Num]);
                break;
            case 7:
                textView.setText(jieshao[Num]);
                break;
            case 8:
                textView.setText(jieshao[Num]);
                break;


        }

    }
    private void BoundAry_feiyong(int Num){



        switch (Num){

            case 0:
                textView.setText(feiyong[Num]);
                break;
            case 1:
                textView.setText(feiyong[Num]);
                break;
            case 2:
                textView.setText(feiyong[Num]);
                break;
            case 3:
                textView.setText(feiyong[Num]);
                break;
            case 4:
                textView.setText(feiyong[Num]);
                break;
            case 5:
                textView.setText(feiyong[Num]);
                break;
            case 6:
                textView.setText(feiyong[Num]);
                break;
            case 7:
                textView.setText(feiyong[Num]);
                break;
            case 8:
                textView.setText(feiyong[Num]);
                break;


        }

    }
    private void BoundAry_shiyong(int Num){


        switch (Num){

            case 0:
                textView.setText(shiyong[Num]);
                break;
            case 1:
                textView.setText(shiyong[Num]);
                break;
            case 2:
                textView.setText(shiyong[Num]);
                break;
            case 3:
                textView.setText(shiyong[Num]);
                break;
            case 4:
                textView.setText(shiyong[Num]);
                break;
            case 5:
                textView.setText(shiyong[Num]);
                break;
            case 6:
                textView.setText(shiyong[Num]);
                break;
            case 7:
                textView.setText(shiyong[Num]);
                break;
            case 8:
                textView.setText(shiyong[Num]);
                break;

        }

    }

    private void Collect(){

        dataBases = new DataBases(CXActivity.this,"DataBase.db",null,1);
        SQLiteDatabase db = dataBases.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursorNu = db.query("Login",null,null,null,null,null,null);

        if(cursorNu.moveToFirst()){
            do {
                if(logins.equals(cursorNu.getString(cursorNu.getColumnIndex("logins")))){

                    AccountNumber = cursorNu.getString(cursorNu.getColumnIndex("login"));
                    Log.d(TAG, "AccountNumber: "+AccountNumber);

                }
            }while(cursorNu.moveToNext());
        }

        values.put("User",AccountNumber);
        values.put("name",name[Num]);
        values.put("number", Indexs);
        values.put("picture", Indexs);
        values.put("introduce", jieshao[Num]);
        values.put("expenses", feiyong[Num]);
        values.put("explain", shiyong[Num]);

        db.insert("Collects",null,values);
        values.clear();

        Cursor cursor = db.query("Collects",null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {

                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("User")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("name")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("number")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("picture")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("introduce")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("expenses")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("explain")));


            }while(cursor.moveToNext());
        }

    }

    private void Shopps(){

        dataBases = new DataBases(CXActivity.this,"DataBase.db",null,1);
        SQLiteDatabase db = dataBases.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursorNu = db.query("Login",null,null,null,null,null,null);

        if(cursorNu.moveToFirst()){
            do {
                if(logins.equals(cursorNu.getString(cursorNu.getColumnIndex("logins")))){

                    AccountNumber = cursorNu.getString(cursorNu.getColumnIndex("login"));
                    Log.d(TAG, "AccountNumber: "+AccountNumber);

                }
            }while(cursorNu.moveToNext());
        }

        values.put("User",AccountNumber);
        values.put("name",name[Num]);
        values.put("number", Indexs);
        values.put("picture", Indexs);
        values.put("introduce", jieshao[Num]);
        values.put("expenses", feiyong[Num]);
        values.put("explain", shiyong[Num]);

        db.insert("Shopps",null,values);
        values.clear();

        Cursor cursor = db.query("Shopps",null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {

                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("User")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("name")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("number")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("picture")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("introduce")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("expenses")));
                Log.d(TAG, "Collect: "+cursor.getString(cursor.getColumnIndex("explain")));


            }while(cursor.moveToNext());
        }

    }
}
