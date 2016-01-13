package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.model.City;
import com.ziyou.tourGuide.util.ScreenHelper;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/10.
 */
public class CityViewHolder extends BaseViewHolder<City> implements View.OnClickListener {

    private TextView cityTv;

    public CityViewHolder(View itemView) {
        super(itemView);
        cityTv = (TextView) itemView;
        ViewGroup.LayoutParams param =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ScreenHelper.dpToPxInt(itemView.getContext(), 45));
        cityTv.setGravity(Gravity.CENTER);
        cityTv.setTextSize(16);
        cityTv.setSingleLine(true);
        cityTv.setPadding(10, 10, 10, 10);
        cityTv.setTextColor(itemView.getContext().getResources().getColor(R.color.pop_text_color));
        cityTv.setLayoutParams(param);

        cityTv.setOnClickListener(this);
    }

    @Override
    protected void inflateView(City data) {
        cityTv.setText(data.getName());
    }

    @Override
    public void onClick(View v) {
        String cityName = cityTv.getText().toString();
        ClickEvent<String> clickEvent = new ClickEvent<>();
        clickEvent.setParam(cityName);

        EventBus.getDefault().post(clickEvent);
    }
}
