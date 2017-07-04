package com.keephealth.app.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.keephealth.app.R;
import com.keephealth.app.biz.VideoManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.entity.MySection;
import com.keephealth.app.entity.VideoBaseBean;
import com.keephealth.app.ui.adapter.VideoBaseAdapter;
import com.keephealth.app.ui.base.BaseActivity;
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

public class VideoListActivity extends BaseActivity {
    RecyclerView shiPinCycler;
    VideoBaseAdapter adapter;
    List<MySection> getData;
    Banner banners;
    List<VideoBaseBean> baner;
    List<String> images=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        initTitle("精选视频");
        initView();
        initValidata();
    }

    public void initView() {
        shiPinCycler = (RecyclerView) findViewById(R.id.shiPinCycler);
        shiPinCycler.setHasFixedSize(true);
        shiPinCycler.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new VideoBaseAdapter(R.layout.item_section_content, R.layout.def_section_head, getData);
        adapter.setContext(this);
        View view = getLayoutInflater().inflate(R.layout.banner_item, (ViewGroup) shiPinCycler.getParent(), false);
        banners=(Banner)view.findViewById(R.id.banners);
        adapter.addHeaderView(view);
        shiPinCycler.setAdapter(adapter);
        banners.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                startActivity(new Intent(VideoListActivity.this,VideoPlayActivity.class).putExtra("title",baner.get(position).getTitle())
//                        .putExtra("titleUrl",baner.get(position).getTitleUrl()));
                WebActivity.runActivity(VideoListActivity.this,baner.get(position).getTitle(),baner.get(position).getTitleUrl());
            }
        });
    }
    public void initValidata() {
        String url=Config.YANGSHENGWANG+"/shipin/";
        OkhttpManager.getAsync(url, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.YANGSHENGWANG);
                baner = VideoManager.getBanerList(document);
                getData= VideoManager.getList(document);
                adapter.setNewData(getData);
                    for(VideoBaseBean bean:baner){
                        String imgtitle=bean.getTitle();
                        String imgUrl=bean.getImgUrl();
                        images.add(imgUrl);
                        titles.add(imgtitle);
                    }
                banners.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                banners.setImageLoader(new PicassoImageLoader());
                banners.setBannerAnimation(Transformer.DepthPage);
                banners.setDelayTime(2000);
                banners.setImages(images);
                banners.setBannerTitles(titles);
                banners.start();
                }
            });
        }
}
