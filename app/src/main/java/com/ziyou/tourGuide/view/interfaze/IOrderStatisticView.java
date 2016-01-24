package com.ziyou.tourGuide.view.interfaze;

import android.widget.TextView;

/**
 * Created by Edward on 16/1/24.
 */
public interface IOrderStatisticView {
    TextView getFinishedOrderTextView();
    TextView getOrderAmountPriceTextView();
    TextView getDeductProfitShareTextView();

    TextView getProvideRouteTextView();
    TextView getRouteOrderNumberTextView();
    TextView getRouteAcquireProfitShareTextView();

    TextView getShareRouteTextView();
    TextView getShareOrderNumberTextView();
    TextView getShareAcquireProfitShareTextView();

    TextView getTotalProfitTextView();
}
