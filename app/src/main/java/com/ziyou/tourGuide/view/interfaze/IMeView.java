package com.ziyou.tourGuide.view.interfaze;

import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/12.
 */
public interface IMeView {
    ViewGroup getInfomationLayoutPart();
    SimpleDraweeView getAvatar();
    ViewGroup getInfomationLayout();
    View getMyMessage();
    View getGuiderArea();
    View getMyTour();
    View getMyWallet();
    View getCustomerService();
    View getSetting();
}
