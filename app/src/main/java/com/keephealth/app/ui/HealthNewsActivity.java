package com.keephealth.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.HealthNewBeanManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.HealthNewBean;
import com.keephealth.app.ui.adapter.HealthNewAdapter;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.OkhttpManager;
import com.keephealth.app.utils.PicassoImageLoader;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class HealthNewsActivity extends BaseActivity implements DefineView {
    Banner banner;
    RecyclerView newsList;
    List<String> images=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    List<HealthNewBean> imgList=new ArrayList<>();
    List<HealthNewBean> newInfo=new ArrayList<>();
    HealthNewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_news);
        initTitle("养生要闻");
        initValidata();
        initView();
    }
    @Override
    public void initView() {
        banner = (Banner) findViewById(R.id.banner);
        newsList = (RecyclerView) findViewById(R.id.newsCycler);
        newsList.setLayoutManager(new LinearLayoutManager(HealthNewsActivity.this));
        newsList.setHasFixedSize(true);
        newsList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        newsList.setNestedScrollingEnabled(true);
        adapter=new HealthNewAdapter(this,newInfo);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        newsList.setAdapter(adapter);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(HealthNewsActivity.this,ViewDetaiActivity.class)
                        .putExtra("imgUrl",Config.CRAWLER_URL+imgList.get(position).getImgUrl())
                .putExtra("title",imgList.get(position).getImgTitle())
                .putExtra("url",imgList.get(position).getImgTitleUrl()));
            }
        });
    }
    @Override
    public void initValidata() {
        OkhttpManager.getAsync(Config.CRAWLER_URL, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CRAWLER_URL);
                imgList = HealthNewBeanManager.getImgList(document,result);
                newInfo= HealthNewBeanManager.getTitleList(document);
                adapter.setNewData(newInfo);
                if(imgList!=null &&imgList.size()>0){
                    for(HealthNewBean bean:imgList){
                        String imgtitle=bean.getImgTitle();
                        String imgUrl=bean.getImgUrl();
                        images.add(Config.CRAWLER_URL+imgUrl);
                        titles.add(imgtitle);
                    }
                    banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                    banner.setImageLoader(new PicassoImageLoader());
                    banner.setBannerAnimation(Transformer.DepthPage);
                    banner.setDelayTime(2000);
                    banner.setImages(images);
                    banner.setBannerTitles(titles);
                    banner.start();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
