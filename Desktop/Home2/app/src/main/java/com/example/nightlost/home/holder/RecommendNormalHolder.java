package com.example.nightlost.home.holder;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightlost.home.R;
import com.example.nightlost.home.base.BaseHolder;
import com.example.nightlost.home.bean.RecommendBean;
import com.example.nightlost.home.conf.httpInfo;
import com.example.nightlost.home.utils.UiUtil;
import com.squareup.picasso.Picasso;

/*
* 普通holder
*
* */

public class RecommendNormalHolder extends BaseHolder<RecommendBean> implements DialogInterface.OnClickListener{

    ImageView mImageview;

    TextView mTvContent;

    ImageView mImgHot;

    TextView mTvComment;

    ImageView mIvDelete;

    @Override
    public View inflateAndFindView() {
        View normalView = View.inflate(UiUtil.getContext(), R.layout.item_news, null);
        mImageview = (ImageView) normalView.findViewById(R.id.imageView);
        mTvContent = (TextView) normalView.findViewById(R.id.tv_content);
        mImgHot = (ImageView) normalView.findViewById(R.id.imageView_hot);
        mTvComment = (TextView) normalView.findViewById(R.id.tv_comment);
        mIvDelete = (ImageView) normalView.findViewById(R.id.iv_delete);
        return normalView;
    }

    @Override
    public void setData(RecommendBean recommendBean) {
        mTvContent.setText(recommendBean.news_content);
        String url = recommendBean.news_img;
        String substring = url.substring(0, 3);
        if (substring.equals("http")){
            Picasso.with(UiUtil.getContext()).load(url).into(mImageview);
        }else{
            Picasso.with(UiUtil.getContext()).load(httpInfo.URL_NEWS_IMG + url).into(mImageview);
        }

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case R.id.tv_comment:
                //评论
                ReadComment();
                break;
            case R.id.iv_delete:
                //删除
                Delete();
                break;
        }
    }
    //以后再做
    private void ReadComment() {

    }
    //以后再做
    private void Delete() {

    }

}
