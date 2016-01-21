package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IAmendPasswordView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/21.
 */
public class AmendPasswordView extends TitleBarContentView implements IAmendPasswordView {

    @Bind(R.id.old_password)
    View old_password;
    @Bind(R.id.new_password)
    View new_password;
    @Bind(R.id.setting)
    Button setting;

    EditText old_password_et;
    EditText new_password_et;

    public AmendPasswordView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_password_setting,null);
        ButterKnife.bind(this, view);
        List<View> viewList = new ArrayList<>();
        viewList.add(old_password);
        viewList.add(new_password);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.old_password));
        viewModeList.add(new ItemViewMode(R.string.confirm_password));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
        }

        old_password_et = (EditText) old_password.findViewById(R.id.item_edittext);
        new_password_et = (EditText) new_password.findViewById(R.id.item_edittext);

        return view;
    }

    @Override
    public EditText getOldPasswordEditText() {
        return old_password_et;
    }

    @Override
    public EditText getNewPasswordEditText() {
        return new_password_et;
    }

    @Override
    public Button getSettingButton() {
        return setting;
    }
}
