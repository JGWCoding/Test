package com.example.nightlost.home.base;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nightlost.home.R;
import com.example.nightlost.home.utils.UiUtil;

public class LoadMoreHolder extends BaseHolder<Integer> {

    public static int STATE_LOADING_MORE = 1;
    public static int STATE_RETRY = 2;
    public static int STATE_NO_MORE = 0; //没有更多数据了

    LinearLayout loadingContainer;

    TextView itemLoadmoreTvRetry;

    LinearLayout retryContainer;

    @Override
    public View inflateAndFindView() {
        View loadMoreView = View.inflate(UiUtil.getContext(), R.layout.item_load_more, null);
        loadingContainer = (LinearLayout) loadMoreView.findViewById(R.id.item_loadmore_container_loading);
        itemLoadmoreTvRetry = (TextView) loadMoreView.findViewById(R.id.item_loadmore_tv_retry);
        retryContainer = (LinearLayout) loadMoreView.findViewById(R.id.item_loadmore_container_retry);
        return loadMoreView;
    }

    @Override
    public void setData(Integer state) {
        if (state == STATE_LOADING_MORE) {
            //显示第一个容器
            retryContainer.setVisibility(View.GONE);
            loadingContainer.setVisibility(View.VISIBLE);
        } else if (state == STATE_RETRY) {
            //显示retry
            retryContainer.setVisibility(View.VISIBLE);
            loadingContainer.setVisibility(View.GONE);
        } else {
            retryContainer.setVisibility(View.GONE);
            loadingContainer.setVisibility(View.GONE);
        }
    }
}