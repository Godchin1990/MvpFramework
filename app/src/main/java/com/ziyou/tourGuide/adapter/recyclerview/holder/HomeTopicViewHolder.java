package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.TopicActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.command.SimpleDraweeViewCommand;
import com.ziyou.tourGuide.command.base.Command;
import com.ziyou.tourGuide.helper.SPHelper;
import com.ziyou.tourGuide.model.HomeTopic;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Home页面Topic item的Holder
 * Created by Edward on 15/11/8.
 */
public class HomeTopicViewHolder extends BaseViewHolder<HomeTopic> implements View.OnClickListener {

    public SimpleDraweeView content_iv ;
    public TextView home_topic_title;
    public TextView home_topic_discription;

    private int height;

    public static View getView(Context context) {
        View topicView = LayoutInflater.from(context).inflate(R.layout.item_home_topic, null);
        return topicView;
    }

    public HomeTopicViewHolder(View itemView) {
        super(itemView);
        content_iv = (SimpleDraweeView) itemView.findViewById(R.id.home_topic_iv);
        home_topic_title = (TextView) itemView.findViewById(R.id.home_topic_title);
        home_topic_discription = (TextView) itemView.findViewById(R.id.home_topic_discription);

        SharedPreferences sp = SPHelper.getInstance().getSharedPreference(itemView.getContext());
        int scale = sp.getInt(SPHelper.Const.windowWidth, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(scale, scale);
        content_iv.setLayoutParams(layoutParams);

    }

    @Override
    protected void inflateView(int position, HomeTopic data) {

        Command command = new SimpleDraweeViewCommand(content_iv,data.getImage());
        command.execute();

        home_topic_title.setText(data.getName());
        home_topic_discription.setText(String.format(getResources().getString(R.string.guider_route), data.getGuide(), data.getRoute()));

        content_iv.setTag(data);
        content_iv.setOnClickListener(this);

    }

    public void setOffset() {
        int itemViewHeight = itemView.getHeight();
        int imageViewHeight = content_iv.getHeight();

        int parentOffsetMax = (getParentHeight() + itemViewHeight) / 2;
        int offsetMax = imageViewHeight - itemViewHeight;

        float y = itemView.getY();
        float location = y + itemViewHeight / 2;
        float v = location - getParentHeight() / 2;

        float percent = v / parentOffsetMax;

        int margin = (int) ((offsetMax / 2) * (percent + 1));

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) content_iv.getLayoutParams();
        layoutParams.setMargins(0, -margin, 0, 0);
        content_iv.setLayoutParams(layoutParams);
    }

    public void setParentHeight(int height) {
        this.height = height;
    }

    public int getParentHeight() {
        return height;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_topic_iv:
                HomeTopic tag = (HomeTopic) v.getTag();
                Intent intent = new Intent(v.getContext(),TopicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Const.TOPIC_TYPE,tag.getType());
                bundle.putString(Const.TOPIC_NAME, tag.getName());
                intent.putExtra(Const.BUNDLE,bundle);
                v.getContext().startActivity(intent);
                break;
        }
    }
}
