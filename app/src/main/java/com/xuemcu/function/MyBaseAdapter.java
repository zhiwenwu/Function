package com.xuemcu.function;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
//        TextView textViewItem01;
//        TextView textViewItem02;
//        TextView textViewItem03;
//        ImageView imageView;
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
                    R.layout.list_view01_item, null);
            viewHolder = new ViewHolder();
            viewHolder.Name = (TextView)convertView.findViewById(R.id.Name);
            viewHolder.text = (TextView)convertView.findViewById(R.id.text);

            viewHolder.picture1 = (ImageView)convertView.findViewById(R.id.picture1);
            viewHolder.picture2 = (ImageView)convertView.findViewById(R.id.picture2);
            viewHolder.picture3 = (ImageView)convertView.findViewById(R.id.picture3);



//            viewHolder = new ViewHolder();
//            viewHolder.textViewItem01 = (TextView)convertView.findViewById(
//                    R.id.listView01Item01);
//            viewHolder.textViewItem02 = (TextView)convertView.findViewById(
//                    R.id.listView01Item02);
//            viewHolder.textViewItem03 = (TextView)convertView.findViewById(
//                    R.id.listView01Item03);


//            //动态增加1个ImageView
//            viewHolder.picture1 = new ImageView(context);
//            LinearLayout.LayoutParams mParams1 = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT);
//            mParams1.gravity = Gravity.CENTER;
//            mParams1.width=50;
//            viewHolder.picture1.setLayoutParams(mParams1);
//            //这个ImageView放到ListView的第2列之后
//            ((LinearLayout)convertView).addView(viewHolder.picture1,2);
//
//            //动态增加1个ImageView
//            viewHolder.picture2 = new ImageView(context);
//            LinearLayout.LayoutParams mParams2 = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            mParams2.gravity = Gravity.CENTER;
//            mParams2.width=50;
//            viewHolder.picture2.setLayoutParams(mParams2);
//            //这个ImageView放到ListView的第2列之后
//            ((LinearLayout)convertView).addView(viewHolder.picture2,2);
//
//            //动态增加1个ImageView
//            viewHolder.picture3 = new ImageView(context);
//            LinearLayout.LayoutParams mParams3 = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            mParams3.gravity = Gravity.CENTER;
//            mParams3.width=50;
//            viewHolder.picture3.setLayoutParams(mParams3);
//            //这个ImageView放到ListView的第2列之后
//            ((LinearLayout)convertView).addView(viewHolder.picture3,2);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            Log.d("MyBaseAdapter", "旧的convertView,position="+position);
        }



        viewHolder.Name.setText(person.name);
        viewHolder.picture1.setImageResource(person.photo1);
        viewHolder.picture2.setImageResource(person.photo2);
        viewHolder.picture3.setImageResource(person.photo3);
        viewHolder.text.setText(person.Text);


//        viewHolder.textViewItem01.setText(String.valueOf(person.id));
//        viewHolder.textViewItem02.setText(person.name);
//        viewHolder.textViewItem03.setText(person.address);
//        viewHolder.imageView.setImageResource(person.photo);

        //T
        viewHolder.Name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        "[textViewItem01.setOnClickListener]点击了"+person.name,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //对ListView中的每一行信息配置OnClick事件
        convertView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        "[convertView.setOnClickListener]点击了"+person.name,
                        Toast.LENGTH_SHORT).show();
            }

        });

        //对ListView中的每一行信息配置OnLongClick事件
        convertView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,
                        "[convertView.setOnLongClickListener]点击了"+person.name,
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return convertView;
    }

}