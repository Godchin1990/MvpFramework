package com.ziyou.tourGuide.view.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.widget.MyActionBar;

/**
 * Created by Edward on 16/1/7.
 */
public abstract class TitleBarBottomContentView extends BaseView implements ITitleBarBottomContentView {

    protected MyActionBar actionBar;
    protected FrameLayout content;
    protected FrameLayout layout_bottom;

    public TitleBarBottomContentView(Context context) {
        super(context);
        content.removeAllViews();
        content.addView(setContentView());
        layout_bottom.removeAllViews();
        layout_bottom.addView(setBottomLayout());
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.layout_titlebar_bottom_content, null);
        initContent(view);
        initBottomLayout(view);
        initActionBar(view);
        return view;
    }

    private void initBottomLayout(View view) {
        layout_bottom = (FrameLayout) view.findViewById(R.id.layout_bottom);
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
     * @param view
     */
    protected void initActionBar(View view) {
        actionBar = (MyActionBar) view.findViewById(R.id.action_bar);
        actionBar.getLeftView().setImageResource(R.mipmap.action_bar_back_black);
    }


    @Override
    public MyActionBar getActionBarView() {
        return actionBar;
    }

    public FrameLayout getContentView(){
        return content;
    }

    public FrameLayout getBottomLayout(){
        return layout_bottom;
    }
    public View setBottomLayout(){
        return new TextView(getContext());
    }
}
