package com.ziyou.tourGuide.command.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.command.base.Command;

/**
 * Created by Edward on 16/1/14.
 */
public class CommonItemCommand implements Command {

    public static final String PARAM_TITLE = "title";
    public static final String PARAM_ICON = "iconId";

    private View itemView;
    private Bundle itemBundle;

    private ViewCallBack endViewCallBack;
    private ViewCallBack startViewCallBack;

    private final int titleId;
    private final int iconId;

    public CommonItemCommand(View itemView, Bundle itemBundle) {
        this.itemView = itemView;
        this.itemBundle = itemBundle;

        titleId = itemBundle.getInt(PARAM_TITLE);
        iconId = itemBundle.getInt(PARAM_ICON);
    }

    public CommonItemCommand(View itemView, Bundle itemBundle, ViewCallBack endViewCallBack) {
        this.itemView = itemView;
        this.itemBundle = itemBundle;
        this.endViewCallBack = endViewCallBack;

        titleId = itemBundle.getInt(PARAM_TITLE);
        iconId = itemBundle.getInt(PARAM_ICON);
    }

    /**
     * 设置获得layout的回调函数callback
     * @param viewCallBack
     */
    public void setEndViewCallBack(ViewCallBack viewCallBack) {
        this.endViewCallBack = viewCallBack;
    }

    /**
     * 设置获得layout的回调函数callback
     * @param viewCallBack
     */
    public void setBeginViewCallBack(ViewCallBack viewCallBack) {
        this.startViewCallBack = viewCallBack;
    }

    @Override
    public void execute() {
        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.item_icon);
        TextView itemText = (TextView) itemView.findViewById(R.id.item_text);
        if(iconId!=0){
            itemIcon.setImageDrawable(itemView.getResources().getDrawable(iconId));
        }else {
            itemIcon.setVisibility(View.GONE);
        }
        if(titleId!=0){
            itemText.setText(itemView.getResources().getString(titleId));
        }
        if(endViewCallBack !=null){
            ViewGroup viewGroup = (ViewGroup) itemView.findViewById(R.id.item_end_layout);
            viewGroup.addView(endViewCallBack.getInflateView());
        }

        if(startViewCallBack !=null){
            ViewGroup viewGroup = (ViewGroup) itemView.findViewById(R.id.item_begin_layout);
            viewGroup.addView(startViewCallBack.getInflateView());
        }
    }

    public interface ViewCallBack{
        View getInflateView();
    }
}
