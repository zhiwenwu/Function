package com.xuemcu.function;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Wuzhiwen on 2017/8/31.
 */

public  class ReadAccount {

    DatabaseManger databaseManger ;
    Context mcontext;
    String account = "account";

    public ReadAccount(Context context){

        databaseManger = DatabaseManger.getInstance(context);
        this.mcontext = context;
    }

    public String RedA(){

        Cursor cursor = databaseManger.queryDataCursor("Login");

        if(cursor.moveToFirst()){
            do {
                if(account.equals(cursor.getString(cursor.getColumnIndex("logins")))) {

                    return cursor.getString(cursor.getColumnIndex("login"));
                }
            }while(cursor.moveToNext());
        }

        return "";
    }
}
