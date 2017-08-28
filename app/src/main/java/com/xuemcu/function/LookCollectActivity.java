package com.xuemcu.function;


import android.app.Activity;
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

public class LookCollectActivity extends Activity implements View.OnClickListener{


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

    private DataBases dataBases;
    private int Num = 1;
    private String Indexs;
    private String AccountNumber;
    private String logins = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_collect);

        imageView   = (ImageView) findViewById(R.id.imageView);
        btnJieshao  = (Button) findViewById(R.id.Jieshao);
        btnFeiyong  = (Button) findViewById(R.id.Feiyong);
        btnShiyong  = (Button) findViewById(R.id.Shiyong);
        btnZhixun   = (Button) findViewById(R.id.Zhixun);
        btnShoucang = (Button) findViewById(R.id.ShouCang);
        btnFenxiang = (Button) findViewById(R.id.Fenxiang);
        btnDingzhi  = (Button) findViewById(R.id.Dingzhis);
        textView    = (TextView) findViewById(R.id.textView);

        btnJieshao.setOnClickListener(this);
        btnFeiyong.setOnClickListener(this);
        btnShiyong.setOnClickListener(this);
        btnZhixun.setOnClickListener(this);
        btnShoucang.setOnClickListener(this);
        btnFenxiang.setOnClickListener(this);
        btnDingzhi.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Jieshao:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx1));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                Log.d(TAG, "onClick: 我点击了!");
                //textView.setText("产品介绍");
                break;
            case R.id.Feiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx1));
                btnShiyong.setBackground(getDrawable(R.drawable.cx2));
                //textView.setText("费用说明");
                break;
            case R.id.Shiyong:
                //Toast.makeText(this, btnJieshao.getBackground().toString(), Toast.LENGTH_SHORT).show();
                btnJieshao.setBackground(getDrawable(R.drawable.cx2));
                btnFeiyong.setBackground(getDrawable(R.drawable.cx2));
                btnShiyong.setBackground(getDrawable(R.drawable.cx1));
                // textView.setText("使用说明");
                break;
            case R.id.Zhixun:
                Toast.makeText(this, "咨询", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ShouCang:

                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Fenxiang:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Dingzhis:
                Toast.makeText(this, "订制", Toast.LENGTH_SHORT).show();
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

    private void BoundAry(){
        //加载数据


    }

}
