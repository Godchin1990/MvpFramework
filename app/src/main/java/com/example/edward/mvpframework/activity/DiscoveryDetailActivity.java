package com.example.edward.mvpframework.activity;

import com.example.edward.mvpframework.activity.base.BaseFragmentActivity;
import com.example.edward.mvpframework.fragment.DiscoveryDetailFragment;
import com.example.edward.mvpframework.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new DiscoveryDetailFragment();
    }
}
