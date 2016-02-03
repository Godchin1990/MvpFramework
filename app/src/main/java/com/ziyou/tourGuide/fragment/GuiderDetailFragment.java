package com.ziyou.tourGuide.fragment;

import android.content.Intent;
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
import com.ziyou.tourGuide.activity.RouteDetailActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.GuiderDetailWebView;
import com.ziyou.tourGuide.view.base.JavaScriptCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 使用的不是V3接口中的数据,因为V3的导游详情没有环信账号信息
 * Created by Edward on 16/1/10.
 */
public class GuiderDetailFragment extends BaseFragment implements JavaScriptCallback, View.OnClickListener, StringCallBack<String> {

    private GuiderDetailWebView webContentView;
    private String im_username;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String guideId = getArguments().getString(Const.GUIDE_ID);
        String url = ServerAPI.GuideDetail.buildGuideDetailUrl(guideId);
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new GuiderDetailWebView(getContext());
        String string = getResources().getString(R.string.guider_detail);
        webContentView.getActionBarView().getTitleView().setText(string);
        return webContentView.getRootView();
    }

    private void initListener() {
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
        webContentView.getBottomLayout().setOnClickListener(this);
        webContentView.getBottomLayout().setOnClickListener(this);
        webContentView.setCallback(this);
    }

    @Override
    public void parseJsonToSkip(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int type = jsonObject.getInt("type");
            Intent intent;
            switch (type) {
                case 7:
                    //跳转到路线详情
                    String routeId = jsonObject.getJSONObject("params").getString("id");
                    intent = new Intent(getContext(), RouteDetailActivity.class);
                    Bundle bundleForRoute = new Bundle();
                    bundleForRoute.putString(Const.ROUTE_ID, routeId);
                    intent.putExtra(Const.BUNDLE, bundleForRoute);
                    getContext().startActivity(intent);
                    break;
                case 8:
                    // 跳转到游客评论
                    String commitId = jsonObject.getJSONObject("params").getString("id");
                    // 跳转到评论页面

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getResources().getString(R.string.parse_json_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.layout_bottom:
                Log.d(TAG,"click layout_bottom");
                if(!TextUtils.isEmpty(im_username)){
                    intent = new Intent(getContext(), ChatActivity.class);
                    intent.putExtra(EaseConstant.EXTRA_USER_ID,im_username);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onSuccess(final String data, String tag) {
        Log.d(TAG,data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    im_username = jsonObject.getString("im_username");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String guiderId = getArguments().getString(Const.GUIDE_ID);
                String url = ServerAPI.H5.getGuiderDetailH5Url(guiderId);
                webContentView.getWebView().loadUrl(url);

            }
        });
    }

    @Override
    public void onFail(int code, final String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,message);
            }
        });
    }
}
