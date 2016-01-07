package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.model.Empty;


/**
 * Created by Edward on 15/11/8.
 */
public class EmptyViewHolder extends BaseViewHolder<Empty> {

    public EmptyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(Empty data) {

    }

    public static View getView(Context context) {
        return View.inflate(context, R.layout.item_loading,null);
    }
}
