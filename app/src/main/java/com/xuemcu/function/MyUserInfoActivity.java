package com.xuemcu.function;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MyUserInfoActivity extends Activity {


    private static final String TAG = "MyUserInfoActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        Log.d(TAG, "onCreate: 进入个人信息设置界面");


    }

}
