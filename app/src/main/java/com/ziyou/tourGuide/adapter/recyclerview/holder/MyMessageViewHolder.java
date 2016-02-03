package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.MyMessageDetailActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.model.MyMessage;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/18.
 */
public class MyMessageViewHolder extends BaseViewHolder<MyMessage> implements View.OnLongClickListener, View.OnClickListener {

    public final static String TAG_DELETE = "message_delete";
    public final static String PARAM_ID = "id";

    private TextView item_title;
    private TextView item_date;
    private TextView item_content;

    public static View getView(Context context) {
        View routeView = LayoutInflater.from(context).inflate(R.layout.item_my_message, null);
        return routeView;
    }

    public MyMessageViewHolder(View itemView) {
        super(itemView);
        item_title = (TextView) itemView.findViewById(R.id.item_title);
        item_date = (TextView) itemView.findViewById(R.id.item_date);
        item_content = (TextView) itemView.findViewById(R.id.item_content);
    }

    @Override
    protected void inflateView(int position, MyMessage data) {
        item_title.setText(data.getFrom_user());
        item_date.setText(data.getCreate_time());
        item_content.setText(data.getContent());
        itemView.setOnLongClickListener(this);
        itemView.setOnClickListener(this);
        itemView.setTag(position);
    }

    @Override
    public boolean onLongClick(View v) {
        MyMessage data = getData();
        ClickEvent clickEvent = new ClickEvent(TAG_DELETE);
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_ID, data.getId() + "");
        Log.d(TAG,bundle.toString());
        clickEvent.setParam(bundle);
        EventBus.getDefault().post(clickEvent);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), MyMessageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Const.MESSAGE_ID,getData().getId()+"");
        bundle.putString(Const.MESSAGE_DATE,getData().getCreate_time());
        bundle.putString(Const.MESSAGE_CONTENT,getData().getContent());
        intent.putExtra(Const.BUNDLE,bundle);
        v.getContext().startActivity(intent);
    }
}
