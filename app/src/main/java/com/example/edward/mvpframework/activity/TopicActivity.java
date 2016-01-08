package com.example.edward.mvpframework.activity;

import com.example.edward.mvpframework.activity.base.BaseFragmentActivity;
import com.example.edward.mvpframework.fragment.TopicFragment;
import com.example.edward.mvpframework.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new TopicFragment();
    }
}
