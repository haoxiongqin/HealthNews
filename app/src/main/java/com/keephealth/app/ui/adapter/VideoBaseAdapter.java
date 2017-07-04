package com.keephealth.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keephealth.app.App;
import com.keephealth.app.R;
import com.keephealth.app.entity.MySection;
import com.keephealth.app.entity.VideoBaseBean;
import com.keephealth.app.ui.VideoMoreActivity;
import com.keephealth.app.ui.WebActivity;

import java.util.List;


public class VideoBaseAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public VideoBaseAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.getView(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,VideoMoreActivity.class)
                        .putExtra("titleUrl",item.getMoreUrl()));
            }
        });
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        int position = helper.getAdapterPosition();
        final VideoBaseBean video = (VideoBaseBean) item.t;
        String imgurl = video.getImgUrl();
        String title = video.getTitle();
        ImageView img = (ImageView) helper.getView(R.id.iv);
        App.getInstance().loadImage(context, imgurl, img);
        helper.setText(R.id.tv, title);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, VideoListActivity.class)
//                        .putExtra("title", video.getTitle())
//                        .putExtra("titleUrl", video.getTitleUrl())
//                );
                WebActivity.runActivity(context,video.getTitle(),video.getTitleUrl());
            }
        });
    }
}