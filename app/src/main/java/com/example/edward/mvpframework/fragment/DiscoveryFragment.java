package com.example.edward.mvpframework.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.fragment.base.LazyFragment;
import com.example.edward.mvpframework.model.City;
import com.example.edward.mvpframework.model.HomeRoute;
import com.example.edward.mvpframework.model.Pagination;
import com.example.edward.mvpframework.network.NetworkHelper;
import com.example.edward.mvpframework.network.ServerAPI;
import com.example.edward.mvpframework.network.StringCallBack;
import com.example.edward.mvpframework.view.DiscoveryView;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryFragment extends LazyFragment implements StringCallBack<String>, View.OnClickListener {

    private DiscoveryView discoveryView;
    private Pagination pagination;

    @Override
    protected void initData() {
        requestNetwork();
        initListener();
        discoveryView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
    }

    private void initListener() {
        discoveryView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                discoveryView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
                requestNetwork();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
//                int type = getArguments().getInt(Const.TOPIC_TYPE, 0);
//                String name = getArguments().getString(Const.TOPIC_NAME);
                String url = ServerAPI.Discovery.buildDiscoveryListUrl("北京");
                NetworkHelper.getInstance().sendGetStringRequest(url, pagination.getMap(), DiscoveryFragment.this, "loadmore");
            }
        });
        discoveryView.getActionBarView().getLeftTextView().setOnClickListener(this);
    }

    private void requestNetwork() {
        String cityUrl = ServerAPI.Discovery.buildCityListUrl();
        NetworkHelper.getInstance().sendGetStringRequest(cityUrl, null, this, "city_list");

    }

    @Override
    protected View initView() {
        discoveryView = new DiscoveryView(getContext());
        String string = getResources().getString(R.string.landmark);
        discoveryView.getActionBarView().getTitleView().setText(string);
        pagination = new Pagination(10, 0);
        return discoveryView.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        Log.d(TAG, data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag) {
                    case "refresh":
                        discoveryView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        HomeRoute.HomeRouteLists homeRouteLists = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
//                        discoveryView.getAdapter().setRoutes(homeRouteLists.list);
                        pagination.update(homeRouteLists.list.size());
                        break;
                    case "loadmore":
//                        HomeRoute.HomeRouteLists homeRouteListsMore = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
//                        discoveryView.getAdapter().appendRoutes(homeRouteListsMore.list);
//                        pagination.update(homeRouteListsMore.list.size());
//                        if (homeRouteListsMore.list.isEmpty()) {
//                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case "city_list":
                        City.CityLists cityLists = gson.fromJson(data, City.CityLists.class);
                        discoveryView.getActionBarView().getLeftTextView().setText(cityLists.list.get(0).getName());
                        discoveryView.getCityListAdapter().setCity(cityLists.list);
                        pagination.initPagination();
                        String url = ServerAPI.Discovery.buildDiscoveryListUrl("北京");
                        NetworkHelper.getInstance().sendGetStringRequest(url, pagination.getMap(), DiscoveryFragment.this, "refresh");
                        return;
                }
                discoveryView.getPullToRefreshRecyclerView().onRefreshComplete();
                discoveryView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
            }
        });
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_bar_left_text:
                Log.d(TAG, "action_bar left click");
                if (discoveryView.getCityListPopupWindow().isShowing()) {
                    discoveryView.getCityListPopupWindow().dismiss();
                }else {
                    discoveryView.getCityListPopupWindow().showAsDropDown(discoveryView.getActionBarView());
                }
                break;
        }
    }
}
