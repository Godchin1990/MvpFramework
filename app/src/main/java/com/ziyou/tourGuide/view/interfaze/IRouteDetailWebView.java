package com.ziyou.tourGuide.view.interfaze;

import android.widget.TextView;

/**
 * Created by Edward on 16/1/25.
 */
public interface IRouteDetailWebView {
    TextView getPriceTextView();
    TextView getAppointTextView();
    void showCallPhoneDialog(String number);

}
