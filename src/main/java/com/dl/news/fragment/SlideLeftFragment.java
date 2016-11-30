package com.dl.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dl.news.HomeActivity;
import com.dl.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SlideLeftFragment extends Fragment {
    @BindView(R.id.rl_left_fragment_news)
    RelativeLayout rlLeftFragmentNews;
    @BindView(R.id.rl_left_fragment_read)
    RelativeLayout rlLeftFragmentRead;
    @BindView(R.id.rl_left_fragment_local)
    RelativeLayout rlLeftFragmentLocal;
    @BindView(R.id.rl_left_fragment_ties)
    RelativeLayout rlLeftFragmentTies;
    @BindView(R.id.rl_left_fragment_pics)
    RelativeLayout rlLeftFragmentPics;

    String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide_left, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.rl_left_fragment_news, R.id.rl_left_fragment_read, R.id.rl_left_fragment_local, R.id.rl_left_fragment_ties, R.id.rl_left_fragment_pics})
    public void onClick(View view) {
        Fragment fragment = null;
        RelativeLayout []views ={rlLeftFragmentNews,rlLeftFragmentRead,rlLeftFragmentLocal,rlLeftFragmentTies,rlLeftFragmentPics};
        for (int i = 0; i < views.length; i++) {
            views[i].setBackgroundColor(0);
        }
        switch (view.getId()) {
            case R.id.rl_left_fragment_news:
                rlLeftFragmentNews.setBackgroundColor(0xff3e0f0f);
                fragment = new NewsFragment();
                text = "资讯";
                break;
            case R.id.rl_left_fragment_read:
                rlLeftFragmentRead.setBackgroundColor(0xff3e0f0f);
                fragment = new ReadFragment();
                text = "收藏";
                break;
            case R.id.rl_left_fragment_local:
                rlLeftFragmentLocal.setBackgroundColor(0xff3e0f0f);
                fragment = new LocalFragment();
                text = "本地";
                break;
            case R.id.rl_left_fragment_ties:
                rlLeftFragmentTies.setBackgroundColor(0xff3e0f0f);
                fragment = new TiesFragment();
                text = "跟帖";
                break;
            case R.id.rl_left_fragment_pics:
                rlLeftFragmentPics.setBackgroundColor(0xff3e0f0f);
                fragment = new PicsFragment();
                text = "图片";
                break;
        }
        HomeActivity activity = (HomeActivity) getActivity();
        activity.showfragment(text,fragment);
    }
}
