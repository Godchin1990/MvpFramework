package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.RouteCommunityFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/24.
 */
public class RouteCommunityActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new RouteCommunityFragment();
    }
}
