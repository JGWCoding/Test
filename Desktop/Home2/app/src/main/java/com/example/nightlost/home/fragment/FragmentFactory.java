package com.example.nightlost.home.fragment;

import android.util.Log;

import com.example.nightlost.home.base.BaseFragment;
import com.example.nightlost.home.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongzhi on 2016/9/28.
 * fragment工厂
 */
public class FragmentFactory {
    //控制集合的大小
    private Map<Integer,BaseFragment> mCaches = new HashMap<>();

    private static FragmentFactory sInstance;

    private FragmentFactory(){}

    public static FragmentFactory getInstance(){
        if(sInstance == null){
            sInstance = new FragmentFactory();
        }
        return sInstance;
    }

    public BaseFragment getFragment(int position){
        //做一个集合记录下来所有fragment，下一次直接取出，这样保证了唯一性，就是内存缓存
        BaseFragment fragment;
        if(mCaches.containsKey(position)){
            //上一次已保存的情况，取出来即可
            fragment = mCaches.get(position);
            Log.e("fragment", "取出fragment");
            return fragment;
        }
        switch (position){
            case 0:
                //推荐
                fragment = new RecommendFragment();
                break;
            case 1:
                //高血压
                fragment = new HypertensionFragment();
                break;
            case 2:
                //高血糖
                fragment = new HyperglycemiaFragment();
                break;
            case 3:
                //高血脂
                fragment = new  HyperlipidemiaFragment();
                break;
            case 4:
                //风湿
                fragment = new RheumatismFragment();
                break;
            case 5:
                //鼻炎
                fragment = new CoryzaFragment();
                break;
            case 6:
                //妇科
                fragment = new GynecologyFragment();
                break;
            default:
                //默认推荐
                fragment = new RecommendFragment();
                break;
        }
        mCaches.put(position, fragment);
        LogUtils.d("fragment", "保存fragment");
        return fragment;
    }
}
