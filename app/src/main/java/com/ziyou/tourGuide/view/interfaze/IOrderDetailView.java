package com.ziyou.tourGuide.view.interfaze;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/2/2.
 */
public interface IOrderDetailView {
    SimpleDraweeView getRouteSimpleDraweeView();
    TextView getRouteTitleTextView();
    View getRouteLayout();
    TextView getStartDateTextView();
    TextView getAppointPeopleCountTextView();
    TextView getPayCashTextView();
    TextView getAppointDateTextView();
    TextView getOrderPayNumberTextView();
    Button getBottomButton();
}
