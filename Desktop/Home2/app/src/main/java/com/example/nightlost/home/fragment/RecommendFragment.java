package com.example.nightlost.home.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.nightlost.home.R;
import com.example.nightlost.home.base.BaseFragment;
import com.example.nightlost.home.base.BaseHolder;
import com.example.nightlost.home.base.BasicAdapter;
import com.example.nightlost.home.bean.RecommendBean;
import com.example.nightlost.home.holder.RecommendNormalHolder;
import com.example.nightlost.home.protocal.RecommendProtocol;
import com.example.nightlost.home.utils.LogUtils;
import com.example.nightlost.home.utils.UiUtil;
import com.example.nightlost.home.utils.ViewUtil;
import com.example.nightlost.home.view.LoadDataUi;

import java.util.List;

/**
 * Created by NightLost on 2017/5/5.
 * 推荐页面
 *
 */

public class RecommendFragment extends BaseFragment {

    private List<RecommendBean> mDatas;
    private RecommendProtocol mProtocol;

    @Override
    protected View onInitSuccessView() {
        ListView mListdView = new ListView(UiUtil.getContext());
        //问诊的view
        View inquiryView = ViewUtil.getView(UiUtil.getContext(),R.layout.activity_fastinquiry);
        //按科室找医生
        View docatorView  = ViewUtil.getView(UiUtil.getContext(),R.layout.activity_find_doctor);
        //消息的view
        View newsView = ViewUtil.getView(UiUtil.getContext(),R.layout.activity_news_notification);


//        mListdView.addHeaderView(inquiryView);
//        mListdView.addHeaderView(docatorView);
//        mListdView.addHeaderView(newsView);

        mListdView.setAdapter(new RecommendAdapter(mDatas));
        return mListdView;
    }

    @Override
    protected LoadDataUi.Result doInbackgroud() {
        RecommendProtocol mProtocol = new RecommendProtocol();
        try {
//            mProtocol.setRequestBody(body);
            List<RecommendBean> recbean = mProtocol.loadData();
            mDatas = recbean;
            LogUtils.d("recommendfragment",mDatas.toString());
            if(mDatas == null|| mDatas.size() ==0){
                LogUtils.d("mdatas","这个家伙为空");
                return LoadDataUi.Result.EMPTY;
            }
            return LoadDataUi.Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUi.Result.ERROR;
        }
    }

    private class RecommendAdapter extends BasicAdapter<RecommendBean> {

        public RecommendAdapter(List<RecommendBean> mDatas) {
            super(mDatas);
        }

        //扩展的时候再用（需要自己写）
//        @Override
//        protected int getNormalType(int position) {
//            RecommendBean bean = mDatas.get(position);
//            if(bean.type== RecommendBean.TYPE_NORMAL){
//                return TYPE_NORMAL;
//            }if else(bean.type== RecommendBean.TYPE_TITLE){
//                return TYPE_TITLE;
//            }if else(bean.type== RecommendBean.TYPE_TYPE_IMAGE){
//                return TYPE_IMAGE;
//            }else{
//                return TYPE_MOVIE;
//            }
//        }

        @Override
        public int getViewTypeCount() {
            //TODO:多几种就加几
            return super.getViewTypeCount();
        }

        @Override
        protected BaseHolder getItemHolder(int position) {
            //TODO:扩展的时候再用
            RecommendBean bean = mDatas.get(position);
//            if(bean.type == RecommendBean.TYPE_NORMAL){
//                return new RecommendNormalHolder();
//            }else{
//                return new RecommendTitleHolder();//我已经写好
//            }
            LogUtils.d("RecommendFragment",bean.type + "");
            return new RecommendNormalHolder();

        }

        @Override
        protected List<RecommendBean> getMoreData() throws Exception {
            return null;
        }
    }
}
