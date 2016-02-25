package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.CustomerServiceActivity;
import com.ziyou.tourGuide.activity.UserInformationActivity;
import com.ziyou.tourGuide.activity.GuiderAreaActivity;
import com.ziyou.tourGuide.activity.LoginActivity;
import com.ziyou.tourGuide.activity.MyMessageActivity;
import com.ziyou.tourGuide.activity.MyTourActivity;
import com.ziyou.tourGuide.activity.MyWalletActivity;
import com.ziyou.tourGuide.activity.SettingActivity;
import com.ziyou.tourGuide.command.SimpleDraweeViewCommand;
import com.ziyou.tourGuide.command.base.Command;
import com.ziyou.tourGuide.fragment.base.LazyFragment;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.model.UserInformation;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.MeView;

/**
 * Created by Edward on 16/1/12.
 */
public class MeFragment extends LazyFragment implements StringCallBack<String>, View.OnClickListener {

    private MeView meView;

    @Override
    public void onResume() {
        super.onResume();
        //用来时刻刷新当前页面的数据和显示状态
        //每次回到这个页面,都会刷新
        //fragment 作为presenter,把model传递给view,供view显示和刷新
        Log.d(TAG, "MeFragment onResume()");
        //刷新本地数据
        UserInformation userInformation = UserHelper.getInstance().getUserInformation();
        setInfomation(userInformation);

        if(UserHelper.getInstance().isLogin()){
            //请求网络,获取最新的数据
            String url = ServerAPI.User.buildUserInfoUrl();
            NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
        }
    }

    @Override
    protected void initData() {
        View view = View.inflate(getContext(), R.layout.item_me_information_unlogin_part, null);
        meView.getInfomationLayoutPart().addView(view);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        meView = new MeView(getContext());
        meView.getAvatar().setOnClickListener(this);
        meView.getInfomationLayout().setOnClickListener(this);
        meView.getMyMessage().setOnClickListener(this);
        meView.getGuiderArea().setOnClickListener(this);
        meView.getMyTour().setOnClickListener(this);
        meView.getMyWallet().setOnClickListener(this);
        meView.getSetting().setOnClickListener(this);
        meView.getCustomerService().setOnClickListener(this);
        return meView.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag) {
                    case "refresh":
                        UserInformation userInformation = gson.fromJson(data, UserInformation.class);
                        setInfomation(userInformation);
                        UserHelper.getInstance().setUserInformation(userInformation);
                        break;
                }
            }
        });
    }

    @Override
    public void onFail(int code, String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //说明用户信息得不到
                //清除用户信息
                Log.d(TAG, "error");
                setInfomation(null);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.me_infomation_layout:
                Log.d(TAG, "click me_infomation_layout");
                if (UserHelper.getInstance().isLogin()) {
                    intent = new Intent(getContext(), UserInformationActivity.class);
                    getContext().startActivity(intent);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.riv_user_avatar:
                Log.d(TAG, "click riv_user_avatar");
                break;
            case R.id.my_message:
                Log.d(TAG, "click my_message");
                if (UserHelper.getInstance().isLogin()) {
                    intent = new Intent(getContext(), MyMessageActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.guider_area:
                Log.d(TAG, "click guider_area");
                if (UserHelper.getInstance().isLogin()) {
                    UserInformation userInformation = UserHelper.getInstance().getUserInformation();
                    if(userInformation.getType()==0){
                        // 进入达人社区页面
                        intent = new Intent(getContext(), GuiderAreaActivity.class);
                        startActivity(intent);
                    }else {
                        // 申请达人页面
                        intent = new Intent(getContext(), GuiderAreaActivity.class);
                        startActivity(intent);
                    }

                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.my_tour:
                Log.d(TAG, "click my_tour");
                if (UserHelper.getInstance().isLogin()) {
                    intent = new Intent(getContext(), MyTourActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.my_wallet:
                Log.d(TAG, "click my_tour");
                if (UserHelper.getInstance().isLogin()) {
                    intent = new Intent(getContext(), MyWalletActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.customer_service:
                Log.d(TAG, "click customer_service");
                intent = new Intent(getContext(), CustomerServiceActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.setting:
                Log.d(TAG, "click setting");
                intent = new Intent(getContext(), SettingActivity.class);
                getContext().startActivity(intent);
                break;
            default:
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 设置用户信息
     *
     * @param userInformation
     */
    private void setInfomation(UserInformation userInformation) {
        meView.getInfomationLayoutPart().removeAllViews();
        if (userInformation == null) {
            //刷新头像
            Command commandForCover = new SimpleDraweeViewCommand(meView.getAvatar(), "");
            commandForCover.execute();
            //刷新Layout
            meView.getInfomationLayoutPart().addView(meView.getUnLoginView());
        } else {
            //刷新头像
            Command commandForCover = new SimpleDraweeViewCommand(meView.getAvatar(), userInformation.getQiniu_avatar());
            commandForCover.execute();
            TextView user_name = (TextView) meView.getUserLoginView().findViewById(R.id.user_name);
            TextView guider_type = (TextView) meView.getUserLoginView().findViewById(R.id.guider_type);
            TextView user_id = (TextView) meView.getUserLoginView().findViewById(R.id.user_id);

            user_name.setText(userInformation.getNickname());
            switch (userInformation.getType()) {
                case 0:
                    guider_type.setText("游客");
                    break;
                case 1:
                    guider_type.setText("达人");
                    break;
                case 2:
                    guider_type.setText("导游");
                    break;
                default:
                    guider_type.setText("未知");
                    break;
            }
            user_id.setText(String.format(getResources().getString(R.string.id), userInformation.getIdentity_num()));
            //刷新Layout
            meView.getInfomationLayoutPart().addView(meView.getUserLoginView());
        }

        if(UserHelper.getInstance().isLogin()){
            meView.getAvatar().setClickable(true);
        }else {
            meView.getAvatar().setClickable(false);
        }
    }
}
