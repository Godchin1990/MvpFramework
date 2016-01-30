package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.CustomerServiceView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 16/1/30.
 */
public class CustomerServiceFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private CustomerServiceView customerServiceView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {

    }

    private void initListener() {
        customerServiceView.getActionBarView().getLeftView().setOnClickListener(this);
        customerServiceView.getCommitButton().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        customerServiceView = new CustomerServiceView(getContext());
        customerServiceView.getActionBarView().getTitleView().setText(getResources().getString(R.string.customer_service));
        return customerServiceView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.commit:
                String content = customerServiceView.getSuggestCallbackEditText().getText().toString();
                String url = ServerAPI.CustomerService.buildFeedbackUrl();
                Map<String,String> params = new HashMap<>();
                params.put("content",content);
                NetworkHelper.getInstance().sendPostStringRequest(url, params,this,"commit");
                break;
        }
    }

    @Override
    public void onSuccess(String data, String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),getResources().getString(R.string.send_success),Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

    @Override
    public void onFail(int code, String message, Object object) {

    }
}
