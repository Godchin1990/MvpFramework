package com.ziyou.tourGuide.activity;

import android.content.Intent;
import android.util.Log;

import com.ziyou.tourGuide.activity.base.BaseFragmentActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.OrderSettingFragment;
import com.ziyou.tourGuide.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/28.
 */
public class OrderSettingActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        return new OrderSettingFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String calendar = getIntent().getStringExtra(Const.CALENDAR);
        Log.d(TAG,"onNewIntent");
//        calendar = getIntent().getStringExtra("calendar");
//        String number = getIntent().getStringExtra("number");
//        mRoutePrice = getIntent().getStringExtra(Const.EXTRA_ROUTE_PRICE);
//        departure_date_view.setText(calendar);
//        appointment_price_view.setText(mRoutePrice+"元");
//        if(!TextUtils.isEmpty(number)){
//            people_number_view.setNumber("1");
//            people_number_view.setMaxNumber(Integer.parseInt(number));
//        }
//        reloadView();
    }
}
