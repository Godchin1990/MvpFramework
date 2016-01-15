package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.TokenHelper;
import com.ziyou.tourGuide.helper.UUIDHelpter;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.model.TokenInfomation;
import com.ziyou.tourGuide.model.UserInformation;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * 登陆页面fragment
 * Created by Edward on 16/1/14.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private LoginView loginView;

    @Override
    protected void initData() {
        loginView.getLoginButton().setOnClickListener(this);
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
            case R.id.login:
                executeLogin();
                break;
        }
    }

    private void executeLogin() {
        //判断用户名和密码是否存在
        String username = loginView.getUserNameEdittext().getText().toString();
        String password = loginView.getPasswordEdittext().getText().toString();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(getContext(),getResources().getString(R.string.phone_number_empty),Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getContext(),getResources().getString(R.string.password_empty),Toast.LENGTH_SHORT).show();
            return;
        }
        //登陆
        String url = ServerAPI.User.buildPasswordLoginUrl();
        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("client_id", UUIDHelpter.getInstance().getUUID(getContext()));
        params.put("client_info","ANDROID");
        NetworkHelper.getInstance().sendPostStringRequest(url, params, this, "login");
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag) {
                    case "login":
                        Log.d(TAG, "login success");
                        TokenInfomation tokenInfomation = gson.fromJson(data, TokenInfomation.class);
                        TokenHelper.getInstance().setTokenInformation(getContext(), tokenInfomation);
                        getUserInfomation(tokenInfomation);
                        break;
                    case "user_info":
                        Log.d(TAG, "user info success");
                        UserInformation userInformation = gson.fromJson(data, UserInformation.class);
                        UserHelper.getInstance().setUserInformation(userInformation);
                        getActivity().finish();
                        break;
                }
            }
        });


    }

    /**
     * 通过token获得用户信息
     * @param tokenInfomation
     */
    private void getUserInfomation(TokenInfomation tokenInfomation) {
        Log.d(TAG,"get user information");
        String url = ServerAPI.User.buildUserInfoUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url,null,this,"user_info");
    }

    @Override
    public void onFail(int code, final String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
