package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.VoucherViewHolder;
import com.ziyou.tourGuide.model.Voucher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/2/3.
 */
public class VoucherAdapter extends BaseAdapter {

    private List<Voucher> vouchers = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = VoucherViewHolder.getView(parent.getContext());
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VoucherViewHolder viewHolder = (VoucherViewHolder) holder;
        viewHolder.setData(position,vouchers.get(position));
    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }

    public void setVouchers(List<Voucher> list){
        vouchers = list;
        notifyDataSetChanged();
    }
}
