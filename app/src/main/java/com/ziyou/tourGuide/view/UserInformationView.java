package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IUserInformationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/20.
 */
public class UserInformationView extends TitleBarContentView implements IUserInformationView {

    @Bind(R.id.name)
    View name;
    @Bind(R.id.appoint_people_count)
    View phone_number;
    @Bind(R.id.current_living_place)
    View current_living_place;
    @Bind(R.id.password_setting)
    View password_setting;
    @Bind(R.id.age)
    View age;
    @Bind(R.id.gender)
    View gender;
    @Bind(R.id.introduction)
    View introduction;

    private EditText username_et;
    private EditText phone_number_et;
    private TextView current_living_place_tv;
    private TextView age_tv;
    private TextView gender_tv;
    private EditText introduction_et;

    public UserInformationView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_user_information,null);
        ButterKnife.bind(this, view);
        initItemList();
        return view.getRootView();
    }

    private void initItemList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(name);
        viewList.add(phone_number);
        viewList.add(current_living_place);
        viewList.add(password_setting);
        viewList.add(age);
        viewList.add(gender);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.name));
        viewModeList.add(new ItemViewMode(R.string.phone_number));
        viewModeList.add(new ItemViewMode(R.string.current_living_place));
        viewModeList.add(new ItemViewMode(R.string.password_setting));
        viewModeList.add(new ItemViewMode(R.string.age));
        viewModeList.add(new ItemViewMode(R.string.gender));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
        }

        username_et = (EditText) name.findViewById(R.id.item_edittext);
        phone_number_et = (EditText) phone_number.findViewById(R.id.item_edittext);
        current_living_place_tv = (TextView)current_living_place.findViewById(R.id.item_right_desc);
        age_tv = (TextView)age.findViewById(R.id.item_right_desc);
        gender_tv = (TextView)gender.findViewById(R.id.item_right_desc);
        introduction_et = (EditText)introduction.findViewById(R.id.muti_edittext);
    }

    @Override
    public EditText getUserNameEditText() {
        return username_et;
    }

    @Override
    public EditText getPhoneNumberEditText() {
        return phone_number_et;
    }

    @Override
    public TextView getCurrentLivingPlaceTextView() {
        return current_living_place_tv;
    }

    @Override
    public View getPasswordSettingView() {
        return password_setting;
    }

    @Override
    public TextView getAgeTextView() {
        return age_tv;
    }

    @Override
    public TextView getGenderTextView() {
        return gender_tv;
    }

    @Override
    public EditText getIntroductionEditText() {
        return introduction_et;
    }

}
