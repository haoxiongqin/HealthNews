package com.keephealth.app.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.keephealth.app.R;
import com.keephealth.app.biz.CookDetailManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.OkhttpManager;
import com.keephealth.app.utils.URLImageGetter;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CookDetailActivity extends BaseActivity {
    String title = "";
    String titleUrl = "";
    TextView detailTxt;
    String txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        title = getIntent().getStringExtra("title");
        titleUrl = getIntent().getStringExtra("url");
        initTitle(title);
        initView();
    }

    public void initView() {
        detailTxt = (TextView) findViewById(R.id.cookDetailTxt);
        initValidata();
    }

    public void initValidata() {
        String url=titleUrl.substring(0,titleUrl.length()-5)+"_all.html";
        OkhttpManager.getAsync(url,new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.YANGSHENGWANG);
                txt = CookDetailManager.getCookDetail(document);
                URLImageGetter ReviewImgGetter = new URLImageGetter(CookDetailActivity.this, detailTxt,"");
                detailTxt.setText(Html.fromHtml(txt,ReviewImgGetter,null));
            }
        });
    }
}