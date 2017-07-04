package com.keephealth.app.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.SearchManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.SearchBean;
import com.keephealth.app.ui.adapter.SearchAdapter;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chad.library.adapter.base.BaseQuickAdapter.ALPHAIN;

public class SeachListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener,DefineView {
    SwipeRefreshLayout swipeLayout;
    RecyclerView dymdicCycler;
    EditText searchText;
    TextView searchCancel;
    String edit;
    List<SearchBean> searchBean;
    int page=1;
    SearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_list);
        initView();
        initValidata();
    }

    @Override
    public void initView() {
        swipeLayout=(SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        dymdicCycler=(RecyclerView)findViewById(R.id.dymdicCycler);
        searchText=(EditText)findViewById(R.id.searchText);
        searchCancel=(TextView)findViewById(R.id.searchCancel);
        searchText.setHint("搜索");

        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(240, 204, 35));
        dymdicCycler.setLayoutManager(new LinearLayoutManager(this));
        dymdicCycler.setHasFixedSize(true);
        dymdicCycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        adapter = new SearchAdapter(this,searchBean);
        adapter.openLoadAnimation(ALPHAIN);
        adapter.setOnLoadMoreListener(this, dymdicCycler);
        dymdicCycler.setAdapter(adapter);
        initValidata();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edit = s.toString().trim();
                if (edit.length() > 0) {
                    dymdicCycler.setVisibility(View.VISIBLE);
                    page = 1;
                    initValidata();
                } else {
                    dymdicCycler.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        searchCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initValidata() {
        String url="http://www.cpoha.com.cn/plus/search.php";
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("kwtype",0+"");
        hashMap.put("channeltype",0+"");
        hashMap.put("orderby","");
        hashMap.put("pagesize","10");
        hashMap.put("typeid","0");
        hashMap.put("TotalResult","50");
        hashMap.put("PageNo",page+"");
        hashMap.put("keyword",edit);
        hashMap.put("searchtype","titlekeyword");
        OkhttpManager.postAsyncParams(url,hashMap,new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CRAWLER_URL);
                searchBean = SearchManager.getSearch(document);
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
