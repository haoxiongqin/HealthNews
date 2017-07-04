package com.keephealth.app.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keephealth.app.App;
import com.keephealth.app.R;
import com.keephealth.app.entity.HealthMessage;
import com.keephealth.app.ui.ViewDetaiActivity;

import java.util.List;
/**
 * Created  on 2017/6/27.
 * author:悟.静
 * 作用:中医养生网adapter
 */
public class RecycleAdapter extends BaseQuickAdapter<HealthMessage, BaseViewHolder> {
    Context context;
    public RecycleAdapter(Context context,List<HealthMessage> homeNewsBeans) {
        super(R.layout.item_home_news_layout);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final HealthMessage item) {
        int position=helper.getAdapterPosition();
        helper.setText(R.id.item_news_tv_intro, item.getIntro())
                .setText(R.id.item_news_tv_title, item.getTitle());
        TextView date = helper.getView(R.id.date);
        ImageView dates =helper.getView(R.id.item_news_tv_img);
        App.getInstance().loadImage(context, item.getImgurl(), dates);
        date.setText(Html.fromHtml(item.getDate()));
        helper.setOnClickListener(R.id.allLiner, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title", item.getTitle());
                intent.putExtra("url", item.getHref());
                intent.setClass(context, ViewDetaiActivity.class);
                context.startActivity(intent);
            }
        });
    }
}