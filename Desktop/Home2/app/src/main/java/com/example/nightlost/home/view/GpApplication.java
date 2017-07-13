package com.example.nightlost.home.view;

import android.app.Application;

import com.example.nightlost.home.utils.UiUtil;

public class GpApplication extends Application {

    /**
     * 应用入口，第一时间执行,做一些初始化工作（用户行为统计工具，常见第三方youmeng,）
     * 开发者也需要初始化组件，内存检测工具，
     * 自定义组件，
     */

    @Override
    public void onCreate() {
        super.onCreate();
        UiUtil.init(this);
    }
}