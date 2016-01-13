package com.ziyou.tourGuide.view.interfaze;

import android.widget.RadioGroup;

import com.ziyou.tourGuide.adapter.IndexAdapter;
import com.ziyou.tourGuide.view.component.IViewPager;

/**
 * Created by Edward on 16/1/1.
 */
public interface IIndexView extends IViewPager<IndexAdapter> {
    RadioGroup getRadioGroup();
}
