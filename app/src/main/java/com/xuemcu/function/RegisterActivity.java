package com.xuemcu.function;


//处理注册账号界面

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    private static final String TAG = "RegisterActivity";
    private EditText Registe_email;
    private EditText Registe_password;
    private EditText Registe_mima;
    private EditText Registe_daan;
    private EditText Registe_order;
    private EditText Registe_passwd;
    private Button Registe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitViews();

    }

    private void InitViews(){

        Registe_email = (EditText) findViewById(R.id.Registe_email);
        Registe_password = (EditText) findViewById(R.id.Registe_password);
        Registe_mima = (EditText) findViewById(R.id.Registe_mima);
        Registe_daan = (EditText) findViewById(R.id.Registe_daan);
        Registe = (Button) findViewById(R.id.Registe);
        Registe_order = (EditText) findViewById(R.id.Registe_Order);
        Registe_passwd = (EditText) findViewById(R.id.Registe_password_ag);


        Registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //优化   点击注册按键时 自动隐藏虚拟键盘
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {

                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }

            DatabaseManger databaseManger = DatabaseManger.getInstance(RegisterActivity.this);
            ContentValues values = new ContentValues();

                if(Registe_email.getText().toString().equals("")) {

                    Toast.makeText(RegisterActivity.this,"账号为空,请输入账号!",Toast.LENGTH_LONG).show();

                }else if(Registe_password.getText().toString().equals("")) {

                    Toast.makeText(RegisterActivity.this,"密码为空,请输入密码!",Toast.LENGTH_LONG).show();

                }else if(Registe_mima.getText().toString().equals("")) {

                    Toast.makeText(RegisterActivity.this,"问题为空,请输入问题!",Toast.LENGTH_LONG).show();

                }else if(Registe_daan.getText().toString().equals("")) {

                    Toast.makeText(RegisterActivity.this,"问题答案为空,请输入问题答案!",Toast.LENGTH_LONG).show();

                }else if(Registe_password.getText().toString().length() <= 4){

                    Toast.makeText(RegisterActivity.this,"密码太短了!",Toast.LENGTH_LONG).show();
                }else if(Registe_order.getText().toString().equals("")){

                    Toast.makeText(RegisterActivity.this,"昵称为空!",Toast.LENGTH_LONG).show();

                }else {
                        if(Registe_passwd.getText().toString().equals(Registe_password.getText().toString())) {
                            values.put("User", Registe_email.getText().toString());
                            values.put("Passwd", Registe_password.getText().toString());
                            values.put("Security", Registe_mima.getText().toString());
                            values.put("Answer", Registe_daan.getText().toString());
                            values.put("Orders", 1);
                            values.put("Sex", "男");
                            values.put("Nickname", Registe_order.getText().toString());

                            try {

                                databaseManger.insetData("Users",values);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            values.clear();
                            databaseManger.close();

                            Log.d(TAG, "onClick:   注册用户成功");
                            Toast.makeText(RegisterActivity.this," 注册用户成功!",Toast.LENGTH_LONG).show();
                            //开启线程延时等待   正在注册信息...........建立一个新的对话框  延时等待
                            //这里还需要做 返回登录界面  携带账号信息自动填入账号输入框
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    SystemClock.sleep(1000);
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    intent.putExtra("account",Registe_email.getText().toString());
                                    intent.putExtra("password",Registe_password.getText().toString());
                                    startActivity(intent);
                                    finish();                            //做一个界面的销毁
                                    Log.d(TAG, "onClick:   注册用户程序结束!");
                                }
                            }).start();

                        }else{

                            Toast.makeText(RegisterActivity.this,"两次密码不相同!",Toast.LENGTH_LONG).show();
                        }

                }
                Log.d(TAG, "onClick:   点击注册用户按键!");
            }
        });


    }
}
