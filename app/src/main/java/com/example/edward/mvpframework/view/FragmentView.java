package com.example.edward.mvpframework.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.view.base.BaseView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/1.
 */
public class FragmentView extends BaseView implements IFragmentView {

    @Bind(R.id.activity_fragment)
    FrameLayout activityFragment;

    public FragmentView(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(),R.layout.fragment_framelayout,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public FrameLayout getFrameLayout() {
        return activityFragment;
    }
}
