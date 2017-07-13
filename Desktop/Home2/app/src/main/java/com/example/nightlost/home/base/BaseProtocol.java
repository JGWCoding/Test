package com.example.nightlost.home.base;

import android.os.Environment;
import android.widget.Toast;

import com.example.nightlost.home.conf.Constants;
import com.example.nightlost.home.conf.httpInfo;
import com.example.nightlost.home.utils.LogUtils;
import com.example.nightlost.home.utils.MD5Utils;
import com.example.nightlost.home.utils.UiUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络协议基类（这个基类的抽取简化了网络数据加载的代码）
 * （如果觉得我抽取的不好的话，超哥可以自己试着抽取一下）
 */

public abstract class BaseProtocol<T> {

    protected RequestBody mBody;

    public void setRequestBody(RequestBody mBody) {
        this.mBody = mBody;
    }

    //支持多参数
    public T loadData() throws Exception{
        //1.先从本地取缓存数据
        T t = getLocalData();
        if(t != null){
            LogUtils.d("cache", "取到了json缓存");
            return t;
        }
        LogUtils.d("cache", "取最新数据");
        return getServerData();
    }

    private T getLocalData() {
        //1.要先找到上次保存缓存的文件
        File file = getCacheFile();
        //2.文件流把json字符串读出来
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            //先读缓存时间
            String time = reader.readLine();
            if(Long.parseLong(time) + Constants.CACHE_OUT_TIME < System.currentTimeMillis()){
                //cache is out
                LogUtils.d("cache", "缓存已过期");
                return null;
            }
            //读取一行,存json的时候就存成当行的
            String json = reader.readLine();
            //3.解析成t类型
            T t = parserJson(json);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(reader!=null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File getCacheFile() {
        //是否有sd卡
        File dir = null;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //has sdcard
            dir = new File(Environment.getExternalStorageDirectory(),"Android/data/" + UiUtil.getContext().getPackageName() + "/json");
        }else{
            //rom
            dir = new File(getCacheFile(),"json");
        }
        if(!dir.exists()){
            dir.mkdirs(); //多级目录
        }
        //第一种是以功能+内容命名，gp_home_40,第二种可以使用url(需要加密）
        File file = new File(dir, MD5Utils.encode(getUrl()));
        return file;
    }

    //网络获取数据存在问题
    private T getServerData() throws Exception {
        String url = getUrl();
        //获取参数
        OkHttpClient client= new OkHttpClient();
//        mBody = getUrlBody();
//        LogUtils.d("BaseProtocol",mBody.toString());
        Request request = new Request.Builder().url(url).build();
        //同步加载
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String json = response.body().string();
            LogUtils.d("BaseProtocol",json);
            T t = parserJson(json);
            LogUtils.d("baseProtocol",toString());
            //保存缓存
            saveCache(new Gson().toJson(t));
            return t;
        }else{
            Toast.makeText(UiUtil.getContext(), "BaseProtocol的数据加载为空", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void saveCache(String json) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(getCacheFile()));
            //先写入当前时间
            writer.write(System.currentTimeMillis() + "");
            writer.newLine();//  换行符\r\n
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private T parserJson(String json) {
        Gson gson = new Gson();
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        return gson.fromJson(json, types[0]);
    }

    private String getUrl() {
        String url = httpInfo.URL_RECOMMEND_NEWS;
        return url;
    }
    //获取params参数
    protected abstract RequestBody getUrlBody();

}
