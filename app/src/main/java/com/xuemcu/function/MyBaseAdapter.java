package com.xuemcu.function;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wuzhiwen on 2017/8/25.
 */


class MyBaseAdapter extends BaseAdapter {
    private List<Person> persons;
    Context context;

    public MyBaseAdapter(Context context, List<Person> persons){
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (persons==null)?0:persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder{

        ImageView portrait;
        TextView Name;
        ImageView picture1;
        ImageView picture2;
        ImageView picture3;
        TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = (Person)getItem(position);
        ViewHolder viewHolder = null;
        if(convertView==null){
            Log.d("MyBaseAdapter", "新建convertView,position="+position);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.listview_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.Name = (TextView)convertView.findViewById(R.id.Name);
            viewHolder.text = (TextView)convertView.findViewById(R.id.text);

            viewHolder.picture1 = (ImageView)convertView.findViewById(R.id.picture1);
            viewHolder.picture2 = (ImageView)convertView.findViewById(R.id.picture2);
            viewHolder.picture3 = (ImageView)convertView.findViewById(R.id.picture3);
            viewHolder.portrait = (ImageView) convertView.findViewById(R.id.portrait);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            Log.d("MyBaseAdapter", "旧的convertView,position="+position);
        }


        viewHolder.portrait.setImageResource(person.portrait);
        viewHolder.Name.setText(person.name);
        viewHolder.picture1.setImageResource(person.photo1);
        viewHolder.picture2.setImageResource(person.photo2);
        viewHolder.picture3.setImageResource(person.photo3);
        viewHolder.text.setText(person.Text);


        return convertView;
    }

}