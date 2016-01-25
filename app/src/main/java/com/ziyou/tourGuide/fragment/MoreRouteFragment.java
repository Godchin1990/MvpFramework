package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Pagination;
import com.ziyou.tourGuide.model.RouteCommunity;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.MyRouteView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/25.
 */
public class MoreRouteFragment extends BaseFragment implements StringCallBack<String> {

    private MyRouteView myRouteView;
    private Pagination pagination;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();

    }
    private void initListener() {
        myRouteView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                myRouteView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
                requestNetwork();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                String url = ServerAPI.RouteCommunity.buildGetWriteRouteUrl();
                NetworkHelper.getInstance().sendGetStringRequest(url, pagination.getMap(), MoreRouteFragment.this, "loadmore");
            }
        });
    }

    private void requestNetwork() {
        pagination.initPagination();
        String url = ServerAPI.RouteCommunity.buildGetShareRouteUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myRouteView = new MyRouteView(getContext());
        pagination = new Pagination(10, 0);
        return myRouteView.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RouteCommunity.RouteCommunityList routeCommunityList;
                switch (tag) {
                    case "refresh":
                        myRouteView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        routeCommunityList = gson.fromJson(data, RouteCommunity.RouteCommunityList.class);
                        myRouteView.getAdapter().setRouteCommunities(routeCommunityList.list);
                        pagination.update(routeCommunityList.list.size());
                        break;
                    case "loadmore":
                        routeCommunityList = gson.fromJson(data, RouteCommunity.RouteCommunityList.class);
                        myRouteView.getAdapter().appendRouteCommunities(routeCommunityList.list);
                        pagination.update(routeCommunityList.list.size());
                        if (routeCommunityList.list.isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                myRouteView.getPullToRefreshRecyclerView().onRefreshComplete();
                myRouteView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
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
