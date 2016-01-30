package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.easemob.easeui.EaseConstant;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.ChatActivity;
import com.ziyou.tourGuide.activity.GuideDetailActivity;
import com.ziyou.tourGuide.activity.OrderSettingActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.LocationHelper;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.RouteDetailWebView;
import com.ziyou.tourGuide.view.base.GuideJavaScriptCallback;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Edward on 16/1/10.
 */
public class RouteDetailFragment extends BaseFragment implements GuideJavaScriptCallback, View.OnClickListener, StringCallBack<String> {

    private RouteDetailWebView webContentView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String routeId = getArguments().getString(Const.ROUTE_ID);
        String url = ServerAPI.Route.buildGetRouteDetailUrl(routeId);
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    private void initListener() {
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
        webContentView.getAppointTextView().setOnClickListener(this);
        //未加载网络数据前,设置不可点击
        webContentView.getAppointTextView().setClickable(false);
        webContentView.getAppointTextView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new RouteDetailWebView(getContext());
        return webContentView.getRootView();
    }

    @Override
    public void parseJsonToSkip(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int type = jsonObject.getInt("type");
            Intent intent;
            switch (type){
                case 1:
                    //跳转到聊天页面
                    String userId = jsonObject.getJSONObject("params").getString("userId");
                    intent = new Intent(getContext(), ChatActivity.class);
                    intent.putExtra(EaseConstant.EXTRA_USER_ID,userId);
                    getContext().startActivity(intent);
                    break;
                case 2:
                    //跳转导游
                    String guideId = jsonObject.getJSONObject("params").getString("guider_id");
                    intent = new Intent(getContext(), GuideDetailActivity.class);
                    Bundle bundleForGuider = new Bundle();
                    bundleForGuider.putString(Const.GUIDE_ID, guideId);
                    intent.putExtra(Const.BUNDLE, bundleForGuider);
                    getContext().startActivity(intent);
                    break;
                case 4:
                    //跳转拨打电话页面
                    String phone = jsonObject.getJSONObject("params").getString("phone");
                    webContentView.showCallPhoneDialog(phone);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),getResources().getString(R.string.parse_json_error),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.appoint:
                Log.d(TAG,"click appoint");
                intent = new Intent(getContext(), OrderSettingActivity.class);
                Bundle bundleForRoute = new Bundle();
                bundleForRoute.putString(Const.ROUTE_ID, getArguments().getString(Const.ROUTE_ID) + "");
                intent.putExtra(Const.BUNDLE, bundleForRoute);
                getContext().startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccess(final String data, String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, data);
//                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String title = jsonObject.getString("title");
                    String price = jsonObject.getString("price");
                    webContentView.getActionBarView().getTitleView().setText(title);
                    webContentView.getPriceTextView().setText(price);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //设置可点击
                webContentView.getAppointTextView().setClickable(true);

                String routeId = getArguments().getString(Const.ROUTE_ID);
                String routeDetailH5Url = ServerAPI.H5.getRouteDetailH5Url(routeId);
                if (!TextUtils.isEmpty(routeDetailH5Url)) {
                    webContentView.getWebView().loadUrl(routeDetailH5Url);
                }
                webContentView.setCallback(RouteDetailFragment.this);
                webContentView.getActionBarView().getLeftView().setOnClickListener(RouteDetailFragment.this);
            }
        });

    }

    @Override
    public void onFail(int code, String message, Object object) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationHelper.getInstance().onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ClickEvent clickEvent){
        switch (clickEvent.getTag()){
            case RouteDetailWebView.TAG_CALL_PHONE:
                //点击拨打电话
                String number = (String) clickEvent.getParam();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
                break;

        }

    }
}
