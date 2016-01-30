package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.DataCleanHelper;
import com.ziyou.tourGuide.helper.HXHelper;
import com.ziyou.tourGuide.helper.LocationHelper;
import com.ziyou.tourGuide.helper.TokenHelper;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.view.SettingView;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Edward on 16/1/15.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private SettingView settingView;

    @Override
    public void onResume() {
        super.onResume();
        if(UserHelper.getInstance().isLogin()){
            settingView.getUnloginButton().setVisibility(View.VISIBLE);
        }else {
            settingView.getUnloginButton().setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        initListener();
    }

    private void initListener() {
        settingView.getActionBarView().getLeftView().setOnClickListener(this);
        settingView.getUnloginButton().setOnClickListener(this);
        settingView.getClearCache().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingView = new SettingView(getContext());
        settingView.getActionBarView().getTitleView().setText(getResources().getString(R.string.more));
        return settingView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.un_login:
                settingView.showCancelLoginDialog();
                break;
            case R.id.clear_cache:
                settingView.showClearCacheDialog();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationHelper.getInstance().onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ClickEvent clickEvent){
        switch (clickEvent.getTag()){
            case SettingView.TAG_CANCEL_LOGIN:
                // 先登出环信
                HXHelper.getInstance().logout();
                break;
            case SettingView.TAG_CLEAR_CACHE:
                //清除缓存点击后执行
                DataCleanHelper.getInstance().cleanAppCache(getContext());
                break;
            case HXHelper.TAG_HX_LOGOUT_SUCCESS:
                UserHelper.getInstance().clearUserInformation();
                TokenHelper.getInstance().clearTokenInformation();
                getActivity().finish();
                break;
        }

    }
}
