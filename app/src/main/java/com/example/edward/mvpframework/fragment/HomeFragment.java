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
    protected View initView() {
        homeView = new HomeView(getContext());
        pagination = new Pagination(10, 0);
        return homeView.getBootView();
    }

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    /**
     * 初始化数据
     */
    private void requestNetwork() {
        pagination.initPagination();
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
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                homeView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
                requestNetwork();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                NetworkHelper.getInstance().sendGetStringRequest(ServerAPI.Home.buildHomeRouteUrl(), pagination.getMap(), HomeFragment.this, "loadmore");
            }
        });
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
                        homeView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        HomeRoute.HomeRouteLists homeRouteLists = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
                        homeView.getAdapter().setRoutes(homeRouteLists.list);
                        pagination.update(homeRouteLists.list.size());
                        break;
                    case "loadmore":
                        HomeRoute.HomeRouteLists homeRouteListsMore = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
                        homeView.getAdapter().appendRoutes(homeRouteListsMore.list);
                        pagination.update(homeRouteListsMore.list.size());
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
