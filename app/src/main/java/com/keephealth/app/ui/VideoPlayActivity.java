package com.keephealth.app.ui;

import android.os.Bundle;

import com.keephealth.app.R;
import com.keephealth.app.biz.VideoPlayManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class VideoPlayActivity extends BaseActivity {
    String title;
    String titleUrl;
    String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initData();
        initView();
        setDataUrl();
    }

    public void initData(){
        title=getIntent().getStringExtra("title");
        titleUrl=getIntent().getStringExtra("titleUrl");
    }
    public void initView(){
    }


    public void setDataUrl(){
        OkhttpManager.getAsync(titleUrl, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.YANGSHENGWANG);
                videoUrl = VideoPlayManager.getVideoUrl(document);
            }
        });
    }
}
