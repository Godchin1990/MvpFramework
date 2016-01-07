package com.example.edward.mvpframework.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.edward.mvpframework.fragment.base.LazyFragment;
import com.example.edward.mvpframework.model.HomeBanner;
import com.example.edward.mvpframework.model.HomeRoute;
import com.example.edward.mvpframework.model.HomeTopic;
import com.example.edward.mvpframework.model.Pagination;
import com.example.edward.mvpframework.network.NetworkHelper;
import com.example.edward.mvpframework.network.ServerAPI;
import com.example.edward.mvpframework.network.StringCallBack;
import com.example.edward.mvpframework.view.HomeView;
import com.example.edward.mvpframework.widget.RefreshViewContainer;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/1.
 */
public class HomeFragment extends LazyFragment implements StringCallBack<String> {

    private HomeView homeView;
    private Pagination pagination;

    @Override
    protected void initData() {
        initListener();
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        NetworkHelper.getInstance().sendGetStringRequest(ServerAPI.Home.buildHomeBannerUrl(), null, this, "banner");
        NetworkHelper.getInstance().sendGetStringRequest(ServerAPI.Home.buildHomeTopicUrl(), null, this, "topic");
        NetworkHelper.getInstance().sendGetStringRequest(ServerAPI.Home.buildHomeRouteUrl(), pagination.getMap(), this, "refresh");
    }

    /**
     * 初始化监听器事件
     */
    private void initListener() {

        homeView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pagination = new Pagination();
                pagination.setOffset(0);
                pagination.setLimit(10);
                pagination.clearTotal();
                init();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pagination.setOffset(pagination.getTotal());
                pagination.setLimit(10);
                NetworkHelper.getInstance().sendGetStringRequest(ServerAPI.Home.buildHomeRouteUrl(), pagination.getMap(), HomeFragment.this, "loadmore");
            }
        });
    }

    @Override
    protected View initView() {
        homeView = new HomeView(getContext());
        pagination = new Pagination();
        pagination.setLimit(10);
        pagination.setOffset(0);
        return homeView.getBootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag) {
                    case "banner":
                        HomeBanner.HomeBannerLists homeBannerLists = gson.fromJson(data, HomeBanner.HomeBannerLists.class);
                        homeView.getAdapter().setBanners(homeBannerLists.list);
                        break;
                    case "topic":
                        HomeTopic.HomeTopicLists homeTopicLists = gson.fromJson(data, HomeTopic.HomeTopicLists.class);
                        homeView.getAdapter().setTopics(homeTopicLists.list);
                        break;
                    case "refresh":
                        HomeRoute.HomeRouteLists homeRouteLists = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
                        homeView.getAdapter().setRoutes(homeRouteLists.list);
                        pagination.appendTotal(homeRouteLists.list.size());
                        break;
                    case "loadmore":
                        HomeRoute.HomeRouteLists homeRouteListsMore = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
                        homeView.getAdapter().appendRoutes(homeRouteListsMore.list);
                        pagination.appendTotal(homeRouteListsMore.list.size());
                        if (homeRouteListsMore.list.isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                homeView.getPullToRefreshRecyclerView().onRefreshComplete();
                homeView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
            }
        });
    }

    @Override
    public void onFail() {

    }
}
