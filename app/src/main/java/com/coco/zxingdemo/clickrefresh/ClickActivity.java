package com.coco.zxingdemo.clickrefresh;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coco.zxingdemo.R;

import java.util.HashMap;

public class ClickActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout home;
    private LinearLayout two;
    private TextView home_tv;
    private TextView two_tv;
    private ImageView home_img;
    private ImageView two_img;
    private FragmentManager manager;
    private HomeFragment homeFragment;
    private TwoFragment twoFragment;
    private HashMap<String, Fragment> fragments;
    private String currentFName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        initFragment();
        initView();


    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        FragmentTransaction transation = manager.beginTransaction();
        homeFragment = new HomeFragment();
        fragments = new HashMap<>();
        fragments.put("first", homeFragment);
        transation.add(R.id.mRel_frag, homeFragment);
        transation.commit();
        currentFName = "first";
    }

    private void initView() {
        home = findViewById(R.id.home);
        two = findViewById(R.id.two);

        home_tv = findViewById(R.id.mTv_home);
        two_tv = findViewById(R.id.mTv_two);

        home_img = findViewById(R.id.mImg_home);
        two_img = findViewById(R.id.mImg_two);

        home.setOnClickListener(this);
        two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                if ("first".equals(currentFName)) {
                    doubleClick(v);
                    return;
                }
                FragmentTransaction fragmentTransation = manager.beginTransaction();
                fragmentTransation.hide(fragments.get(currentFName));
                fragmentTransation.show(homeFragment);
                fragmentTransation.commit();
                currentFName = "first";
                switchStatus(1);
                break;
            case R.id.two:
                fragmentTransation = manager.beginTransaction();
                if (null == twoFragment) {
                    fragmentTransation.hide(fragments.get(currentFName));
                    twoFragment = new TwoFragment();
                    fragmentTransation.add(R.id.mRel_frag, twoFragment);
                    fragments.put("indexFragment", twoFragment);
                    fragmentTransation.commit();
                } else {
                    fragmentTransation.hide(fragments.get(currentFName));
                    fragmentTransation.show(twoFragment);
                    fragmentTransation.commit();
                }
                currentFName = "indexFragment";
                switchStatus(2);
                break;
        }
    }

    private void switchStatus(int i) {
        if (i == 1) {
            home_img.setImageResource(R.mipmap.ic_launcher_round);
            two_img.setImageResource(R.mipmap.ic_launcher);
            home_tv.setTextColor(Color.BLUE);
            two_tv.setTextColor(Color.CYAN);
        } else if (i == 2) {
            home_img.setImageResource(R.mipmap.ic_launcher);
            two_img.setImageResource(R.mipmap.ic_launcher_round);
            home_tv.setTextColor(Color.CYAN);
            two_tv.setTextColor(Color.BLUE);
        }
    }

    long firstClickTime = 0;
    long secondClickTime = 0;

    public void doubleClick(View view) {
        //判断是不是处于首页，如果是首页的话再次点击进行回到顶部并且刷新数据
        if (currentFName.equals("first")){
            homeFragment.ScrollToTop();
        }
        //双击首页进行刷新数据
//        if (firstClickTime > 0) {
//            secondClickTime = SystemClock.uptimeMillis();
//            if (secondClickTime - firstClickTime < 500) {
//                homeFragment.ScrollToTop();
//            }
//            firstClickTime = 0;
//            return;
//        }
//
//        firstClickTime = SystemClock.uptimeMillis();
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(500);
//                    firstClickTime = 0;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
