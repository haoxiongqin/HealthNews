package com.keephealth.app.entity;

import java.io.Serializable;

/**
 * Created  on 2017/6/27.
 * author:秦浩雄
 * 作用:
 */

public class ChinaBean implements Serializable {
    private String title;
    private String titleUrl;
    private String date;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
