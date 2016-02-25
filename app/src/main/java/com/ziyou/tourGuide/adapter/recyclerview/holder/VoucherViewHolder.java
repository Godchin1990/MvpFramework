package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.Voucher;

/**
 * Created by Edward on 16/2/3.
 */
public class VoucherViewHolder extends BaseViewHolder<Voucher> {

    private String userVoucherIcons[] = {
            "未使用",
            "已使用",
            "已过期",
            "已失效"
    };

    private final TextView price;
    private final TextView status;
    private final TextView date;

    public static View getView(Context context) {
        View view =View.inflate(context,R.layout.item_voucher,null);
        return view;
    }

    public VoucherViewHolder(View itemView) {
        super(itemView);
        price = (TextView) itemView.findViewById(R.id.price);
        status = (TextView) itemView.findViewById(R.id.status);
        date = (TextView) itemView.findViewById(R.id.date);

    }

    @Override
    protected void inflateView(int position, Voucher data) {
        price.setText(data.getPrice());
        setStartAndEndTime(date,data.getCreate_time(),data.getValid_until());
        setVoucherState(status,data.getType());

        if (data.getType()==0) {
            itemView.setAlpha(1f);
        } else {
            itemView.setAlpha(0.5f);
        }
    }

    /**
     * 设置代金券起止时间
     */
    private void setStartAndEndTime(TextView textView,String createTime,String untilTime) {
        StringBuilder sb = new StringBuilder();
        String temp = createTime.replace("-", ".");
        sb.append(temp.substring(0,temp.indexOf(" ")));
        sb.append("-");
        temp = untilTime.replace("-",".");
        sb.append(temp.substring(0, temp.indexOf(" ")));

        textView.setText(sb.toString());
    }

    public void setVoucherState(TextView view,int level) {
        if (level < 0 || level > userVoucherIcons.length-1) return;
        view.setText(userVoucherIcons[level]);
    }
}
