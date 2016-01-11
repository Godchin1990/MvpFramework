package com.example.edward.mvpframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.util.ScreenHelper;

import java.math.BigDecimal;

/**
 * <p>含有四个格子的View</p>
 * Created by zoucy on 2015/11/19.
 */
public class PlainView extends LinearLayout {

    /**
     * 1号格子
     */
    private FrameLayout plain1Fl;
    /**
     * 2号格子
     */
    private FrameLayout plain2Fl;
    /**
     * 3号格子
     */
    private FrameLayout plain3Fl;
    /**
     * 4号格子
     */
    private FrameLayout plain4Fl;

    public PlainView(Context context) {
        super(context);
    }

    public PlainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttrs(attrs);
    }


    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PlainView);
            View itemView;

            int plain1 = typedArray.getResourceId(R.styleable.PlainView_plain1, 0);
            if (plain1 > 0) {
                itemView = LayoutInflater.from(getContext()).inflate(plain1, null);
                plain1Fl.addView(itemView);
            }

            int plain2 = typedArray.getResourceId(R.styleable.PlainView_plain2, 0);
            if (plain2 > 0) {
                itemView = LayoutInflater.from(getContext()).inflate(plain2, null);
                plain2Fl.addView(itemView);
            }

            int plain3 = typedArray.getResourceId(R.styleable.PlainView_plain3, 0);
            if (plain3 > 0) {
                itemView = LayoutInflater.from(getContext()).inflate(plain3, null);
                plain3Fl.addView(itemView);
            }

            int plain4 = typedArray.getResourceId(R.styleable.PlainView_plain4, 0);
            if (plain4 > 0) {
                itemView = LayoutInflater.from(getContext()).inflate(plain4, null);
                plain4Fl.addView(itemView);
            }

            typedArray.recycle();
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_plain, this, true);
        plain1Fl = (FrameLayout) view.findViewById(R.id.sfl_plain_1);
        plain2Fl = (FrameLayout) view.findViewById(R.id.sl_plain_2);
        plain3Fl = (FrameLayout) view.findViewById(R.id.sl_plain_3);
        plain4Fl = (FrameLayout) view.findViewById(R.id.sfl_plain_4);

        Point out = ScreenHelper.getWindowPoint(getContext());
        int screenWidth = out.x;

        // 计算屏幕一半的宽度 与 长格子 的黄金分割
        float gapPx = getContext().getResources().getDimension(R.dimen.gap_item_plain_view);
        BigDecimal bigDecimal = new BigDecimal((screenWidth - gapPx) / 2 / 0.618)
                .setScale(0, BigDecimal.ROUND_HALF_UP);
        int height = bigDecimal.intValue();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        plain2Fl.setLayoutParams(param);
        plain3Fl.setLayoutParams(param);
    }


    public PlainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FrameLayout getPlain1Fl() {
        return plain1Fl;
    }

    public FrameLayout getPlain2Fl() {
        return plain2Fl;
    }

    public FrameLayout getPlain3Fl() {
        return plain3Fl;
    }

    public FrameLayout getPlain4Fl() {
        return plain4Fl;
    }
}
