package com.xuemcu.function;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wuzhiwen on 2017/8/30.
 */

public class HomePageFragment extends Fragment {


    //定义Person
    List<Person> persons ;
    ListAdapter adapter = null;
    ListView listView = null;
    private View homepage_layout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homepage_layout = inflater.inflate(R.layout.homepage_layout,
                container, false);

        InitViews();
        //显示ListView
        initListAllPersons();
        showByMyBaseAdapter();


        return homepage_layout;
    }
    private void InitViews(){

        //获取组件
        listView = (ListView)homepage_layout.findViewById(R.id.listView);

    }

    public void initListAllPersons(){
        persons = new ArrayList<Person>();
        persons.clear();
        persons.add(new Person(R.drawable.photo1,"传说中的爱河",R.drawable.timg1,R.drawable.timg2,R.drawable.timg3,"有的旅行是为了拓宽眼界，浏览风景名胜。有的旅行是为了体验生活，感悟人生。有的旅行时为了寻找逝去的年华，重温青春的惆怅。而有的旅行是释放负面情绪，换个心情，轻装上阵。"));
        persons.add(new Person(R.drawable.photo2,"褪了色的相册",R.drawable.timg4,R.drawable.timg5,R.drawable.timg6,"一直想去旅行，去很美很美的地方，但往往真正踏足想去的地方，便觉不过如此。也许我们只是想让自己的心去旅行，无论身处何处，只要有一颗放松而美好的心态，生活便是美好！"));
        persons.add(new Person(R.drawable.photo3,"再见丶再也不见",R.drawable.timg7,R.drawable.timg8,R.drawable.timg9,"旅行，其实是需要具有一些流浪精神的，这种精神使人能在旅行中和大自然更加接近，悠然享受和大自然融合之乐。旅行，有一种苍凉，“浮云游子意，落日故人情”，孑然一身，隐入苍茫自然，自有一种孤独的意味；旅行，更有一种逍遥，浑然忘我，与大自然交融的境界令人心弛神往。"));
        persons.add(new Person(R.drawable.photo2,"你好丶我却不好",R.drawable.timg3,R.drawable.timg,R.drawable.timg1,"每个人在他的人生发轫之初，总有一段时光，没有什么可留恋，只有抑制不住的梦想，没有什么可凭仗，只有他的好身体，没有地方可去，只想到处流浪人生就像一场旅行，不必在乎目的地，在乎的是沿途的风景以及看风景的心情，让心灵去旅行！"));
        persons.add(new Person(R.drawable.photo3,"HO丶为什么",R.drawable.timg2,R.drawable.qinqin,R.drawable.timg10,"旅行，不要害怕错过什么，因为在路上你就已经收获了自由自在的好心情。切忌贪婪，恨不得一次玩遍所有传说中的好景点，累死累活不说，走马观花反而少了真实体验，要知道，当你一直在担心错过了什么的时候，其实你已经错过了旅行的意义。"));
    }

    public void showByMyBaseAdapter(){
        adapter = new MyBaseAdapter(getActivity(), persons);
        listView.setAdapter(adapter);

    }

}
