package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.BannerActivity;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.command.base.Command;
import com.ziyou.tourGuide.command.SimpleDraweeViewCommand;
import com.ziyou.tourGuide.model.HomeBanner;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 15/11/8.
 */
public class HomeBannerViewHolder extends BaseViewHolder<List<HomeBanner>> {
    public ViewPager viewPager;
    public BannerViewPagerAdapter adapter;

    public static View getView(Context context) {
        View bannerView = LayoutInflater.from(context).inflate(R.layout.item_home_banner, null);
        return bannerView;
    }

    public HomeBannerViewHolder(View itemView) {
        super(itemView);
        viewPager = (ViewPager) itemView.findViewById(R.id.head_view_pager);
        adapter = new BannerViewPagerAdapter();
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void inflateView(List<HomeBanner> data) {
        adapter.setData(data);
    }

    public class BannerViewPagerAdapter extends PagerAdapter implements View.OnClickListener {
        private List<HomeBanner> banners = new ArrayList<>();

        public void setData(List<HomeBanner> list) {
            if (!list.isEmpty()) {
                banners = list;
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return banners.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.viewpager_home_banner, null);

            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.home_banner_iv);
            Command command = new SimpleDraweeViewCommand(simpleDraweeView, banners.get(position).getImage());
            command.execute();

            view.setTag(banners.get(position));
            container.addView(view);
            view.setOnClickListener(this);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void onClick(View v) {
            HomeBanner tag = (HomeBanner) v.getTag();
            Intent intent = new Intent(v.getContext(), BannerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Const.BANNER, tag);
            intent.putExtra(Const.BUNDLE, bundle);
            v.getContext().startActivity(intent);
        }
    }
}
