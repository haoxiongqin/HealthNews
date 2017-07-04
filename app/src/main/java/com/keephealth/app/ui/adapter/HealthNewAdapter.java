package com.keephealth.app.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keephealth.app.R;
import com.keephealth.app.entity.HealthNewBean;
import com.keephealth.app.ui.NewLinkActivity;
import com.keephealth.app.ui.ViewDetaiActivity;

import java.util.List;

public class HealthNewAdapter  extends BaseQuickAdapter<HealthNewBean, BaseViewHolder> {
    Context context;
    public HealthNewAdapter(Context context,List<HealthNewBean> homeNewsBeans) {
        super(R.layout.health_news_item);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final HealthNewBean item) {
        helper.setText(R.id.titleTxt,item.getTitle());
        String txt="类型: "+item.getMask();
        SpannableString sp=new SpannableString(txt);
        sp.setSpan(new UnderlineSpan(),4,txt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView txts=(TextView)helper.getView(R.id.titleMask);
        txts.setText(sp);
        txts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,NewLinkActivity.class)
                        .putExtra("title",item.getMask())
                        .putExtra("url",item.getMaskUrl()));
            }
        });
        helper.getView(R.id.titleTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,ViewDetaiActivity.class)
                        .putExtra("title",item.getTitle())
                        .putExtra("url",item.getTitleUrl()));
            }
        });
    }
}
