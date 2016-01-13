package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IMeView;
import com.ziyou.tourGuide.widget.MyActionBar;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/12.
 */
public class MeView extends TitleBarContentView implements IMeView {

    @Bind(R.id.me_infomation)
    ViewGroup me_infomation;
    @Bind(R.id.riv_user_avatar)
    SimpleDraweeView riv_user_avatar;
    public MeView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_me,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected void initActionBar(View view) {
        actionBar = (MyActionBar) view.findViewById(R.id.action_bar);
        actionBar.getLeftView().setVisibility(View.GONE);
        String title = getContext().getResources().getString(R.string.me);
        actionBar.getTitleView().setText(title);
    }

    @Override
    public SimpleDraweeView getAvatar() {
        return riv_user_avatar;
    }

    @Override
    public ViewGroup getInfomationLayout() {
        return me_infomation;
    }
}
