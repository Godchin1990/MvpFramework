package com.ziyou.tourGuide.command.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.command.base.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/14.
 */
public class InitMeItemCommand implements Command {

    private final Context context;
    private List<MeItemCommand> commands = new ArrayList<>();

    public InitMeItemCommand(Context context,List<View> views) {
        this.context = context;
        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.my_message,R.mipmap.ic_my_message));
        viewModeList.add(new ItemViewMode(R.string.guider_area,R.mipmap.ic_guider_area));
        viewModeList.add(new ItemViewMode(R.string.my_tour,R.mipmap.ic_my_tour));
        viewModeList.add(new ItemViewMode(R.string.my_wallet,R.mipmap.ic_my_wallet));
        viewModeList.add(new ItemViewMode(R.string.customer_service,R.mipmap.ic_customer_service));
        viewModeList.add(new ItemViewMode(R.string.setting,R.mipmap.ic_setting));

        for (int i = 0;i< views.size();i++){
            MeItemCommand command = new MeItemCommand(views.get(i), getBundle(viewModeList.get(i)));
            commands.add(command);
        }
    }

    @Override
    public void execute() {
        for (Command command:commands){
            command.execute();
        }
    }

    private Bundle getBundle(ItemViewMode mode){
        Bundle bundle = new Bundle();
        bundle.putString(MeItemCommand.PARAM_TITLE,mode.title);
        bundle.putInt(MeItemCommand.PARAM_ICON, mode.iconId);
        return bundle;
    }

    public MeItemCommand getItemCommand(int position){
        return commands.get(position);
    }

    class ItemViewMode{
        String title;
        int iconId;
        public ItemViewMode(int titleId,int iconId) {
            this.title = context.getResources().getString(titleId);
            this.iconId = iconId;
        }
    }

}
