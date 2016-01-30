package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.ICustomerServiceView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/30.
 */
public class CustomerServiceView extends TitleBarContentView implements ICustomerServiceView {

    @Bind(R.id.customer_service_phone_number)
    View customer_service_phone_number;
    @Bind(R.id.muti_edittext)
    EditText suggestCallback;
    @Bind(R.id.commit)
    Button commit;

    private TextView phoneNumber;

    public CustomerServiceView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_customer_service,null);
        ButterKnife.bind(this,view);
        initMeItemList();
        return view;
    }

    private void initMeItemList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(customer_service_phone_number);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.customer_service_phone_number,R.mipmap.ic_customer_service));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            ImageView itemIcon = (ImageView) viewList.get(i).findViewById(R.id.item_icon);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
            itemIcon.setImageDrawable(getContext().getResources().getDrawable(viewModeList.get(i).getIconId()));
        }

        phoneNumber = (TextView) customer_service_phone_number.findViewById(R.id.item_left_desc);
        phoneNumber.setText(getContext().getResources().getString(R.string.real_phone_number));
    }

    @Override
    public TextView getCustomerServicePhoneNumberTextView() {
        return phoneNumber;
    }

    @Override
    public EditText getSuggestCallbackEditText() {
        return suggestCallback;
    }

    @Override
    public Button getCommitButton() {
        return commit;
    }
}
