package com.example.edward.mvpframework.command;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 15/12/9.
 */
public class SimpleDraweeViewCommand implements Command {

    public static final String TAG = "SimpleDraweeViewCommand";

    public static final String TYPE_URL = "TYPE_URL";

    private final SimpleDraweeView simpleDraweeView;
    private String url;

    public SimpleDraweeViewCommand(SimpleDraweeView simpleDraweeView, String url) {
        this.simpleDraweeView = simpleDraweeView;
        this.url = url;
    }

    @Override
    public void execute() {
//        url = "http://selftravel-image.qiniudn.com/b39c4de2c63021c086883e1ca20449fd.jpg";
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }
}
