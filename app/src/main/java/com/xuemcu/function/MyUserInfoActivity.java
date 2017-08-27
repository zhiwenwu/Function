package com.xuemcu.function;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import static com.xuemcu.function.HomeActivity.AccountNumber;


public class MyUserInfoActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "MyUserInfoActivity";
    private ImageView iv_back;
    private RelativeLayout re_avatar;
    private RelativeLayout re_name;
    private RelativeLayout re_sex;
    private TextView tv_name;
    private TextView tv_fxid;
    private TextView tv_sex;
    private ImageView iv_avatar;


    private String sex;
    private String NickName;
    private String Photo;

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
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_fxid = (TextView) findViewById(R.id.tv_fxid);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        iv_avatar = (ImageView) findViewById(R.id.iv_avatar);


        iv_back.setOnClickListener(this);
        re_avatar.setOnClickListener(this);
        re_name.setOnClickListener(this);
        re_sex.setOnClickListener(this);
        tv_fxid.setText(AccountNumber);

        DataBases dataBases = new DataBases(MyUserInfoActivity.this,"DataBase.db",null,1);

        SQLiteDatabase db = dataBases.getWritableDatabase();

        Cursor cursor = db.query("Users",null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {
                if(AccountNumber.equals(cursor.getString(cursor.getColumnIndex("User")))){

                     sex = cursor.getString(cursor.getColumnIndex("Sex"));
                     NickName = cursor.getString(cursor.getColumnIndex("Nickname"));
                     Photo = cursor.getString(cursor.getColumnIndex("Orders"));

                    tv_name.setText(NickName);

                    switch (Photo){

                        case "1":
                            iv_avatar.setImageResource(R.drawable.photo1);
                            break;
                        case "2":
                            iv_avatar.setImageResource(R.drawable.photo2);
                            break;
                        case "3":
                            iv_avatar.setImageResource(R.drawable.photo3);
                            break;

                    }
                    tv_sex.setText(sex);
                    tv_name.setText(NickName);
                     Log.d(TAG, "InitView: "+sex+NickName+Photo);

                }
            }while(cursor.moveToNext());
        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_back:
                Log.d(TAG, "onClick: 点击返回按键!");
                updateData();
                back();
               // finish();
                break;
            case R.id.re_avatar:                                                 //头像选择
                PhotoDialog();
                updateData();
                Log.d(TAG, "onClick: 头像选择!");
                break;
            case R.id.re_name:                                                     //昵称
                NameDialog();
                updateData();
                Log.d(TAG, "onClick: 昵称!");
                break;
            case R.id.re_sex:
                showSexDialog();
                updateData();                                                   //性别选择
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
                tv_sex.setText("男");
                Log.d(TAG, "onClick: 男男男男");
                updateData();
                dlg.cancel();
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("女");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                sex = "女";
                tv_sex.setText("女");
                Log.d(TAG, "onClick: 女女女女");
                updateData();
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
                tv_name.setText(NickName);
                updateData();
                Log.d(TAG, "onClick: 我被点击了"+NickName);
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
                Photo = "1";
                iv_avatar.setImageResource(R.drawable.photo1);
                dlg.cancel();
            }
        });
        photo2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                //这里对新的昵称进行保存
                Photo = "2";
                iv_avatar.setImageResource(R.drawable.photo2);
                dlg.cancel();
            }
        });
        photo3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                //这里对新的昵称进行保存
                Photo = "3";
                iv_avatar.setImageResource(R.drawable.photo3);
                dlg.cancel();
            }
        });


    }

    public void updateData(){

        DataBases dataBases = new DataBases(MyUserInfoActivity.this,"DataBase.db",null,1);

        SQLiteDatabase db = dataBases.getWritableDatabase();

        Cursor cursor = db.query("Users",null,null,null,null,null,null);
        ContentValues values = new ContentValues();
        values.put("sex",sex);
        values.put("NickName",NickName);
        values.put("Orders",Photo);
        db.update("Users",values,"User = ?",new String[]{AccountNumber});
        Log.d(TAG, " 现在的数据是: : "+sex+NickName+Photo);
        cursor = db.query("Users",null,null,null,null,null,null);

        //测试结果 这里的数据 没有发生改变  ？？？？？？？？？？？？？？？？？？？

        if(cursor.moveToFirst()){
            do {
                if(AccountNumber.equals(cursor.getString(cursor.getColumnIndex("User")))){

                    sex = cursor.getString(cursor.getColumnIndex("Sex"));
                    NickName = cursor.getString(cursor.getColumnIndex("Nickname"));
                    Photo = cursor.getString(cursor.getColumnIndex("Orders"));

                    Log.d(TAG, "updateData: "+sex+NickName+Photo);

                }
            }while(cursor.moveToNext());
        }

     //   finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        back();
        Log.d(TAG, "onBackPressed: 返回键!");
    }

    private void back(){
        //Intent intent = new Intent(MyUserInfoActivity.this,HomeActivity.class);
        //intent.putExtra("jiemian","3");
        //startActivity(intent);
        finish();
    }
}
