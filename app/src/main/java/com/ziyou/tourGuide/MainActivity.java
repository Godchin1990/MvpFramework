package com.ziyou.tourGuide;

import android.os.Bundle;

import com.ziyou.tourGuide.activity.base.BaseActivity;
import com.ziyou.tourGuide.widget.AutoSwitchLineLayout;

public class MainActivity extends BaseActivity {

    private AutoSwitchLineLayout tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        tag.setTagList(getTextViewList(list,0));

    }


}
