package com.xuemcu.function;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public  class NewsFragment extends Fragment implements View.OnClickListener {


	private static final String TAG = "NewsFragment";
	private View newsLayout;


	private View messageLayout;
	private View contactsLayout;
	private View settingLayout;
	private TextView messageText;
	private TextView contactsText;
	private TextView settingText;

	private ListView list;
	//定义Person
	List<Person> persons ;
	ListAdapter adapter = null;

	List<Person> persons_miyue ;
	ListAdapter adapter_miyue = null;

	List<Person> persons_zijia ;
	ListAdapter adapter_zijia = null;

	private int indexs = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		 newsLayout = inflater.inflate(R.layout.news_layout, container,
				false);
		InitViews();
		initListAllPersons();
		showByMyBaseAdapter();
		setTabSelection(0);

		return newsLayout;
	}
	private void InitViews(){
		messageLayout = newsLayout.findViewById(R.id.message_layout);
		messageText = (TextView) newsLayout.findViewById(R.id.message_text);
		messageLayout.setOnClickListener(this);

		contactsLayout = newsLayout.findViewById(R.id.contacts_layout);
		contactsText = (TextView) newsLayout.findViewById(R.id.contacts_text);
		contactsLayout.setOnClickListener(this);

		settingLayout = newsLayout.findViewById(R.id.setting_layout);
		settingText = (TextView) newsLayout.findViewById(R.id.setting_text);
		settingLayout.setOnClickListener(this);

		list = (ListView) newsLayout.findViewById(R.id.list);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
								Toast.makeText(getActivity(),
						"[OnItemClickListener]点击了："+persons.get(position).name,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),CXActivity.class);
				startActivity(intent);
			}
		});


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


	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		Log.d(TAG, "setTabSelection: 我想看看数据"+persons.size());
		clearSelection();
		Log.d(TAG, "setTabSelection: index             " +index);
		switch (index) {
			case 0:
				int indexs = 0;
				messageText.setTextColor(Color.RED);
				initListAllPersons();
				showByMyBaseAdapter();

				Log.d(TAG, "setTabSelection: index"                 +index);
				break;
			case 1:
				indexs = 1;
				contactsText.setTextColor(Color.RED);
				initListAllPersons_miyue();
				showByMyBaseAdapter_miyue();
				Log.d(TAG, "setTabSelection: index"                 +index);
				break;
			case 2:
				indexs = 2;
				settingText.setTextColor(Color.RED);
				initListAllPersons_zijia();
				showByMyBaseAdapter_zijia();
				Log.d(TAG, "setTabSelection: index"                 +index);
				break;
			default:
				indexs = 0;
				break;


		}


	}

		/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {

		messageText.setTextColor(Color.parseColor("#82858b"));
		contactsText.setTextColor(Color.parseColor("#82858b"));
		settingText.setTextColor(Color.parseColor("#82858b"));
	}


	public void initListAllPersons(){
		persons = new ArrayList<Person>();
		//persons.clear();
		persons.add(new Person(R.drawable.meitu_1,"亲爱的丶",R.drawable.timg1,R.drawable.timg2,R.drawable.timg3,"有的旅行是为了拓宽眼界，浏览风景名胜。有的旅行是为了体验生活，感悟人生。有的旅行时为了寻找逝去的年华，重温青春的惆怅。而有的旅行是释放负面情绪，换个心情，轻装上阵。"));
		persons.add(new Person(R.drawable.meitu_3,"你想说的，我都懂",R.drawable.timg7,R.drawable.timg8,R.drawable.timg9,"旅行，其实是需要具有一些流浪精神的，这种精神使人能在旅行中和大自然更加接近，悠然享受和大自然融合之乐。旅行，有一种苍凉，“浮云游子意，落日故人情”，孑然一身，隐入苍茫自然，自有一种孤独的意味；旅行，更有一种逍遥，浑然忘我，与大自然交融的境界令人心弛神往。"));
		persons.add(new Person(R.drawable.meitu_4,"等我驾着彩云归来",R.drawable.timg3,R.drawable.timg,R.drawable.timg1,"每个人在他的人生发轫之初，总有一段时光，没有什么可留恋，只有抑制不住的梦想，没有什么可凭仗，只有他的好身体，没有地方可去，只想到处流浪人生就像一场旅行，不必在乎目的地，在乎的是沿途的风景以及看风景的心情，让心灵去旅行！"));
		persons.add(new Person(R.drawable.meitu_5,"小生姓吴",R.drawable.timg2,R.drawable.qinqin,R.drawable.timg10,"旅行，不要害怕错过什么，因为在路上你就已经收获了自由自在的好心情。切忌贪婪，恨不得一次玩遍所有传说中的好景点，累死累活不说，走马观花反而少了真实体验，要知道，当你一直在担心错过了什么的时候，其实你已经错过了旅行的意义。"));
	}

	public void showByMyBaseAdapter(){
		adapter = new MyBaseAdapter(getActivity(), persons);
		list.setAdapter(adapter);

	}

	public void initListAllPersons_miyue(){
		persons_miyue = new ArrayList<Person>();
		//persons.clear();
		persons_miyue.add(new Person(R.drawable.meitu_6,"爱你不是两三天",R.drawable.timg1,R.drawable.timg2,R.drawable.timg3,"有的旅行是为了拓宽眼界，浏览风景名胜。有的旅行是为了体验生活，感悟人生。有的旅行时为了寻找逝去的年华，重温青春的惆怅。而有的旅行是释放负面情绪，换个心情，轻装上阵。"));
		persons.add(new Person(R.drawable.meitu_2,"北京那点事儿",R.drawable.timg4,R.drawable.timg5,R.drawable.timg6,"一直想去旅行，去很美很美的地方，但往往真正踏足想去的地方，便觉不过如此。也许我们只是想让自己的心去旅行，无论身处何处，只要有一颗放松而美好的心态，生活便是美好！"));
	}

	public void showByMyBaseAdapter_miyue(){
		adapter_miyue = new MyBaseAdapter(getActivity(), persons_miyue);
		list.setAdapter(adapter_miyue);

	}

	public void initListAllPersons_zijia(){
		persons_zijia = new ArrayList<Person>();
		//persons.clear();
		persons_zijia.add(new Person(R.drawable.meitu_7,"世界这么大丶我想去看看",R.drawable.timg7,R.drawable.timg8,R.drawable.timg9,"旅行，其实是需要具有一些流浪精神的，这种精神使人能在旅行中和大自然更加接近，悠然享受和大自然融合之乐。旅行，有一种苍凉，“浮云游子意，落日故人情”，孑然一身，隐入苍茫自然，自有一种孤独的意味；旅行，更有一种逍遥，浑然忘我，与大自然交融的境界令人心弛神往。"));
		persons_zijia.add(new Person(R.drawable.meitu_8,"我带着你丶你带着钱",R.drawable.timg1,R.drawable.timg2,R.drawable.timg3,"有的旅行是为了拓宽眼界，浏览风景名胜。有的旅行是为了体验生活，感悟人生。有的旅行时为了寻找逝去的年华，重温青春的惆怅。而有的旅行是释放负面情绪，换个心情，轻装上阵。"));

	}

	public void showByMyBaseAdapter_zijia(){
		adapter_zijia = new MyBaseAdapter(getActivity(), persons_zijia);
		list.setAdapter(adapter_zijia);

	}

//
//	/**
//	 * 初始化list的事件.
//	 */
//	public void initListView01Event(){
//
//		//ListView的item点击事件
//		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//									int position, long id) {
//
//				Intent intent = new Intent(getActivity(),CXActivity.class);
//				startActivity(intent);
//
//				Toast.makeText(getActivity(),
//						"[OnItemClickListener]点击了："+persons.get(position).name,
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//		//ListView的item长按点击事件
//		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//										   int position, long id) {
////				Toast.makeText(getActivity(),
////						"[OnItemLongClickListener]点击了："+persons.get(position).name,
////						Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});
//		//ListView的键盘选中事件(直接触摸屏幕选中不会激发)
//		list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//									   int position, long id) {
////				Toast.makeText(getActivity(),
////						"[OnItemSelectedListener:onItemSelected]点击了："
////								+persons.get(position).name,
////						Toast.LENGTH_SHORT).show();
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
////				Toast.makeText(getActivity(),
////						"[OnItemSelectedListener:onNothingSelected]点击了",
////						Toast.LENGTH_SHORT).show();
//			}
//		});
//	}



}

//
//public class NewsFragment extends Fragment implements View.OnClickListener {
//
//	public static int indexs = 0;
//	private static final String TAG = "NewsFragment";
//	private static final  String[] FRAGMENT_TAG = {"msgfrag","contacfrag","actfrag","settfrag"};
//	private PhotoFragment photoFragment;
//	private ParentFragment parentFragment;
//	private HoneyFragment honeyFragment;
//
//
//	private View messageLayout;
//	private View contactsLayout;
//	private View settingLayout;
//
//	private TextView messageText;
//	private TextView contactsText;
//	private TextView settingText;
//
//	private FragmentManager fragmentManager;
//	private View newsLayout;
//
//
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//		 newsLayout = inflater.inflate(R.layout.news_layout, container,
//				false);
//		initViews();
//		fragmentManager = getFragmentManager();
//		setTabSelection(0);
//		Log.d(TAG, "onCreateView: 我是大坏蛋!");
//
//		return newsLayout;
//	}
//
//	/**
//	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
//	 */
//	private void initViews() {
//		messageLayout = newsLayout.findViewById(R.id.message_layout);
//		messageText = (TextView) newsLayout.findViewById(R.id.message_text);
//		messageLayout.setOnClickListener(this);
//
//		contactsLayout = newsLayout.findViewById(R.id.contacts_layout);
//		contactsText = (TextView) newsLayout.findViewById(R.id.contacts_text);
//		contactsLayout.setOnClickListener(this);
//
//		settingLayout = newsLayout.findViewById(R.id.setting_layout);
//		settingText = (TextView) newsLayout.findViewById(R.id.setting_text);
//		settingLayout.setOnClickListener(this);
//
//	}
//
//	@Override
//	public void onClick(View view) {
//		switch (view.getId()){
//
//			case R.id.message_layout:
//				// 当点击了消息tab时，选中第1个tab
//				setTabSelection(0);
//				break;
//			case R.id.contacts_layout:
//				// 当点击了联系人tab时，选中第2个tab
//				setTabSelection(1);
//				break;
//			case R.id.setting_layout:
//				// 当点击了设置tab时，选中第4个tab
//				setTabSelection(2);
//				break;
//			default:
//				break;
//		}
//
//	}
//	/**
//	 * 根据传入的index参数来设置选中的tab页。
//	 *
//	 * @param index
//	 *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
//	 */
//	private void setTabSelection(int index) {
//		// 每次选中之前先清楚掉上次的选中状态
//		clearSelection();
//		// 开启一个Fragment事务
//		FragmentTransaction transaction = fragmentManager.beginTransaction();
//		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//		hideFragments(transaction);
//		Log.d(TAG, "setTabSelection: index             " +index);
//		switch (index) {
//			case 0:
//				indexs = 0;
//
//				messageText.setTextColor(Color.RED);
//				if (photoFragment == null) {
//					// 如果MessageFragment为空，则创建一个并添加到界面上
//					photoFragment = new PhotoFragment();
//					transaction.add(R.id.content, photoFragment);
//				} else {
//					// 如果MessageFragment不为空，则直接将它显示出来
//					transaction.show(photoFragment);
//				}
//				Log.d(TAG, "setTabSelection: index"                 +index);
//				break;
//			case 1:
//				indexs = 1;
//
//				contactsText.setTextColor(Color.RED);
//				if (parentFragment == null) {
//					// 如果MessageFragment为空，则创建一个并添加到界面上
//					parentFragment = new ParentFragment();
//					transaction.add(R.id.content, parentFragment);
//				} else {
//					// 如果MessageFragment不为空，则直接将它显示出来
//					transaction.show(parentFragment);
//				}
//				Log.d(TAG, "setTabSelection: index"                 +index);
//				break;
//			case 2:
//				indexs = 2;
//
//				settingText.setTextColor(Color.RED);
//				if (honeyFragment == null) {
//					// 如果MessageFragment为空，则创建一个并添加到界面上
//					honeyFragment = new HoneyFragment();
//					transaction.add(R.id.content, honeyFragment);
//				} else {
//					// 如果MessageFragment不为空，则直接将它显示出来
//					transaction.show(honeyFragment);
//				}
//				Log.d(TAG, "setTabSelection: index"                 +index);
//				break;
//			default:
//				indexs = 0;
//				break;
//
//
//		}
//		transaction.commit();
//	}
//
//	/**
//	 * 清除掉所有的选中状态。
//	 */
//	private void clearSelection() {
//
//		messageText.setTextColor(Color.parseColor("#82858b"));
//		contactsText.setTextColor(Color.parseColor("#82858b"));
//		settingText.setTextColor(Color.parseColor("#82858b"));
//	}
//
//	/**
//	 * 将所有的Fragment都置为隐藏状态。
//	 *
//	 * @param transaction
//	 *            用于对Fragment执行操作的事务
//	 */
//	private void hideFragments(FragmentTransaction transaction) {
//		Log.d(TAG, "hideFragments: 我执行了");
//		if (photoFragment != null) {
//			transaction.hide(photoFragment);
//		}
//		if (parentFragment != null) {
//			transaction.hide(parentFragment);
//		}
//		if (honeyFragment != null) {
//			transaction.hide(honeyFragment);
//		}
//	}
//
//}
