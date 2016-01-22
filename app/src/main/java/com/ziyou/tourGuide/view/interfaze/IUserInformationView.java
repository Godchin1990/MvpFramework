package com.ziyou.tourGuide.view.interfaze;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Edward on 16/1/20.
 */
public interface IUserInformationView {
    EditText getUserNameEditText();
    EditText getPhoneNumberEditText();
    TextView getCurrentLivingPlaceTextView();
    View getPasswordSettingView();
    TextView getAgeTextView();
    TextView getGenderTextView();
    EditText getIntroductionEditText();
}
