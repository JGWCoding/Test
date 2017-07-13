package com.example.nightlost.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightlost.home.R;
import com.example.nightlost.home.base.BaseHolder;
import com.example.nightlost.home.bean.RecommendBean;
import com.example.nightlost.home.utils.UiUtil;

/*
* 超哥到时候用到的时候自己写吧,算了，还是我写好吧
* */

public class RecommendTitleHolder extends BaseHolder<RecommendBean> {

    private TextView mTvTitle;
    private ImageView mIvHot;
    private TextView mTvFrom;
    private TextView mTvComment;
    private ImageView mIvDelete;

    @Override
    public View inflateAndFindView() {
        View mTitleView = View.inflate(UiUtil.getContext(), R.layout.item_news, null);
        mTvTitle = (TextView) mTitleView.findViewById(R.id.tv_content);
        mIvHot = (ImageView) mTitleView.findViewById(R.id.imageView_hot);
        mTvFrom = (TextView) mTitleView.findViewById(R.id.tv_from);
        mTvComment = (TextView) mTitleView.findViewById(R.id.tv_comment);
        mIvDelete = (ImageView) mTitleView.findViewById(R.id.iv_delete);
        return mTitleView;
    }

    @Override
    public void setData(RecommendBean recommendBean) {
        mTvTitle.setText(recommendBean.title);
        //剩下的就交给超哥了

    }
}
