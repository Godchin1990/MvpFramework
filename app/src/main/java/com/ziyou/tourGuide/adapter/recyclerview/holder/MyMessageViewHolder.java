package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.model.MyMessage;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/18.
 */
public class MyMessageViewHolder extends BaseViewHolder<MyMessage> implements View.OnLongClickListener {

    public final static String TAG_DELETE = "message_delete";
    public final static String PARAM_POSITION = "position";
    public final static String PARAM_ID = "id";

//    @Bind(R.id.item_title)
    private static TextView item_title;
    private static TextView item_date;
    private static TextView item_content;

    public static View getView(Context context) {
        View routeView = LayoutInflater.from(context).inflate(R.layout.item_my_message, null);
        item_title = (TextView) routeView.findViewById(R.id.item_title);
        item_date = (TextView) routeView.findViewById(R.id.item_date);
        item_content = (TextView) routeView.findViewById(R.id.item_content);
        return routeView;
    }

    public MyMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(int position, MyMessage data) {
        item_title.setText(data.getFrom_user());
        item_date.setText(data.getCreate_time());
        item_content.setText(data.getContent());
        itemView.setOnLongClickListener(this);
        itemView.setTag(position);
    }

    @Override
    public boolean onLongClick(View v) {
        MyMessage data = getData();
        ClickEvent clickEvent = new ClickEvent(TAG_DELETE);
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_ID,data.getId()+"");
        Log.d(TAG,bundle.toString());
        clickEvent.setParam(bundle);
        EventBus.getDefault().post(clickEvent);
        return true;
    }
}
