package com.ziyou.tourGuide.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Edward on 16/1/22.
 */
public class AutoSwitchLineLayout extends ViewGroup {
    private final static String TAG = "MyViewGroup";

    private final static int VIEW_MARGIN = 2;

    private int mUserTextSize;
    private int mUserMarginInitial;

    private int mUserPaddingInitial;
    private int mUserLeftPaddingInitial;
    private int mUserTopPaddingInitial;
    private int mUserRightPaddingInitial;
    private int mUserBottomPaddingInitial;

    private Drawable tagBackground;

    public AutoSwitchLineLayout(Context context) {
        super(context);
    }

    public AutoSwitchLineLayout(Context context, AttributeSet attrs) {
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

//        int leftMargin = -1;
//        int topMargin = -1;
//        int rightMargin = -1;
//        int bottomMargin = -1;
//        int margin = -1;

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoSwitchLineLayout,
//                0, 0);
//        final int N = a.getIndexCount();
//        for (int i = 0; i < N; i++) {
//            int attr = a.getIndex(i);
//            switch (attr) {
//                case R.styleable.AutoSwitchLineLayout_child_background:
//                    background = a.getDrawable(attr);
//                    tagBackground = background;
//                    break;
//                case R.styleable.AutoSwitchLineLayout_child_padding:
//                    padding = a.getDimensionPixelSize(attr, -1);
//                    mUserPaddingInitial = padding;
//                    break;
//                case R.styleable.AutoSwitchLineLayout_child_padding_left:
//                    leftPadding = a.getDimensionPixelSize(attr, -1);
//                    mUserLeftPaddingInitial = leftPadding;
//                    break;
//                case R.styleable.AutoSwitchLineLayout_child_padding_top:
//                    topPadding = a.getDimensionPixelSize(attr, -1);
//                    mUserTopPaddingInitial = topPadding;
//                    break;
//                case R.styleable.AutoSwitchLineLayout_child_padding_right:
//                    rightPadding = a.getDimensionPixelSize(attr, -1);
//                    mUserRightPaddingInitial = rightPadding;
//                    break;
//                case R.styleable.AutoSwitchLineLayout_child_padding_bottom:
//                    bottomPadding = a.getDimensionPixelSize(attr, -1);
//                    mUserBottomPaddingInitial = bottomPadding;
//                    break;
//                case R.styleable.AutoSwitchLineLayout_child_textsize:
//                    textSize = a.getInteger(i,textSize);
//                    mUserTextSize = textSize;
//                    break;
//            }
//        }
//        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - VIEW_MARGIN * 2;//实际宽度
        int childCount = getChildCount();
        for(int index = 0;index<childCount;index++){
            View child = getChildAt(index);
//            child.setPadding(PADDING_LEFT, PADDING_HOR, PADDING_RIGHT, PADDING_HOR);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width+mUserMarginInitial;
            if(x>actualWidth){//换行
                x = width;
                rows++;
            }
            y = rows*(height+mUserMarginInitial);
        }
        setMeasuredDimension(actualWidth, y);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.d(TAG, "changed = " + changed + " left = " + l + " top = " + t
                + " right = " + r + " botom = " + b);

        final int count = getChildCount();

        int row = 0;// which row lay you view relative to parent

        int lengthX = l; // right position of child relative to parent

        int lengthY = t; // bottom position of child relative to parent

        for (int i = 0; i < count; i++) {

            final View child = this.getChildAt(i);

            int width = child.getMeasuredWidth();

            int height = child.getMeasuredHeight();

            lengthX += width + VIEW_MARGIN;

            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height
                    + t;

            // if it can't drawing on a same line , skip to next line

            if (lengthX > r) {

                lengthX = width + VIEW_MARGIN + l;

                row++;

                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height
                        + t;

            }

            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);

        }

    }

    /**
     * 设置标签
     * <p>
     *     备注:这个方法并不完美,因为TextView和这个类高度的耦合在一起
     * </p>
     * @param list
     */
//    public void setTagList(List<View> list){
//        for(int i = 0;i<list.size();i++){
//            addView(list.get(i));
//        }
//    }

    public void setTagList(List<String> list,int limit){
        for(int i = 0;i<list.size();i++){
            if(limit>0&&i>=limit) {
                break;
            }
            TextView textView = getTextView();
            textView.setText(list.get(i));
            addView(textView);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0;i<getChildCount();i++){
            getChildAt(i).draw(canvas);
        }

        super.onDraw(canvas);
    }

    private TextView getTextView(){
        TextView textView = new TextView(getContext());
        if(tagBackground!=null){
            textView.setBackgroundDrawable(tagBackground);
        }
        textView.setTextSize(mUserTextSize);
        textView.setPadding(mUserLeftPaddingInitial,mUserTopPaddingInitial,mUserRightPaddingInitial,mUserBottomPaddingInitial);
        return textView;
    }


}
