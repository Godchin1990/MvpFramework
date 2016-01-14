package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.ILoginView;

/**
 * Created by Edward on 16/1/8.
 */
public class LoginView extends TitleBarContentView implements ILoginView {

    public LoginView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
//        View view =
//        refreshViewContainer = new RefreshViewContainer(getContext());
//        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
//            @Override
//            public View setSuccessView() {
//                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview, null);
//                pullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_to_refresh_recyclerview);
//                pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
//                recyclerView = pullToRefreshRecyclerView.getRefreshableView();
//                adapter = new TopicAdapter();
//                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                recyclerView.setLayoutManager(manager);
//                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
//                recyclerView.setAdapter(adapter);
//                return view;
//            }
//        });
        TextView view = new TextView(getContext());
        view.setText("登陆页面");

        return view;
    }

}
