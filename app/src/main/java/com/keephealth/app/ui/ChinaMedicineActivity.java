package com.keephealth.app.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.keephealth.app.R;
import com.keephealth.app.fragment.ChinaFemaleFragment;
import com.keephealth.app.fragment.ChinaHairdFragment;
import com.keephealth.app.fragment.ChinaHealthFragment;
import com.keephealth.app.fragment.ChinaKeepFragment;
import com.keephealth.app.fragment.ChinaMaleFragment;
import com.keephealth.app.fragment.ChinaMentalityFragment;
import com.keephealth.app.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ChinaMedicineActivity extends BaseActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab;
    List<Pair<String,Fragment>> fragment;
    TextView headerBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_medicine);
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        headerBack=(TextView)findViewById(R.id.headerBack);
        fragment = new ArrayList<>();
        fragment.add(new Pair<String, Fragment>("养生", new ChinaKeepFragment()));
        fragment.add(new Pair<String, Fragment>("美容", new ChinaHairdFragment()));
        fragment.add(new Pair<String, Fragment>("健康", new ChinaHealthFragment()));
        fragment.add(new Pair<String, Fragment>("心理", new ChinaMentalityFragment()));
        fragment.add(new Pair<String, Fragment>("男性", new ChinaMaleFragment()));
        fragment.add(new Pair<String, Fragment>("女性", new ChinaFemaleFragment()));
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        headerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public class  MainAdapter extends FragmentPagerAdapter {
        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment.get(position).second;
        }

        @Override
        public int getCount() {
            return fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragment.get(position).first;
        }
    }
}
