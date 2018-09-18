package com.coco.zxingdemo;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SCAN = 101;
    private TextView textView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (AndPermission.hasPermission(this, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //执行业务
            Toast.makeText(this, "权限申请通过", Toast.LENGTH_SHORT).show();
        } else {
            //申请权限
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .send();
        }
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);


//          ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
//          也可以不传这个参数
//          不传的话  默认都为默认不震动  其他都为true
                //ZxingConfig config = new ZxingConfig();
                //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
                //config.setPlayBeep(true);//是否播放提示音
                //config.setShake(true);//是否震动
                //config.setShowAlbum(true);//是否显示相册
                //config.setShowFlashLight(true);//是否显示闪光灯
                //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                textView.setText("扫描结果为：" + content);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //只需要调用这一句，第一个参数是当前Activity/Fragment，回调方法写在当前Activity/Fragment
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    // 成功回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionYes(100)
    private void getSucceed(List<String> grantedPermissions) {
        // TODO 申请权限成功。
        Toast.makeText(this, "已允许权限", Toast.LENGTH_SHORT).show();
    }

    // 失败回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionNo(100)
    private void getDefeated(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            //用默认的提示语。
            AndPermission.defaultSettingDialog(this, 1).show();
        }
        Toast.makeText(this, "请设置相关权限", Toast.LENGTH_SHORT).show();
    }
}
