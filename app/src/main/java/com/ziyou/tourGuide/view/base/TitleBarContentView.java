package com.ziyou.tourGuide.view.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.widget.MyActionBar;

/**
 * Created by Edward on 16/1/7.
 */
public abstract class TitleBarContentView extends BaseView implements ITitleBarContentView {

    protected FrameLayout content;
    private MyActionBar actionBar;

    public TitleBarContentView(Context context) {
        super(context);
        content.removeAllViews();
        content.addView(setContentView());
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.layout_titlebar_content, null);
        actionBar = (MyActionBar) view.findViewById(R.id.action_bar);
        initContent(view);
        initActionBar();
        return view;
    }

    /**
     * 初始化内容
     * @param view
     */
    private void initContent(View view) {
        content = (FrameLayout) view.findViewById(R.id.content);
    }

    /**
     * 初始化头
     */
    protected void initActionBar() {
        getActionBarView().getLeftView().setImageResource(R.mipmap.action_bar_back_black);
    }


    @Override
    public MyActionBar getActionBarView() {
        return actionBar;
    }

    public FrameLayout getContentView(){
        return content;
    }

}
