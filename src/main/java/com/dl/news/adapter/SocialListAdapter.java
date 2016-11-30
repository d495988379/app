package com.dl.news.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dl.news.R;
import com.dl.news.bean.SocietyNewBean;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/2.
 */
public class SocialListAdapter extends BaseAdapter {


    private Context context;
    private List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> dataList;
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THREE = 2;

    private final static int COUNT = 3;
    private final HashMap<Integer, Boolean> selects;

    public SocialListAdapter(Context context, List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        selects = new HashMap<>();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean> imageurls = dataList.get(position).imageurls;
        int size = imageurls.size();
        if (size == 0) return TYPE_ONE;
        else if (size == 1) return TYPE_TWO;
        else if (size > 1) return TYPE_THREE;
        return -1;
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void saveClickItem(int position){
        selects.put(position,true);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder holder = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_ONE:
                    convertView = View.inflate(context, R.layout.item_social_one, null);
                    holder = new ViewHolder01(convertView);
                    convertView.setTag(holder);
                    break;
                case TYPE_TWO:
                    convertView = View.inflate(context, R.layout.item_social_two, null);
                    holder = new ViewHolder02(convertView);
                    convertView.setTag(holder);
                    break;
                case TYPE_THREE:
                    convertView = View.inflate(context, R.layout.item_social_third, null);
                    holder = new ViewHolder03(convertView);
                    convertView.setTag(holder);
                    break;
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean data = dataList.get(position);
        holder.setData(data);
        if(selects.containsKey(position)){
            holder.setTitleColor(Color.GRAY);
        }else {
            holder.setTitleColor(Color.BLACK);
        }
        return convertView;
    }

    public void clear() {
        selects.clear();
    }


    public static abstract class ViewHolder {
        public abstract void setData(SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean data);
        public abstract void setSelect(int position);
        public abstract void setTitleColor(int color);
        public String url;
    }


    class ViewHolder01 extends ViewHolder {
        @BindView(R.id.item_recommend_first_title)
        TextView itemRecommendFirstTitle;
        @BindView(R.id.item_recommend_first_text)
        TextView itemRecommendFirstText;

        ViewHolder01(View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean data) {
            url = data.link;
            itemRecommendFirstTitle.setText(data.title);
            itemRecommendFirstText.setText(data.desc);
        }

        @Override
        public void setSelect(int position) {
            selects.put(position,true);
        }

        @Override
        public void setTitleColor(int color) {
            itemRecommendFirstTitle.setTextColor(color);
            itemRecommendFirstText.setTextColor(color);
        }
    }

    class ViewHolder02 extends ViewHolder {
        @BindView(R.id.item_recommend_second_title)
        TextView itemRecommendSecondTitle;
        @BindView(R.id.item_recommend_second_text)
        TextView itemRecommendSecondText;
        @BindView(R.id.item_recommend_second_img)
        ImageView itemRecommendSecondImg;

        ViewHolder02(View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean data) {
            url = data.link;
            itemRecommendSecondTitle.setText(data.title);
            itemRecommendSecondText.setText(data.source);
            Glide.with(context).load(data.imageurls.get(0).url).into(itemRecommendSecondImg);
        }

        @Override
        public void setSelect(int position) {
            selects.put(position,true);
        }

        @Override
        public void setTitleColor(int color) {
            itemRecommendSecondTitle.setTextColor(color);
        }
    }

    class ViewHolder03 extends ViewHolder{
        @BindView(R.id.tv_recommend_third_title)
        TextView tvRecommendThirdTitle;
        @BindView(R.id.item_recommend_third_img1)
        ImageView itemRecommendThirdImg1;
        @BindView(R.id.item_recommend_third_img2)
        ImageView itemRecommendThirdImg2;
        @BindView(R.id.item_recommend_third_img3)
        ImageView itemRecommendThirdImg3;
        @BindView(R.id.tv_recommend_third_show)
        TextView tvRecommendThirdShow;

        ViewHolder03(View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean data) {
            url = data.link;
            tvRecommendThirdTitle.setText(data.title);
            tvRecommendThirdShow.setText(data.source);

            List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean> imageurls = data.imageurls;
            if (data.imageurls.size() == 2) {
                Glide.with(context).load(data.imageurls.get(0).url).into(itemRecommendThirdImg1);
                Glide.with(context).load(data.imageurls.get(1).url).into(itemRecommendThirdImg1);
                Glide.with(context).load(data.imageurls.get(1).url).into(itemRecommendThirdImg2);
            } else {
                Glide.with(context).load(data.imageurls.get(0).url).into(itemRecommendThirdImg1);
                Glide.with(context).load(data.imageurls.get(1).url).into(itemRecommendThirdImg2);
                Glide.with(context).load(data.imageurls.get(2).url).into(itemRecommendThirdImg3);
            }
        }

        @Override
        public void setSelect(int position) {
            selects.put(position,true);
        }

        @Override
        public void setTitleColor(int color) {
            tvRecommendThirdTitle.setTextColor(color);
        }
    }
}