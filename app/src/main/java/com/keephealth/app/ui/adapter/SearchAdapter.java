package com.keephealth.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keephealth.app.R;
import com.keephealth.app.entity.SearchBean;
import com.keephealth.app.ui.ViewDetaiActivity;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {
    Context context;
    public SearchAdapter(Context context,List<SearchBean> homeNewsBeans) {
        super(R.layout.search_adapter);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final SearchBean item) {
        helper.setText(R.id.searchTitle, Html.fromHtml(item.getTitle()))
                .setText(R.id.searchIntro, Html.fromHtml(item.getIntro()))
                .setText(R.id.searchType, Html.fromHtml(item.getType()));
        helper.setOnClickListener(R.id.searchAll, new View.OnClickListener() {
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
