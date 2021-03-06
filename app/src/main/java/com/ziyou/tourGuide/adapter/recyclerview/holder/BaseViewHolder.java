package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 血的教训,千万不能在holder里面做耗时操作,比如说这个例子里的
 * <p>
 *      FontHelper.applyFont(itemView.getContext(), itemView, "font/ltxh.TTF");
 * </p>
 * Created by Edward on 15/11/17.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected final String TAG;
    {
        TAG = getClass().getSimpleName();
    }
    private boolean autoInflate = true;
    private T data;
    private static  Resources resources;

    public Resources getResources() {
        return resources;
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        if (resources == null){
            resources = itemView.getResources();
        }
    }
    public void setData(int position, T data) {
        this.data = data;
        if (autoInflate){
            inflateView(position, data);
        }
    }

    protected abstract void inflateView(int position, T data);

    public T getData() {
        return data;
    }

}
