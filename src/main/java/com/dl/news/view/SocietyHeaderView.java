package com.dl.news.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dl.news.R;
import com.dl.news.adapter.SocietyPagerAdapter;
import com.dl.news.bean.SocietyPager;
import com.dl.news.util.CommentUtils;
import com.dl.news.util.DisplayUtil;
import com.dl.news.util.MD5Utils;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/2.
 */
public class SocietyHeaderView implements View.OnClickListener{

    private ViewPager mpager;
    private LinearLayout mPointParent;
    private TextView mTvNewTitle;
    private Context context;
    private static final int POST_DELAY = 4000;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = mpager.getCurrentItem();
            if(currentItem >= 99){
                currentItem = count * 10;
            }
            mpager.setCurrentItem(currentItem + 1);
            handler.postDelayed(runnable,POST_DELAY);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            Gson gson = new Gson();
            SocietyPager societyPager = gson.fromJson(json, SocietyPager.class);
            mpager.setAdapter(new SocietyPagerAdapter(context, societyPager.images, mTvNewTitle));
            count = societyPager.images.size();
            mpager.setCurrentItem(count * 10);
            handler.postDelayed(runnable,POST_DELAY);
            initData(societyPager);
        }
    };
    private int count;

    private void initData(SocietyPager societyPager) {
        for (int i = 0; i < societyPager.images.size(); i++) {
            View view = new View(context);
            view.setBackgroundResource(R.drawable.shape_point_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.px2dip(context, 100), DisplayUtil.px2dip(context, 100));
            if (i != 0) {
                params.leftMargin = DisplayUtil.px2dip(context, 100);
            }else {
                view.setBackgroundResource(R.drawable.shape_point_select_bg);
            }
            view.setLayoutParams(params);
            view.setTag(i);
            view.setOnClickListener(this);
            mPointParent.addView(view);

        }
    }

    private FileOutputStream fos;
    private FileInputStream fis;
    private View view;

    public View getCustomHeaderView(Context context) {
        this.context = context;
        view = View.inflate(context, R.layout.view_society_list_header, null);
        mpager = getViewByID(R.id.vp_society_item);
        mPointParent = getViewByID(R.id.llay_point_parent);
        mTvNewTitle = getViewByID(R.id.tv_society_newtitle);
        initData(context);
        setListener();
        return view;
    }

    private void setListener() {
        mpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0;i<mPointParent.getChildCount();i++){
                    View childAt = mPointParent.getChildAt(i);
                    childAt.setBackgroundResource(i == (position % count) ? R.drawable.shape_point_select_bg : R.drawable.shape_point_bg);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData(Context context) {
        File file = new File(context.getCacheDir(), MD5Utils.encode(CommentUtils.URL_SOCIETY_VP));
        if (file.exists()) {
            try {
                fis = new FileInputStream(file.getAbsolutePath());
                byte[] bytes = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = fis.read(bytes)) != -1) {
                    bos.write(bytes, 0, len);
                }
                String json = new String(bos.toByteArray());
                Message msg = handler.obtainMessage();
                msg.obj = json;
                handler.sendMessage(msg);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(CommentUtils.URL_SOCIETY_VP).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    saveDataToLocal(json);
                    Message msg = handler.obtainMessage();
                    msg.obj = json;
                    handler.sendMessage(msg);
                }
            });
        }
    }

    private void saveDataToLocal(String json) {
        File file = new File(context.getCacheDir(), MD5Utils.encode(CommentUtils.URL_SOCIETY_VP));
        try {
            fos = new FileOutputStream(file.getAbsoluteFile());
            fos.write(json.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T extends View> T getViewByID(@IdRes int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        int positon = (int) v.getTag();
        for(int i = 0;i<mPointParent.getChildCount();i++){
            View childAt = mPointParent.getChildAt(i);
            childAt.setBackgroundResource(i == positon ? R.drawable.shape_point_select_bg : R.drawable.shape_point_bg);
        }
        int currentItem = mpager.getCurrentItem();
        int index = currentItem % count;
        currentItem = currentItem - index + positon;
        mpager.setCurrentItem(currentItem,false);
    }
}
