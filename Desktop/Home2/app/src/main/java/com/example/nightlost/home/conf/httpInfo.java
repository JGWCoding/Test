package com.example.nightlost.home.conf;

/**
 * Created by john on 2017/4/12.
 * 存储的url地址
 */

public interface httpInfo {

    String STATE = "200";

    //图片加载的根网址
    String URL_IMAGE = "http://119.23.21.21/ywapp/";
    //根IP
    String URL_IP = "http://119.23.21.21/ywapp/index.php/mobile";
    //注册
    String URL_REGISTER = URL_IP + "/user/validaddusersms";
    //获取验证码
    String URL_SMS = URL_IP + "/user/sms";
    //登陆
    String URL_LOGIN = URL_IP + "/login/login";
    //用户信息添加
    String URL_ADD = URL_IP + "/user/adduser";
    //忘记密码
    String URL_FORGET = URL_IP + "/user/forget_password";
    //推出登陆
    String URL_LOGOUT = URL_IP + "/login/logout";
    //获取疾病分类
    String URL_DIVISION = URL_IP + "/division/getdivision";
    //获取医生信息
    String URL_DOCTORUSER = URL_IP + "/doctoruser/doctoruser_message";
    //根据科室分类获取对应的医生
    String URL_DISIVION_DOCTOR = URL_IP + "/doctoruser/disivion_doctor";
    //获取医生帖子列表
    String URL_COMMENT = URL_IP + "/comment/docotr_invitation";
    //用户评论列表
    String URL_USER_COMMENT = URL_IP + "/comment/user_comment";
    //用户评论信息添加
    String URL_ADDCOMMENT = URL_IP + "/comment/addcomment";
    //医生发布帖子的列表
    String URL_INVITATIONS = URL_IP + "/comment/invitations";
    //某个医生的所有帖子
    String URL_DOCTOR_INVITATIONS = URL_IP + "/comment/doctor_invitation";
    //医生发表帖子，增加帖子
    String URL_ADDINVITATION = URL_IP + "/comment/addinvitation";
    //上传帖子图片
    String URL_ADDIMG = URL_IP + "comment/add_img";
    //医生回复的接口
    String URL_REPLY = URL_IP + "doctoruser/reply";
    //推送回复列表的接口
    String URL_REPLY_LIST = URL_IP + "/doctoruser/reply_list";
    //意见反馈接口
    String URL_FEED_BACK = URL_IP + "/";
    //康复方案接口(参数patient_id)
    String URL_RECOVERY = URL_IP + "/html/recovery";
    //体检数据(参数patient_id)
    String URL_PHYSICAL = URL_IP + "/html/physical";
    //使用帮助
    String URL_HELP = "http://119.23.21.21/ywapp/mobiles/html/help";
    //新闻的图片加载前缀
    String URL_NEWS_IMG = "http://120.77.208.169";
    //获取新闻列表
    String URL_NEWS = "http://119.23.21.21/ywapp/mobiles/news/news";
    //推荐新闻
    String URL_RECOMMEND_NEWS = "http://119.23.21.21/ywapp/mobiles/news/recommend_news";


}
