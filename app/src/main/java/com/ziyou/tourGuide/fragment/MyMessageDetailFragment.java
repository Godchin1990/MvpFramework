package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.MyMessageDetailView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 16/2/3.
 */
public class MyMessageDetailFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private MyMessageDetailView view;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void initListener() {
        view.getActionBarView().getLeftView().setOnClickListener(this);
    }

    private void requestNetwork() {
        String messageId = getArguments().getString(Const.MESSAGE_ID);
        Map<String,String> params = new HashMap<>();
        params.put("id",messageId);
        String url = ServerAPI.User.buildReadMessageUrl();
        NetworkHelper.getInstance().sendPostStringRequest(url,params,this,"refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MyMessageDetailView(getContext());
        view.getDateTextView().setText(getArguments().getString(Const.MESSAGE_DATE));
        view.getContentTextView().setText(getArguments().getString(Const.MESSAGE_CONTENT));
        view.getActionBarView().getTitleView().setText(getResources().getString(R.string.message_detail));
        return view.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onSuccess(final String data, String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"success" + data);
                //TODO 通知上一个页面这条消息已经阅读
            }
        });
    }

    @Override
    public void onFail(int code, final String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"error " + message);
            }
        });
    }
}
