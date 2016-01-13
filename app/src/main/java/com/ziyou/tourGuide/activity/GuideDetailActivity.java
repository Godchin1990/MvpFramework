package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.GuideDetailFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/10.
 */
public class GuideDetailActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new GuideDetailFragment();
    }
}
