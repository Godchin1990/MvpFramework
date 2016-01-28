package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.view.base.TitleBarContentView;

/**
 * Created by Edward on 16/1/28.
 */
public class OrderSettiingView extends TitleBarContentView {

    public OrderSettiingView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        TextView textView = new TextView(getContext());
        textView.setText("OrderSettiingView");
        return textView;
    }
}
