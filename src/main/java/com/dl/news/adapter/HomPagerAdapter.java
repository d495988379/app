package com.dl.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dl.news.fragment.HotFragment;
import com.dl.news.fragment.InternationalFragment;
import com.dl.news.fragment.MilitaryFragment;
import com.dl.news.fragment.PictureFragment;
import com.dl.news.fragment.RecommendFragment;
import com.dl.news.fragment.RecreationFragment;
import com.dl.news.fragment.ScienceFragment;
import com.dl.news.fragment.SocialFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HomPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> newsTab;

    public HomPagerAdapter(FragmentManager fm, ArrayList<String> newsTab) {
        super(fm);
        this.newsTab = newsTab;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new RecommendFragment();
                break;
            case 1:
                fragment = new HotFragment();
                break;
            case 2:
                fragment = new SocialFragment();
                break;
            case 3:
                fragment = new ScienceFragment();
                break;
            case 4:
                fragment = new RecreationFragment();
                break;
            case 5:
                fragment = new PictureFragment();
                break;
            case 6:
                fragment = new InternationalFragment();
                break;
            case 7:
                fragment = new MilitaryFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return newsTab.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsTab.get(position);
    }
}
