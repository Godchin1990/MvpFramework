package com.ziyou.tourGuide.view.interfaze;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Edward on 16/1/20.
 */
public interface IAmendUserInformationView {
    EditText getUserNameEditText();
    EditText getPhoneNumberEditText();
    TextView getCurrentLivingPlaceTextView();
    View getPasswordSettingView();
    View getAgeTextView();
    View getGenderTextView();
}
