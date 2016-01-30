package com.ziyou.tourGuide.view.interfaze;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Edward on 16/1/30.
 */
public interface ICustomerServiceView {
    TextView getCustomerServicePhoneNumberTextView();
    EditText getSuggestCallbackEditText();
    Button getCommitButton();
}
