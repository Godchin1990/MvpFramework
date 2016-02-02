package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.OrderSettingActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.view.base.JavaScriptCallback;
import com.ziyou.tourGuide.view.base.WebContentView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edward on 16/2/2.
 */
public class CalendarFragment extends BaseFragment implements JavaScriptCallback, View.OnClickListener {

    public static final String PARAM_CALENDAR = "param_calendar";

    private WebContentView webContentView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String routeId = getArguments().getString(Const.ROUTE_ID);
        String phoneNumber = UserHelper.getInstance().getPhoneNumber();
        String url;
        if(TextUtils.isEmpty(phoneNumber)){
            url = ServerAPI.H5.getCalendarH5Url(routeId);
        }else {
            url = ServerAPI.H5.getCalendarH5Url(routeId,phoneNumber);
        }
        webContentView.getWebView().loadUrl(url);
    }

    private void initListener() {
        webContentView.setCallback(this);
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new WebContentView(getContext());
        webContentView.getActionBarView().getTitleView().setText(getResources().getString(R.string.appointment_calendar));
        return webContentView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void parseJsonToSkip(String json) {
        Log.d(TAG,json);
        Intent intent = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int type = jsonObject.getInt("type");
            switch (type) {
                case 5:
                    String calendar = jsonObject.getJSONObject("params").getString("calendar");
                    String number = jsonObject.getJSONObject("params").getString("number");
                    String price = jsonObject.getJSONObject("params").getString("price");
                    intent = new Intent(getContext(),OrderSettingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Const.CALENDAR, calendar);
                    bundle.putString(Const.NUMBER, number);
                    bundle.putString(Const.PRICE, price);
                    bundle.putString(Const.ROUTE_ID,getArguments().getString(Const.ROUTE_ID));
                    intent.putExtra(Const.BUNDLE, bundle);
                    startActivity(intent);
                    getActivity().finish();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getResources().getString(R.string.parse_json_error), Toast.LENGTH_SHORT).show();
        }
    }
}
