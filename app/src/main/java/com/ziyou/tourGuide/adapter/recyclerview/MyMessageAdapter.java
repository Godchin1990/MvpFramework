package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.MyMessageViewHolder;
import com.ziyou.tourGuide.model.MyMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/17.
 */
public class MyMessageAdapter extends BaseAdapter {

    private List<MyMessage> messages = new ArrayList<>();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = MyMessageViewHolder.getView(parent.getContext());
        return new MyMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyMessageViewHolder myMessageViewHolder  =  (MyMessageViewHolder)holder;
        myMessageViewHolder.setData(position, messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<MyMessage> list) {
        messages = list;
        notifyDataSetChanged();
    }

    public void removeItem(String id) {
        for(int i = 0;i<messages.size();i++){
            if(id.equals(messages.get(i).getId()+"")){
                messages.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}
