package com.example.nightlost.home.bean;


public class InquiryExpandListViewGroupData {

    private String k_id;
    private String k_name;
    private String dec;
    private String url;

    public InquiryExpandListViewGroupData(String k_id, String k_name, String dec, String url) {
        this.k_id = k_id;
        this.k_name = k_name;
        this.dec = dec;
        this.url = url;
    }

    public String getK_id() {
        return k_id;
    }

    public String getK_name() {
        return k_name;
    }

    public String getDec() {
        return dec;
    }

    public String getUrl() {
        return url;
    }

    public void setK_id(String k_id) {
        this.k_id = k_id;
    }

    public void setK_name(String name) {
        this.k_name = name;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
