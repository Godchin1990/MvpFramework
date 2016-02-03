package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.VoucherActivity;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Wallet;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.MyWalletView;

/**
 * Created by Edward on 16/1/18.
 */
public class MyWalletFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private MyWalletView myWalletView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String url = ServerAPI.Wallet.buildMyWalletUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url,null,this,"refresh");
    }

    private void initListener() {
        myWalletView.getActionBarView().getLeftView().setOnClickListener(this);
        myWalletView.getActionBarView().getRightTextView().setOnClickListener(this);
        myWalletView.getWithdrawCashExplainView().setOnClickListener(this);
        myWalletView.getDiscountCouponView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myWalletView = new MyWalletView(getContext());
        myWalletView.getActionBarView().getTitleView().setText(getResources().getString(R.string.my_wallet));
        return myWalletView.getRootView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.action_bar_right_text:
                //TODO 提现页面
                break;
            case R.id.withdraw_cash_explain:
                //TODO 体现说明
                break;
            case R.id.discount_coupon:
                intent = new Intent(getContext(), VoucherActivity.class);
                startActivity(intent);
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
                        Wallet wallet = gson.fromJson(data, Wallet.class);
                        myWalletView.getCashView().setText(wallet.getAmount()+"");
                        myWalletView.getCanWithdrawCashView().setText(wallet.getTotal_amount() + "");
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

            }
        });
    }
}
