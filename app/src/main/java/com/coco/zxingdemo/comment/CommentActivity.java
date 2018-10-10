package com.coco.zxingdemo.comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coco.zxingdemo.R;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mList;
    private ImageView mImg_comment;
    private ImageView mImg_share;
    private LinearLayout mLiner;
    private TextView mTv_hide;
    private EditText mEd_comment;
    private Button mBtn_send;
    private RelativeLayout mRl_comment;
    private List<CommentBean> list;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
    }

    private void initView() {
        mList = findViewById(R.id.mList);
        mImg_comment = findViewById(R.id.mImg_comment);
        mImg_share = findViewById(R.id.mImg_share);
        mLiner = findViewById(R.id.mLiner);
        mTv_hide = findViewById(R.id.mTv_hide);
        mEd_comment = findViewById(R.id.mEd_comment);
        mBtn_send = findViewById(R.id.mBtn_send);
        mRl_comment = findViewById(R.id.mRl_comment);
        list = new ArrayList<>();
        adapter = new CommentAdapter(this, list);
        mList.setAdapter(adapter);

        mBtn_send.setOnClickListener(this);
        mTv_hide.setOnClickListener(this);
        mImg_comment.setOnClickListener(this);
        mImg_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn_send:
                submit();
                break;

            case R.id.mImg_comment:
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                mRl_comment.setVisibility(View.VISIBLE);
                mLiner.setVisibility(View.GONE);
                break;

            case R.id.mTv_hide:
                mLiner.setVisibility(View.VISIBLE);
                mRl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(mEd_comment.getWindowToken(), 0);
                break;
            case R.id.mImg_share:
                Toast.makeText(this, "此功能还有待完善", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void submit() {
        // validate
        String comment = mEd_comment.getText().toString().trim();
        if (TextUtils.isEmpty(comment)) {
            Toast.makeText(this, "评论一下吧～", Toast.LENGTH_SHORT).show();
        } else {
            CommentBean bean = new CommentBean();
            bean.setName("评论者" + (list.size() + 1) + ":");
            bean.setContent(comment);
            adapter.addComment(bean);
            mEd_comment.setText("");//发送完，清空输入框
            Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
        }
    }
}
