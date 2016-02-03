package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.VoucherAdapter;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Voucher;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.VoucherView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/2/3.
 */
public class VoucherFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private VoucherView<VoucherAdapter> view;
    private VoucherAdapter adapter;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
        view.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
    }

    private void requestNetwork() {
        String url = ServerAPI.Voucher.buildGetCouponListUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    private void initListener() {
        view.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new VoucherView<>(getContext());
        adapter = new VoucherAdapter();
        view.setAdapter(adapter);
        view.getActionBarView().getTitleView().setText(getResources().getString(R.string.discount_coupon));
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
    public void onSuccess(final String data, final String tag) {
        Log.d(TAG,data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag){
                    case "refresh":
                        Voucher.VoucherList voucherList = gson.fromJson(data, Voucher.VoucherList.class);
                        adapter.setVouchers(voucherList.list);
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
