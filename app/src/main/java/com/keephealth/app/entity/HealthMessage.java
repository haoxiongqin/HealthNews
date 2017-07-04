package com.keephealth.app.entity;

/**
 * Created  on 2017/6/7.
 * author:秦浩雄
 * 作用: 健康资讯实体类
 */

public class HealthMessage {
    private  String href; //文章地址
    private  String imgurl; //图片地址
    private  String title; //标题
    private  String date; //日期
    private  String click; //点击量
    private  String evaluate; //评价量
    private  String intro; //简介
    private  String id; //编号Id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
