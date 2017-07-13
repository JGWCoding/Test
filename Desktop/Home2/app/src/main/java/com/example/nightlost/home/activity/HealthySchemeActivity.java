package com.example.nightlost.home.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nightlost.home.conf.OkHttp;
import com.example.nightlost.home.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

import static com.example.nightlost.home.R.id.progressBar;

/**
 * Created by NightLost on 2017/5/4.
 * 使用帮助页面
 */

public class HealthySchemeActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressBar probar;
    private static final String TAG = "webview";
    private String patient_id = 16 + "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthyschemen);
        webview = (WebView) findViewById(R.id.webview);
        probar = (ProgressBar) findViewById(progressBar);

        //支持js
        webview.getSettings().setJavaScriptEnabled(true);
        //支持将图片调整到适合调整到web view的大小
        webview.getSettings().setUseWideViewPort(false);
        //支持缩放
        webview.getSettings().setSupportZoom(true);
        //缩放至屏幕的大小
        webview.getSettings(). setLoadWithOverviewMode(true);
        //支持自动加载图片
        webview.getSettings().setLoadsImagesAutomatically(true);
        //关闭webview缓存
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        //加载数据
        String url = "http://119.23.21.21/ywapp/index.php/mobile/html/recovery";
        Map<String , String> params = new HashMap<>();
        params.put("patient_id" , patient_id);
        loadWebview(url,params);
        //设置进度条
        webview.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                probar.setVisibility(View.VISIBLE);
                Log.d(TAG ,"onPagerStart");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                probar.setVisibility(View.INVISIBLE);
                Log.d(TAG ,"onPageFinished");
            }
        });


    }
    private void loadWebview(String url, Map<String, String> params) {
        OkHttp.postAsync(url, params, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.d(TAG, request.toString() + e.toString());
                Toast.makeText(HealthySchemeActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d(TAG, result);
                JSONObject obj = new JSONObject(result);
                String msg = obj.getString("msg");
                Toast.makeText(HealthySchemeActivity.this, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, msg);
                String data = obj.getString("data");
                JSONObject object = new JSONObject(data);
                String url1 = object.getString("url");
                Log.d(TAG, url1);
                webview.loadUrl(url1);
            }
        });
    }

}
