package com.ziyou.tourGuide.command.h5;

import android.os.Bundle;

import com.ziyou.tourGuide.command.base.WebBundleCommand;

/**
 * Created by Edward on 16/1/13.
 */
public class EmptyWebBundleCommand extends WebBundleCommand {

    public EmptyWebBundleCommand(String json, Bundle bundle, Object... objects) {
        super(json, bundle,objects);
    }

    @Override
    public void execute() {

    }
}
