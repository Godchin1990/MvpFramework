package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.event.ClickEvent;
import com.ziyou.tourGuide.view.interfaze.IMyMessageView;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/17.
 */
public class MyMessageView <T extends RecyclerView.Adapter> extends RefreshRecyclerView<T> implements IMyMessageView {

    public static final String TAG_DELETE = "tag_delete";

    private AlertDialog deleteDialog;

    public MyMessageView(Context context) {
        super(context);
    }

    @Override
    public void showDeleteMessageDialog() {
        if(deleteDialog ==null){
            deleteDialog = new AlertDialog.Builder(getContext()).create();
            deleteDialog.show();
            deleteDialog.getWindow().setContentView(R.layout.dialog_common);

            TextView tv_dialog_title = (TextView) deleteDialog.getWindow()
                    .findViewById(R.id.tv_dialog_title);
            tv_dialog_title.setText(getContext().getString(R.string.delete_message));

            TextView tv_dialog_content = (TextView) deleteDialog.getWindow()
                    .findViewById(R.id.tv_dialog_content);
            tv_dialog_content.setText(getContext().getString(R.string.is_sure_delete_message));

            deleteDialog.getWindow()
                    .findViewById(R.id.btn_dialog_left)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteDialog.dismiss();
                            ClickEvent clickEvent = new ClickEvent(TAG_DELETE);
                            EventBus.getDefault().post(clickEvent);
                        }
                    });
            deleteDialog.getWindow()
                    .findViewById(R.id.btn_dialog_right)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteDialog.dismiss();
                        }
                    });
        }else {
            deleteDialog.show();
        }
    }
}
