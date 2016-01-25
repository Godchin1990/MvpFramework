package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.ISettingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/12.
 */
public class SettingView extends TitleBarContentView implements ISettingView {

    public static final String TAG_CANCEL_LOGIN = "tag_cancel_login";
    public static final String TAG_CLEAR_CACHE = "tag_clear_cache";

    @Bind(R.id.clear_cache)
    View clear_cache;
    @Bind(R.id.use_help)
    View use_help;
    @Bind(R.id.about_me)
    View about_me;
    @Bind(R.id.un_login)
    Button un_login;

    private AlertDialog cancelLoginDialog;
    private AlertDialog clearCacheDialog;

    public SettingView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_setting,null);
        ButterKnife.bind(this, view);
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

    @Override
    public void showCancelLoginDialog() {
        if(cancelLoginDialog ==null){
            cancelLoginDialog = new AlertDialog.Builder(getContext()).create();
            cancelLoginDialog.show();
            cancelLoginDialog.getWindow().setContentView(R.layout.dialog_common);

            TextView tv_dialog_title = (TextView) cancelLoginDialog.getWindow()
                    .findViewById(R.id.tv_dialog_title);
            tv_dialog_title.setText(getContext().getString(R.string.cancel_login));

            TextView tv_dialog_content = (TextView) cancelLoginDialog.getWindow()
                    .findViewById(R.id.tv_dialog_content);
            tv_dialog_content.setText(getContext().getString(R.string.is_sure_cancel_login));

            cancelLoginDialog.getWindow()
                    .findViewById(R.id.btn_dialog_left)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancelLoginDialog.dismiss();
                            ClickEvent clickEvent = new ClickEvent(TAG_CANCEL_LOGIN);
                            EventBus.getDefault().post(clickEvent);
                        }
                    });
            cancelLoginDialog.getWindow()
                    .findViewById(R.id.btn_dialog_right)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancelLoginDialog.dismiss();
                        }
                    });
        }else {
            cancelLoginDialog.show();
        }

    }

    @Override
    public void showClearCacheDialog() {
        if(clearCacheDialog ==null){
            clearCacheDialog = new AlertDialog.Builder(getContext()).create();
            clearCacheDialog.show();
            clearCacheDialog.getWindow().setContentView(R.layout.dialog_common);

            TextView tv_dialog_title = (TextView) clearCacheDialog.getWindow()
                    .findViewById(R.id.tv_dialog_title);
            tv_dialog_title.setText(getContext().getString(R.string.clear_cache));

            TextView tv_dialog_content = (TextView) clearCacheDialog.getWindow()
                    .findViewById(R.id.tv_dialog_content);
            tv_dialog_content.setText(getContext().getString(R.string.is_sure_clear_cache));

            clearCacheDialog.getWindow()
                    .findViewById(R.id.btn_dialog_left)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clearCacheDialog.dismiss();
                            ClickEvent clickEvent = new ClickEvent(TAG_CLEAR_CACHE);
                            EventBus.getDefault().post(clickEvent);
                        }
                    });
            clearCacheDialog.getWindow()
                    .findViewById(R.id.btn_dialog_right)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clearCacheDialog.dismiss();
                        }
                    });
        }else {
            clearCacheDialog.show();
        }
    }
}
