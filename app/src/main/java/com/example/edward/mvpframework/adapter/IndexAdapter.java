package com.example.edward.mvpframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import com.example.edward.mvpframework.fragment.DiscoveryFragment;
import com.example.edward.mvpframework.fragment.EmptyFragment;
import com.example.edward.mvpframework.fragment.HomeFragment;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexAdapter extends FragmentPagerAdapter {

    private RadioGroup radioGroup;

    public IndexAdapter(FragmentManager fm,RadioGroup radioGroup) {
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
