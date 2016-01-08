package com.example.edward.mvpframework.command;

import android.text.TextUtils;
import android.widget.ImageView;

import com.example.edward.mvpframework.command.base.Command;
import com.squareup.picasso.Picasso;

/**
 * Created by Edward on 16/1/6.
 */
public class ImageViewCommand implements Command {

    private ImageView imageView;
    private String url;

    public ImageViewCommand(ImageView imageView,String url) {
        this.imageView = imageView;
        this.url = url;
    }

    @Override
    public void execute() {
        if(!TextUtils.isEmpty(url)){
            Picasso.with(imageView.getContext()).load(url).resize(200,200).centerCrop().into(imageView);
        }
    }
}
