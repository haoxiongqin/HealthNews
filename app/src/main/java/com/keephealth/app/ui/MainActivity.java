package com.keephealth.app.ui;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.keephealth.app.R;
import com.keephealth.app.adapter.LeftItemAdapter;
import com.keephealth.app.common.ActivityStack;
import com.keephealth.app.common.DefineView;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.T;
import com.keephealth.app.widget.DragLayout;
import com.nineoldandroids.view.ViewHelper;

import java.util.List;

import static com.keephealth.app.utils.T.context;

/**
 * 当前类注释:主Activity类
 */
public class MainActivity extends BaseActivity implements DefineView {
    public DragLayout getDrag_layout() {
        return drag_layout;
    }
    private DragLayout drag_layout;
    private ImageView top_bar_icon;
    private ListView lv_left_main;
    Button top_bar_search_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        initView();
        initValidata();
    }
//    protected boolean translucentStatusBar() {
//        return true;
//    }
    public void initView() {
        drag_layout = (DragLayout) findViewById(R.id.drag_layout);
        top_bar_icon = (ImageView) findViewById(R.id.top_bar_icon);
        top_bar_search_btn = (Button) findViewById(R.id.top_bar_search_btn);
        lv_left_main=(ListView)findViewById(R.id.lv_left_main);
        drag_layout.setDragListener(new CustomDragListener());
        top_bar_icon.setOnClickListener(new CustomOnClickListener());
        top_bar_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SeachListActivity.class));
            }
        });
    }
    @Override
    public void initValidata() {
        lv_left_main.setAdapter(new LeftItemAdapter());
        lv_left_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    openActivity(HealthNewsActivity.class);
                }else if(position==1){
                    openActivity(ChinaMedicineActivity.class);
                }else if(position==2){
                    openActivity(CookBookActivity.class);
                }else if(position==3){
                    openActivity(VideoListActivity.class);
                }
            }
        });
    }
    class CustomDragListener implements DragLayout.DragListener{
        /**
         * 界面打开
         */
        @Override
        public void onOpen() {

        }
        /**
         * 界面关闭
         */
        @Override
        public void onClose() {
        }
        /**
         * 界面进行滑动
         * @param percent
         */
        @Override
        public void onDrag(float percent) {
              ViewHelper.setAlpha(top_bar_icon,1-percent);
        }
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View arg0) {
            drag_layout.open();
        }
    }

    // 实现按两次退出
    long waitTime = 2000;
    long touchTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                T.showShort("再按一次退出程序");
                touchTime = currentTime;
            } else {
//                exitAPP1();
                ActivityStack.create().appExit(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //finish掉所有的activity
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void exitAPP1() {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        for (ActivityManager.AppTask appTask : appTaskList) {
            appTask.finishAndRemoveTask();
        }
        System.exit(0);
    }
}
