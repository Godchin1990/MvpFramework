package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.IndexViewPagerAdapter;
import com.ziyou.tourGuide.view.base.BaseView;
import com.ziyou.tourGuide.view.interfaze.IIndexView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexView extends BaseView implements IIndexView, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    private final IndexViewPagerAdapter adapter;

    public IndexView(Context context, FragmentManager fragmentManager) {
        super(context);
        adapter = new IndexViewPagerAdapter(fragmentManager, radioGroup);
        viewpager.setAdapter(adapter);
    }

    @Override
    public ViewPager getViewPager() {
        return viewpager;
    }

    @Override
    public IndexViewPagerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_index,null);
        ButterKnife.bind(this, view);
        radioGroup.setOnCheckedChangeListener(this);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(3);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View view = group.findViewById(checkedId);
        int radioGroupPosition = group.indexOfChild(view);
        int viewPagerPosition = viewpager.getCurrentItem();
        if(radioGroupPosition != viewPagerPosition){
            viewpager.setCurrentItem(radioGroupPosition);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
        radioButton.performClick();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
