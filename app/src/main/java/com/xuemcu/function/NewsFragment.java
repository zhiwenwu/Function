package com.xuemcu.function;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsFragment extends Fragment implements View.OnClickListener {

	private static final String TAG = "NewsFragment";

	private static final  String[] FRAGMENT_TAG = {"msgfrag","contacfrag","actfrag","settfrag"};


	private HomeFrsgment homeFrsgment;


	private View messageLayout;
	private View contactsLayout;
	private View settingLayout;

	private TextView messageText;
	private TextView contactsText;
	private TextView settingText;

	private FragmentManager fragmentManager;


	private View newsLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		 newsLayout = inflater.inflate(R.layout.news_layout, container,
				false);
		initViews();
		fragmentManager = getFragmentManager();
		setTabSelection(0);
		Log.d(TAG, "onCreateView: 我是大坏蛋!");

		return newsLayout;
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		messageLayout = newsLayout.findViewById(R.id.message_layout);
		messageText = (TextView) newsLayout.findViewById(R.id.message_text);
		messageLayout.setOnClickListener(this);

		contactsLayout = newsLayout.findViewById(R.id.contacts_layout);
		contactsText = (TextView) newsLayout.findViewById(R.id.contacts_text);
		contactsLayout.setOnClickListener(this);

		settingLayout = newsLayout.findViewById(R.id.setting_layout);
		settingText = (TextView) newsLayout.findViewById(R.id.setting_text);
		settingLayout.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){

			case R.id.message_layout:
				// 当点击了消息tab时，选中第1个tab
				setTabSelection(0);
				break;
			case R.id.contacts_layout:
				// 当点击了联系人tab时，选中第2个tab
				setTabSelection(1);
				break;
			case R.id.setting_layout:
				// 当点击了设置tab时，选中第4个tab
				setTabSelection(2);
				break;
			default:
				break;
		}

	}
	/**
	 * 根据传入的index参数来设置选中的tab页。
	 *
	 * @param index
	 *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		Log.d(TAG, "setTabSelection: index             " +index);
		switch (index) {
			case 0:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				messageText.setTextColor(Color.WHITE);
				if (homeFrsgment == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					homeFrsgment = new HomeFrsgment();
					transaction.add(R.id.content, homeFrsgment);
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(homeFrsgment);
				}
				Log.d(TAG, "setTabSelection: index"                 +index);
				break;
			case 1:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				contactsText.setTextColor(Color.WHITE);
				if (homeFrsgment == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					homeFrsgment = new HomeFrsgment();
					transaction.add(R.id.content, homeFrsgment);
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(homeFrsgment);
				}
				Log.d(TAG, "setTabSelection: index"                 +index);
				break;
			case 2:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				settingText.setTextColor(Color.WHITE);
				if (homeFrsgment == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					homeFrsgment = new HomeFrsgment();
					transaction.add(R.id.content, homeFrsgment);
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(homeFrsgment);
				}
				Log.d(TAG, "setTabSelection: index"                 +index);
				break;


		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {

		messageText.setTextColor(Color.parseColor("#82858b"));
		contactsText.setTextColor(Color.parseColor("#82858b"));
		settingText.setTextColor(Color.parseColor("#82858b"));
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 *
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFrsgment != null) {
			transaction.hide(homeFrsgment);
		}
	}

}
