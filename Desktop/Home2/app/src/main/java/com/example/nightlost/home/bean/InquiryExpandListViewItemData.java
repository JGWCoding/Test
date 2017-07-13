package com.example.nightlost.home.bean;


import org.json.JSONException;
import org.json.JSONObject;

public class InquiryExpandListViewItemData {

    private JSONObject jsonObject;

    private String doctor_id;
    private String doctor_name;
    private String doctor_mobile;
    private String doctor_wechat;
    private String doctor_head;
    private String doctor_hospital;
    private String doctor_age;
    private String doctor_sex;
    private String cat_id;
    private String doctor_cat;
    private String doctor_skill;
    private String password;
    private String nickname;
    private String add_time;
    private String realname;
    private String registrationID;
    private String alias;

    public InquiryExpandListViewItemData(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        try {
            this.doctor_id = jsonObject.getString("doctor_id");
            this.doctor_name = jsonObject.getString("doctor_name");
            this.doctor_mobile = jsonObject.getString("doctor_mobile");
            this.doctor_wechat = jsonObject.getString("doctor_wechat");
            this.doctor_head = "http://119.23.21.21/ywapp/"+jsonObject.getString("doctor_head");
            this.doctor_hospital = jsonObject.getString("doctor_hospital");
            this.doctor_age = jsonObject.getString("doctor_age");
            this.doctor_sex = jsonObject.getString("doctor_sex");
            this.cat_id = jsonObject.getString("cat_id");
            this.doctor_cat = jsonObject.getString("doctor_cat");
            this.doctor_skill = jsonObject.getString("doctor_skill");
            this.password = jsonObject.getString("password");
            this.nickname = jsonObject.getString("nickname");
            this.add_time = jsonObject.getString("add_time");
            this.realname = jsonObject.getString("realname");
            this.registrationID = jsonObject.getString("registrationID");
            this.alias = jsonObject.getString("alias");
			
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setDoctor_mobile(String doctor_mobile) {
        this.doctor_mobile = doctor_mobile;
    }

    public void setDoctor_wechat(String doctor_wechat) {
        this.doctor_wechat = doctor_wechat;
    }

    public void setDoctor_head(String doctor_head) {
        this.doctor_head = doctor_head;
    }

    public void setDoctor_hospital(String doctor_hospital) {
        this.doctor_hospital = doctor_hospital;
    }

    public void setDoctor_age(String doctor_age) {
        this.doctor_age = doctor_age;
    }

    public void setDoctor_sex(String doctor_sex) {
        this.doctor_sex = doctor_sex;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public void setDoctor_cat(String doctor_cat) {
        this.doctor_cat = doctor_cat;
    }

    public void setDoctor_skill(String doctor_skill) {
        this.doctor_skill = doctor_skill;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDoctor_mobile() {
        return doctor_mobile;
    }

    public String getDoctor_wechat() {
        return doctor_wechat;
    }

    public String getDoctor_head() {
        return doctor_head;
    }

    public String getDoctor_hospital() {
        return doctor_hospital;
    }

    public String getDoctor_age() {
        return doctor_age;
    }

    public String getDoctor_sex() {
        return doctor_sex;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getDoctor_cat() {
        return doctor_cat;
    }

    public String getDoctor_skill() {
        return doctor_skill;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getRealname() {
        return realname;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public String getAlias() {
        return alias;
    }
}
