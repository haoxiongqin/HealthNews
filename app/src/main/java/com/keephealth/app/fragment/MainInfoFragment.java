package com.keephealth.app.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keephealth.app.R;
import com.keephealth.app.adapter.FixedPagerAdapter;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.entity.CategoriesBean;
import com.keephealth.app.fragment.base.BaseFragment;
import com.keephealth.app.ui.MainActivity;
import com.keephealth.app.ui.WebActivity;
import com.keephealth.app.utils.CategoryDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2017/6/27.
 * author:悟.静
 */
public class MainInfoFragment extends BaseFragment implements DefineView,ViewPager.OnPageChangeListener{
    private View mView;
    private TabLayout tab_layout;
    private ViewPager info_viewpager;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<Fragment> fragments;
    private static List<CategoriesBean> categoriesBeans= CategoryDataUtils.getCategoryBeans();
    FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.main_info_fragment_layout,container,false);
            initView();
            initValidata();
        }
        return mView;
    }

    @Override
    public void initView() {
        tab_layout=(TabLayout)mView.findViewById(R.id.tab_layout);
        fab=(FloatingActionButton)mView.findViewById(R.id.fab);
        info_viewpager=(ViewPager)mView.findViewById(R.id.info_viewpager);
        info_viewpager.setOnPageChangeListener(this);
    }

    @Override
    public void initValidata() {
        fixedPagerAdapter=new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setCategoriesBeans(categoriesBeans);
        fragments=new ArrayList<Fragment>();
        for(int i=0;i<categoriesBeans.size();i++){
            BaseFragment fragment=null;
            if(i==0){
                fragment= HomeFragment.newInstance(categoriesBeans.get(i));
            }else if(i==1){
                fragment= PageFragment.newInstance(categoriesBeans.get(i));
            }else if(i==2){
                fragment=ShanShiFragment.newInstance(categoriesBeans.get(i));
            }else if(i==3){
                fragment=MedicineFoodFragment.newInstance(categoriesBeans.get(i));
            }else if(i==4){
                fragment=DynamicStateFragment.newInstance(categoriesBeans.get(i));
            }
            fragments.add(fragment);
        }
        fixedPagerAdapter.setFragments(fragments);
        info_viewpager.setAdapter(fixedPagerAdapter);
        tab_layout.setupWithViewPager(info_viewpager);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=info_viewpager.getCurrentItem();
                if(position%2==0){
                    WebActivity.runActivity(getActivity(),"我的CSDN博客","http://blog.csdn.net/qq_32365567");
                }else{
                    WebActivity.runActivity(getActivity(),"我的GITHUB博客","https://github.com/haoxiongqin");
                }
            }
        });
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        if(position==0){
            ((MainActivity)getActivity()).getDrag_layout().setIsDrag(true);
        }else {
            ((MainActivity)getActivity()).getDrag_layout().setIsDrag(false);
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
