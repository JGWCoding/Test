package com.example.nightlost.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.nightlost.home.adapter.InquiryAdapter;
import com.example.nightlost.home.bean.InquiryExpandListViewGroupData;
import com.example.nightlost.home.bean.InquiryExpandListViewItemData;
import com.example.nightlost.home.conf.OkHttp;
import com.example.nightlost.home.R;
import com.example.nightlost.home.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by NightLost on 2017/5/4.
 */

public class TabInquiryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ExpandableListView epdListView;

    private List<InquiryExpandListViewGroupData> groupArray = new ArrayList<InquiryExpandListViewGroupData>();
    private List<InquiryExpandListViewGroupData> groups = new ArrayList<InquiryExpandListViewGroupData>();
    private List<List<InquiryExpandListViewItemData>> itemArray = new ArrayList<List<InquiryExpandListViewItemData>>();

    private InquiryAdapter adapter;

    String TAG = "InquiryFragment";

    private String URL_IMAGE = "http://119.23.21.21/ywapp/" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_inquiry);

        epdListView = (ExpandableListView) findViewById(R.id.expandablelistview);
        requestGroupData();

        adapter = new InquiryAdapter(this, groupArray, itemArray);
        epdListView.setAdapter(adapter);
    }

    private void requestGroupData() {
        LogUtils.d(TAG, "requestData");
        String url = "http://119.23.21.21/ywapp/index.php/mobile/division/getdivision";
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                LogUtils.d(TAG , request.toString() + e.toString());
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                JSONObject jsonObject = new JSONObject(result);
                String msg = jsonObject.getString("msg");
                Toast.makeText(TabInquiryActivity.this, msg, Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0 ; i < jsonArray.length(); i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    String k_id = item.getString("k_id");
                    String k_name = item.getString("k_name");
                    String imgs = item.getString("imgs");
                    LogUtils.d("k_name", k_name);
                    LogUtils.d("imgs", URL_IMAGE + imgs);
                    groupArray.add(new InquiryExpandListViewGroupData(k_id, k_name, "test", URL_IMAGE + imgs));
                    adapter.setmGroupArray(groupArray);
                }
                if(groupArray.size() > 0){
                    requestItemData();
                }
            }
        });
    }

    private void requestItemData() {
        LogUtils.d(TAG, "requestItemData");
        for (int i = 0; i < groupArray.size(); i++) {
            final InquiryExpandListViewGroupData groupData = groupArray.get(i);
            String k_id = groupData.getK_id();
            String url = "http://119.23.21.21/ywapp/index.php/mobile/doctoruser/disivion_doctor";
            final String k_name = groupData.getK_name();
            Map<String, String> params = new HashMap<>();
            params.put("k_id", k_id);
            OkHttp.postAsync(url, params, new OkHttp.DataCallBack() {
                @Override
                public void requestFailure(Request request, IOException e) {
                    LogUtils.d(TAG ,request.toString() + e.toString() );
                }
                @Override
                public void requestSuccess(String result) throws Exception {
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(TabInquiryActivity.this, msg, Toast.LENGTH_SHORT).show();
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    LogUtils.d(TAG, "dataArray[" + dataArray.length() + "] = " + dataArray);
                    List<InquiryExpandListViewItemData> doctorArray = new ArrayList<InquiryExpandListViewItemData>();
                    for (int j = 0; j < dataArray.length(); j++) {
                        JSONObject item = dataArray.getJSONObject(j);
                        doctorArray.add(new InquiryExpandListViewItemData(item));
                    }
                    groupData.setDec(dataArray.length() + "名" + k_name + "医生在线为您服务");
                    groups.add(groupData);
                    itemArray.add(doctorArray);
                    adapter.setmGroupArray(groups);
                    adapter.setmItemArray(itemArray);
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "i", Toast.LENGTH_SHORT).show();
    }

}
