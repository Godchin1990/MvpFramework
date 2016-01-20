package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.ISettingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/12.
 */
public class SettingView extends TitleBarContentView implements ISettingView {

    @Bind(R.id.clear_cache)
    View clear_cache;
    @Bind(R.id.use_help)
    View use_help;
    @Bind(R.id.about_me)
    View about_me;
    @Bind(R.id.un_login)
    Button un_login;


    public SettingView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_setting,null);
        ButterKnife.bind(this,view);
        initSettingItemList();
        return view;
    }

    private void initSettingItemList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(clear_cache);
        viewList.add(use_help);
        viewList.add(about_me);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.clear_cache));
        viewModeList.add(new ItemViewMode(R.string.use_helpe));
        viewModeList.add(new ItemViewMode(R.string.about_me));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
        }

    }

    @Override
    public View getClearCache() {
        return clear_cache;
    }

    @Override
    public View getUseHelp() {
        return use_help;
    }

    @Override
    public View getAboutMe() {
        return about_me;
    }

    @Override
    public Button getUnloginButton() {
        return un_login;
    }
}
