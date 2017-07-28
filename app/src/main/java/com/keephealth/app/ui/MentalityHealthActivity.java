package com.keephealth.app.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.CookBookManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.entity.SearchBean;
import com.keephealth.app.ui.adapter.CookBookAdapter;
import com.keephealth.app.ui.adapter.DropMenuAdapter;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.FilterType;
import com.keephealth.app.utils.FilterUrl;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.chad.library.adapter.base.BaseQuickAdapter.ALPHAIN;

public class MentalityHealthActivity extends BaseActivity implements OnFilterDoneListener,BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    RecyclerView dymdicCycler;
    List<SearchBean> searchBean;
    SwipeRefreshLayout swipeLayout;
    DropDownMenu dropDownMenu;
    String[] titleList = new String[]{"请选择大项", "请选择小项"};


    HashMap<String, Object> mapValue;
    HashMap<String, String> mapAll;
    List<FilterType> doubleList;
    String value;
    String valueURL=Config.XLY+"xinlijb/";
    int page;
    Config config;
    CookBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentality_health);
        initTitle("心理养生");
        initData();
        initView();
        initFilterDropDownView();
    }

    private void initData() {
        mapValue = new HashMap();
        mapAll=new HashMap();
        doubleList=new ArrayList<>();
        config=new Config();
    }

    private void initView() {
        dymdicCycler = (RecyclerView) findViewById(R.id.dymdicCycler);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(240, 204, 35));
        dymdicCycler.setLayoutManager(new LinearLayoutManager(this));
        dymdicCycler.setHasFixedSize(true);
        dymdicCycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        adapter = new CookBookAdapter(this,searchBean);
        adapter.openLoadAnimation(ALPHAIN);
        adapter.setOnLoadMoreListener(this, dymdicCycler);
        dymdicCycler.setAdapter(adapter);
        getDataList();
    }

    private void initFilterDropDownView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < config.tytitle.length; i++) {
            String title = config.tytitle[i];
            String titleUrl=config.titleUlr1[i];
            mapAll.put(title,titleUrl);
            list.add(title);
        }
        mapValue.put(titleList[0], list);

        for(int i = 0; i < list.size(); i++){
            FilterType filterType = new FilterType();
            filterType.desc = list.get(i);
            List<String> str = new ArrayList<>();
            if(i==0){
                for(int k=0;k<config.mentalJK.length;k++){
                    String title = config.mentalJK[k];
                    String titleUrl=config.mentalJKUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }else if(i==1){
                for(int k=0;k<config.Life.length;k++){
                    String title = config.Life[k];
                    String titleUrl=config.LifeUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }else if(i==2){
                for(int k=0;k<config.Test.length;k++){
                    String title = config.Test[k];
                    String titleUrl=config.TestUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }else if(i==3){
                for(int k=0;k<config.Study.length;k++){
                    String title = config.Study[k];
                    String titleUrl=config.StudyUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }else if(i==4){
                for(int k=0;k<config.Constel.length;k++){
                    String title = config.Constel[k];
                    String titleUrl=config.ConstelUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }else if(i==5){
                for(int k=0;k<config.Animals.length;k++){
                    String title = config.Animals[k];
                    String titleUrl=config.AnimalsUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }else if(i==6){
                for(int k=0;k<config.Geomancy.length;k++){
                    String title = config.Geomancy[k];
                    String titleUrl=config.GeomancyUrl[k];
                    str.add(title);
                    mapAll.put(title,titleUrl);
                }
            }
            filterType.child = str;
            doubleList.add(filterType);
        }
        mapValue.put(titleList[1], doubleList);
        setType();
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        value = FilterUrl.instance().positionTitle;
        switch (FilterUrl.instance().position) {
            case 0:
                valueURL = mapAll.get(FilterUrl.instance().positionTitle);
                break;
            case 1:
                valueURL = mapAll.get(FilterUrl.instance().positionTitle);
                break;
        }
        dropDownMenu.setPositionIndicatorText(FilterUrl.instance().position, value);
        dropDownMenu.close();
        page = 1;
        getDataList();
    }
    public void getDataList() {
        String url=valueURL+"index_"+page+".html";
        OkhttpManager.getAsync(url, new OkhttpManager.DataCallBack() {
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
    //判断是否该给标签插值
    private void setType() {
        if (mapValue.size() == 2)
            dropDownMenu.setMenuAdapter(new DropMenuAdapter(this, titleList, this, mapValue));
    }
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page=1;
                getDataList();
            }
        }, 1000);
    }
    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                getDataList();
            }
        }, 1000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FilterUrl.instance().clear();
    }
}
