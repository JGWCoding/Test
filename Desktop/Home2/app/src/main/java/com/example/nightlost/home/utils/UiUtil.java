package com.example.nightlost.home.utils;

import android.content.Context;
import android.os.Handler;

import com.example.nightlost.home.view.GpApplication;

/**
 * 我们项目特有组件,获取context,提交任务，读取string。。。。
 */
public class UiUtil {

    private static Context context;
    private static Handler mHandler;

    public static Context getContext() {
        return context;
    }

    public static void init(GpApplication gpApplication) {
        //application是context的子类
        context = gpApplication;
//        TextView tv = new TextView(gpApplication);
//        context.startActivity(new Intent(context,AbsActivity.class));
        mHandler = new Handler();
    }

    /**
     * 提交一个任务
     */
    public static void post(Runnable task){
        mHandler.post(task);
    }

    /**
     * 延时提交一个任务
     */
    public static void postDelay(Runnable task,long delay){
        mHandler.postDelayed(task, delay);
    }

    /**
     * 取消一个任务
     */
    public static void cacel(Runnable task) {
        mHandler.removeCallbacks(task);
    }

    public static String[] getStringArray(int resId){
        return context.getResources().getStringArray(resId);
    }

    /**
     * 获取带占位符的string,
     */
    public static String getString(int resId, Object... formatArgs){
        return context.getResources().getString(resId, formatArgs);
    }

}
