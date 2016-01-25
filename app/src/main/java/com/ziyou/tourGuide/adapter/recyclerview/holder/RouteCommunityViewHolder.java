package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.RouteCommunity;
import com.ziyou.tourGuide.widget.WordWrapView;

/**
 * Created by Edward on 15/12/13.
 */
public class RouteCommunityViewHolder extends BaseViewHolder<RouteCommunity> {

    private final TextView item_title;
    private final TextView suggest_price;
    private final ImageView is_receive;
    private final String suggest_price_string;
    private final WordWrapView view_wordwrap;
    private View itemView  ;

    public static View getView(Context context){
        View routeCommunityView = LayoutInflater.from(context).inflate(R.layout.item_route_community, null);
        return routeCommunityView;
    }

    public RouteCommunityViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        item_title = (TextView) itemView.findViewById(R.id.item_title);
        view_wordwrap = (WordWrapView) itemView.findViewById(R.id.view_wordwrap);
        suggest_price = (TextView) itemView.findViewById(R.id.suggest_price);
        is_receive = (ImageView) itemView.findViewById(R.id.is_receive);
        suggest_price_string = getResources().getString(R.string.suggest_price);
    }

    @Override
    protected void inflateView(RouteCommunity data) {
        item_title.setText(data.getTitle());
        view_wordwrap.setLabelList(data.getLabels(),3);
        this.itemView.setTag(data);
        if(TextUtils.isEmpty(data.getSuggest_price())){
            suggest_price.setText("未知价格");
        }else {
            suggest_price.setText(String.format(suggest_price_string, Integer.parseInt(data.getSuggest_price())));
        }
        if(data.getIs_me()==0){
            is_receive.setVisibility(View.INVISIBLE);
        }else {
            is_receive.setVisibility(View.VISIBLE);
        }

    }

}
