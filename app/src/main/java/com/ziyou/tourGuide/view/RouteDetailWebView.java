package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.view.base.WebContentBottomView;
import com.ziyou.tourGuide.view.interfaze.IRouteDetailWebView;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/25.
 */
public class RouteDetailWebView extends WebContentBottomView implements IRouteDetailWebView {
    public static final String TAG_CALL_PHONE = "tag_call_phone";

    private TextView appoint;
    private TextView price;
    private AlertDialog callPhoneDialog;

    public RouteDetailWebView(Context context) {
        super(context);
    }

    @Override
    public View setBottomLayout() {
        View view = View.inflate(getContext(), R.layout.layout_route_detail_bottom,null);
        price = (TextView) view.findViewById(R.id.price);
        appoint = (TextView) view.findViewById(R.id.appoint);
        return view;
    }

    @Override
    public TextView getPriceTextView() {
        return price;
    }

    @Override
    public TextView getAppointTextView() {
        return appoint;
    }

    @Override
    public void showCallPhoneDialog(final String number) {
        if(callPhoneDialog ==null){
            callPhoneDialog = new AlertDialog.Builder(getContext()).create();
            callPhoneDialog.show();
            callPhoneDialog.getWindow().setContentView(R.layout.dialog_common);

            TextView tv_dialog_title = (TextView) callPhoneDialog.getWindow()
                    .findViewById(R.id.tv_dialog_title);
            tv_dialog_title.setText(getContext().getString(R.string.customer_service_phone_number));

            TextView tv_dialog_content = (TextView) callPhoneDialog.getWindow()
                    .findViewById(R.id.tv_dialog_content);
            tv_dialog_content.setText(String.format(getContext().getString(R.string.is_sure_call_phone),number));

            callPhoneDialog.getWindow()
                    .findViewById(R.id.btn_dialog_left)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callPhoneDialog.dismiss();
                            ClickEvent clickEvent = new ClickEvent(TAG_CALL_PHONE);
                            clickEvent.setParam(number);
                            EventBus.getDefault().post(clickEvent);
                        }
                    });
            callPhoneDialog.getWindow()
                    .findViewById(R.id.btn_dialog_right)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callPhoneDialog.dismiss();
                        }
                    });
        }else {
            callPhoneDialog.show();
        }

    }
}
