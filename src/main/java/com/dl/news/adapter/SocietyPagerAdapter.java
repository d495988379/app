package com.dl.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dl.news.R;
import com.dl.news.bean.SocietyPager;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class SocietyPagerAdapter extends PagerAdapter {


    private Context context;

    private List<SocietyPager.ImagesBean> datas;
    private TextView text;

    public SocietyPagerAdapter(Context context, List<SocietyPager.ImagesBean> datas, TextView text) {
        this.context = context;
        this.datas = datas;
        this.text = text;
    }


    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.item_society_pager, null);
        ImageView img = (ImageView) view.findViewById(R.id.img_socirty_pager_item);
        container.addView(view);
        SocietyPager.ImagesBean imagesBean = datas.get(position % datas.size());
        Glide.with(context).load(imagesBean.imgurl).into(img);
        text.setText(imagesBean.title);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
