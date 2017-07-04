package com.keephealth.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.Display;
import android.widget.TextView;

import java.net.URL;

/**
 */

public class URLImageGetter implements Html.ImageGetter {
    TextView textView;
    Context context;
    String BaseUrl;
    public URLImageGetter(Context contxt, TextView textView,String BaseUrl) {
        this.context = contxt;
        this.textView = textView;
        this.BaseUrl=BaseUrl;
    }
    @Override
    public Drawable getDrawable(String paramString) {
        URLDrawable urlDrawable = new URLDrawable(context);

        ImageGetterAsyncTask getterTask = new ImageGetterAsyncTask(urlDrawable);
        getterTask.execute(paramString);
        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;
        public ImageGetterAsyncTask(URLDrawable drawable) {
            this.urlDrawable = drawable;
        }
        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                urlDrawable.drawable = result;
                URLImageGetter.this.textView.requestLayout();
            }
        }
        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        public Drawable fetchDrawable(String url) {
            Drawable drawable = null;
            URL Url;
            String urlTxt=BaseUrl+url;
            try {
                Url = new URL(urlTxt);
                drawable = Drawable.createFromStream(Url.openStream(), "");
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, 400,250);

            // 按比例缩放图片
//            Rect bounds = getDefaultImageBounds(context);
//            int newwidth = bounds.width();
//            int newheight = bounds.height();
//            double factor = 1;
//            double fx = (double) drawable.getIntrinsicWidth()
//                    / (double) newwidth;
//            double fy = (double) drawable.getIntrinsicHeight()
//                    / (double) newheight;
//            factor = fx > fy ? fx : fy;
//            if (factor < 1)
//                factor = 1;
//            newwidth = (int) (drawable.getIntrinsicWidth() / factor);
//            newheight = (int) (drawable.getIntrinsicHeight() / factor);
//            drawable.setBounds(0, 0, newwidth, newheight);
            return drawable;
        }
    }

    // 预定图片宽高比例为 4:3
    @SuppressWarnings("deprecation")
    public Rect getDefaultImageBounds(Context context) {
        Display display = ((Activity) context).getWindowManager()
                .getDefaultDisplay();
        int width = display.getWidth();
        int height = (int) (width * 3 / 4);
        Rect bounds = new Rect(0, 0, width, height);
        return bounds;
    }
}
