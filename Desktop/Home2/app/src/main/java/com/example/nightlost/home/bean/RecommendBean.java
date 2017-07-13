package com.example.nightlost.home.bean;

/**
 * Created by john on 2017/5/6.
 * 推荐新闻的bean
 */

public class RecommendBean {
    public static final int TYPE_NORMAL = 0;//指item中含有文字和一张图片的样式
//    public static final int TYPE_TITLE = 1;//指item中只有title的样式
//    public static final int TYPE_IMAGE = 2;//指item中有三张图片展示的样式
//    public static final int TYPE_MOVIE = 3;//指item中含有视频播放的样式
//    public static final int TYPE_EXTEND = 4;//可扩展的item样式

    public int type;
    //normal类型
    public String divison_id;
    public String news_content;
    public String news_id;
    public String news_img;

    //title类型
    public String title;

    //image类型
//    public String url;

    //movie类型
//    public String url;

    //extend类型
//    public String url;

}
