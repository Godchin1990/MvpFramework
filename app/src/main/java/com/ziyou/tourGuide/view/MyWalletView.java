package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.command.view.CommonItemCommand;
import com.ziyou.tourGuide.command.view.InitItemCommand;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IMyWalletView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/18.
 */
public class MyWalletView extends TitleBarContentView implements IMyWalletView {

    @Bind(R.id.cash)
    View cash;
    @Bind(R.id.can_withdraw_cash)
    View can_withdraw_cash;
    @Bind(R.id.discount_coupon)
    View discount_coupon;

    TextView cash_tv;
    TextView can_withdraw_cash_tv;

    @Bind(R.id.withdraw_cash_explain)
    TextView withdraw_cash_explain;

    public MyWalletView(Context context) {
        super(context);
    }

    @Override
    public TextView getCashView() {
        return cash_tv;
    }

    @Override
    public TextView getCanWithdrawCashView() {
        return can_withdraw_cash_tv;
    }

    @Override
    public View getDiscountCouponView() {
        return discount_coupon;
    }

    @Override
    public TextView getWithdrawCashExplainView() {
        return withdraw_cash_explain;
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_my_wallet,null);
        ButterKnife.bind(this, view);
        List<View> viewList = new ArrayList<>();
        viewList.add(cash);
        viewList.add(can_withdraw_cash);
        viewList.add(discount_coupon);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.cash));
        viewModeList.add(new ItemViewMode(R.string.can_withdraw_cash));
        viewModeList.add(new ItemViewMode(R.string.discount_coupon));

        InitItemCommand initItemCommand = new InitItemCommand(getContext(),viewList,viewModeList);
        initItemCommand.getItemCommand(0).setBeginViewCallBack(new CommonItemCommand.ViewCallBack() {
            @Override
            public View getInflateView() {
                cash_tv = new TextView(getContext());
                cash_tv.setText("0");
                return cash_tv;
            }
        });
        initItemCommand.getItemCommand(1).setBeginViewCallBack(new CommonItemCommand.ViewCallBack() {
            @Override
            public View getInflateView() {
                can_withdraw_cash_tv = new TextView(getContext());
                can_withdraw_cash_tv.setText("0");
                return can_withdraw_cash_tv;
            }
        });

        cash.findViewById(R.id.next).setVisibility(View.GONE);
        can_withdraw_cash.findViewById(R.id.next).setVisibility(View.GONE);
        initItemCommand.execute();

        getActionBarView().getRightTextView().setText(getContext().getString(R.string.withdraw_cash));
        getActionBarView().getRightTextView().setVisibility(View.VISIBLE);
        return view;
    }
}
