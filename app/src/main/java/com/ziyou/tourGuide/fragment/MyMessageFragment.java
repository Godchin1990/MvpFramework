package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.MyMessageAdapter;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.MyMessage;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.RefreshRecyclerView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/17.
 */
public class MyMessageFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private RefreshRecyclerView<MyMessageAdapter> myMessageView;
    private MyMessageAdapter myMessageAdapter;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String url = ServerAPI.User.buildGetMessageListUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url,null,this,"refresh");
    }

    private void initListener() {
        myMessageView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myMessageView = new RefreshRecyclerView<>(getContext());
        myMessageAdapter = new MyMessageAdapter();
        myMessageView.setAdapter(myMessageAdapter);
        myMessageView.getActionBarView().getTitleView().setText(getResources().getString(R.string.my_message));
        return myMessageView.getRootView();
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
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag){
                    case "refresh":
                        MyMessage.MyMessageList myMessageList = gson.fromJson(data, MyMessage.MyMessageList.class);
                        myMessageAdapter.setMessages(myMessageList.list);
                        break;
                }
                myMessageView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
            }
        });
    }

    @Override
    public void onFail(int code, String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
