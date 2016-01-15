package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.TokenHelper;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.view.SettingView;

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
                UserHelper.getInstance().clearUserInformation();
                TokenHelper.getInstance().clearTokenInformation();
                getActivity().finish();
                break;
        }
    }
}
