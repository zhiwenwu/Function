package com.xuemcu.function;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {


	//定义Person
	List<Person> persons ;
	ListAdapter adapter = null;
	ListView listView01 = null;
	EditText editTextPersonName = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);

		//获取组件
		listView01 = (ListView)messageLayout.findViewById(R.id.listView01);
		//显示ListView
		initListAllPersons();
		showByMyBaseAdapter();

		return messageLayout;
	}

	public void initListAllPersons(){
		persons = new ArrayList<Person>();
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));
		persons.add(new Person("李小龙",R.drawable.qinqin,R.drawable.qinqin,R.drawable.qinqin,"我是爱你的，你们是世界上面最好的"));

	}

	public void showByMyBaseAdapter(){
		adapter = new MyBaseAdapter(getActivity(), persons);
		listView01.setAdapter(adapter);
	}
	/**
	 * 按钮button01Edit的onClick事件.
	 * @param view
	 */
	public void editPersonAndRefreshListView01(View view){
		//获取TextEdit数据
		String value = editTextPersonName.getText().toString();
		//更新ListView的内容，并且动态刷新.
		persons.get(0).name=value;
		((BaseAdapter) adapter).notifyDataSetChanged();
	}

	/**
	 * 初始化listView01的事件.
	 */
	public void initListView01Event(){

		//ListView的item点击事件
		listView01.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Toast.makeText(getActivity(),
						"[OnItemClickListener]点击了："+persons.get(position).name,
						Toast.LENGTH_SHORT).show();
			}
		});
		//ListView的item长按点击事件
		listView01.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
										   int position, long id) {
				Toast.makeText(getActivity(),
						"[OnItemLongClickListener]点击了："+persons.get(position).name,
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		//ListView的键盘选中事件(直接触摸屏幕选中不会激发)
		listView01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int position, long id) {
				Toast.makeText(getActivity(),
						"[OnItemSelectedListener:onItemSelected]点击了："
								+persons.get(position).name,
						Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity(),
						"[OnItemSelectedListener:onNothingSelected]点击了",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
