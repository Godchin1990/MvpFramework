package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.ReceiveRouteAdapter;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Pagination;
import com.ziyou.tourGuide.model.ReceiveRoute;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.ReceiveRouteView;
import com.ziyou.tourGuide.view.RefreshRecyclerView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/21.
 */
public class ReceiveRouteFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private RefreshRecyclerView<ReceiveRouteAdapter> receiveRouteView;
    private Pagination pagination;
    private ReceiveRouteAdapter receiveRouteAdapter;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        pagination.initPagination();
        String url = ServerAPI.Route.buildGetFetchRouteslUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url,pagination.getMap(),this,"refresh");
    }

    private void initListener() {
        receiveRouteView.getActionBarView().getLeftView().setOnClickListener(this);
        receiveRouteView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                receiveRouteView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
                requestNetwork();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                String url = ServerAPI.Route.buildGetFetchRouteslUrl();
                NetworkHelper.getInstance().sendGetStringRequest(url, pagination.getMap(), ReceiveRouteFragment.this, "loadmore");
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        receiveRouteView = new ReceiveRouteView<>(getContext());
        receiveRouteAdapter = new ReceiveRouteAdapter();
        receiveRouteView.setAdapter(receiveRouteAdapter);

        pagination = new Pagination(10, 0);
        receiveRouteView.getActionBarView().getTitleView().setText(getResources().getString(R.string.receive_route));
        return receiveRouteView.getRootView();
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
                Log.d(TAG,data);
                Gson gson = new Gson();
                ReceiveRoute.ReceiveRouteList list = null;
                switch (tag){
                    case "refresh":
                        receiveRouteView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        list = gson.fromJson(data, ReceiveRoute.ReceiveRouteList.class);
                        receiveRouteAdapter.setReceiveRoutes(list.list);
                        pagination.update(list.list.size());
                        break;
                    case "loadmore":
                        list = gson.fromJson(data, ReceiveRoute.ReceiveRouteList.class);
                        receiveRouteAdapter.appendReceiveRoutes(list.list);
                        pagination.update(list.list.size());
                        if (list.list.isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                receiveRouteView.getPullToRefreshRecyclerView().onRefreshComplete();
                receiveRouteView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
            }
        });
    }

    @Override
    public void onFail(int code, final String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,message);
            }
        });
    }
}
