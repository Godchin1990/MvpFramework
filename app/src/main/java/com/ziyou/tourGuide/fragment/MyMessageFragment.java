package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.MyMessageAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.MyMessageViewHolder;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.LocationHelper;
import com.ziyou.tourGuide.model.MyMessage;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.MyMessageView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Edward on 16/1/17.
 */
public class MyMessageFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private MyMessageView<MyMessageAdapter> myMessageView;
    private MyMessageAdapter myMessageAdapter;

    private boolean currentStatus = false;
    private Bundle bundle;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String url = ServerAPI.User.buildGetMessageListUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    private void initListener() {
        myMessageView.getActionBarView().getLeftView().setOnClickListener(this);
        myMessageView.getActionBarView().getRightTextView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myMessageView = new MyMessageView<>(getContext());
        myMessageAdapter = new MyMessageAdapter();
        myMessageView.setAdapter(myMessageAdapter);
        myMessageView.getActionBarView().getTitleView().setText(getResources().getString(R.string.my_message));
        myMessageView.getActionBarView().getRightTextView().setVisibility(View.VISIBLE);
        myMessageView.getActionBarView().getRightTextView().setText(getResources().getString(R.string.delete));
        return myMessageView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.action_bar_right_text:
                clearList();
                break;
        }
    }

    private void clearList() {

    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag) {
                    case "refresh":
                        MyMessage.MyMessageList myMessageList = gson.fromJson(data, MyMessage.MyMessageList.class);
                        myMessageAdapter.setMessages(myMessageList.list);
                        break;
                    case "delete":
                        String id = bundle.getString(MyMessageViewHolder.PARAM_ID);
                        myMessageAdapter.removeItem(id);
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
            case MyMessageViewHolder.TAG_DELETE:
                bundle = (Bundle) clickEvent.getParam();
                myMessageView.showDeleteMessageDialog();
                break;
            case MyMessageView.TAG_DELETE:
                Map<String ,String> parmas = new  HashMap<>() ;
                parmas.put("id", bundle.getString(MyMessageViewHolder.PARAM_ID));
                String url = ServerAPI.User.buildDeleteMessageListUrl();
                NetworkHelper.getInstance().sendPostStringRequest(url,parmas,this,"delete");
                break;
        }

    }
}
