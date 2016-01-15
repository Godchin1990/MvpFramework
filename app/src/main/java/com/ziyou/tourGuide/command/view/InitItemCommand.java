package com.ziyou.tourGuide.command.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ziyou.tourGuide.command.base.Command;
import com.ziyou.tourGuide.model.ItemViewMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/14.
 */
public class InitItemCommand implements Command {

    private final Context context;
    private List<MeItemCommand> commands = new ArrayList<>();

    public InitItemCommand(Context context, List<View> views, List<ItemViewMode> viewModeList) {
        this.context = context;
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
        bundle.putInt(MeItemCommand.PARAM_TITLE, mode.getTitleId());
        bundle.putInt(MeItemCommand.PARAM_ICON, mode.getIconId());
        return bundle;
    }

    public MeItemCommand getItemCommand(int position){
        return commands.get(position);
    }

}
