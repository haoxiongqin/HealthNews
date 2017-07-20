package com.keephealth.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceResponse;

import com.keephealth.app.R;
import com.keephealth.app.ui.base.BaseActivity;

import org.xwalk.core.XWalkJavascriptResult;
import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

public class VideoWebviewActivity extends BaseActivity {
    XWalkView xWalkWebView;
    XWalkSettings mMSettings;
    public final static String URL = "url";
    public final static String TITLE = "title";

    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_webview);
        initView();
    }
    public void initView() {
        String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);
        initTitle(title);
        xWalkWebView=(XWalkView)findViewById(R.id.xWalkWebView);
        XWalkPreferences.setValue("enable-javascript", true);//添加对javascript支持
        //置是否允许通过file url加载的Javascript可以访问其他的源,包括其他的文件和http,https等其他的源
        XWalkPreferences.setValue(XWalkPreferences.ALLOW_UNIVERSAL_ACCESS_FROM_FILE, true);
        //设置滑动样式。
         xWalkWebView.setHorizontalScrollBarEnabled(false);
        xWalkWebView.setVerticalScrollBarEnabled(false);
        xWalkWebView.setScrollBarStyle(XWalkView.SCROLLBARS_OUTSIDE_INSET);
        xWalkWebView.setScrollbarFadingEnabled(true);
       //获取setting
        mMSettings = xWalkWebView.getSettings();
        //支持空间导航
        mMSettings.setSupportSpatialNavigation(true);
        mMSettings.setBuiltInZoomControls(true);
        mMSettings.setSupportZoom(true);
        xWalkWebView.setDrawingCacheEnabled(false);//不使用缓存
        xWalkWebView.getNavigationHistory().clear();//清除历史记录
        xWalkWebView.clearCache(true);//清楚包括磁盘缓存
        xWalkWebView.setUIClient(new XWalkUIClient(xWalkWebView) {
            @Override
            public void onPageLoadStarted(XWalkView view, String url) {
                super.onPageLoadStarted(view, url);
            }
            @Override
            public boolean onJsAlert(XWalkView view, String url, String message, XWalkJavascriptResult result) {
                return super.onJsAlert(view, url, message, result);
            }
            @Override
            public void onScaleChanged(XWalkView view, float oldScale, float newScale) {
                if (view != null) {
                    view.invalidate();
                }
                super.onScaleChanged(view, oldScale, newScale);
            }
            @Override
            public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
                super.onPageLoadStopped(view, url, status);
            }
        });
        xWalkWebView.setResourceClient(new XWalkResourceClient(xWalkWebView) {
            @Override
            public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {
                super.onReceivedLoadError(view, errorCode, description, failingUrl);
            }
            @Override
            public WebResourceResponse shouldInterceptLoadRequest(XWalkView view, String url) {
                return super.shouldInterceptLoadRequest(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        xWalkWebView.load(url, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (xWalkWebView != null) {
            xWalkWebView.pauseTimers();
            xWalkWebView.onHide();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (xWalkWebView != null) {
            xWalkWebView.resumeTimers();
            xWalkWebView.onShow();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xWalkWebView != null) {
            xWalkWebView.onDestroy();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (xWalkWebView.getNavigationHistory().canGoBack()) {
                xWalkWebView.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1);//返回上一页面
            } else {
            /*finish();*/
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
