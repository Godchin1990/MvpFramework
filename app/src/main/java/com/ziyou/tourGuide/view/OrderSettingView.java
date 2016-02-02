package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarBottomContentView;
import com.ziyou.tourGuide.view.interfaze.IOrderSettingView;
import com.ziyou.tourGuide.widget.SelectNumberView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/28.
 */
public class OrderSettingView extends TitleBarBottomContentView implements IOrderSettingView {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.start_date)
    View start_date;
    @Bind(R.id.people_number)
    View people_number;
    @Bind(R.id.appoint_price)
    View appoint_price;
    @Bind(R.id.name)
    View name;
    @Bind(R.id.appoint_people_count)
    View appoint_people_count;
    @Bind(R.id.discount_coupon)
    View discount_coupon;

    private TextView price;
    private TextView commit_appoint;
    private TextView start_date_tv;
    private TextView appoint_price_tv;
    private SelectNumberView people_number_snv;
    private EditText name_et;
    private EditText phone_number_et;

    public OrderSettingView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view =View.inflate(getContext(), R.layout.fragment_order_setting,null);
        ButterKnife.bind(this, view);
        initItemList();
        return view;
    }

    private void initItemList(){
        List<View> viewList = new ArrayList<>();
        viewList.add(start_date);
        viewList.add(people_number);
        viewList.add(appoint_price);
        viewList.add(name);
        viewList.add(appoint_people_count);
        viewList.add(discount_coupon);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.start_date));
        viewModeList.add(new ItemViewMode(R.string.appoint_people_count));
        viewModeList.add(new ItemViewMode(R.string.appoint_price));
        viewModeList.add(new ItemViewMode(R.string.name));
        viewModeList.add(new ItemViewMode(R.string.phone_number));
        viewModeList.add(new ItemViewMode(R.string.discount_coupon));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
        }

        start_date_tv = (TextView) start_date.findViewById(R.id.item_right_desc);
        start_date_tv.setHint(getContext().getResources().getString(R.string.please_select_date));
        appoint_price_tv = (TextView) appoint_price.findViewById(R.id.item_right_desc);
        appoint_price_tv.setText("0");
        people_number_snv = (SelectNumberView) people_number.findViewById(R.id.select_number_view);
        name_et = (EditText) name.findViewById(R.id.item_edittext);
        phone_number_et = (EditText) appoint_people_count.findViewById(R.id.item_edittext);
    }

    @Override
    public View setBottomLayout() {
        View view = View.inflate(getContext(),R.layout.layout_order_setting_bottom,null);
        price = (TextView) view.findViewById(R.id.price);
        commit_appoint = (TextView) view.findViewById(R.id.commit_appoint);
        return view;
    }

    @Override
    public TextView getPriceTextView() {
        return price;
    }

    @Override
    public TextView getCommitAppointTextView() {
        return commit_appoint;
    }

    @Override
    public TextView getTitleTextView() {
        return title;
    }

    @Override
    public TextView getStartDateTextView() {
        return start_date_tv;
    }

    @Override
    public View getStartDateView() {
        return start_date;
    }

    @Override
    public TextView getAppointPriceTextView() {
        return appoint_price_tv;
    }

    @Override
    public SelectNumberView getSelectNumberView() {
        return people_number_snv;
    }

    @Override
    public EditText getNameEditText() {
        return name_et;
    }

    @Override
    public EditText getPhoneNumberEditText() {
        return phone_number_et;
    }
}
