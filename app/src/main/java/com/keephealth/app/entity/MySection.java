package com.keephealth.app.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class MySection extends SectionEntity<VideoBaseBean> {
    private boolean isMore;
    private String moreUrl;
    public MySection(boolean isHeader, String header, boolean isMroe,String moreUrl) {
        super(isHeader, header);
        this.isMore = isMroe;
        this.moreUrl=moreUrl;
    }

    public String getMoreUrl() {
        return moreUrl;
    }
    public void setMoreUrl(String moreUrl) {
        this.moreUrl = moreUrl;
    }
    public MySection(VideoBaseBean t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}