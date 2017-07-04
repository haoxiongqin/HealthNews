package com.keephealth.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.ChinaListManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.ChinaBean;
import com.keephealth.app.fragment.adapter.ChinaKeepAdapter;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created  on 2017/6/27.
 * author:悟.静
 * 作用:老中医养生网(男性)
 */

public class ChinaMaleFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener,DefineView {
    private View DymView;
    private RecyclerView mrecycler;
    private List<ChinaBean> beanList;
    private ChinaKeepAdapter quickAdapter;
    int page=1;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (DymView == null) {
            DymView = inflater.inflate(R.layout.dymdic_fragment, container, false);
            initView();
        }
        return DymView;
    }

    @Override
    public void initView() {
        mrecycler = (RecyclerView) DymView.findViewById(R.id.dymdicCycler);
        swipeLayout = (SwipeRefreshLayout) DymView.findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(66, 209, 219),Color.rgb(240, 30, 30));
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecycler.setHasFixedSize(true);
        mrecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        quickAdapter = new ChinaKeepAdapter(getActivity(), beanList);
        quickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        quickAdapter.setOnLoadMoreListener(this, mrecycler);
        mrecycler.setAdapter(quickAdapter);
        initValidata();
    }

    @Override
    public void initValidata() {
        String url= Config.LZYYSW+"/nanxing/list/0/"+page;
        OkhttpManager.getAsync(url, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.LZYYSW);
                beanList = new ChinaListManager().getChinese(document);
                if(page==1){
                    quickAdapter.setNewData(beanList);
                    swipeLayout.setRefreshing(false);
                }else{
                    quickAdapter.addData(beanList);
                    quickAdapter.loadMoreComplete();
                    if(beanList.size()==0){
                        quickAdapter.loadMoreEnd(true);
                    }
                }
            }
        });
    }
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page=1;
                initValidata();
            }
        }, 1000);
    }
    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                initValidata();
            }
        }, 1000);
    }
}
