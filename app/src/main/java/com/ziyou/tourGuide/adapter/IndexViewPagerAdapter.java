package com.ziyou.tourGuide.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import com.ziyou.tourGuide.fragment.DiscoveryFragment;
import com.ziyou.tourGuide.fragment.EmptyFragment;
import com.ziyou.tourGuide.fragment.HomeFragment;
import com.ziyou.tourGuide.fragment.MeFragment;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexViewPagerAdapter extends FragmentPagerAdapter {

    private RadioGroup radioGroup;

    public IndexViewPagerAdapter(FragmentManager fm, RadioGroup radioGroup) {
        super(fm);
        this.radioGroup = radioGroup;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new DiscoveryFragment();
                break;
            case 3:
                fragment = new MeFragment();
                break;
            default:
                fragment = new EmptyFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return radioGroup.getChildCount();
    }
}
