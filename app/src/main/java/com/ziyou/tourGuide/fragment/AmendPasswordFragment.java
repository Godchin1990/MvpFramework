package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.AmendPasswordView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 16/1/21.
 */
public class AmendPasswordFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private AmendPasswordView amendPasswordView;

    @Override
    protected void initData() {
        initListener();
    }

    private void initListener() {
        amendPasswordView.getActionBarView().getLeftView().setOnClickListener(this);
        amendPasswordView.getSettingButton().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        amendPasswordView = new AmendPasswordView(getContext());
        amendPasswordView.getActionBarView().getTitleView().setText(getResources().getString(R.string.password_setting));
        return amendPasswordView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.setting:
                Log.d(TAG,"click setting");
                String newPassword = amendPasswordView.getNewPasswordEditText().getText().toString();
                String confirmPassword = amendPasswordView.getOldPasswordEditText().getText().toString();
                if(TextUtils.isEmpty(confirmPassword)||TextUtils.isEmpty(newPassword)){
                    Toast.makeText(getContext(),getResources().getString(R.string.password_can_not_be_null),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(confirmPassword.length()<6){
                    Toast.makeText(getContext(),getResources().getString(R.string.password_can_not_less_six),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!confirmPassword.equals(newPassword)){
                    Toast.makeText(getContext(),getResources().getString(R.string.password_two_input_not_equal),Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = ServerAPI.User.buildChangePasswordUrl();
                Map<String,String> params = new HashMap<>();
                params.put("password",newPassword);
                NetworkHelper.getInstance().sendPostStringRequest(url,params,this,"refresh");
                break;
        }
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (tag){
                    case "refresh":
                        Log.d(TAG,data);
                        Toast.makeText(getContext(),getResources().getString(R.string.password_amend_success),Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                        break;
                }
            }
        });
    }

    @Override
    public void onFail(int code, final String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,message);
                Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
