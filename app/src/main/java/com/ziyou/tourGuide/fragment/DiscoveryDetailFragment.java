package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.DiscoveryDetail;
import com.ziyou.tourGuide.model.Pagination;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.DiscoveryDetailView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailFragment extends BaseFragment implements StringCallBack<String>, View.OnClickListener {

    private DiscoveryDetailView discoveryDetailView;
    private Pagination pagination;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        pagination.initPagination();
        String id = getArguments().getString(Const.DISCOVERY_ID);
        Log.d(TAG,id);
        String url = ServerAPI.DiscoveryDetail.buildDestRouteUrl(id);
        NetworkHelper.getInstance().sendGetStringRequest(url,pagination.getMap(), this, "refresh");
    }

    private void initListener() {
        discoveryDetailView.getActionBarView().getLeftView().setOnClickListener(this);
        discoveryDetailView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                discoveryDetailView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
                requestNetwork();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                String id = getArguments().getString(Const.DISCOVERY_ID);
                String url = ServerAPI.DiscoveryDetail.buildDestRouteUrl(id);
                NetworkHelper.getInstance().sendGetStringRequest(url,pagination.getMap(), DiscoveryDetailFragment.this, "loadmore");
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        discoveryDetailView = new DiscoveryDetailView(getContext());
        pagination = new Pagination(10, 0);
        return discoveryDetailView.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, data);
                Gson gson = new Gson();
                switch (tag) {
                    case "refresh":
                        discoveryDetailView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        DiscoveryDetail discoveryDetailLists = gson.fromJson(data, DiscoveryDetail.class);
                        //设置标题头
                        discoveryDetailView.getActionBarView().getTitleView().setText(discoveryDetailLists.getBanner().getName());
                        //设置路线
                        discoveryDetailView.getAdapter().setRoutes(discoveryDetailLists.getList());
                        //设置banner
                        discoveryDetailView.getAdapter().setBanner(discoveryDetailLists.getBanner());
                        pagination.update(discoveryDetailLists.getList().size());
                        break;
                    case "loadmore":
                        DiscoveryDetail discoveryDetailListsMore = gson.fromJson(data, DiscoveryDetail.class);
                        discoveryDetailView.getAdapter().appendDiscoveryDetail(discoveryDetailListsMore.getList());
                        pagination.update(discoveryDetailListsMore.getList().size());
                        if (discoveryDetailListsMore.getList().isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                discoveryDetailView.getPullToRefreshRecyclerView().onRefreshComplete();
                discoveryDetailView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);

            }
        });
    }

    @Override
    public void onFail(int code, String message, Object object) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
        }
    }
}
