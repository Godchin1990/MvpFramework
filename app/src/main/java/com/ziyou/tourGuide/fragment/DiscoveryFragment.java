package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.DiscoveryAdapter;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.fragment.base.LazyFragment;
import com.ziyou.tourGuide.helper.LocationHelper;
import com.ziyou.tourGuide.model.City;
import com.ziyou.tourGuide.model.Discovery;
import com.ziyou.tourGuide.model.Pagination;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.DiscoveryView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryFragment extends LazyFragment implements StringCallBack<String>, View.OnClickListener {

    private DiscoveryView<DiscoveryAdapter> discoveryView;
    private Pagination pagination;
    private DiscoveryAdapter adapter;

    @Override
    protected void initData() {
        requestNetwork();
        initListener();
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
                if (TextUtils.isEmpty(discoveryView.getActionBarView().getLeftTextView().getText())) {
                    requestNetwork();
                } else {
                    requestLandmarkInfo(discoveryView.getActionBarView().getLeftTextView().getText().toString());
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
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
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        discoveryView = new DiscoveryView<>(getContext());
        adapter = new DiscoveryAdapter();
        discoveryView.setAdapter(adapter);

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
                        Discovery.DiscoveryLists discoveryLists = gson.fromJson(data, Discovery.DiscoveryLists.class);
                        adapter.setDiscovery(discoveryLists.list);
                        pagination.update(discoveryLists.list.size());
                        break;
                    case "loadmore":
                        Discovery.DiscoveryLists discoveryListsMore = gson.fromJson(data, Discovery.DiscoveryLists.class);
                        adapter.appendDiscovery(discoveryListsMore.list);
                        pagination.update(discoveryListsMore.list.size());
                        if (discoveryListsMore.list.isEmpty()) {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "city_list":
                        City.CityLists cityLists = gson.fromJson(data, City.CityLists.class);
                        discoveryView.getActionBarView().getLeftTextView().setText(cityLists.list.get(0).getName());
                        discoveryView.getCityListAdapter().setCity(cityLists.list);
                        requestLandmarkInfo(cityLists.list.get(0).getName());
                        return;
                }
                discoveryView.getPullToRefreshRecyclerView().onRefreshComplete();
                discoveryView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
            }
        });
    }

    void requestLandmarkInfo(String name){
        pagination.initPagination();
        String url = ServerAPI.Discovery.buildDiscoveryListUrl(name);
        NetworkHelper.getInstance().sendGetStringRequest(url, pagination.getMap(), DiscoveryFragment.this, "refresh");
    }


    @Override
    public void onFail(int code, String message, Object object) {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationHelper.getInstance().onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ClickEvent clickEvent){
        Log.d(TAG, clickEvent.toString());
        switch (clickEvent.getTag()){
            case DiscoveryView.TAG_CHECK_CITY:
                String param = (String) clickEvent.getParam();
                discoveryView.getCityListPopupWindow().dismiss();
                discoveryView.getActionBarView().getLeftTextView().setText(param);
                requestLandmarkInfo(param);
                break;
        }
    }

}
