package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.DiscoveryDetailFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new DiscoveryDetailFragment();
    }
}
