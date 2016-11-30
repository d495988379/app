package com.dl.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import com.dl.news.adapter.HottwoAdapter;
import com.dl.news.bean.Hotbean2;
import com.dl.news.util.HttpUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/9.
 */
public class ImageHotActivity extends Activity {
    @BindView(R.id.recy_view_hot)
    RecyclerView recyViewHot;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        ButterKnife.bind(this);
        Intent intent =  getIntent();
        url = intent.getStringExtra("url");
        recyViewHot.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        initData();
    }

    private void initData() {
        HashMap<String,String> param = new HashMap<>();
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.RequstNet(this,url,param).callBack(new HttpUtil.onResponseListener() {
            @Override
            public void onFailure() {

            }

            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                Hotbean2 hotbean2 = gson.fromJson(json, Hotbean2.class);
                List<String> imgList = hotbean2.showapi_res_body.imgList;
                if(imgList.size() != 0){
                    recyViewHot.setAdapter(new HottwoAdapter(ImageHotActivity.this,imgList));
                }
            }
        });
    }
}