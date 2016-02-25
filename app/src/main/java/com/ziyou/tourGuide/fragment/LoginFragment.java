package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.HXHelper;
import com.ziyou.tourGuide.helper.LocationHelper;
import com.ziyou.tourGuide.helper.ShareHelper;
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

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 登陆页面fragment
 * Created by Edward on 16/1/14.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private LoginView loginView;

    @Override
    protected void initData() {
        loginView.getLoginButton().setOnClickListener(this);
        loginView.getActionBarView().getLeftView().setOnClickListener(this);
        loginView.getQQImageView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginView = new LoginView(getContext());
        loginView.getActionBarView().getTitleView().setText(getResources().getString(R.string.quick_login));
        return loginView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.login:
                Log.d(TAG,"click login");
                String username = loginView.getUserNameEdittext().getText().toString();
                String password = loginView.getPasswordEdittext().getText().toString();
                executeLogin(username,password);
                break;
            case R.id.qq:
                ShareHelper.getInstance().oauthLogin(getActivity(),SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                        Log.d(TAG,"complete "+ data.toString());
                    }

                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                        Log.d(TAG, "fail");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Log.d(TAG, "cancel");
                    }
                });
                break;
        }
    }

    /**
     * 执行登录逻辑
     * @param username
     * @param password
     */
    private void executeLogin(String username, String password) {
        //判断用户名和密码是否存在

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
                        loginHX(userInformation);
                        break;
                }
            }
        });


    }

    /**
     * 登录环信
     * @param userInformation
     */
    private void loginHX(UserInformation userInformation) {
        String userName = userInformation.getIm_username();
        String password = userInformation.getIm_password();
        HXHelper.getInstance().login(userName,password);
    }

    /**
     * 通过token获得用户信息
     * @param tokenInfomation
     */
    private void getUserInfomation(TokenInfomation tokenInfomation) {
        Log.d(TAG, "get user information");
        String url = ServerAPI.User.buildUserInfoUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "user_info");
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
            case HXHelper.TAG_HX_LOGIN_SUCCESS:
                // 登陆环信成功
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        getActivity().finish();
                    }
                });
                break;
            case HXHelper.TAG_HX_LOGIN_ERROR:
                // 登陆环信失败
                UserHelper.getInstance().clearUserInformation();
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getContext(), getResources().getString(R.string.login_hx_error), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }
}
