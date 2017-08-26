package com.xuemcu.function;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MyUserInfoActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "MyUserInfoActivity";
    private ImageView iv_back;
    private RelativeLayout re_avatar;
    private RelativeLayout re_name;
    private RelativeLayout re_sex;


    private String sex;
    private String NickName;
    private int Photo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        Log.d(TAG, "onCreate: 进入个人信息设置界面");
        InitView();

    }
    private void InitView(){

        iv_back = (ImageView) findViewById(R.id.iv_back);
        re_avatar = (RelativeLayout) findViewById(R.id.re_avatar);
        re_name = (RelativeLayout) findViewById(R.id.re_name);
        re_sex = (RelativeLayout) findViewById(R.id.re_sex);


        iv_back.setOnClickListener(this);
        re_avatar.setOnClickListener(this);
        re_name.setOnClickListener(this);
        re_sex.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_back:
                Log.d(TAG, "onClick: 点击返回按键!");
                finish();
                break;
            case R.id.re_avatar:                                                 //头像选择
                PhotoDialog();
                Log.d(TAG, "onClick: 头像选择!");
                break;
            case R.id.re_name:                                                     //昵称
                NameDialog();
                Log.d(TAG, "onClick: 昵称!");
                break;
            case R.id.re_sex:
                showSexDialog();                                 //性别选择
                Log.d(TAG, "onClick: 性别选择!");
                break;


        }
    }

    private void showSexDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.alertdialog);
        LinearLayout ll_title = (LinearLayout) window
                .findViewById(R.id.ll_title);
        ll_title.setVisibility(View.VISIBLE);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
        tv_title.setText("性别");
        // 为确认按钮添加事件,执行退出应用操作
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
        tv_paizhao.setText("男");
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {

                sex = "男";
                dlg.cancel();
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("女");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                sex = "女";

                dlg.cancel();
            }
        });

    }
    private void NameDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.nickname_dialog);

        final EditText editText = (EditText) window.findViewById(R.id.nickname);
        // 为确认按钮添加事件,执行退出应用操作
        Button qu = (Button) window.findViewById(R.id.qu);
        qu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {


                dlg.cancel();
            }
        });
        Button que = (Button) window.findViewById(R.id.que);
        que.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                //这里对新的昵称进行保存
                NickName = editText.getText().toString();
                dlg.cancel();

            }
        });

    }
    private void PhotoDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.photo_dialog);

        final ImageView photo1 = (ImageView) window.findViewById(R.id.photo1);
        final ImageView photo2 = (ImageView) window.findViewById(R.id.photo2);
        final ImageView photo3 = (ImageView) window.findViewById(R.id.photo3);
        // 为确认按钮添加事件,执行退出应用操作
        photo1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                //这里对新的昵称进行保存
                Photo = 1;
                dlg.cancel();
            }
        });
        photo2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                //这里对新的昵称进行保存
                Photo = 2;
                dlg.cancel();
            }
        });
        photo3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                //这里对新的昵称进行保存
                Photo = 3;
                dlg.cancel();
            }
        });


    }

    public void updateData(final String sexnum){


     //   finish();
    }
}
