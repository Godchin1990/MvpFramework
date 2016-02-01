package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ReceiveRoute;
import com.ziyou.tourGuide.widget.WordWrapView;

/**
 * Created by Edward on 16/1/22.
 */
public class ReceiveRouteViewHolder extends BaseViewHolder<ReceiveRoute> {

    private final TextView item_title;
    private final WordWrapView view_wordwrap;
    private final TextView item_date;
    private final ImageView item_status;
    private final TextView old_price;
    private final TextView new_price;

    public static View getView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receive_route, null);
        return view;
    }

    public ReceiveRouteViewHolder(View itemView) {
        super(itemView);
        item_title = (TextView) itemView.findViewById(R.id.item_title);
        view_wordwrap = (WordWrapView) itemView.findViewById(R.id.view_wordwrap);
        item_date = (TextView) itemView.findViewById(R.id.item_date);
        item_status = (ImageView) itemView.findViewById(R.id.item_status);
        new_price = (TextView) itemView.findViewById(R.id.new_price);


        old_price = (TextView) itemView.findViewById(R.id.old_price);
        old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

    }

    @Override
    protected void inflateView(int position, ReceiveRoute data) {
        item_title.setText(data.getTitle());
        view_wordwrap.setLabelList(data.getLabels(), 3);
        item_date.setText(data.getStart_date());
        old_price.setText(String.format(getResources().getString(R.string.old_price), data.getSuggest_price()));
        new_price.setText(data.getPrice());
        switch (data.getShow_status()){
            case 1:
                item_status.setImageResource(R.mipmap.ic_receive_route_online);
                break;
            default:
                item_status.setImageResource(R.mipmap.ic_receive_route_offline);
                break;
        }
    }
}
