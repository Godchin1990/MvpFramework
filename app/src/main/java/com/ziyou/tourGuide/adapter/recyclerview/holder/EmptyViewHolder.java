package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.Empty;


/**
 * Created by Edward on 15/11/8.
 */
public class EmptyViewHolder extends BaseViewHolder<Empty> {

    public EmptyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(int position, Empty data) {

    }

    public static View getView(Context context) {
        return View.inflate(context, R.layout.item_loading,null);
    }
}
