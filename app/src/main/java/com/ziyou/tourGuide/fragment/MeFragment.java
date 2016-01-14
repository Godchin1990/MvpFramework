package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.LoginActivity;
import com.ziyou.tourGuide.fragment.base.LazyFragment;
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
    }

    @Override
    protected void initData() {
        View view = View.inflate(getContext(), R.layout.item_me_information_unlogin_part,null);
        meView.getInfomationLayoutPart().addView(view);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        meView = new MeView(getContext());
        meView.getInfomationLayout().setOnClickListener(this);
        meView.getInfomationLayoutPart().setOnClickListener(this);
//        meView.getAvatar().setOnClickListener(this);
        return meView.getRootView();
    }

    @Override
    public void onSuccess(String data, String tag) {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_infomation_layout:
                Log.d(TAG,"click me_infomation_layout");
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.me_infomation_layout_part:
                Log.d(TAG,"click me_infomation_layout_part");
                break;
            case R.id.riv_user_avatar:
                Log.d(TAG,"click riv_user_avatar");
                break;
        }
    }
}
