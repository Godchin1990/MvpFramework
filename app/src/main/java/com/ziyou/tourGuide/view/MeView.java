package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.command.view.InitMeItemCommand;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IMeView;
import com.ziyou.tourGuide.widget.MyActionBar;

import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.my_message)
    View my_message;
    @Bind(R.id.guider_area)
    View guider_area;
    @Bind(R.id.my_tour)
    View my_tour;
    @Bind(R.id.my_wallet)
    View my_wallet;
    @Bind(R.id.customer_service)
    View customer_service;
    @Bind(R.id.setting)
    View setting;

    public MeView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_me,null);
        ButterKnife.bind(this,view);

        initMeItemList();
        return view;
    }

    private void initMeItemList() {
        List<View> list = new ArrayList<>();
        list.add(my_message);
        list.add(guider_area);
        list.add(my_tour);
        list.add(my_wallet);
        list.add(customer_service);
        list.add(setting);

        InitMeItemCommand initMeItemCommand = new InitMeItemCommand(getContext(),list);
        initMeItemCommand.execute();
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
