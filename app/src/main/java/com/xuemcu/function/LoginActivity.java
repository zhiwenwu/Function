package com.xuemcu.function;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static com.xuemcu.function.R.id.email;

/*
* 时间:2017年8月30日23:21
* 事件: 对登录页面进行全部整理重写
* 功能: 实现可以自动登录，但是没有进行功能添加
*
* */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private CheckBox rememberpasswd;
    private UserLoginTask mAuthTask;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor_account;
    private SharedPreferences.Editor editor_passwd;
    private Button register;
    private Button forget;
    private Button sign_in;
    private DatabaseManger databaseManger;
    private int Passwd_Length = 4;

    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


        InitViews();

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        }

    private void InitViews(){

        mEmailView = (AutoCompleteTextView) findViewById(email);
        mPasswordView = (EditText) findViewById(R.id.password);
        rememberpasswd = (CheckBox) findViewById(R.id.rememberpasswd);
        sign_in = (Button) findViewById(R.id.email_sign_in);
        register = (Button) findViewById(R.id.register);
        forget = (Button) findViewById(R.id.forget);

        sign_in.setOnClickListener(this);
        register.setOnClickListener(this);
        forget.setOnClickListener(this);

        databaseManger = DatabaseManger.getInstance(LoginActivity.this);

        try {
             if(databaseManger.getDataCounts("Users") == 0){

                 Toast.makeText(this,"此软件里没有任何账号,请先注册一个账号!",Toast.LENGTH_LONG).show();

             }
        } catch (Exception e) {
            Toast.makeText(this,"此软件里没有任何账号,请先注册一个账号!",Toast.LENGTH_LONG).show();

            e.printStackTrace();

        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = sharedPreferences.getBoolean("remember_passwd",false);

        if(isRemember){        //优先检测 记住账号按键点击事件

            String account = sharedPreferences.getString("account","");
            String password = sharedPreferences.getString("password","");
            mEmailView.setText(account);
            mPasswordView.setText(password);
            rememberpasswd.setChecked(true);

         }

        try{                    //再处理从注册账号intent传过来的 新的账号信息  自动填写
            Intent intent = getIntent();
            Log.d(TAG, "onCreate: 读取注册界面        "+intent.getStringExtra("account"));
            Log.d(TAG, "onCreate: 读取注册界面        "+intent.getStringExtra("password"));

            if(intent.getStringExtra("account").length()>0) {

                Log.d(TAG, "onCreate: 获取注册界面账号成功!");

                mEmailView.setText(intent.getStringExtra("account"));
                mPasswordView.setText(intent.getStringExtra("password"));
            }

        }catch(Exception e){

        }


        //这里是将光标移动输入框的最后
        mEmailView.setSelection(mEmailView.getText().length());
    }

    public void userLogin() {
        String usernameString = mEmailView.getText().toString();
        String passwordString = mPasswordView.getText().toString();

        if (VerifyUser(usernameString, passwordString)) {

            editor_account = sharedPreferences.edit();
            editor_passwd = sharedPreferences.edit();
            editor_account.putBoolean("remember_user",true);
            editor_account.putString("account",usernameString);
            if(rememberpasswd.isChecked()) {

                editor_passwd.putBoolean("remember_passwd",true);
                editor_passwd.putString("password",passwordString);

                     Log.d(TAG, "onPostExecute: 记录密码");

                }else{
                     editor_passwd.clear();
                    Log.d(TAG, "onPostExecute: 清除记住密码，忘记密码");

                }

            editor_account.apply();
            editor_passwd.apply();


        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.email_sign_in:
                if(DetectEmpty(mEmailView.getText().toString(),mPasswordView.getText().toString())){
                    showProgress(true);

                    mAuthTask = new UserLoginTask(mEmailView.getText().toString(),mPasswordView.getText().toString());
                    mAuthTask.execute((Void) null);

                }
                break;
            case R.id.register:
                Intent register_intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register_intent);
              //  finish();
                break;
            case R.id.forget:
                Intent forget_intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(forget_intent);
                Log.d(TAG, "onClick: 点击忘记密码!");
                break;
        }

    }
    //检测账号 或者 密码长度
    private boolean DetectEmpty(String email,String passwd){

        if(email.isEmpty())
        {
            Toast.makeText(this,"账号为空，请输入账号!",Toast.LENGTH_LONG).show();
            return  false;

        }else if(passwd.isEmpty() ||  (passwd.length() <= Passwd_Length)){

            mPasswordView.setError(getString(R.string.error_invalid_password));
            return false;
        }
        return true;
    }
    private boolean VerifyUser(String email,String passwd){

        Cursor cursor = databaseManger.queryDataCursor("Users");
        if(cursor.moveToFirst()){
            do {
                if(email.equals(cursor.getString(cursor.getColumnIndex("User")))) {
                    if (passwd.equals(cursor.getString(cursor.getColumnIndex("Passwd")))) {

                        return true;

                    }else{
                        //告知用户  密码错误，并清空密码输入框
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.setText("");

                    }
                }else {

                    Log.d(TAG, "该账号不存在...");
                }

            }while(cursor.moveToNext());
        }


        return false;
    }

    //该方法 引用 android studio  官方LoginActivity里
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return false;
            }

            return VerifyUser(mEmail,mPassword);

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if(success){
                userLogin();
                ContentValues values = new ContentValues();
                try {
                    if(databaseManger.getDataCounts("Login") == 0){

                        values.put("logins","account");
                        values.put("login",mEmail);

                            databaseManger.insetData("Login",values);
                            values.clear();
                    }else{
                        values.put("login",mEmail);
                        databaseManger.updateData("Login",values,"logins = ?",new String[]{"account"});
                        values.clear();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute: 这里异常,没有该表!");
                }
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                Log.d(TAG, "onPostExecute: 正在跳转主界面");

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

}


