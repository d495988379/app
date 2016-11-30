package com.dl.news;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl.news.fragment.NewsFragment;
import com.dl.news.fragment.SlideLeftFragment;
import com.dl.news.fragment.SlideRightFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.img_home_top_left)
    ImageView imgHomeTopLeft;
    @BindView(R.id.img_home_top_right)
    ImageView imgHomeTopRight;
    @BindView(R.id.rl_home)
    RelativeLayout rlHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    private SlidingMenu slidingMenu;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setBehindOffsetRes(R.dimen.slidemenu_left);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.layout_slidemenu_left);
        slidingMenu.setSecondaryMenu(R.layout.layout_slidemenu_right);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.rl_home, new NewsFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.lin_slidemenu_left, new SlideLeftFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.lin_slidemenu_right, new SlideRightFragment()).commit();
    }


    @OnClick({R.id.img_home_top_left, R.id.img_home_top_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_home_top_left:
                if (!slidingMenu.isMenuShowing()) {
                    slidingMenu.showMenu();
                }
                break;
            case R.id.img_home_top_right:
                if (!slidingMenu.isSecondaryMenuShowing()) {
                    slidingMenu.showSecondaryMenu();
                }
                break;
        }
    }

    public void showfragment(String text, Fragment fragment) {
        tvHome.setText(text);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.rl_home, fragment).commit();
        slidingMenu.showContent();
    }

}
