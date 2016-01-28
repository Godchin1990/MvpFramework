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
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.adapter.recyclerview.TopicAdapter;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.HomeRoute;
import com.ziyou.tourGuide.model.Pagination;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.RefreshRecyclerView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicFragment extends BaseFragment implements StringCallBack<String>, View.OnClickListener {

//    private TopicView topicView;
    private Pagination pagination;
    private RefreshRecyclerView<TopicAdapter> refreshRecyclerView;
    private TopicAdapter topicAdapter;

    @Override
    protected void initData() {
        initDataFromArguments();
        initListener();
        requestNetwork();
    }

    private void initDataFromArguments() {
        String name = getArguments().getString(Const.TOPIC_NAME);
        refreshRecyclerView.getActionBarView().getTitleView().setText(name);
    }

    private void requestNetwork() {
        pagination.initPagination();
        int type = getArguments().getInt(Const.TOPIC_TYPE, 0);
        String name = getArguments().getString(Const.TOPIC_NAME);

        String routeUrl = ServerAPI.Topic.buildTopicRouteUrl(type,name);
        NetworkHelper.getInstance().sendGetStringRequest(routeUrl, pagination.getMap(), this, "refresh");
    }

    private void initListener() {
        refreshRecyclerView.getActionBarView().getLeftView().setOnClickListener(this);
        refreshRecyclerView.getPullToRefreshRecyclerView().setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                /**
                 * 添加这一行,是为了避免多次下拉刷新后,立马拉到最底部,开始上拉加载,造成分页数据获取出现断层
                 * 即在上拉加载成功后,下拉刷新的数据过来了,导致游标出现错误
                 * 这只是折中的解决方案
                 * 并不完美
                 */
                refreshRecyclerView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.DISABLED);
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
        refreshRecyclerView = new RefreshRecyclerView<>(getContext());
        topicAdapter = new TopicAdapter();
        refreshRecyclerView.setAdapter(topicAdapter);
        pagination = new Pagination(10, 0);
        return refreshRecyclerView.getRootView();
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
                        refreshRecyclerView.getPullToRefreshRecyclerView().setMode(PullToRefreshBase.Mode.BOTH);
                        HomeRoute.HomeRouteLists homeRouteLists = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
//                        topicView.getAdapter().setRoutes(homeRouteLists.list);
                        topicAdapter.setRoutes(homeRouteLists.list);
                        pagination.update(homeRouteLists.list.size());
                        break;
                    case "loadmore":
                        HomeRoute.HomeRouteLists homeRouteListsMore = gson.fromJson(data, HomeRoute.HomeRouteLists.class);
//                        topicView.getAdapter().appendRoutes(homeRouteListsMore.list);
                        topicAdapter.appendRoutes(homeRouteListsMore.list);
                        pagination.update(homeRouteListsMore.list.size());
                        if (homeRouteListsMore.list.isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                refreshRecyclerView.getPullToRefreshRecyclerView().onRefreshComplete();
                refreshRecyclerView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
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
