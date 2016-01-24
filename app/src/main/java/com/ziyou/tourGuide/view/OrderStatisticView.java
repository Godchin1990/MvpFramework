package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IOrderStatisticView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/24.
 */
public class OrderStatisticView extends TitleBarContentView implements IOrderStatisticView {
    @Bind(R.id.order)
    View order;
    @Bind(R.id.route_profit_share)
    View route_profit_share;
    @Bind(R.id.share_profit_share)
    View share_profit_share;
    @Bind(R.id.total)
    View total;

    @Bind(R.id.order_content)
    View order_content;
    @Bind(R.id.route_profit_share_content)
    View route_profit_share_content;
    @Bind(R.id.share_profit_share_content)
    View share_profit_share_content;
    public OrderStatisticView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_order_statistic,null);
        ButterKnife.bind(this,view);
        initOrderStatisticView();
        return view;
    }

    private void initOrderStatisticView() {
        List<View> viewList = new ArrayList<>();
        viewList.add(order);
        viewList.add(route_profit_share);
        viewList.add(share_profit_share);

        List<Integer> itemTitle = new ArrayList<>();
        itemTitle.add(R.string.order);
        itemTitle.add(R.string.route_profit_share);
        itemTitle.add(R.string.share_profit_share);

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            itemText.setText(getContext().getResources().getString(itemTitle.get(i)));
        }

        ((TextView) order.findViewById(R.id.item_button)).setText(getContext().getResources().getString(R.string.check_description));
        ((TextView) route_profit_share.findViewById(R.id.item_button)).setText(getContext().getResources().getString(R.string.check_description));
        share_profit_share.findViewById(R.id.item_button).setVisibility(View.GONE);

    }

    @Override
    public TextView getFinishedOrderTextView() {
        return (TextView) order_content.findViewById(R.id.first_tv);
    }

    @Override
    public TextView getOrderAmountPriceTextView() {
        return (TextView) order_content.findViewById(R.id.second_tv);
    }

    @Override
    public TextView getDeductProfitShareTextView() {
        return (TextView) order_content.findViewById(R.id.third_tv);
    }

    @Override
    public TextView getProvideRouteTextView() {
        return (TextView) route_profit_share_content.findViewById(R.id.first_tv);
    }

    @Override
    public TextView getRouteOrderNumberTextView() {
        return (TextView) route_profit_share_content.findViewById(R.id.second_tv);
    }

    @Override
    public TextView getRouteAcquireProfitShareTextView() {
        return (TextView) route_profit_share_content.findViewById(R.id.third_tv);
    }

    @Override
    public TextView getShareRouteTextView() {
        return (TextView) share_profit_share_content.findViewById(R.id.first_tv);
    }

    @Override
    public TextView getShareOrderNumberTextView() {
        return (TextView) share_profit_share_content.findViewById(R.id.second_tv);
    }

    @Override
    public TextView getShareAcquireProfitShareTextView() {
        return (TextView) share_profit_share_content.findViewById(R.id.third_tv);
    }

    @Override
    public TextView getTotalProfitTextView() {
        return (TextView) total.findViewById(R.id.item_text);
    }
}
