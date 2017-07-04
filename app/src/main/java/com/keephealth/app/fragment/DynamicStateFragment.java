package com.keephealth.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keephealth.app.R;
import com.keephealth.app.biz.HealthInMessageManager;
import com.keephealth.app.common.Config;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.CategoriesBean;
import com.keephealth.app.entity.HealthMessage;
import com.keephealth.app.fragment.adapter.RecycleAdapter;
import com.keephealth.app.fragment.base.BaseFragment;
import com.keephealth.app.utils.DividerItemDecoration;
import com.keephealth.app.utils.OkhttpManager;
import com.squareup.okhttp.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import static com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_RIGHT;
/**
 * Created  on 2017/6/27.
 * author:悟.静
 */

public class DynamicStateFragment  extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener,DefineView {
    private View DymView;
    private static final String KEY = "EXTRA";
    private CategoriesBean categoriesBean;
    private RecyclerView mrecycler;
    private List<HealthMessage> homeNewsBeans;
    private RecycleAdapter quickAdapter;
    int page=1;
    private SwipeRefreshLayout swipeLayout;

    public static DynamicStateFragment newInstance(CategoriesBean extra) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, extra);
        DynamicStateFragment fragment = new DynamicStateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            categoriesBean = (CategoriesBean) bundle.getSerializable(KEY);
        }
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
        swipeLayout.setColorSchemeColors(Color.rgb(66, 209, 219));
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecycler.setHasFixedSize(true);
        mrecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.border)));
        quickAdapter = new RecycleAdapter(getActivity(), homeNewsBeans);
        quickAdapter.openLoadAnimation(SLIDEIN_RIGHT);
        quickAdapter.setOnLoadMoreListener(this, mrecycler);
        mrecycler.setAdapter(quickAdapter);
        initValidata();
    }

    @Override
    public void initValidata() {
        String url=categoriesBean.getHref()+"yangsheng410"+page+".shtml";
        OkhttpManager.getAsync(url, new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
            }
            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CRAWLER_URL);
                Log.d("zttjianggqq", result);
                homeNewsBeans = HealthInMessageManager.getlist(document);
                if(page==1){
                    quickAdapter.setNewData(homeNewsBeans);
                    swipeLayout.setRefreshing(false);
                }else{
                    quickAdapter.addData(homeNewsBeans);
                    quickAdapter.loadMoreComplete();
                    if(homeNewsBeans.size()==0){
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
