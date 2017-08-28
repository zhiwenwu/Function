package com.xuemcu.function;

//处理密码忘记的界面

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ForgetActivity extends AppCompatActivity {

    private static final String TAG = "ForgetActivity";
    private EditText account;
    private EditText Security;
    private EditText answer;
    private TextView password;
    private Button lookup;
    private DataBases dataBases;
    private int Err = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        InitView();
    }

    private void InitView(){

        account = (EditText) findViewById(R.id.zhang);
        Security = (EditText) findViewById(R.id.ma);
        answer = (EditText) findViewById(R.id.da);
        password = (TextView) findViewById(R.id.mima);
        lookup = (Button) findViewById(R.id.chazhao);


        lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBases = new DataBases(ForgetActivity.this,"DataBase.db",null,1);

                SQLiteDatabase db = dataBases.getWritableDatabase();

                Cursor cursor = db.query("Login",null,null,null,null,null,null);



                if(account.getText().toString().equals(""))
                {
                    Toast.makeText(ForgetActivity.this,"账号不能为空!",Toast.LENGTH_LONG).show();

                }else if(Security.getText().toString().equals("")){

                    Toast.makeText(ForgetActivity.this,"密保问题不能为空!",Toast.LENGTH_LONG).show();

                }else if(answer.getText().toString().equals("")){

                    Toast.makeText(ForgetActivity.this,"密保问题答案不能为空!",Toast.LENGTH_LONG).show();

                }
                else{

                        if(cursor.moveToFirst()){
                            do {
                                if(account.getText().toString().equals(cursor.getString(cursor.getColumnIndex("User")))) {
                                    if (Security.getText().toString().equals(cursor.getString(cursor.getColumnIndex("Security")))) {
                                        if (answer.getText().toString().equals(cursor.getString(cursor.getColumnIndex("Answer")))) {

                                            password.setText(cursor.getString(cursor.getColumnIndex("Passwd")));
                                            Toast.makeText(ForgetActivity.this,"密码查找成功!",Toast.LENGTH_LONG).show();

                                        }else
                                        {

                                            Toast.makeText(ForgetActivity.this,"密码查找失败!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }while(cursor.moveToNext());
                        }
                }

            }
        });

    }
}
