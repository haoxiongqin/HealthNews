package com.keephealth.app.entity;

/**
 * Created  on 2017/6/29.
 * author:秦浩雄
 * 作用:
 */

public class VideoBaseBean {
    private String title;
    private String videoUrl;
    private String imgUrl;
    private String titleUrl;
    private String moreUrl;

    public VideoBaseBean() {
    }
    public VideoBaseBean(String title, String imgUrl,String titleUrl,String moreUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.titleUrl = titleUrl;
        this.moreUrl = moreUrl;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getMoreUrl() {
        return moreUrl;
    }

    public void setMoreUrl(String moreUrl) {
        this.moreUrl = moreUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
