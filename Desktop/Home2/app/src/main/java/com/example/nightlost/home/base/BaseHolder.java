package com.example.nightlost.home.base;

import android.view.View;

public abstract class BaseHolder<T> {

    //第一个任务：填充布局 //第二个任务：查找子view
    public abstract View inflateAndFindView();

    //第三个任务：填充数据
    public abstract void setData(T t);

}
