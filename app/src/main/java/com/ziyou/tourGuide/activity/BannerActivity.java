package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.BannerFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/7.
 */
public class BannerActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new BannerFragment();
    }
}
