package com.dl.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dl.news.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class HottwoAdapter extends RecyclerView.Adapter<HottwoAdapter.Myholder> {

    private final Context context;
    private final List<String> imgList;

    public HottwoAdapter(Context context, List<String> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_hot_two,null);
        Myholder holder = new Myholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        String bean = imgList.get(position);
        Glide.with(context).load(bean).into(holder.mImgView);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {

        private ImageView mImgView;

        public Myholder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.img_hot_two);
        }
    }
}
