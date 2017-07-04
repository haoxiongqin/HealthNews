package com.keephealth.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.keephealth.app.R;
import com.keephealth.app.common.Config;
import com.keephealth.app.utils.PreferencesUtil;


public class SplashActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String isFirst= PreferencesUtil.getData(Config.REMARK,"").toString();
                if (!TextUtils.isEmpty(isFirst)) {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                }
                finish();
            }
        }, 3000);
    }
}
