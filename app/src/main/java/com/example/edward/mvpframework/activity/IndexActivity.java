package com.example.edward.mvpframework.activity;

import com.example.edward.mvpframework.activity.base.BaseFragmentActivity;
import com.example.edward.mvpframework.fragment.IndexFragment;
import com.example.edward.mvpframework.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexActivity extends BaseFragmentActivity {

    @Override
    protected BaseFragment createFragmentForActivity() {
        return new IndexFragment();
    }
}
