package com.example.nightlost.home.protocal;

import com.example.nightlost.home.base.BaseProtocol;
import com.example.nightlost.home.bean.RecommendBean;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 添加请求参数
 */
public class RecommendProtocol extends BaseProtocol<List<RecommendBean>> {

    public String PAGESIZE = 12 + "";
    public String DIVISON_ID = 1 + "";
    public String PAGENUM = 1 + "";

    @Override
    protected RequestBody getUrlBody() {
        RequestBody body = new FormBody.Builder()
                .add("divison_id",DIVISON_ID)
                .add("pageNum",PAGENUM)
                .add("pageSize",PAGESIZE)
                .build();
        return body;
    }
}
