package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.GuiderAreaFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/18.
 */
public class GuiderAreaActivity extends BaseFragmentActivity{
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new GuiderAreaFragment();
    }
}
