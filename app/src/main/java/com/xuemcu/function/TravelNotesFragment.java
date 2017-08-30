package com.xuemcu.function;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelNotesFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ContactsFragment";
    private View travelnotes_layout;
    private ListView list;
    private Button xin;
    private AlertDialog mAlertDialog = null;
    private Button btn_ok = null;
    private Button btn_dele = null;
    private TextView text_dialog;
    private TextView text_Title;
    int Position = 1;
    private DatabaseManger databaseManger;
    private Cursor cursor;

    List<String> data = new ArrayList<String>();

    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,getData()));
        list.setTextFilterEnabled(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        travelnotes_layout = inflater.inflate(R.layout.travelnotes_layout,
                container, false);

        InitViews();

        return travelnotes_layout;
    }

    private void InitViews(){

        databaseManger = DatabaseManger.getInstance(getActivity());
        cursor = databaseManger.queryDataCursor("Notes");

        View view_Search = View.inflate(getActivity(), R.layout.dailog_query, null);

        mAlertDialog = new AlertDialog.Builder(getActivity()).setView(view_Search).create();

        mAlertDialog.setTitle("查看游记:");


        list = (ListView) travelnotes_layout.findViewById(R.id.lsit);
        xin = (Button) travelnotes_layout.findViewById(R.id.xin);
        xin.setOnClickListener(this);
        list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,getData()));
        list.setTextFilterEnabled(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "onItemClick:            "+position  +"onItemClick         "    +id);
                Position = position;
             //   mAlertDialog.show();
             //   MyHandler.sendEmptyMessage(0x01);

                //这里点击事件处理       有查看丶删除功能

            }
        });
    }
    private List<String> getData(){

        data.clear();

        if(cursor.moveToFirst()){
            do{
                if(new ReadAccount(getActivity()).RedA().equals(cursor.getString(cursor.getColumnIndex("User"))))

                    data.add(cursor.getString(cursor.getColumnIndex("title")));

            }while(cursor.moveToNext());


        }
        for(int i=0; i < data.size(); i++){

            Log.d(TAG, "getData: "+data.get(i).toString());
        }

        return data;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.xin:
                Intent intent = new Intent(getActivity(),XinActivity.class);
                startActivity(intent);
                //	Toast.makeText(getActivity(),"新建游记!",Toast.LENGTH_LONG).show();
                break;
        }

    }


    public Handler MyHandler = new Handler(){
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            int what = msg.what;
            if (what == 0x01) {    //update
                int i = 1;
                text_dialog = (TextView) mAlertDialog.findViewById(R.id.display_dialog);
                text_Title = (TextView) mAlertDialog.findViewById(R.id.display_title);
                btn_ok = (Button) mAlertDialog.findViewById(R.id.btn_ok);
                btn_dele = (Button) mAlertDialog.findViewById(R.id.btn_dele);
                List<String> mdata = new ArrayList<String>();
                text_Title.setGravity(Gravity.CENTER);
                text_dialog.setMovementMethod(new ScrollingMovementMethod());
                // text_dialog.setText();

                if(cursor.moveToFirst()){
                    do{
                        if(cursor.getString(cursor.getColumnIndex("title")).equals(data.get(Position))){
                            text_Title.setText(cursor.getString(cursor.getColumnIndex("title")));

                            String context = cursor.getString(cursor.getColumnIndex("context"));
                            String path = cursor.getString(cursor.getColumnIndex("path"));

                            Log.d(TAG, "主文的内容是:             "+ context);
                            Log.d(TAG, "图片的路径是: "   + path);
                            String[] sourceStrArray = context.split("test");
                            String[] sourcePath = path.split("####");

                            text_dialog.append(sourceStrArray[0]);
                            Pattern p = Pattern.compile("test");
                            Matcher m = p.matcher(context);
                            while (m.find()) {


                                ContentResolver cr = getActivity().getContentResolver();
                                Bitmap bitmap = null;
                                //将对象存入Bitmap中
                                // bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                                //bitmap = getLoacalBitmap("/storage/emulated/0/DCIM/Camera/IMG20170512173710.jpg");
                                bitmap = getLoacalBitmap(sourcePath[i]);
                                int imgWidth = bitmap.getWidth();
                                int imgHeight = bitmap.getHeight();
                                double partion = imgWidth*1.0/imgHeight;
                                double sqrtLength = Math.sqrt(partion*partion + 1);
                                //新的缩略图大小
                                double newImgW = 320*(partion / sqrtLength);
                                double newImgH = 320*(1 / sqrtLength);
                                float scaleW = (float) (newImgW/imgWidth);
                                float scaleH = (float) (newImgH/imgHeight);
                                Log.d(TAG, "imgHeight: "+newImgH);
                                Matrix mx = new Matrix();
                                //对原图片进行缩放
                                mx.postScale(scaleW, scaleH);
                                bitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, mx, true);
                                ImageSpan imageSpan = new ImageSpan(getActivity(),bitmap);
                                SpannableString spannableString = new SpannableString("test");
                                spannableString.setSpan(imageSpan, 0, spannableString.length(), SpannableString.SPAN_MARK_MARK);
                                text_dialog.append("\n");
                                text_dialog.append(spannableString);
                                text_dialog.append("\n");
                                text_dialog.append(sourceStrArray[i]);
                                i++;

                            }
//                            int contexttimes = appearNumber(context,"test");
//                            int pathtimes = appearNumber(path,"####");
//                            pathtimes = pathtimes - 1;

//
//                            for (int i = 0; i < sourceStrArray.length; i ++){
//
//                                Log.d(TAG, "sourceStrArray:   " +sourceStrArray[i]);
//                            }
//                            for (int i = 0; i < sourcePath.length; i ++){
//
//                                Log.d(TAG, "sourcePath:   " +sourcePath[i]);
//                            }
//                            Log.d(TAG, "主文的test出现的次数:                  "+contexttimes);
//                            Log.d(TAG, "图片的路径出现的次数:                  "+pathtimes);

                        }



                    }while(cursor.moveToNext());


                }


                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text_dialog.setText("");
                        mAlertDialog.cancel();
                    }
                });
                btn_dele.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        text_dialog.append("");
                        mAlertDialog.cancel();



                    }
                });

                if(mAlertDialog.isShowing()){
                    MyHandler.sendEmptyMessageDelayed(0,1000);
                }
            }else {
                //   mAlertDialog.cancel();
            }
        }
    };

    //获取指定字符串出现的次数
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
