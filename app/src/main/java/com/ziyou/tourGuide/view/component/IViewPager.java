package com.ziyou.tourGuide.view.component;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Edward on 16/1/4.
 */
public interface IViewPager<T extends PagerAdapter>  {
    ViewPager getViewPager();
    T getAdapter();
}
