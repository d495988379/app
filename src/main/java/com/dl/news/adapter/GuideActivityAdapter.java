package com.dl.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/21.
 */
public class GuideActivityAdapter extends PagerAdapter {
    private final Context context;
    private final int[] images;

    public GuideActivityAdapter(Context context, int[] image){

        this.context = context;
        this.images = image;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView image = new ImageView(context);
        image.setBackgroundResource(images[position]);
        ViewGroup.LayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        image.setLayoutParams(params);
        container.addView(image);
        return image;
    }
}
