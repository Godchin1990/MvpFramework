package com.ziyou.tourGuide.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Edward on 16/1/21.
 */
public class AutoSwitchLineTabLayout extends ViewGroup {

    public static final String TAG = "AutoSwitchLineTabLayout";

    //最大Tag个数
    private int tag_max_number;
    //Tag背景
    private Drawable tag_background;

    private int mUserPaddingInitial;

    private int mUserMarginInitial;

    private int tagTextSize;
    private TextPaint mTextPaint;

    public AutoSwitchLineTabLayout(Context context) {
        this(context, null);
    }

    public AutoSwitchLineTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoSwitchLineTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final Resources res = getResources();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.density = res.getDisplayMetrics().density;

        int textSize = 15;
        int maxNumber = -1;
        boolean single_line = false;

        Drawable background = null;

        int leftPadding = -1;
        int topPadding = -1;
        int rightPadding = -1;
        int bottomPadding = -1;
        int padding = -1;

        int leftMargin = -1;
        int topMargin = -1;
        int rightMargin = -1;
        int bottomMargin = -1;
        int margin = -1;

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoSwitchLineTabLayout,
//                0, 0);
//        final int N = a.getIndexCount();
//        for (int i = 0; i < N; i++) {
//            int attr = a.getIndex(i);
//            switch (attr) {
//                case R.styleable.AutoSwitchLineTabLayout_max_number:
//                    tag_max_number = a.getInteger(i, 0);
//                    break;
//                case R.styleable.AutoSwitchLineTabLayout_child_background:
//                    background = a.getDrawable(attr);
//                    break;
//                case R.styleable.AutoSwitchLineTabLayout_child_margin:
//                    padding = a.getDimensionPixelSize(attr, -1);
//                    mUserPaddingInitial = padding;
//                    break;
//                case R.styleable.AutoSwitchLineTabLayout_child_padding:
//                    margin = a.getDimensionPixelSize(attr, -1);
//                    mUserMarginInitial = margin;
//                    break;
//                case R.styleable.AutoSwitchLineTabLayout_child_textsize:
//                    textSize = a.getDimensionPixelSize(attr, textSize);
//                    break;
//                case R.styleable.AutoSwitchLineTabLayout_single_line:
//                    single_line = a.getBoolean(attr, single_line);
//                    break;
//            }
//            a.recycle();
//        }

    }

    public void setTagList(List<String> list){
        TextView textView = new TextView(getContext());
        textView.setTextSize(tagTextSize);
        addView(textView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.d(TAG,"onLayout");
    }
}
