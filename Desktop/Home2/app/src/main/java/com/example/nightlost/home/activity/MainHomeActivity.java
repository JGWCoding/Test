package com.example.nightlost.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nightlost.home.R;
import com.example.nightlost.home.base.BaseFragment;
import com.example.nightlost.home.fragment.FragmentFactory;
import com.example.nightlost.home.utils.UiUtil;

import org.itheima.tabindicator.library.TabIndicator;

import static com.example.nightlost.home.R.id.indicator;

/**
 * Created by NightLost on 2017/5/4.
 * 首页展示页面
 */

public class MainHomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    protected TabIndicator mIndicator;
    protected ViewPager mViewPager;
    private String[] titles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_home);

        initView();
        initData();
        initListener();

    }

    private void initListener() {

        mIndicator.setOnPageChangeListener(this);
    }

    private void initView() {
        mIndicator = (TabIndicator) findViewById(indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData() {
        titles = UiUtil.getStringArray(R.array.pagers);
        mViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
        mIndicator.setViewPager(mViewPager);
    }


    private class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {


        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(titles != null){
                return titles[position];
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            if(titles != null){
                return titles.length;
            }
            return 0;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("MainActivity","getItem");
            Fragment fragment = FragmentFactory.getInstance().getFragment(position);
            return fragment;
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BaseFragment fragment = FragmentFactory.getInstance().getFragment(position);
        fragment.loadData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
