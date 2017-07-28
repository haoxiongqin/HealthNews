package com.keephealth.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.DoubleListView;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.CommonUtil;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.keephealth.app.R;
import com.keephealth.app.utils.FilterType;
import com.keephealth.app.utils.FilterUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private List<FilterType> list;
    HashMap<String, Object> mapValue;
    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener, HashMap<String, Object> mapValue) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.mapValue = mapValue;
        this.list=(List<FilterType>)mapValue.get(titles[1]);
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
//        if (position == 1) {
//            return 0;
//        }
        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createSingleListView(0,(List<String>) mapValue.get(titles[0]));
                break;
            case 1:
                view = createDoubleListView(1,list);
                break;
        }
        return view;
    }

    private View createSingleListView(final int i,List<String> list) {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }
                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;
                        FilterUrl.instance().titleUrl = item;
                        FilterUrl.instance().position = i;
                        FilterUrl.instance().positionTitle = item;
                        onFilterDone();
                    }
                });

        singleListView.setList(list, -1);
        return singleListView;
    }

    private View createDoubleListView(final int i,List<FilterType> list) {
        List<String> child=new ArrayList<>();
        if (list!=null&&list.size()>0) {
            child = list.get(0).child;
        }
        DoubleListView<FilterType, String> comTypeDoubleListView = new DoubleListView<FilterType, String>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterType>(null, mContext) {
                    @Override
                    public String provideText(FilterType filterType) {
                        return filterType.desc;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .rightAdapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 15), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> childList = item.child;
                        if (CommonUtil.isEmpty(childList)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";
                            FilterUrl.instance().position = i;
                            FilterUrl.instance().positionTitle = item.desc;
                            onFilterDone();
                        }
                        return childList;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;
                        FilterUrl.instance().position = i;
                        FilterUrl.instance().positionTitle = string;
                        onFilterDone();
                    }
                });
        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 0);
        comTypeDoubleListView.setRightList(child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));
        return comTypeDoubleListView;
    }

    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }
}
