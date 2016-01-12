package com.example.edward.mvpframework.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.activity.base.Const;
import com.example.edward.mvpframework.fragment.base.BaseFragment;
import com.example.edward.mvpframework.model.HomeRoute;
import com.example.edward.mvpframework.model.Pagination;
import com.example.edward.mvpframework.network.NetworkHelper;
import com.example.edward.mvpframework.network.ServerAPI;
import com.example.edward.mvpframework.network.StringCallBack;
import com.example.edward.mvpframework.view.TopicView;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicFragment extends BaseFragment implements StringCallBack<String>, View.OnClickListener {

    private TopicView topicView;
    private Pagination pagination;

    @Override
    protected void initData() {
        initDataFromArguments();
        initListener();
        requestNetwork();
    }

    private void initDataFromArguments() {
        String name = getArguments().getString(Const.TOPIC_NAME);
        topicView.getActionBarView().getTitleView().setText(name);
    }

    private void requestNetwork() {
        pagination.initPagination();
        int type = getArguments().getInt(Const.TOPIC_TYPE, 0);
        String name = getArguments().getString(Const.TOPIC_NAME);

        String routeUrl = ServerAPI.Topic.buildTopicRouteUrl(type,name);
        NetworkHelper.getInstance().sendGetStringRequest(routeUrl, pagination.getMap(), this, "refresh");
    }

    private void initListener() {
        topicView.getActionBarView().getLeftView().setOnClickListener(this);
        topicView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                topicView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
                requestNetwork();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                int type = getArguments().getInt(Const.TOPIC_TYPE, 0);
                String name = getArguments().getString(Const.TOPIC_NAME);
                String routeUrl = ServerAPI.Topic.buildTopicRouteUrl(type,name);
                NetworkHelper.getInstance().sendGetStringRequest(routeUrl, pagination.getMap(), TopicFragment.this, "loadmore");
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        topicView = new TopicView(getContext());
        pagination = new Pagination(10, 0);
        return topicView.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Log.d(TAG,data);
                switch (tag) {
                    case "refresh":
                        topicView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        HomeRoute.HomeRouteLists homeRouteLists = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
                        topicView.getAdapter().setRoutes(homeRouteLists.list);
                        pagination.update(homeRouteLists.list.size());
                        break;
                    case "loadmore":
                        HomeRoute.HomeRouteLists homeRouteListsMore = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
                        topicView.getAdapter().appendRoutes(homeRouteListsMore.list);
                        pagination.update(homeRouteListsMore.list.size());
                        if (homeRouteListsMore.list.isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                topicView.getPullToRefreshRecyclerView().onRefreshComplete();
                topicView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
            }
        });
    }

    @Override
    public void onFail() {

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
