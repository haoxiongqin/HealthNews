package com.keephealth.app.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keephealth.app.R;
import com.keephealth.app.entity.ChinaBean;
import com.keephealth.app.ui.ChinaDetailActivity;

import java.util.List;

/**
 * Created  on 2017/6/27.
 * author:悟.静
 * 作用:中医养生网adapter
 */

public class ChinaKeepAdapter extends BaseQuickAdapter<ChinaBean, BaseViewHolder> {
    Context context;
    public ChinaKeepAdapter(Context context, List<ChinaBean> homeNewsBeans) {
        super(R.layout.china_item_adapter);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final ChinaBean item) {
        int position=helper.getAdapterPosition();
        helper.setText(R.id.titleChina, item.getTitle())
                .setText(R.id.dateChina,"日期: "+item.getDate());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ChinaDetailActivity.class).putExtra("title",item.getTitle())
                .putExtra("titleUrl",item.getTitleUrl()));
            }
        });
    }
}
