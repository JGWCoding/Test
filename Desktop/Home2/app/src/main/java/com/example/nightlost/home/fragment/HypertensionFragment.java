package com.example.nightlost.home.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nightlost.home.R;
import com.example.nightlost.home.base.BaseFragment;
import com.example.nightlost.home.bean.RecommendBean;
import com.example.nightlost.home.conf.httpInfo;
import com.example.nightlost.home.utils.UiUtil;
import com.example.nightlost.home.view.LoadDataUi;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by NightLost on 2017/5/5.
 * 高血压
 */

public class HypertensionFragment extends BaseFragment {
    private List<RecommendBean> datas;
    public String PAGESIZE = 12 + "";
    public String DIVISON_ID = 1 + "";
    public String PAGENUM = 1 + "";
    @Override
    protected View onInitSuccessView() {
        ListView lv = new ListView(UiUtil.getContext());
        lv.setAdapter(new HypertensionAdapter(datas));
        return lv;
    }

    @Override
    protected LoadDataUi.Result doInbackgroud() {
        String url = httpInfo.URL_NEWS;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("divison_id",DIVISON_ID)
                .add("pageNum",PAGENUM)
                .add("pageSize",PAGESIZE)
                .build();
        Request request = new Request.Builder().post(body).url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String result = response.body().string();
                JSONObject object = new JSONObject(result);
                String data = object.getString("data");
                JSONArray array = new JSONArray(data);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    RecommendBean bean = new RecommendBean();
                    String content = jsonObject.getString("news_content");
                    String image = jsonObject.getString("news_img");
                    bean.news_content = content;
                    bean.news_img = image;
                    datas.add(bean);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUi.Result.ERROR;
        }
        if(datas == null){
            return LoadDataUi.Result.EMPTY;
        }
        return LoadDataUi.Result.SUCCESS;
    }

    private class HypertensionAdapter extends BaseAdapter {

        List<RecommendBean> mDatas = new ArrayList<>();

        public HypertensionAdapter(List<RecommendBean> datas) {
            mDatas = datas;
        }

        @Override
        public int getCount() {
            if(mDatas != null){
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            if(mDatas != null){
                return mDatas.get(i);
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = View.inflate(UiUtil.getContext(), R.layout.item_news, null);
                holder = new ViewHolder();
                holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_content);
                holder.mIvImag = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            RecommendBean bean = mDatas.get(i);
            holder.mTvTitle.setText(bean.news_content);
            String url = bean.news_img;
            String substring = url.substring(0, 3);
            if (substring.equals("http")){
                Picasso.with(UiUtil.getContext()).load(url).into(holder.mIvImag);
            }else{
                Picasso.with(UiUtil.getContext()).load(httpInfo.URL_NEWS_IMG + url).into(holder.mIvImag);
            }
            return convertView;
        }
    }
    class ViewHolder{
        TextView mTvTitle;
        ImageView mIvImag;
    }
}
