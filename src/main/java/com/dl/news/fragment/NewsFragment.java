package com.dl.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dl.news.R;
import com.dl.news.adapter.HomPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/31.
 */
public class NewsFragment extends Fragment {
    @BindView(R.id.tab_home)
    TabLayout tabHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        initdate();
        return view;
    }

    protected void initdate() {
        ArrayList<String> newsTab = new ArrayList<>();
        newsTab.add("推荐");
        newsTab.add("热点");
        newsTab.add("社会");
        newsTab.add("科技");
        newsTab.add("娱乐");
        newsTab.add("图片");
        newsTab.add("国际");
        newsTab.add("军事");
        tabHome.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (String text : newsTab) {
            tabHome.addTab(tabHome.newTab().setText(text));
        }
        vpHome.setAdapter(new HomPagerAdapter(getActivity().getSupportFragmentManager(), newsTab));
        tabHome.setupWithViewPager(vpHome);
    }


    @OnClick({R.id.tab_home, R.id.vp_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                break;
            case R.id.vp_home:
                break;
        }
    }
}
