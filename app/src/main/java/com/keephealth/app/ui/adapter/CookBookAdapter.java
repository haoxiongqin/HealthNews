package com.keephealth.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keephealth.app.App;
import com.keephealth.app.R;
import com.keephealth.app.entity.SearchBean;
import com.keephealth.app.ui.CookDetailActivity;
import com.keephealth.app.ui.WebActivity;

import java.util.List;


public class CookBookAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {
    Context context;
    int page=0;

    public void setPage(int page) {
        this.page = page;
    }
    public CookBookAdapter(Context context, List<SearchBean> homeNewsBeans) {
        super(R.layout.cook_book_item);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final SearchBean item) {
        helper.setText(R.id.cookTitleTxt, item.getTitle())
                .setText(R.id.cookcontent, item.getIntro())
                .setText(R.id.cookMaskType, "类型: "+item.getType())
                .setText(R.id.cookDate, "日期: "+item.getDate());
        ImageView img=(ImageView)helper.getView(R.id.cookImg);
        App.getInstance().loadImage(context, item.getClick(), img);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               if (page == 1) {
                   WebActivity.runActivity(context,item.getTitle(),item.getHref());
               }else{
                   intent.putExtra("title", item.getTitle());
                   intent.putExtra("url", item.getHref());
                   intent.setClass(context, CookDetailActivity.class);
                   context.startActivity(intent);
               }
           }
       });
    }
}
