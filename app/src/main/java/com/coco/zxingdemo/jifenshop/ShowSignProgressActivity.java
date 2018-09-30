package com.coco.zxingdemo.jifenshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class ShowSignProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("这是显示签到进度及任务的页面");
        setContentView(textView);
    }
}
