package com.keephealth.app.ui;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.keephealth.app.R;
import com.keephealth.app.common.Config;
import com.keephealth.app.ui.base.BaseActivity;
import com.keephealth.app.utils.CustomTextView;
import com.keephealth.app.utils.ForegroundAlphaColorSpan;
import com.keephealth.app.utils.ForegroundAlphaColorSpanGroup;
import com.keephealth.app.utils.PreferencesUtil;
import com.keephealth.app.utils.SpanUtils;
import com.keephealth.app.widget.CustomVideoView;

/**
 * 当前类注释:启动欢迎界面
 */
public class WelcomeActivity extends BaseActivity {
    private Button welcome_button;
    private CustomVideoView welcome_videoview;
    private CustomTextView welcomeTxt;
    SpanUtils   mSpanUtils;
    SpannableStringBuilder animSsb;
    ValueAnimator valueAnimator;
    Shader        mShader;
    float         mShaderWidth;
    Matrix        matrix;
    ForegroundAlphaColorSpan mForegroundAlphaColorSpan;
    ForegroundAlphaColorSpanGroup mForegroundAlphaColorSpanGroup;
    String   mPrinterString;
    float    density;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcome_button=(Button)this.findViewById(R.id.welcome_button);
        welcomeTxt=(CustomTextView)this.findViewById(R.id.welcomeTxt);
        setMainText(welcomeTxt);
        PreferencesUtil.saveData(Config.REMARK,"first");
        welcome_videoview = (CustomVideoView) this.findViewById(R.id.welcome_videoview);
        welcome_videoview.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.shipin));
        welcome_videoview.start();
        welcome_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                welcome_videoview.start();
                welcome_videoview=null;
                openActivity(MainActivity.class);
                WelcomeActivity.this.finish();
            }
        });
        welcome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(welcome_videoview.isPlaying()){
                    welcome_videoview.stopPlayback();
                    welcome_videoview=null;
                }
                openActivity(MainActivity.class);
                WelcomeActivity.this.finish();
            }
        });
//        initAnimSpan();
//        startAnim();
    }
    public  void setMainText(TextView view){
        SpannableStringBuilder builder = new SpannableStringBuilder(view.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan whiteSpan = new ForegroundColorSpan(Color.WHITE);
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.GREEN);
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(Color.YELLOW);
        ForegroundColorSpan cyanSpan = new ForegroundColorSpan(getResources().getColor(R.color.mask_tags_11));
        ForegroundColorSpan purpleSpan = new ForegroundColorSpan(getResources().getColor(R.color.mask_tags_3));

        builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(whiteSpan, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(blueSpan, 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(greenSpan, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(yellowSpan, 4,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(cyanSpan, 5,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(purpleSpan, 6,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(builder);
    }

    private void initAnimSpan() {
//        mShaderWidth = 64 * density * 4;
        mShader = new LinearGradient(0, 0, mShaderWidth, 0,
                getResources().getIntArray(R.array.rainbow),
                null,Shader.TileMode.REPEAT);
//        matrix = new Matrix();
        mForegroundAlphaColorSpanGroup = new ForegroundAlphaColorSpanGroup(0);
        mForegroundAlphaColorSpan = new ForegroundAlphaColorSpan(Color.TRANSPARENT);
        mPrinterString = "科技替代不了爱";
        mSpanUtils = new SpanUtils().appendLine("科技替代不了爱").setFontSize(20, true).setShader(mShader);
//        for(int i = 0,len = mPrinterString.length();i <len;++i){
//            ForegroundAlphaColorSpan span = new ForegroundAlphaColorSpan(Color.TRANSPARENT);
//            mSpanUtils.append(mPrinterString.substring(i, i + 1)).setSpans(span);
//            mForegroundAlphaColorSpanGroup.addSpan(span);
//        }
        animSsb = mSpanUtils.create();
    }
    private void startAnim() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // shader
//                matrix.reset();
//                matrix.setTranslate((Float) animation.getAnimatedValue() * mShaderWidth, 0);
//                mShader.setLocalMatrix(matrix);
//                // blur
//                mBlurMaskFilterSpan.setRadius(25 * (1.00001f - (Float) animation.getAnimatedValue()));
//                // shadow
//                mShadowSpan.setDx(16 * (0.5f - (Float) animation.getAnimatedValue()));
//                mShadowSpan.setDy(16 * (0.5f - (Float) animation.getAnimatedValue()));

                // alpha
//                mForegroundAlphaColorSpan.setAlpha((int) (255 * (Float) animation.getAnimatedValue()));

                // printer
                mForegroundAlphaColorSpanGroup.setAlpha((Float) animation.getAnimatedValue());

                // update
                welcomeTxt.setText(animSsb);
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(600 * 3);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }
}
