package com.xuemcu.function;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 * @author wuzhiwen
 */
public class HomeActivity extends Activity implements View.OnClickListener {

    static String AccountNumber = "AccountNumber";
    private static final String TAG = "HomeActivity";
    private HomePageFragment homePageFragment;
    private TravelNotesFragment travelNotesFragment;
    private OrderFragment orderFragment;
    private SettingFragment settingFragment;
    private View homepagelayout;
    private View travelnoteslayout;
    private View orderlayout;
    private View settingLayout;
    private ImageView messageImage;
    private ImageView contactsImage;
    private ImageView newsImage;
    private ImageView settingImage;
    private FragmentManager fragmentManager;

    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fragmentManager = getFragmentManager();
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);

    }

    private void initViews() {
        homepagelayout = findViewById(R.id.homepage_layout);
        orderlayout = findViewById(R.id.personalorder_layout);
        travelnoteslayout = findViewById(R.id.travelnots_layout);
        settingLayout = findViewById(R.id.setting_layout);
        messageImage = (ImageView) findViewById(R.id.message_image);
        contactsImage = (ImageView) findViewById(R.id.contacts_image);
        newsImage = (ImageView) findViewById(R.id.news_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);

        homepagelayout.setOnClickListener(this);
        travelnoteslayout.setOnClickListener(this);
        orderlayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);

        Log.d(TAG, "initViews: "+new ReadAccount(this).RedA());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage_layout:
                // 当点击时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.travelnots_layout:
                // 当点击时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.personalorder_layout:
                // 当点击时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.setting_layout:
                // 当点击时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击时，改变控件的图片和文字颜色
                messageImage.setImageResource(R.drawable.shouye2);
                //messageText.setTextColor(Color.WHITE);
                if (homePageFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.content, homePageFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(homePageFragment);
                }
                break;
            case 1:
                // 当点击时，改变控件的图片和文字颜色
                contactsImage.setImageResource(R.drawable.youji2);
                //contactsText.setTextColor(Color.WHITE);
                if (travelNotesFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    travelNotesFragment = new TravelNotesFragment();
                    transaction.add(R.id.content, travelNotesFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(travelNotesFragment);
                }
                break;
            case 2:
                // 当点击时，改变控件的图片和文字颜色
                newsImage.setImageResource(R.drawable.dingzhi2);
               // newsText.setTextColor(Color.WHITE);
                if (orderFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.content, orderFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(orderFragment);
                }
                break;
            case 3:
            default:
                // 当点击时，改变控件的图片和文字颜色
                settingImage.setImageResource(R.drawable.shezhi2);
                //settingText.setTextColor(Color.WHITE);
                if (settingFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection() {
        messageImage.setImageResource(R.drawable.shouye1);
        contactsImage.setImageResource(R.drawable.youji1);
        newsImage.setImageResource(R.drawable.dingzhi1);
        settingImage.setImageResource(R.drawable.shezhi1);

    }

    private void hideFragments(FragmentTransaction transaction) {

        Log.d(TAG, "hideFragments: 我执行了");
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }
        if (travelNotesFragment != null) {
            transaction.hide(travelNotesFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }
}

