package com.example.edward.mvpframework.activity;

import com.example.edward.mvpframework.activity.base.BaseFragmentActivity;
import com.example.edward.mvpframework.fragment.BannerFragment;
import com.example.edward.mvpframework.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/7.
 */
public class BannerActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new BannerFragment();
    }
}