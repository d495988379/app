package com.dl.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dl.news.R;
import com.dl.news.bean.Hotbean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Hotadapter extends RecyclerView.Adapter<Hotadapter.ViewHolder> implements View.OnClickListener {

    private final Context context;
    private List<Hotbean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public Hotadapter(Context context, List<Hotbean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_hot_list,null);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hotbean.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean = data.get(position);
        Glide.with(context).load(bean.img).into(holder.imageView);
        holder.itemView.setTag(bean.link);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_item_hot);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
