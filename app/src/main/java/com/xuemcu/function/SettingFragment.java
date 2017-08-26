package com.xuemcu.function;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SettingFragment extends Fragment implements View.OnClickListener {

	private static final String TAG = "SettingFragment";

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
