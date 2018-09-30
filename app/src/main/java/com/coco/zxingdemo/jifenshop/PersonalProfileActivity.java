package com.coco.zxingdemo.jifenshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class PersonalProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView=new TextView(this);
        textView.setText("这是个人资料修改完善页面");
        setContentView(textView);
    }
}
