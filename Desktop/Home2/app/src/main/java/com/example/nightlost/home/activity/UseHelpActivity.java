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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nightlost.home.conf.OkHttp;
import com.example.nightlost.home.R;
import com.example.nightlost.home.conf.httpInfo;
import com.example.nightlost.home.utils.LogUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

public class UseHelpActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webview;
    private TextView title;
    private ProgressBar progressBar;
    private ImageView back;

    private static final String TAG = "UseHelpActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_help);

        title = (TextView) findViewById(R.id.tv_title);
        back = (ImageView) findViewById(R.id.iv_back);
        webview = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        back.setOnClickListener(this);

        title.setText("使用帮助");
        //支持js
        webview.getSettings().setJavaScriptEnabled(true);
        //支持将图片调整到适合调整到web view的大小
        webview.getSettings().setUseWideViewPort(true);
        //支持缩放
        webview.getSettings().setSupportZoom(true);
        // 设置显示缩放按钮  
        webview.getSettings().setDisplayZoomControls(true);
        //为图片添加放大缩小功能
        webview.getSettings().setUseWideViewPort(true);
        //缩放至屏幕的大小
        webview.getSettings().setLoadWithOverviewMode(true);
        //适应内容大小
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //支持自动加载图片
        webview.getSettings().setLoadsImagesAutomatically(true);
        //关闭webview缓存
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //加载数据
        String url = httpInfo.URL_HELP;

        loadWebview(url);
        //设置进度条
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                LogUtils.d(TAG, "onPagerStart");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
                LogUtils.d(TAG, "onPageFinished");
            }
        });
    }

    private void loadWebview(String url) {
        OkHttp.postAsync(url, null, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.d(TAG, request.toString() + e.toString());
                Toast.makeText(UseHelpActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d(TAG, result);
                JSONObject obj = new JSONObject(result);
                String msg = obj.getString("msg");
                Toast.makeText(UseHelpActivity.this, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, msg);
                String data = obj.getString("data");
                Log.d(TAG, data);
                webview.loadUrl(data);
            }
        });
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
