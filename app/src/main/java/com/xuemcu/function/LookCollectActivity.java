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

public class LookCollectActivity extends Activity implements View.OnClickListener{


    private static final String TAG = "CXActivity";

    private ImageView collect_Image = null;
    private Button btnJieshao = null;
    private Button btnFeiyong = null;
    private Button btnShiyong = null;
    private TextView Name = null;
    private TextView collect_textView  = null;
    private Button collect_look;

    private DataBases dataBases;
    private int Num = 1;
    private String Indexs;
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
        setContentView(R.layout.activity_look_collect);

        InitViews();

    }

    private void InitViews(){

        btnJieshao = (Button) findViewById(R.id.collect_Jieshao);
        btnFeiyong = (Button) findViewById(R.id.collect_Feiyong);
        btnShiyong = (Button) findViewById(R.id.collect_Shiyong);
        Name = (TextView) findViewById(R.id.name);
        collect_Image = (ImageView) findViewById(R.id.collect_Image);
        collect_textView = (TextView) findViewById(R.id.collect_textView);
        collect_look = (Button) findViewById(R.id.collect_look);




        Intent intent = getIntent();
        String string = intent.getStringExtra("name");

        Log.d(TAG, "InitViews: "+string);

        dataBases = new DataBases(LookCollectActivity.this,"DataBase.db",null,1);
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
        Num = Integer.valueOf(tu).intValue();
        Name.setText(mingcheng);
        BoundAry(Num);


        //先做数据的加载然后再做事件的监听
        btnJieshao.setOnClickListener(this);
        btnFeiyong.setOnClickListener(this);
        btnShiyong.setOnClickListener(this);
        collect_look.setOnClickListener(this);
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
                collect_textView.setText(jie);
                break;
            case R.id.collect_Feiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx1));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                //textView.setText("费用说明");
                collect_textView.setText(fei);
                break;
            case R.id.collect_Shiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx1));
                // textView.setText("使用说明");
                collect_textView.setText(shi);
                break;
            case R.id.collect_look:
                finish();
                break;
            

        }
    }

    private void BoundAry(int Num){

        switch (Num){

            case 0:
                collect_Image.setImageResource(R.drawable.qinqin);
                break;
            case 1:
                collect_Image.setImageResource(R.drawable.timg1);
                break;
            case 2:
                collect_Image.setImageResource(R.drawable.timg2);
                break;
            case 3:
                collect_Image.setImageResource(R.drawable.timg3);
                break;
            case 4:
                collect_Image.setImageResource(R.drawable.timg4);
                break;
            case 5:
                collect_Image.setImageResource(R.drawable.timg5);
                break;
            case 6:
                collect_Image.setImageResource(R.drawable.timg6);
                break;
            case 7:
                collect_Image.setImageResource(R.drawable.timg7);
                break;
            case 8:
                collect_Image.setImageResource(R.drawable.timg8);
                break;
        }

    }

    private void BoundAry(){
        //加载数据




    }

}
