package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.command.SimpleDraweeViewCommand;
import com.example.edward.mvpframework.command.base.Command;
import com.example.edward.mvpframework.model.Discovery;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryFullViewHolder extends BaseViewHolder<Discovery> {

    private final SimpleDraweeView fullSimpleDraweeView;
    private View fullDesc;

    public static View getView(Context context) {
        View fullView = View.inflate(context, R.layout.item_discovery_no_map,null);
        return fullView;
    }

    public DiscoveryFullViewHolder(View itemView) {
        super(itemView);
        fullSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.full_image);
        fullDesc = itemView.findViewById(R.id.no_map_desc);
    }

    @Override
    protected void inflateView(Discovery data) {
        bindDiscovery(fullSimpleDraweeView, fullDesc, data.getList().get(0));
    }

    private void bindDiscovery(SimpleDraweeView simpleDraweeView,View desc,Discovery.ListEntity model){
        TextView tv_title = (TextView) desc.findViewById(R.id.tv_title);
        TextView tv_desc = (TextView) desc.findViewById(R.id.tv_desc);
        Command simpleDraweeViewCommand = new SimpleDraweeViewCommand(simpleDraweeView,model.getCover());
        tv_title.setText(model.getTitle());
        tv_desc.setText(model.getRoute_num()+"条路线");
        simpleDraweeViewCommand.execute();
    }
}
