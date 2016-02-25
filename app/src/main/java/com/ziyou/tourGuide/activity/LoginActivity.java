package com.ziyou.tourGuide.activity;

import android.content.Intent;

import com.umeng.socialize.UMShareAPI;
import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.fragment.LoginFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * 登录页面activity
 * Created by Edward on 16/1/14.
 */
public class LoginActivity extends BaseFragmentActivity{
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new LoginFragment();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
