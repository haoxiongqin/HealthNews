package com.keephealth.app;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class App extends Application {
    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }
    public static App getInstance() {
        return instance;
    }
    public  void loadImage(Context context, String url, ImageView view) {
        Picasso.with(context)
                .load(url)
                .into(view);
    }
}
