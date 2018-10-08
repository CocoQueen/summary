package com.coco.zxingdemo.progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coco.zxingdemo.R;

public class ProgressActivity extends AppCompatActivity {

    private CustomCircleProgress progressBar;
    private MusicProgressBar progressBar2;
    private int mTotalProgress = 1000;
    private int mCurrentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = findViewById(R.id.mProgresss);
        progressBar2 = findViewById(R.id.mProgresss2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mCurrentProgress < mTotalProgress) {
                    mCurrentProgress += 1;
                    progressBar.setProgress(mCurrentProgress);
                    progressBar2.setProgress(mCurrentProgress);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
