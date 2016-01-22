package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.AmendPasswordActivity;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.UserHelper;
import com.ziyou.tourGuide.model.UserInformation;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.AmendUserInformationView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 16/1/15.
 */
public class AmendUserInformationFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private AmendUserInformationView amendUserInformationView;

    @Override
    protected void initData() {
        initListener();
        refreshInformation();
    }

    private void initListener() {
        amendUserInformationView.getActionBarView().getLeftView().setOnClickListener(this);
        amendUserInformationView.getActionBarView().getRightTextView().setOnClickListener(this);
        amendUserInformationView.getPasswordSettingView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        amendUserInformationView = new AmendUserInformationView(getContext());
        amendUserInformationView.getActionBarView().getTitleView().setText(getResources().getString(R.string.edit));
        amendUserInformationView.getActionBarView().getRightTextView().setVisibility(View.VISIBLE);
        amendUserInformationView.getActionBarView().getRightTextView().setText(getResources().getString(R.string.save));
        return amendUserInformationView.getRootView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.action_bar_right_text:
                Log.d(TAG, "click action_bar_right_text");
                uploadInformation();
                break;
            case R.id.password_setting:
                Log.d(TAG,"click password_setting");
                intent = new Intent(getContext(),AmendPasswordActivity.class);
                getContext().startActivity(intent);
                break;
        }
    }

    private Map<String, String> getUploadInformationParams() {
        Map<String,String> params = new HashMap<>();
        String name = amendUserInformationView.getUserNameEditText().getText().toString();
        String phone = amendUserInformationView.getPhoneNumberEditText().getText().toString();
        String gender = amendUserInformationView.getGenderTextView().getText().toString();
        String intro = amendUserInformationView.getIntroductionEditText().getText().toString();
        String city = amendUserInformationView.getCurrentLivingPlaceTextView().getText().toString();
        params.put("name",name);
        params.put("phone",phone);
        params.put("gender","1");
        params.put("gender",UserHelper.getInstance().getGenderCode(gender));
        params.put("intro",intro);
        params.put("city",city);
        return params;
    }

    /**
     * 上传数据到网络
     */
    private void uploadInformation(){
        //TODO 上传数据
        String url = ServerAPI.User.buildModifyUserUrl();
        Map<String,String> params = getUploadInformationParams();
        NetworkHelper.getInstance().sendPostStringRequest(url, params, this, "upload");
    }

    /**
     * 从UserHelper (shareprefrence) 刷新数据
     */
    private void refreshInformation(){
        UserInformation userInformation = UserHelper.getInstance().getUserInformation();

        amendUserInformationView.getUserNameEditText().setText(userInformation.getNickname());
        amendUserInformationView.getPhoneNumberEditText().setText(userInformation.getPhone());
        amendUserInformationView.getAgeTextView().setText(userInformation.getAge()+"");
        amendUserInformationView.getCurrentLivingPlaceTextView().setText(userInformation.getCity());
        amendUserInformationView.getIntroductionEditText().setText(userInformation.getIntro());
        switch (userInformation.getGender()){
            case 1:
                amendUserInformationView.getGenderTextView().setText(getResources().getString(R.string.gentle_man));
                break;
            case 2:
                amendUserInformationView.getGenderTextView().setText(getResources().getString(R.string.lady));
                break;
            default:
                amendUserInformationView.getGenderTextView().setText(getResources().getString(R.string.unknown));
                break;
        }

    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,data);
                switch (tag){
                    case "upload":
                        // 因为每次返回Mefragment都会重新获得一次个人信息,所以不需要保存个人信息,直接关闭当前页面就好
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
            }
        });
    }
}
