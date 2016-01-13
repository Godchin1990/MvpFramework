package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.TopicFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new TopicFragment();
    }
}
