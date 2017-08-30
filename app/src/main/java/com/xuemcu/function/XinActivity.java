package com.xuemcu.function;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XinActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button tu;
    private EditText biao;
    private EditText et_Notes;
    private Button Sava;
    private List<String> list;

    private String Path = "####";

    private DataBases dataBases;  //数据库
    GetPathFromUri4kitkat Get;
    // 要申请的权限
    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin);

        InitViews();
    }

    private void InitViews(){


        //创建一个数据库
        dataBases = new DataBases(XinActivity.this,"DataBase.db",null,1);
        Get = new GetPathFromUri4kitkat();

        tu = (Button) findViewById(R.id.bu);
        biao = (EditText) findViewById(R.id.biao);
        et_Notes = (EditText) findViewById(R.id.content_et);
        tu.setOnClickListener(this);
        Sava = (Button) findViewById(R.id.Sava);
        Sava.setOnClickListener(this);

        //定义个路径地址保存集合
        list = new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bu:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    // 检查该权限是否已经获取
                    int i1 = ContextCompat.checkSelfPermission(this, permissions[0]);
                    int i2 = ContextCompat.checkSelfPermission(this, permissions[1]);
                    int i3 = ContextCompat.checkSelfPermission(this, permissions[2]);

                    // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                    if (i1 != PackageManager.PERMISSION_GRANTED ||
                            i2 != PackageManager.PERMISSION_GRANTED ||
                            i3 != PackageManager.PERMISSION_GRANTED) {

                        // 如果没有授予该权限，就去提示用户请求
                        startRequestPermission();
                    }
                }

                Log.d(TAG, "onClick:  调用图片");
                Intent intent;
                //添加图片的主要代码
                intent = new Intent();
                //设定类型为image
                intent.setType("image/*");
                //设置action
                intent.setAction(Intent.ACTION_PICK);
                //选中相片后返回本Activity
                startActivityForResult(intent, 1);
                break;
            case R.id.Sava:
                SQLiteDatabase db = dataBases.getWritableDatabase();
                ContentValues values = new ContentValues();

//                        + "title text,"         //保存的是标题
//                        + "context text,"       //保存的是主文
//                        + "path text,"          //图片的路径
//                        + "time varchar(20))";  //时间
//                values.put("User", Registe_email.getText().toString());
//                values.put("Passwd", Registe_password.getText().toString());
//                db.insert("Users",null,values);
//                values.clear();


                //取得EditText中的内容
                String context = et_Notes.getText().toString();
                String first_words = context.replaceAll("\r|\n|\t", " ");
                Log.d(TAG, "onClick: first_words内容的:"+first_words);
                if(context.isEmpty()){
                    Toast.makeText(XinActivity.this, "记事为空!", Toast.LENGTH_LONG).show();
                }
                else{
                    //取得当前时间
                    SimpleDateFormat formatter   =   new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date curDate   =   new Date(System.currentTimeMillis());//获取当前时间
                    String time   =   formatter.format(curDate);

                    Log.d(TAG, "onClick: 图片的数量是:"+list.size());
                    for(int i = 0;i < list.size();i ++){

                         Path = Path+list.get(i)+"####";

                       // Log.d(TAG, "路径是:"+list.get(i).toString());
                    }
                    Log.d(TAG, "全部路径:"+Path);
                   // list.clear();//这里的集合需要注意。
                    values.put("title", biao.getText().toString());
                    values.put("context", first_words);
                    values.put("path", Path);
                    values.put("time", time);
                db.insert("Notes",null,values);
                values.clear();
                }
               // Intent intent_back = new Intent(XinActivity.this,HomeActivity.class);
               // intent_back.putExtra("ABC","ABC");    //返回一个数据给主界面  辅助判断是哪个Fragment
              //  startActivity(intent_back);
                finish();
                break;
        }
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){


            //取得数据
            Uri uri = data.getData();

         //   list.add(uri.toString());           //取得每一个图片的地址
            //
            // Get.getPath(XinActivity.this,uri);
            list.add(Get.getPath(XinActivity.this,uri));
            Log.d(TAG, "吴致文在这里:"+Get.getPath(XinActivity.this,uri));
            Log.d(TAG, "onActivityResult:123456 "+uri.toString());
            ContentResolver cr = XinActivity.this.getContentResolver();
            Bitmap bitmap = null;
            //如果是选择照片
            if(requestCode == 1){

                try {
                    //将对象存入Bitmap中
                    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            int imgWidth = bitmap.getWidth();
            int imgHeight = bitmap.getHeight();
            double partion = imgWidth*1.0/imgHeight;
            double sqrtLength = Math.sqrt(partion*partion + 1);
            //新的缩略图大小
            double newImgW = 320*(partion / sqrtLength);
            double newImgH = 320*(1 / sqrtLength);
            float scaleW = (float) (newImgW/imgWidth);
            float scaleH = (float) (newImgH/imgHeight);

            Matrix mx = new Matrix();
            //对原图片进行缩放
            mx.postScale(scaleW, scaleH);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, mx, true);
            final ImageSpan imageSpan = new ImageSpan(this,bitmap);
            SpannableString spannableString = new SpannableString("test");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), SpannableString.SPAN_MARK_MARK);
            //光标移到下一行
            et_Notes.append("\n");
            Editable editable = et_Notes.getEditableText();
            int selectionIndex = et_Notes.getSelectionStart();
            spannableString.getSpans(0, spannableString.length(), ImageSpan.class);
            //将图片添加进EditText中
            editable.insert(selectionIndex, spannableString);
            //添加图片后自动空出两行
            et_Notes.append("\n\n");
        }
    }


    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 1);
    }


}