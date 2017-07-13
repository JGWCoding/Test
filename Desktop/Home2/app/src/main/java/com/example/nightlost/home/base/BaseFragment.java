package com.example.nightlost.home.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightlost.home.utils.UiUtil;
import com.example.nightlost.home.view.LoadDataUi;

/**
 * 基类，预留一个层次，方便以后扩展
 */

public abstract class BaseFragment extends Fragment {

    private LoadDataUi mLoadDataUi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("MainHomeActivity", "onCreateView");
        //把添加3个页面的任务交给LoadDataUi
        if(mLoadDataUi == null) {
            mLoadDataUi = new LoadDataUi(UiUtil.getContext()) {
                @Override
                protected View getSuccessView() {
                    return onInitSuccessView();
                }

                @Override
                protected Result getData() {
                    //获取数据
                    return doInbackgroud();
                }
            };
        }
        return mLoadDataUi;
    }

    protected abstract View onInitSuccessView();

    protected abstract LoadDataUi.Result doInbackgroud();

    /**
     * fragment已经挂载到activity上，可以开始请求数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("MainActivity", "onActivityCreated");

    }

    public void loadData(){
        mLoadDataUi.loadData();
    }

}
