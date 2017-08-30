package com.xuemcu.function;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wuzhiwen on 2017/8/27.
 */

public class LookShoppActivity extends Activity implements View.OnClickListener{


    private static final String TAG = "CXActivity";

    private ImageView shopp_Image = null;
    private Button btnJieshao = null;
    private Button btnFeiyong = null;
    private Button btnShiyong = null;
    private TextView Name = null;
    private TextView shopp_textView  = null;
    private Button shopp_look;

    private DataBases dataBases;
    private int Num = 1;
    private String AccountNumber;
    private String logins = "123";

    private String mingcheng = "";
    private String tu = "";
    private String jie = "";
    private String fei = "";
    private String shi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_shopp);

        InitViews();




    }

    private void InitViews(){

        btnJieshao = (Button) findViewById(R.id.shopp_Jieshao);
        btnFeiyong = (Button) findViewById(R.id.shopp_Feiyong);
        btnShiyong = (Button) findViewById(R.id.shopp_Shiyong);
        Name = (TextView) findViewById(R.id.name);
        shopp_Image = (ImageView) findViewById(R.id.shopp_Image);
        shopp_textView = (TextView) findViewById(R.id.shopp_textView);
        shopp_look = (Button) findViewById(R.id.shopp_look);




        Intent intent = getIntent();
        String string = intent.getStringExtra("name");

        Log.d(TAG, "InitViews: "+string);

        dataBases = new DataBases(LookShoppActivity.this,"DataBase.db",null,1);
        SQLiteDatabase db = dataBases.getWritableDatabase();
        Cursor cursorNu = db.query("Login",null,null,null,null,null,null);
        if(cursorNu.moveToFirst()){
            do {
                if(logins.equals(cursorNu.getString(cursorNu.getColumnIndex("logins")))){

                    AccountNumber = cursorNu.getString(cursorNu.getColumnIndex("login"));
                    Log.d(TAG, "AccountNumber: "+AccountNumber);

                }
            }while(cursorNu.moveToNext());
        }

        Cursor cursor = db.query("Collects",null,null,null,null,null,null);


        if(cursor.moveToFirst()){
            do{
                if(AccountNumber.equals(cursor.getString(cursor.getColumnIndex("User"))))

                    if(string.equals(cursor.getString(cursor.getColumnIndex("name")))){

                        Log.d(TAG, "InitViews: 查找成功!");
                        Log.d(TAG, "InitViews: "+cursor.getString(cursor.getColumnIndex("name")));
                        mingcheng = cursor.getString(cursor.getColumnIndex("name"));
                        tu = cursor.getString(cursor.getColumnIndex("picture"));
                        jie = cursor.getString(cursor.getColumnIndex("introduce"));
                        fei = cursor.getString(cursor.getColumnIndex("expenses"));
                        shi = cursor.getString(cursor.getColumnIndex("explain"));

                    }

            }while(cursor.moveToNext());


        }
        Log.d(TAG, "123: "+Num);
        Num = Integer.valueOf(tu).intValue();
        Log.d(TAG, "456: "+Num);
        Name.setText(mingcheng);
        BoundAry(Num);


        //先做数据的加载然后再做事件的监听
        btnJieshao.setOnClickListener(this);
        btnFeiyong.setOnClickListener(this);
        btnShiyong.setOnClickListener(this);
        shopp_look.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_Jieshao:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx1));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                Log.d(TAG, "onClick: 我点击了!");
                shopp_textView.setText(jie);
                break;
            case R.id.collect_Feiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx1));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                //textView.setText("费用说明");
                shopp_textView.setText(fei);
                break;
            case R.id.collect_Shiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx1));
                // textView.setText("使用说明");
                shopp_textView.setText(shi);
                break;
            case R.id.shopp_look:
                finish();
                break;


        }
    }

    private void BoundAry(int Num){

        switch (Num){

            case 0:
                shopp_Image.setImageResource(R.drawable.qinqin);
                break;
            case 1:
                shopp_Image.setImageResource(R.drawable.timg1);
                break;
            case 2:
                shopp_Image.setImageResource(R.drawable.timg2);
                break;
            case 3:
                shopp_Image.setImageResource(R.drawable.timg3);
                break;
            case 4:
                shopp_Image.setImageResource(R.drawable.timg4);
                break;
            case 5:
                shopp_Image.setImageResource(R.drawable.timg5);
                break;
            case 6:
                shopp_Image.setImageResource(R.drawable.timg6);
                break;
            case 7:
                shopp_Image.setImageResource(R.drawable.timg7);
                break;
            case 8:
                shopp_Image.setImageResource(R.drawable.timg8);
                break;
        }

    }

}
