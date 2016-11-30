package com.dl.news;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/21.
 */
public class LogoAcitvity extends BaseActivity {

    private TextView mtextView;
    private Timer timer;
    private int titleTime = 4;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_logo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mtextView = (TextView) findViewById(R.id.logo_tx);
    }

    @Override
    protected void setlisten() {
        mtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartHomeActivity();
            }
        });
    }

    @Override
    protected void initdate() {
        timer = new Timer(false);
           timer.schedule(new TimerTask() {
                @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        mtextView.setText(String.format(getResources().getString(
                                R.string.logo_jump), titleTime--));
                    }
                });
                if (titleTime <= 0) {
                    StartHomeActivity();
                }
            }
        }, 0, 1000);
    }
    private void StartHomeActivity() {
        if (timer != null) timer.cancel();
        startActivity(new Intent(LogoAcitvity.this,HomeActivity.class));
        overridePendingTransition(R.anim.home_enter, R.anim.logo_exit);
        finish();
    }
}
