package com.example.nightlost.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.nightlost.home.R;
import com.example.nightlost.home.conf.ThreadManager;
import com.example.nightlost.home.utils.UiUtil;

/**
 * 加载数据的ui界面
 *
 * */

public abstract class LoadDataUi extends FrameLayout {

    //定义几个状态对应显示不同的view
    public static final int STATE_NONE = 0; //初始化是空状态
    public static final int STATE_LOADING = 1; //view初始化完成可以开始加载

    public static final int STATE_ERROR = 2; //没有成功连上服务器
    public static final int STATE_SUCCESS = 4;
    public static final int STATE_EMPTY = 3;

    private int currentState = STATE_NONE;

    private View emptyView;
    private View errorView;
    private View loadingView;
    private View successView;

    public LoadDataUi(Context context) {
        super(context);
        initView();
    }

    public LoadDataUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //在此处处理掉错误页面、空页面、加载页面
        emptyView = View.inflate(getContext(), R.layout.pager_empty, null);
        addView(emptyView);
        errorView = View.inflate(getContext(), R.layout.pager_error, null);
        addView(errorView);
        //创建一个viewgroup，把几个特殊页面都添加进去
        loadingView = View.inflate(getContext(), R.layout.pager_loading, null);
        addView(loadingView);
        updateUi();
    }

    private void updateUi() {
        loadingView.setVisibility(currentState == STATE_LOADING ? VISIBLE:GONE);
        errorView.setVisibility(currentState == STATE_ERROR ? VISIBLE : GONE);
        emptyView.setVisibility(currentState == STATE_EMPTY ? View.VISIBLE : View.GONE);

        if(currentState == STATE_SUCCESS && successView == null){
            //第一次请求成功，创建成功view
            successView = getSuccessView();
            addView(successView);
        }
        //第二次请求成功的情形
        if(successView!=null){
            successView.setVisibility(currentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    protected abstract View getSuccessView();

    public void loadData() {
        //TODO:已经成功的页面不需要再加载，在此处做拦截处理
        //少量用户手速特别快，会对同一页面重复加载（上一次数据未返回，又请求了一次）
        if(currentState == STATE_SUCCESS || currentState == STATE_LOADING){
            return;
        }
        currentState = STATE_LOADING;
        updateUi();
        //使用线程池的原因：排错方便，第二个提高性能：线程比较耗资源，更好管理线程的创建与回收
        ThreadManager.getNormalPool().execute(new LoadDataTask());
    }

    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            //执行耗时操作，获取数据
            Result result = getData();
            currentState = result.getState();
            //再次更新UI
            UiUtil.post(new Runnable() {
                @Override
                public void run() {
                    updateUi();
                }
            });
        }
    }

    public enum Result{
        ERROR(STATE_ERROR),EMPTY(STATE_EMPTY),SUCCESS(STATE_SUCCESS);

        Result(int state) {
            this.state = state;
        }

        private int state;


        public int getState() {
            return state;
        }
    }

    protected abstract Result getData();

}