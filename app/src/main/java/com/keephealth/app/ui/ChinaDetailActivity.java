package com.keephealth.app.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.keephealth.app.R;
import com.keephealth.app.biz.ChinaDeatilManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.OkhttpManager;
import com.keephealth.app.utils.URLImageGetter;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ChinaDetailActivity extends BaseActivity {
    TextView detailTxt;
    String beanTxt;
    String title;
    String titleUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_detail);
        title = getIntent().getStringExtra("title");
        titleUrl = getIntent().getStringExtra("titleUrl");
        initTitle(title);
        detailTxt = (TextView) findViewById(R.id.detailTxt);
//        detailTxt.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
        detailTxt.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页
        initValidata();
    }

    public void initValidata() {
        OkhttpManager.getAsync(titleUrl, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CRAWLER_URL);
                beanTxt = ChinaDeatilManager.getArticleBean(document);
                URLImageGetter ReviewImgGetter = new URLImageGetter(ChinaDetailActivity.this, detailTxt,Config.LZYYSW);
                detailTxt.setText(Html.fromHtml(beanTxt,ReviewImgGetter,null));
            }
        });
    }
}
