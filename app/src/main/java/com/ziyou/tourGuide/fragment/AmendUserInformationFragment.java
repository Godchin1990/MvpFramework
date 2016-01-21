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
import com.ziyou.tourGuide.view.AmendUserInformationView;

/**
 * Created by Edward on 16/1/15.
 */
public class AmendUserInformationFragment extends BaseFragment implements View.OnClickListener {

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
                Log.d(TAG,"click action_bar_right_text");

                break;
            case R.id.password_setting:
                Log.d(TAG,"click password_setting");
                intent = new Intent(getContext(),AmendPasswordActivity.class);
                getContext().startActivity(intent);
                break;
        }
    }

    /**
     * 上传数据到网络
     */
    private void uploadInformation(){
        //TODO 上传数据
        //TODO 数据保存到本地
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
}
