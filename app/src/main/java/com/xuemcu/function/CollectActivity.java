package com.xuemcu.function;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class CollectActivity extends AppCompatActivity {


    private static final String TAG = "CollectActivity";
    private DataBases dataBases;
    private ListView list;
    private ImageView iv_back;
    List<String> data = new ArrayList<String>();
    private String logins = "123";
    private String AccountNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);


        InitViews();

    }

    private void InitViews(){

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        list = (ListView) findViewById(R.id.list_look);
        list.setAdapter(new ArrayAdapter<String>(CollectActivity.this, android.R.layout.simple_list_item_1,getData()));
        list.setTextFilterEnabled(true);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //加载数据   然后传递参数  进入查看详情界面

                Intent intent = new Intent(CollectActivity.this,LookCollectActivity.class);
                intent.putExtra("name",data.get(i).toString());
                startActivity(intent);

            }
        });

    }


    private List<String> getData(){

        dataBases = new DataBases(CollectActivity.this,"DataBase.db",null,1);
        SQLiteDatabase db = dataBases.getWritableDatabase();
        Cursor cursorNu = db.query("Login",null,null,null,null,null,null);

        if(cursorNu.moveToFirst()){
            do {
                if(logins.equals(cursorNu.getString(cursorNu.getColumnIndex("logins")))){

                    AccountNumber = cursorNu.getString(cursorNu.getColumnIndex("login"));
                    Log.d(TAG, "AccountNumber: "+AccountNumber);

                }
            }while(cursorNu.moveToNext());
        }
        Cursor cursor = db.query("Collects",null,null,null,null,null,null);


        if(cursor.moveToFirst()){
            do{
                if(AccountNumber.equals(cursor.getString(cursor.getColumnIndex("User"))))

                    data.add(cursor.getString(cursor.getColumnIndex("name")));

            }while(cursor.moveToNext());


        }
        for(int i=0; i < data.size(); i++){

            Log.d(TAG, "getData: "+data.get(i).toString());
        }

        return data;
    }


}
