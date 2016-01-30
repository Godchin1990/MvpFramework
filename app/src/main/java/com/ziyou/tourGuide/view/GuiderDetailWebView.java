package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.WebContentBottomView;
import com.ziyou.tourGuide.view.interfaze.IGuiderDetailWebView;

/**
 * Created by Edward on 16/1/25.
 */
public class GuiderDetailWebView extends WebContentBottomView implements IGuiderDetailWebView {
    public static final String TAG_CALL_PHONE = "tag_call_phone";


    public GuiderDetailWebView(Context context) {
        super(context);
    }

    @Override
    public View setBottomLayout() {
        View view = View.inflate(getContext(), R.layout.layout_guider_detail_bottom,null);
        return view;
    }
}
