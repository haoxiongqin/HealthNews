package com.keephealth.app.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.HealthInMessageManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.HealthMessage;
import com.keephealth.app.fragment.adapter.RecycleAdapter;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class NewLinkActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, DefineView {
    String title;
    String url;
    private RecyclerView mrecycler;
    private List<HealthMessage> homeNewsBeans;
    private RecycleAdapter quickAdapter;
    int page = 1;
    int flag = 0;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_link);
        initView();
        initTitle(title);
    }

    @Override
    public void initView() {
        String titles = getIntent().getStringExtra("title");
        title = titles.substring(1, titles.length() - 1) + "专栏";
        url = getIntent().getStringExtra("url");
        mrecycler = (RecyclerView) findViewById(R.id.dymdicCycler);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(66, 209, 219));
        mrecycler.setLayoutManager(new LinearLayoutManager(this));
        mrecycler.setHasFixedSize(true);
        mrecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        quickAdapter = new RecycleAdapter(this, homeNewsBeans);
        quickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        quickAdapter.setOnLoadMoreListener(this, mrecycler);
        mrecycler.setAdapter(quickAdapter);
        initValidata();
    }

    @Override
    public void initValidata() {
        String urls = Config.CRAWLER_URL + url;
        String baseUrl = "";
        String fianlUrl = "";
        if (title.equals("糖尿病")) {
            flag = 1;
            baseUrl = urls + "/yangsheng196+" + page + ".shtml";
        } else if (title.equals("膳食养生")) {
            flag = 1;
            baseUrl = urls + "yangsheng471" + page + ".shtml";
        } else if (title.equals("老年阶段")) {
            flag = 1;
            baseUrl = urls + "yangsheng160" + page + ".shtml";
        } else if (title.equals("健康资讯")) {
            flag = 1;
            baseUrl = urls + "jiankang468" + page + ".shtml";
        } else if (title.equals("大家养生")) {
            flag = 1;
            baseUrl = urls + "yangsheng178" + page + ".shtml";
        } else if (title.equals("春季生活常识")) {
            flag = 1;
            baseUrl = urls + "yangsheng36" + page + ".shtml";
        } else if (title.equals("五行平衡")) {
            flag = 1;
            baseUrl = urls + "yangsheng109" + page + ".shtml";
        } else if (title.equals("中年阶段")) {
            flag = 1;
            baseUrl = urls + "yangsheng159" + page + ".shtml";
        }
        if (TextUtils.isEmpty(baseUrl)) {
            fianlUrl = urls;
        } else {
            fianlUrl = baseUrl;
        }
        OkhttpManager.getAsync(fianlUrl, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }

            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CRAWLER_URL);
                homeNewsBeans = HealthInMessageManager.getlist(document);
                if (page == 1) {
                    quickAdapter.setNewData(homeNewsBeans);
                    swipeLayout.setRefreshing(false);
                } else {
                    quickAdapter.addData(homeNewsBeans);
                    quickAdapter.loadMoreComplete();
                    if (homeNewsBeans.size() == 0) {
                        quickAdapter.loadMoreEnd(true);
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                initValidata();
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flag == 0) {
                    quickAdapter.loadMoreComplete();
                    quickAdapter.loadMoreEnd(true);
                } else {
                    page++;
                    initValidata();
                }
            }
        }, 1000);
    }
}
