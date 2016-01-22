package com.ziyou.tourGuide.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziyou.tourGuide.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/22.
 */
public class WordWrapView extends ViewGroup {
    private static final int SIDE_MARGIN = 0;   //左右间距
    private static final int TEXT_MARGIN = 10;

    private int mUserTextSize;
    private int mUserMarginInitial;

    private int mUserPaddingInitial;
    private int mUserLeftPaddingInitial;
    private int mUserTopPaddingInitial;
    private int mUserRightPaddingInitial;
    private int mUserBottomPaddingInitial;

    private Drawable tagBackground;

    interface WordWrapViewAdapter<T>{
        View getItemView();
        void inflateItemView(View view, T list);
    }

    WordWrapViewAdapter adapter;

    /**
     * @param context
     */
    public WordWrapView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WordWrapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * @param context
     * @param attrs
     */
    public WordWrapView(Context context, AttributeSet attrs) {
        super(context, attrs);
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

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WordWrapView,
                0, 0);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.WordWrapView_child_background:
                    background = a.getDrawable(attr);
                    tagBackground = background;
                    break;
                case R.styleable.WordWrapView_child_padding:
                    padding = a.getDimensionPixelSize(attr, -1);
                    mUserPaddingInitial = padding;
                    break;
                case R.styleable.WordWrapView_child_padding_left:
                    leftPadding = a.getDimensionPixelSize(attr, -1);
                    mUserLeftPaddingInitial = leftPadding;
                    break;
                case R.styleable.WordWrapView_child_padding_top:
                    topPadding = a.getDimensionPixelSize(attr, -1);
                    mUserTopPaddingInitial = topPadding;
                    break;
                case R.styleable.WordWrapView_child_padding_right:
                    rightPadding = a.getDimensionPixelSize(attr, -1);
                    mUserRightPaddingInitial = rightPadding;
                    break;
                case R.styleable.WordWrapView_child_padding_bottom:
                    bottomPadding = a.getDimensionPixelSize(attr, -1);
                    mUserBottomPaddingInitial = bottomPadding;
                    break;
                case R.styleable.WordWrapView_child_textsize:
                    textSize = a.getInteger(i,textSize);
                    mUserTextSize = textSize;
                    break;
            }
        }
        a.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = 0;// 横坐标开始
        int y = 0;//纵坐标开始
        int rows = 1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            if(i==0){
                x += width;
            }else {
                x += width + TEXT_MARGIN;
            }
            if (x > autualWidth) {
                x = width + SIDE_MARGIN;
                rows++;
            }
            y = rows * (height + TEXT_MARGIN);
            view.layout(x - width, y - height, x, y);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - SIDE_MARGIN * 2;//实际宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + TEXT_MARGIN;
            if (x > actualWidth) {//换行
                x = width;
                rows++;
            }
            y = rows * (height + TEXT_MARGIN);
        }
        setMeasuredDimension(actualWidth, y);
    }

    public void setLabelList(List<String> list,int limit){
        removeAllViews();
        for(int i = 0;i<list.size();i++){
            if(limit>0&&i>=limit) {
                break;
            }
            TextView view = getTextView();
            assert view != null;
            view.setText(list.get(i));
            addView(view);
        }
    }

    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        textView.setBackgroundDrawable(tagBackground);
        textView.setPadding(mUserLeftPaddingInitial, mUserTopPaddingInitial, mUserRightPaddingInitial, mUserBottomPaddingInitial);
        textView.setTextSize(mUserTextSize);
        return textView;
    }

    public List<TextView> getLabelTextViewList(){
        List<TextView> list = new ArrayList<>();
        for(int i = 0;i<getChildCount();i++){
            View childAt = getChildAt(i);
            if(childAt instanceof TextView){
                list.add((TextView) childAt);
            }
        }
        return list;
    }

    public <T> void setLabelList(List<T> list,int limit,WordWrapViewAdapter<T> adapter){
        removeAllViews();
        for(int i = 0;i<list.size();i++){
            if(limit>0&&i>=limit) {
                break;
            }
            View view = adapter.getItemView();
            assert view != null;
            adapter.inflateItemView(view,list.get(i));
            addView(view);
        }
    }

}
