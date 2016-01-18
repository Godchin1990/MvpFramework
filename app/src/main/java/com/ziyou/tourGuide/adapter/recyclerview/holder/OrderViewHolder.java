package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.OrderDetailActivity;
import com.ziyou.tourGuide.model.Order;
import com.ziyou.tourGuide.network.ServerAPI;

/**
 * Created by Edward on 15/11/8.
 */
public class OrderViewHolder extends BaseViewHolder<Order> implements View.OnClickListener {

    /** 新订单*/
    public static final int ORDER_NEW = 0 ;
    /**等待街客接单*/
    public static final int ORDER_WAIT_RECEIVE = 1 ;
    /**已确定或者已接受*/
    public static final int ORDER_CONFIRM = 2 ;
    /**已取消*/
    public static final int ORDER_CANCEL = 6 ;
    /**待评论*/
    public static final int ORDER_WAIT_COMMENT = 11 ;
    /**已完成*/
    public static final int ORDER_FINISH = 13;

    public TextView title;
    public TextView time;
    public TextView status;

    public static View getView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_guide_order, null);
        return view;
    }

    public OrderViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        time = (TextView) itemView.findViewById(R.id.time);
        status = (TextView) itemView.findViewById(R.id.status);
    }

    @Override
    protected void inflateView(Order data) {
        title.setText(data.getTitle());
        time.setText(data.getCreate_time());
        switch (data.getTrade_status()){
            case ORDER_WAIT_RECEIVE : // 待接受
                status.setText("待接受");
                status.setTextColor(itemView.getResources().getColor(R.color.order_list_status_wait_receive_text));
                break;
            case ORDER_CONFIRM : // 已接受
                status.setText("已接受");
                status.setTextColor(itemView.getResources().getColor(R.color.order_list_status_over_text));
                break;
            case ORDER_FINISH : // 已完成
                status.setText("已完成");
                status.setTextColor(itemView.getResources().getColor(R.color.order_list_status_over_text));
                break;
            case ORDER_WAIT_COMMENT : // 待评论
                status.setText("待评价");
                status.setTextColor(itemView.getResources().getColor(R.color.order_list_status_wait_comment_text));
                break;
            case ORDER_CANCEL : // 已取消
                status.setText("已取消");
                status.setTextColor(itemView.getResources().getColor(R.color.order_list_status_over_text));
                break;
            default:
                if (ServerAPI.DEBUG) {
                    status.setTextColor(itemView.getResources().getColor(R.color.red));
                    status.setText(String.valueOf(data.getTrade_status()));
                }else{
                    status.setText("");
                }
                break;
        }
        itemView.setOnClickListener(this);
        itemView.setTag(data);
    }

    @Override
    public void onClick(View v) {
        Order tag = (Order) v.getTag();
        Intent intent = new Intent(itemView.getContext(),OrderDetailActivity.class);
        intent.putExtra("orderId", tag.getId());
        v.getContext(). startActivity(intent);
    }
}
