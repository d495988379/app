package com.dl.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dl.news.adapter.GuideActivityAdapter;
import com.dl.news.util.DisplayUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Guideactivity extends BaseActivity {
    private boolean isFirst;
    private SharedPreferences sp;
    private static final String IS_FIRST_RUN = "isFirstRun";
    private ViewPager mViewpager;
    private TextView mTextView;
    private LinearLayout mbottom;
    private int[] date = {R.mipmap.bd, R.mipmap.small, R.mipmap.welcome, R.mipmap.wy};

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sp = getSharedPreferences("news_info", MODE_PRIVATE);
        isFirst = sp.getBoolean(IS_FIRST_RUN, true);
        if (isFirst) {
            setContentView(R.layout.activity_guide);
            mViewpager = (ViewPager) findViewById(R.id.guide_vg);
            mTextView = (TextView) findViewById(R.id.guide_tx);
            mbottom = (LinearLayout) findViewById(R.id.guide_bottom);
        } else {
            startActivity(new Intent(this,LogoAcitvity.class));
            finish();
        }
    }

    private void startlogoActivity() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_FIRST_RUN,false).commit();
        startActivity(new Intent(this, LogoAcitvity.class));
        overridePendingTransition(R.anim.logo_enter, R.anim.guide_exit);
        finish();
    }

    @Override
    protected void setlisten() {
        if (isFirst) {
            mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(final int position) {
                    for (int i = 0; i < mbottom.getChildCount(); i++) {
                        View childAt = mbottom.getChildAt(i);
                        childAt.setSelected(i == position);
                        new Timer(false).schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if(position == mbottom.getChildCount() - 1 ){
                                    startlogoActivity();
                                    overridePendingTransition(R.anim.logo_enter, R.anim.guide_exit);
                                }
                            }
                        },1000);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    protected void initdate() {
        if (isFirst) {
            mViewpager.setAdapter(new GuideActivityAdapter(this, date));
            for (int i = 0; i < date.length; i++) {
                View view = new View(this);
                view.setBackgroundResource(R.drawable.guide_point);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        DisplayUtil.px2dip(this,70),DisplayUtil.px2dip(this,70));
                if(i != 0){
                    params.leftMargin = DisplayUtil.px2dip(this,70);
                }
                if(i == 0){
                    view.setSelected(true);
                }
                view.setLayoutParams(params);
                mbottom.addView(view);
            }
        }
    }

    public void onClick(View view) {
        startlogoActivity();
    }
}
