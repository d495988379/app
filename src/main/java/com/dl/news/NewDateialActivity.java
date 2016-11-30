package com.dl.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/11/8.
 */
public class NewDateialActivity extends AppCompatActivity{

    private WebView mwebView;
    private Toolbar toolbar;
    private WebSettings settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdateial);
        mwebView = (WebView) findViewById(R.id.web_newdateial);
        toolbar = (Toolbar) findViewById(R.id.tb_newsateil);
        inittoolbar();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        initWebView();
        mwebView.loadUrl(url);
    }

    private void inittoolbar() {
        setSupportActionBar(toolbar);
    }

    private void initWebView(){
        settings = mwebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        settings.setUseWideViewPort(true);
        int mDensity = metrics.densityDpi;
        if(mDensity == 120){
            settings.setUseWideViewPort(true);
        }else if (mDensity == 160) {
            settings.setUseWideViewPort(true);
        }else if(mDensity == 240){
            settings.setUseWideViewPort(true);
        }
        mwebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tb_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tx_size_small:
                settings.setTextZoom(50);
                break;
            case R.id.tx_size_narmal:
                settings.setTextZoom(100);
                break;
            case R.id.tx_size_big:
                settings.setTextZoom(150);
                break;
            case R.id.tx_size_super_big:
                settings.setTextZoom(200);
                break;
        }
        return true;
    }
}
