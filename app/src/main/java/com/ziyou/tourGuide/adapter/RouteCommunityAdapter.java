package com.ziyou.tourGuide.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/24.
 */
public class RouteCommunityAdapter extends FragmentPagerAdapter {

    ViewPagerAdapterCallBack callBack;

    List<String> list = new ArrayList<>();

    public interface ViewPagerAdapterCallBack{
        Fragment getFragment(String title,int index);
    }

    public RouteCommunityAdapter(FragmentManager fm,List<String> list,ViewPagerAdapterCallBack callBack) {
        super(fm);
        this.list = list;
        this.callBack = callBack;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return callBack.getFragment(list.get(position),position);
    }

}
