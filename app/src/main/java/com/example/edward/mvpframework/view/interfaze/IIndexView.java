package com.example.edward.mvpframework.view.interfaze;

import android.widget.RadioGroup;

import com.example.edward.mvpframework.adapter.IndexAdapter;
import com.example.edward.mvpframework.view.component.IViewPager;

/**
 * Created by Edward on 16/1/1.
 */
public interface IIndexView extends IViewPager<IndexAdapter> {
    RadioGroup getRadioGroup();
}
