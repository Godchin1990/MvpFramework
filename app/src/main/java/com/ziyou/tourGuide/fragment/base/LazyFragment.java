package com.ziyou.tourGuide.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 包装类对BaseFragment做一层包装
 * 仅仅对BaseFragment做了一层加工,适用于ViewPager的fragment
 */
public abstract class LazyFragment extends BaseFragment {

    protected boolean isVisible=false;
    protected boolean isPrepared=false;
    protected boolean isLoadFinish=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isPrepared=true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        isLoadFinish = true;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.v(getTAG(), "lazyfragment setUserVisibleHint");
        if (getUserVisibleHint()){
            isVisible=true;
            onVisible();
        }else{
            isVisible=false;
            onInVisible();
        }

    }
    public void onVisible(){
//        Log.v(getTAG(), "哈哈，layzyfragment我已经可见了，准备加载");
        if(!isPrepared) {
            return;
        }
        if(!isLoadFinish){
            initData();
        }

    }

    public void onInVisible(){
//        Log.v(getTAG(), "layzyfragmrent我还没准备好呢");

    }

}
