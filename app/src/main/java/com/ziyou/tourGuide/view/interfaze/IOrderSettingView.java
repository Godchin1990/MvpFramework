package com.ziyou.tourGuide.view.interfaze;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziyou.tourGuide.widget.SelectNumberView;

/**
 * Created by Edward on 16/1/28.
 */
public interface IOrderSettingView {
    TextView getPriceTextView();
    TextView getCommitAppointTextView();
    TextView getTitleTextView();
    TextView getStartDateTextView();
    View getStartDateView();
    View getAppointPriceTextView();
    SelectNumberView getSelectNumberView();
    EditText getNameEditText();
    EditText getPhoneNumberEditText();
}
