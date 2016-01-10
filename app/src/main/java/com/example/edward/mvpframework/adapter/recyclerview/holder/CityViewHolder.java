package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.model.City;
import com.example.edward.mvpframework.util.ScreenHelper;

/**
 * Created by Edward on 16/1/10.
 */
public class CityViewHolder extends BaseViewHolder<City> {

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
        cityTv.setPadding(10,10,10,10);
        cityTv.setTextColor(itemView.getContext().getResources().getColor(R.color.pop_text_color));
        cityTv.setLayoutParams(param);
    }

    @Override
    protected void inflateView(City data) {
        cityTv.setText(data.getName());
    }
}
