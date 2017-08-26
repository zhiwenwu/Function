package com.xuemcu.function;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wuzhiwen on 2017/8/26.
 */

public class HomeFrsgment extends Fragment{

    private View homelayot;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homelayot = inflater.inflate(R.layout.order,
                container, false);

        InitViews();
        return homelayot ;
    }

    private void InitViews(){



    }
}
