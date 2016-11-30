package com.dl.news.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/7.
 */
public class HttpUtil {

    private static final long CACHE_MAX_SIZE = 1024 * 1024 * 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object object = msg.obj;
            if (listener != null) {
                if (object == null) {//访问网络失败
                    listener.onFailure();
                } else {
                    listener.succeed((String) object);
                }
            }
        }
    };
    private onResponseListener listener;


    /**
     * 获取网络是否可用
     *
     * @return
     */
    public boolean isNetAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络状态信息
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo.isAvailable();
    }

    public void formNet(Context context, String url, HashMap<String, String> param) {
        //创建缓存文件
        Cache cache = new Cache(new File(context.getCacheDir(), MD5Utils.encode(url)), CACHE_MAX_SIZE);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();
        //如果网络可用从网络获取，否则从缓存中获取
        CacheControl control = isNetAvailable(context) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE;
        //拼接请求参数
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String, String>> entries = param.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody body = builder.build();
        Request request = new Request.Builder().cacheControl(control).url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.obj = null;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message msg = handler.obtainMessage();
                msg.obj = json;
                handler.sendMessage(msg);
            }
        });
    }

    /*设置网络访问监听*/
    public void callBack(onResponseListener listener) {
        this.listener = listener;
    }

    public interface onResponseListener {
        /*访问网络失败*/
        void onFailure();

        /**
         * 访问网络成功，
         *
         * @param json ：返回的数据
         */
        void succeed(String json);
    }

    /**
     * 请求网络
     *
     * @param context ：上下文
     * @param url     ：资源地址
     * @param param   ：请求参数
     * @return
     */
    public HttpUtil RequstNet(Context context, String url, HashMap<String, String> param) {
        formNet(context, url, param);
        return this;
    }
}
