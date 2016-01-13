package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.IndexFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexActivity extends BaseFragmentActivity {

    @Override
    protected BaseFragment createFragmentForActivity() {
        return new IndexFragment();
    }
}
