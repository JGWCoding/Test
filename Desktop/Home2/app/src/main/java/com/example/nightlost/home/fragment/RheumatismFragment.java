package com.example.nightlost.home.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.nightlost.home.base.BaseFragment;
import com.example.nightlost.home.utils.UiUtil;
import com.example.nightlost.home.view.LoadDataUi;

/**
 * Created by NightLost on 2017/5/5.
 * 风湿
 */

public class RheumatismFragment extends BaseFragment {
    @Override
    protected View onInitSuccessView() {
        TextView tv = new TextView(UiUtil.getContext());
        tv.setText("这是风湿页面");
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    @Override
    protected LoadDataUi.Result doInbackgroud() {

        return LoadDataUi.Result.SUCCESS;
    }
}
