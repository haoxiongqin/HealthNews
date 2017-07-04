package com.keephealth.app.entity;

/**
 * Created  on 2017/6/19.
 * author:秦浩雄
 * 作用: 首页的轮播图和养生要闻信息
 */

public class HealthNewBean {
    private String imgUrl;
    private String imgTitle;
    private String imgTitleUrl;
    private String title;
    private String titleUrl;
    private String mask;
    private String maskUrl;

    public String getMaskUrl() {
        return maskUrl;
    }

    public void setMaskUrl(String maskUrl) {
        this.maskUrl = maskUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgTitleUrl() {
        return imgTitleUrl;
    }

    public void setImgTitleUrl(String imgTitleUrl) {
        this.imgTitleUrl = imgTitleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
}
