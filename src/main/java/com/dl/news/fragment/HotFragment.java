package com.dl.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dl.news.ImageHotActivity;
import com.dl.news.R;
import com.dl.news.adapter.Hotadapter;
import com.dl.news.bean.Hotbean;
import com.dl.news.util.CommentUtils;
import com.dl.news.util.HttpUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HotFragment extends Fragment {
    @BindView(R.id.tl_bar)
    Toolbar tlBar;
    @BindView(R.id.recy_view)
    RecyclerView recyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);
        tlBar.setTitle("请往下看");
        recyView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        initData();
        return view;
    }

    private void initData() {
        final HashMap<String,String> param = new HashMap<>();

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.RequstNet(getActivity(), CommentUtils.URL_HOT_DONGMAN,param).callBack(new HttpUtil.onResponseListener() {
            @Override
            public void onFailure() {

            }

            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                Hotbean hotbean = gson.fromJson(json, Hotbean.class);
                List<Hotbean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = hotbean.showapi_res_body.pagebean.contentlist;
                Hotadapter hotadapter = new Hotadapter(getActivity(),list);
                recyView.setAdapter(hotadapter);
                hotadapter.setOnItemClickListener(new Hotadapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, String data) {

                        Intent intent = new Intent(getActivity(), ImageHotActivity.class);
                        intent.putExtra("url",CommentUtils.URL_HOT_DONGMAN1 + data);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
