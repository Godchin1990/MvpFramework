package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.RouteCommunityViewPagerAdapter;
import com.ziyou.tourGuide.fragment.EmptyFragment;
import com.ziyou.tourGuide.fragment.MoreRouteFragment;
import com.ziyou.tourGuide.fragment.MyRouteFragment;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IRouteCommunityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * eg:路线社区界面
 * Created by Edward on 16/1/24.
 */
public class RouteCommunityView extends TitleBarContentView implements IRouteCommunityView {

    @Bind(R.id.tab_layout)
    TabLayout tab_layout;
    @Bind(R.id.view_pager)
    ViewPager view_pager;

    private RouteCommunityViewPagerAdapter adapter;

    public RouteCommunityView(Context context,FragmentManager fragmentManager) {
        super(context);
        List<String>  tilteList = new ArrayList<>();
        tilteList.add("我的路线");
        tilteList.add("更多路线");

        /**
         * 新建与tablayout绑定的adapter
         */
        adapter = new RouteCommunityViewPagerAdapter(fragmentManager,tilteList,new RouteCommunityViewPagerAdapter.ViewPagerAdapterCallBack() {
            @Override
            public Fragment getFragment(String title, int index) {
                switch (index){
                    case 0:
                        return new MyRouteFragment();
                    case 1:
                        return new MoreRouteFragment();
                    default:
                        return new EmptyFragment();
                }
            }
        });

        /**
         * 设置viewpager
         */
        getViewPager().setAdapter(adapter);

        /**
         * 设置tablayout
         */
        getTabLayout().setupWithViewPager(getViewPager());
        getTabLayout().setTabMode(TabLayout.MODE_FIXED);
        getTabLayout().setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.black));
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_route_community,null);
        ButterKnife.bind(this, view);
        return view.getRootView();
    }

    @Override
    public TabLayout getTabLayout() {
        return tab_layout;
    }

    @Override
    public ViewPager getViewPager() {
        return view_pager;
    }
}
