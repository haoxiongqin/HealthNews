package com.keephealth.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.keephealth.app.entity.CategoriesBean;

import java.util.List;

/**
 * 当前类注释:ViewPager Fragment自定义适配器，其中管理每个页面(Fragment集合)和Tab显示标题
 */
public class FixedPagerAdapter extends FragmentStatePagerAdapter {
    private List<CategoriesBean> categoriesBeans;

    public void setCategoriesBeans(List<CategoriesBean> categoriesBeans) {
        this.categoriesBeans = categoriesBeans;
    }
    private List<Fragment> fragments;
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try{
             fragment=(Fragment)super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return categoriesBeans.get(position%categoriesBeans.size()).getTitle();
    }
}
