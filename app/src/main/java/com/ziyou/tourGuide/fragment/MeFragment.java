package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.AmendUserInformationActivity;
import com.ziyou.tourGuide.activity.GuiderAreaActivity;
import com.ziyou.tourGuide.activity.LoginActivity;
import com.ziyou.tourGuide.activity.MyMessageActivity;
import com.ziyou.tourGuide.activity.SettingActivity;
import com.ziyou.tourGuide.fragment.base.LazyFragment;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.model.UserInformation;
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
        UserInformation userInformation = UserHelper.getInstance().getUserInformation();
        meView.setInfomationLayoutPart(userInformation);
        if(UserHelper.getInstance().isLogin()){
            meView.getAvatar().setClickable(true);
        }else {
            meView.getAvatar().setClickable(false);
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
        meView.getInfomationLayoutPart().setOnClickListener(this);
        meView.getMyMessage().setOnClickListener(this);
        meView.getGuiderArea().setOnClickListener(this);
        meView.getSetting().setOnClickListener(this);
        return meView.getRootView();
    }

    @Override
    public void onSuccess(String data, String tag) {

    }

    @Override
    public void onFail(int code, String message, Object object) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.me_infomation_layout:
                Log.d(TAG, "click me_infomation_layout");
                if (UserHelper.getInstance().isLogin()) {
                    intent = new Intent(getContext(), AmendUserInformationActivity.class);
                    getContext().startActivity(intent);
                }else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    getContext().startActivity(intent);
                }

                break;
            case R.id.me_infomation_layout_part:
                Log.d(TAG, "click me_infomation_layout_part");
                break;
            case R.id.riv_user_avatar:
                Log.d(TAG, "click riv_user_avatar");
                break;
            case R.id.my_message:
                Log.d(TAG, "click my_message");
                if(UserHelper.getInstance().isLogin()){
                    intent = new Intent(getContext(),MyMessageActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.guider_area:
                Log.d(TAG, "click guider_area");
                if(UserHelper.getInstance().isLogin()){
                    intent = new Intent(getContext(), GuiderAreaActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.setting:
                Log.d(TAG, "click setting");
                intent = new Intent(getContext(), SettingActivity.class);
                getContext().startActivity(intent);
                break;
            default:
                intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
