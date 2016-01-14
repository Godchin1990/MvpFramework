package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.LoginView;

/**
 * 登陆页面fragment
 * Created by Edward on 16/1/14.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private LoginView loginView;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginView = new LoginView(getContext());
        loginView.getActionBarView().getTitleView().setText(getResources().getString(R.string.quick_login));
        loginView.getActionBarView().getLeftView().setOnClickListener(this);
        return loginView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
        }
    }
}
