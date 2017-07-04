package com.keephealth.app.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keephealth.app.App;
import com.keephealth.app.R;
import com.keephealth.app.biz.DeatailDataManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.ViewDetailInfo;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ViewDetaiActivity extends BaseActivity implements DefineView{

    TextView introduction;
    TextView antistop;
    ImageView detailImg;
    TextView detailContent;
    String title;
    String url;
    private ViewDetailInfo homeNewsBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detai);
        initView();
        initValidata();
        initTitle(Html.fromHtml(title)+"");
        mBaseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        introduction = (TextView) findViewById(R.id.introduction);
        antistop = (TextView) findViewById(R.id.antistop);
        detailImg = (ImageView) findViewById(R.id.detailImg);
        detailContent = (TextView) findViewById(R.id.detailContent);

    }

    @Override
    public void initValidata() {
        title=getIntent().getStringExtra("title");
        url=getIntent().getStringExtra("url");
        bindData();
    }

    public void bindData() {
        OkhttpManager.getAsync(Config.CRAWLER_URL+url, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CRAWLER_URL);
                homeNewsBeans = DeatailDataManager.getlist(document);
                introduction.setText(Html.fromHtml(homeNewsBeans.getIntro()));
                SpannableString sp=new SpannableString(homeNewsBeans.getIntroduce());
                sp.setSpan(new UnderlineSpan(),0,homeNewsBeans.getIntroduce().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                antistop.setText(sp);
                detailContent.setText(Html.fromHtml(homeNewsBeans.getContent()));
                String imgUrl=homeNewsBeans.getImgeUrl();
                if(!TextUtils.isEmpty(imgUrl)){
                    App.getInstance().loadImage(ViewDetaiActivity.this,Config.CRAWLER_URL+imgUrl,detailImg);
                }else{
                    detailImg.setImageResource(R.mipmap.backgroud_icon);
                }
            }
        });
    }
}
