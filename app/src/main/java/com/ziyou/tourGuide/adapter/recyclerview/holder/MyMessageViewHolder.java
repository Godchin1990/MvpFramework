package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.MyMessage;

/**
 * Created by Edward on 16/1/18.
 */
public class MyMessageViewHolder extends BaseViewHolder<MyMessage> {
    public static View getView(Context context) {
        View routeView = LayoutInflater.from(context).inflate(R.layout.item_my_message, null);
        return routeView;
    }

    public MyMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(MyMessage data) {

    }
}
