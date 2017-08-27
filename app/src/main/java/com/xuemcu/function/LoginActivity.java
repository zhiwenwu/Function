package com.xuemcu.function;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static com.xuemcu.function.R.id.email;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_READ_CONTACTS = 0;
    private String[] DUMMY_CREDENTIALS = new String[50];// 这里存放临时的账号  直接操作这个数据
    private UserLoginTask mAuthTask = null;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button register;
    private Button forget;
    private DataBases dataBases;
    private boolean isFlog = true;
    private int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        pref = PreferenceManager.getDefaultSharedPreferences(this);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        rememberPass = (CheckBox) findViewById(R.id.rememberpass);
        boolean isRemember = pref.getBoolean("remember_password",false);

        if(isRemember){        //优先检测 记住账号按键点击事件

            String account = pref.getString("account","");
            String password = pref.getString("passeord","");
            mEmailView.setText(account);
            mPasswordView.setText(password);
            rememberPass.setChecked(true);
        }
        try{                    //再处理从注册账号intent传过来的 新的账号信息  自动填写
            Intent intent = getIntent();
            String data = intent.getStringExtra("zhanghao");
            Log.d(TAG, "onCreate: 读取注册界面        "+data);
            if(intent.getStringExtra("zhanghao").length()>0)
            {
                Log.d(TAG, "onCreate: 获取注册界面账号成功!");

                mEmailView.setText(intent.getStringExtra("zhanghao"));
                isFlog = false;
            }

        }catch(Exception e){

        }
        dataBases = new DataBases(LoginActivity.this,"DataBase.db",null,1);

        SQLiteDatabase db = dataBases.getWritableDatabase();

        Cursor cursor = db.query("Users",null,null,null,null,null,null);

        if(cursor.moveToFirst()){

            do {

                String User = cursor.getString(cursor.getColumnIndex("User"));
                String Passwd = cursor.getString(cursor.getColumnIndex("Passwd"));
                DUMMY_CREDENTIALS[i] = User+":"+Passwd;
                Log.d(TAG, "onCreate: 用户名:密码      "+DUMMY_CREDENTIALS[i]);
                i++;

            }while(cursor.moveToNext());
        }



        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里实现注册账号点击事件
                Log.d(TAG, "onClick: 注册账号点击事件");
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });
        forget = (Button) findViewById(R.id.forget);
        forget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里实现找回密码点击事件
                Log.d(TAG, "onClick: 找回密码点击事件");
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
              //  finish();
            }
        });

        Log.d(TAG, "onCreate: 登录界面进入成功");
        // Toast.makeText(LoginActivity.this,"wuzhiwen",Toast.LENGTH_LONG).show();
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } //else if (!isEmailValid(email))
        else if (false){
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        } else {

            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {     //判断密码长度

        return password.length() > 4;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
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

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                editor = pref.edit();
                if(rememberPass.isChecked()) {
                    editor.putBoolean("remember_password",true);
                    editor.putString("account",mEmail);
                    //   editor.putString("password",mEmail);
                    Log.d(TAG, "onPostExecute: 记录密码");
                }else{
                    editor.clear();
                    Log.d(TAG, "onPostExecute: 清除记住密码，忘记密码");

                }
                editor.apply();
//                Bundle bundle = new Bundle();
//                bundle.putString("dealerid", mEmail);
                //注意  得先传递值  然后才能跳转界面  否则值不能被成功的传递过去
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                intent.putExtra("mEmail",mEmail);                      //在这里做一个账号的传递  在主界面里面需要判断是哪个账户登录的!
                startActivity(intent);
                Log.d(TAG, "onPostExecute: 传递的账号是:"+mEmail);
                finish();
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