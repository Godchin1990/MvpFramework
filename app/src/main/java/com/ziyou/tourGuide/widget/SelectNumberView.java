package com.ziyou.tourGuide.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ziyou.tourGuide.R;

/**
 * Created by Edward on 16/1/28.
 */
public class SelectNumberView extends FrameLayout implements View.OnClickListener {

    public static final int DEFAULT_NUMBER = 1;

    private int number = DEFAULT_NUMBER;

    private int minNumber = DEFAULT_NUMBER;
    private int maxNumber = Integer.MAX_VALUE;

    private Button decrease_btn;
    private TextView number_tv;
    private Button increate_btn;

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setNumber(int number) {
        this.number = number;
        initData();
        callBack.onItemClick(getCurrentNumber());
    }

    public interface CallBack{
        void onItemClick(int currentNumber);
    }

    public SelectNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SelectNumberView(Context context) {
        this(context, null);
    }

    public SelectNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_select_number,this,true);
        initView(view);
    }

    private void initView(View view) {
        decrease_btn = (Button) view.findViewById(R.id.decrease_btn);
        number_tv = (TextView) view.findViewById(R.id.number_tv);
        increate_btn = (Button) view.findViewById(R.id.increate_btn);
        initData();
    }

    private void initData() {
        number_tv.setText(String.valueOf(number));
        decrease_btn.setOnClickListener(this);
        increate_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //处理并发异常
        synchronized (this){
            switch (v.getId()){
                case R.id.decrease_btn:
                    if(decrease_btn.isClickable()){
                        number--;
                        if(callBack!=null){
                            callBack.onItemClick(number);
                        }
                    }
                    break;
                case R.id.increate_btn:
                    if(increate_btn.isClickable()){
                        number++;
                        if(callBack!=null){
                            callBack.onItemClick(number);
                        }
                    }
                    break;
            }
            refreshView();
        }
    }

    private void refreshView() {
        number_tv.setText(number+"");
        if(number<=minNumber){
            increate_btn.setClickable(true);
            decrease_btn.setClickable(false);
        }else if(number>=maxNumber){
            increate_btn.setClickable(false);
            decrease_btn.setClickable(true);
        }else {
            increate_btn.setClickable(true);
            decrease_btn.setClickable(true);
        }
    }

    public void setMinNumber(int minNumber){
        this.minNumber = minNumber;
    }
    public void setMaxNumber(int maxNumber){
        this.maxNumber = maxNumber;
    }

    public int getCurrentNumber(){
        return number;
    }
    public void setBound(int minNumber,int maxNumber){
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }


}
