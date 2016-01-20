package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
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

    @Bind(R.id.me_infomation_layout)
    ViewGroup me_infomation_layout;
    @Bind(R.id.me_infomation_layout_part)
    ViewGroup me_infomation_layout_part;
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

    View unLoginView;
    View userLoginView;
    View LoginView;

    public MeView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_me,null);
        ButterKnife.bind(this,view);
        unLoginView = View.inflate(getContext(),R.layout.item_me_information_unlogin_part,null);
        userLoginView = View.inflate(getContext(),R.layout.item_me_information_login_part,null);
        initMeItemList();
        return view;
    }

    private void initMeItemList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(my_message);
        viewList.add(guider_area);
        viewList.add(my_tour);
        viewList.add(my_wallet);
        viewList.add(customer_service);
        viewList.add(setting);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.my_message,R.mipmap.ic_my_message));
        viewModeList.add(new ItemViewMode(R.string.guider_area,R.mipmap.ic_guider_area));
        viewModeList.add(new ItemViewMode(R.string.my_tour,R.mipmap.ic_my_tour));
        viewModeList.add(new ItemViewMode(R.string.my_wallet, R.mipmap.ic_my_wallet));
        viewModeList.add(new ItemViewMode(R.string.customer_service,R.mipmap.ic_customer_service));
        viewModeList.add(new ItemViewMode(R.string.setting, R.mipmap.ic_setting));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            ImageView itemIcon = (ImageView) viewList.get(i).findViewById(R.id.item_icon);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
            itemIcon.setImageDrawable(getContext().getResources().getDrawable(viewModeList.get(i).getIconId()));
        }

    }

    @Override
    protected void initActionBar(View view) {
        actionBar = (MyActionBar) view.findViewById(R.id.action_bar);
        actionBar.getLeftView().setVisibility(View.GONE);
        String title = getContext().getResources().getString(R.string.me);
        actionBar.getTitleView().setText(title);
    }

    @Override
    public ViewGroup getInfomationLayoutPart() {
        return me_infomation_layout_part;
    }

    @Override
    public SimpleDraweeView getAvatar() {
        return riv_user_avatar;
    }

    @Override
    public ViewGroup getInfomationLayout() {
        return me_infomation_layout;
    }

    @Override
    public View getMyMessage() {
        return my_message;
    }

    @Override
    public View getGuiderArea() {
        return guider_area;
    }

    @Override
    public View getMyTour() {
        return my_tour;
    }

    @Override
    public View getMyWallet() {
        return my_wallet;
    }

    @Override
    public View getCustomerService() {
        return customer_service;
    }

    @Override
    public View getSetting() {
        return setting;
    }

    @Override
    public View getUnLoginView() {
        return unLoginView;
    }

    @Override
    public View getUserLoginView() {
        return userLoginView;
    }

}
