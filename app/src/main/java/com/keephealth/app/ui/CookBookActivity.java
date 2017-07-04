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

public class CookBookActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    SwipeRefreshLayout swipeLayout;
    RecyclerView shiPuCycler;
    List<SearchBean> searchBean;
    int page=1;
    CookBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_book);
        initTitle("养生网食谱");
        initView();
    }
    public void initView() {
        swipeLayout=(SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        shiPuCycler=(RecyclerView)findViewById(R.id.shiPuCycler);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(240, 204, 35));
        shiPuCycler.setLayoutManager(new LinearLayoutManager(this));
        shiPuCycler.setHasFixedSize(true);
        shiPuCycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        adapter = new CookBookAdapter(this,searchBean);
        adapter.openLoadAnimation(ALPHAIN);
        adapter.setOnLoadMoreListener(this, shiPuCycler);
        shiPuCycler.setAdapter(adapter);
        initValidata();
    }

    public void initValidata() {
        String url=Config.YANGSHENGWANG+"/caipu/index_"+page+".html";
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
