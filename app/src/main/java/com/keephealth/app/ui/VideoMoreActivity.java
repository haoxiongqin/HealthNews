package com.keephealth.app.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.CookBookManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.entity.SearchBean;
import com.keephealth.app.ui.adapter.CookBookAdapter;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import static com.chad.library.adapter.base.BaseQuickAdapter.ALPHAIN;

public class VideoMoreActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    SwipeRefreshLayout swipeLayout;
    RecyclerView moreCycler;
    String titleUrl;
    CookBookAdapter adapter;
    List<SearchBean> searchBean;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_more);
        initTitle("更多视频");
        initData();
        initView();
    }
    public void initView(){
        moreCycler=(RecyclerView)findViewById(R.id.moreCycler);
        swipeLayout=(SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(240, 204, 35));
        moreCycler.setLayoutManager(new LinearLayoutManager(this));
        moreCycler.setHasFixedSize(true);
        moreCycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        adapter = new CookBookAdapter(this,searchBean);
        adapter.openLoadAnimation(ALPHAIN);
        adapter.setPage(1);
        adapter.setOnLoadMoreListener(this, moreCycler);
        moreCycler.setAdapter(adapter);
        initValidata();
    }
    public void initData(){
        titleUrl=getIntent().getStringExtra("titleUrl");
    }
    public void initValidata() {
        String url=titleUrl+"/index_"+page+".html";
        OkhttpManager.getAsync(url,new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.YANGSHENGWANG);
                searchBean = CookBookManager.getCooKBook(document);
                if(page==1){
                    adapter.setNewData(searchBean);
                    swipeLayout.setRefreshing(false);
                }else{
                    adapter.addData(searchBean);
                    adapter.loadMoreComplete();
                    if(searchBean.size()==0){
                        adapter.loadMoreEnd(true);
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
                page=1;
                initValidata();
            }
        }, 1000);
    }
    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                initValidata();
            }
        }, 1000);
    }
}
