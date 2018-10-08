package com.coco.zxingdemo.jifenshop;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coco.zxingdemo.R;

public class MineActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg_head;
    private TextView mTv_nickname;
    private TextView mTv_inviteTips;
    private boolean flag = true;
    private Handler handler;
    private TextView mTv_signTips;
    private boolean firstFlag = true;
    private RelativeLayout mRl_signTips;

    private boolean isStopCount = false;

    private boolean isPause = true;

    private Handler mHandler = new Handler();

    private long timer = 0;
    private String timeStr = "";
    private TextView mTv_readTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();//初始化控件
        setCofigs();//设置一些显示效果
        inviteTipsChange();//邀请码提示不停的动态变换
        initListener();//初始化一些监听事件
        countTimer();

    }

    private void setCofigs() {
        //给textview添加下划线
        mTv_inviteTips.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        if (firstFlag) {
            mRl_signTips.setBackgroundResource(R.drawable.not_sign_tips_bg);
            mTv_signTips.setText("签到");
        }
    }

    private void initListener() {
        mTv_inviteTips.setOnClickListener(this);
        mImg_head.setOnClickListener(this);
        mRl_signTips.setOnClickListener(this);

    }

    //初始化控件
    private void initView() {
        mImg_head = findViewById(R.id.mImg_head);
        mTv_nickname = findViewById(R.id.mTv_nickname);
        mTv_inviteTips = findViewById(R.id.mTv_inviteTips);
        mTv_signTips = findViewById(R.id.mTv_signTips);
        mRl_signTips = findViewById(R.id.mRl_signTips);
        mTv_readTime = findViewById(R.id.mTv_readTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTv_inviteTips:
                ClipboardManager cmb = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText("A115529969"); //将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
                Toast.makeText(this, "邀请码已复制", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mImg_head:
//                if (!isPause) {
//                    isPause = true;
//                    isStopCount = false;
//                } else {
//                    isPause = false;
//                    isStopCount = true;
//                }
//                startActivity(new Intent(this, PersonalProfileActivity.class));
                break;
            case R.id.mRl_signTips:
                startActivity(new Intent(this, ShowSignProgressActivity.class));
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    public void inviteTipsChange() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if ((msg.arg1) % 2 == 0) {
                    mTv_inviteTips.setText("邀请码：A115529969");
                } else {
                    mTv_inviteTips.setText("点击复制我的邀请码");
                }
                super.handleMessage(msg);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 1;
                while (flag) {//这是个死循环,需要在activity消失时,把flag设为false,结束循环
                    Message msg = new Message();
                    msg.arg1 = count;
                    handler.sendMessage(msg);
                    count++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Runnable TimerRunnable = new Runnable() {

        @Override
        public void run() {
            if (!isStopCount) {
                timer += 1000;
                timeStr = TimeUtil.getFormatTime(timer);
//                String s = timeStr.substring(timeStr.length() - 2, timeStr.length());
                mTv_readTime.setText(timeStr);
            }
            countTimer();
        }
    };

    private void countTimer() {
        mHandler.postDelayed(TimerRunnable, 1000);
    }

    @Override
    protected void onStop() {
        flag = false;
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mRl_signTips.setBackgroundResource(R.drawable.sign_tips_bg);
        mTv_signTips.setText("明日+50");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(TimerRunnable);
    }

}
