package com.xuemcu.function;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.xuemcu.function.HomeActivity.AccountNumber;

public class SettingFragment extends Fragment implements View.OnClickListener {

	private static final String TAG = "SettingFragment";
	private ImageView iv_avatar = null;
	private TextView tv_name = null;
	private TextView tv_fxid = null;
	private String logins = "123";


	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		DataBases dataBases = new DataBases(getActivity(),"DataBase.db",null,1);

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


		Cursor cursor = db.query("Users",null,null,null,null,null,null);

		if(cursor.moveToFirst()){
			do {
				if(AccountNumber.equals(cursor.getString(cursor.getColumnIndex("User")))){
					tv_name.setText(cursor.getString(cursor.getColumnIndex("Nickname")));
					tv_fxid.setText("账号:"+AccountNumber);
					String Photo = cursor.getString(cursor.getColumnIndex("Orders"));
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
				}
			}while(cursor.moveToNext());
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);

		RelativeLayout re_myinfo = (RelativeLayout)settingLayout.findViewById(
				R.id.re_myinfo);
		re_myinfo.setOnClickListener(this);

		RelativeLayout re_xiangce = (RelativeLayout)settingLayout.findViewById(
				R.id.re_xiangce);
		re_xiangce.setOnClickListener(this);

		RelativeLayout re_shoucang = (RelativeLayout)settingLayout.findViewById(
				R.id.re_shoucang);
		re_shoucang.setOnClickListener(this);

		RelativeLayout re_setting = (RelativeLayout)settingLayout.findViewById(
				R.id.re_setting);
		re_setting.setOnClickListener(this);

		iv_avatar = (ImageView) settingLayout.findViewById(R.id.iv_avatar);
		tv_name = (TextView) settingLayout.findViewById(R.id.tv_name);
		tv_fxid = (TextView) settingLayout.findViewById(R.id.tv_fxid);



		DataBases dataBases = new DataBases(getActivity(),"DataBase.db",null,1);

		SQLiteDatabase db = dataBases.getWritableDatabase();

		Cursor cursor = db.query("Users",null,null,null,null,null,null);
		AccountNumber = "123";
		if(cursor.moveToFirst()){
			do {
				if(AccountNumber.equals(cursor.getString(cursor.getColumnIndex("User")))){

					tv_name.setText(cursor.getString(cursor.getColumnIndex("Nickname")));
					tv_fxid.setText("账号:"+AccountNumber);
					String Photo = cursor.getString(cursor.getColumnIndex("Orders"));
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

//					NickName = cursor.getString(cursor.getColumnIndex("Nickname"));
//					Photo = cursor.getString(cursor.getColumnIndex("Orders"));

					//Log.d(TAG, "InitView: "+sex+NickName+Photo);

				}
			}while(cursor.moveToNext());
		}



		return settingLayout;
	}



	@Override
	public void onClick(View view) {

		switch (view.getId()){

			case R.id.re_myinfo:
				startActivity(new Intent(getActivity(),
						MyUserInfoActivity.class));
				Log.d(TAG, "onClick:       个人信息设置");
				break;
			case R.id.re_xiangce:
				Log.d(TAG, "onClick:       购物车");
				break;
			case R.id.re_shoucang:
				Log.d(TAG, "onClick:       查看收藏");
				break;
			case R.id.re_setting:
				Log.d(TAG, "onClick:       退出登录");
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
				getActivity().finish();


				break;

		}

	}
}
