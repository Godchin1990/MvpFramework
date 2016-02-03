package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IMyMessageDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/2/3.
 */
public class MyMessageDetailView extends TitleBarContentView implements IMyMessageDetailView {

    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.content)
    TextView content;

    public MyMessageDetailView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_my_message_detail,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public TextView getDateTextView() {
        return date;
    }

    @Override
    public TextView getContentTextView() {
        return content;
    }
}
