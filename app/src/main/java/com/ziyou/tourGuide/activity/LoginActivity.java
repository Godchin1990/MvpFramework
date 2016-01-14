package com.ziyou.tourGuide.activity;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.LoginFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/14.
 */
public class LoginActivity extends BaseFragmentActivity{
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new LoginFragment();
    }
}
