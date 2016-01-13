package com.ziyou.tourGuide.widget.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * <p>
 *     可刷新控件,包含 正在加载 & 成功加载 & 加载失败 & 加载为空 四个页面<br/>
 *     并管理这四个页面的显示
 * </p>
 * Created by Edward on 15/10/28.
 */
public class RefreshViewContainer extends FrameLayout {

    public static final int STATE_LOADING = 0;
    public static final int STATE_SUCCESS = 1;
    public static final int STATE_EMPTY = 2;
    public static final int STATE_ERROR = 3;

    private int currentState = STATE_LOADING;

    private FrameLayout errorView;
    private FrameLayout successView;
    private FrameLayout emptyView;
    private FrameLayout loadingView;

    public RefreshViewContainer(Context context) {
        this(context, null);
    }
    private RefreshViewAdapter adapter;

    public void setAdapter(RefreshViewAdapter adapter){
        this.adapter = adapter;
        setErrorView(adapter.setErrorView());
        setSuccessView(adapter.setSuccessView());
        setEmptyView(adapter.setEmptyView());
        setLoadingView(adapter.setLoadingView());
    }

    public RefreshViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        errorView = new FrameLayout(context);
        successView = new FrameLayout(context);
        emptyView = new FrameLayout(context);
        loadingView = new FrameLayout(context);

        addView(errorView);
        addView(successView);
        addView(emptyView);
        addView(loadingView);

        reloadView();
    }

    /**
     * 根据当前状态currentState,显示某一个界面
     */
    private void reloadView() {
        switch (currentState){
            case STATE_LOADING:
                errorView.setVisibility(INVISIBLE);
                successView.setVisibility(INVISIBLE);
                emptyView.setVisibility(INVISIBLE);
                loadingView.setVisibility(VISIBLE);
                break;
            case STATE_EMPTY:
                errorView.setVisibility(INVISIBLE);
                successView.setVisibility(INVISIBLE);
                emptyView.setVisibility(VISIBLE);
                loadingView.setVisibility(INVISIBLE);
                break;
            case STATE_ERROR:
                errorView.setVisibility(VISIBLE);
                successView.setVisibility(INVISIBLE);
                emptyView.setVisibility(INVISIBLE);
                loadingView.setVisibility(INVISIBLE);
                break;
            case STATE_SUCCESS:
                errorView.setVisibility(INVISIBLE);
                successView.setVisibility(VISIBLE);
                emptyView.setVisibility(INVISIBLE);
                loadingView.setVisibility(INVISIBLE);
                break;
        }
    }

    public void showCurrentView(){
        reloadView();
    }
    /**
     * 设置容器当前状态,并显示对应状态的View
     * @param currentState 设置当前状态
     */
    public void setCurrentState(int currentState) {
        if(currentState != this.currentState){
            this.currentState = currentState;
            reloadView();
        }
    }

    public View getErrorView() {
        return errorView;
    }

    public View getSuccessView() {
        return successView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public void setErrorView(View view) {
        errorView.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setSuccessView(View view) {
        successView.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setEmptyView(View view) {
        emptyView.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setLoadingView(View view) {
        loadingView.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
