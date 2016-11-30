package com.dl.news.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MyImageView extends ImageView{

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setImageBitmap(Bitmap bm) {
        Drawable drawable = new BitmapDrawable();
        setBackground(drawable);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setImageDrawable(Drawable drawable) {
        setBackground(drawable);
    }

}
