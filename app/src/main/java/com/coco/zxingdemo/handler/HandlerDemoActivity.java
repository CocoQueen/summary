package com.coco.zxingdemo.handler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coco.zxingdemo.R;

/**
 * handler的用法
 */
public class HandlerDemoActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv;
    @SuppressLint("HandlerLeak")
    private Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText(msg.arg1 + "======" + msg.arg2 + "--------------" + msg.obj);
        }
    };

    private Handler handler1 = new Handler();
    private Handler handler2 = new Handler();

    @SuppressLint("HandlerLeak")
    private Handler handler4 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {//拦截消息，如果为true吐司2就不会执行，如果为false1和2都会执行
            Toast.makeText(HandlerDemoActivity.this, "" + 1, Toast.LENGTH_SHORT).show();
            return true;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(HandlerDemoActivity.this, "" + 2, Toast.LENGTH_SHORT).show();
        }
    };
    private ImageView img;

    private MyRunnable runnable = new MyRunnable();

    //图片数组
    private int[] images = {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_action_add};

    private int index;//索引
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        tv = findViewById(R.id.tv);
        img = findViewById(R.id.img);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(this);

        /***********第一种用法**************/

        /*这种写法会报 android.view.ViewRootImpl$CalledFromWrongThreadException:
        Only the original thread that created a view hierarchy can touch its views.的错误，
        原因是在非ui线程中更新ui.解决方案就是：添加handler机制更新ui*/
//        new Thread() {
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    tv.setText("update thread");
//                } catch (InterruptedException e) {
//                        e.printStackTrace();
//                }
//            }
//        }.start();
        /*正解*/
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler1.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText("update thread");
                        }
                    });
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
        }.start();

        /**************第二种用法********************/

        /*类似轮播图功能的实现*/
        handler2.postDelayed(runnable, 1000);



        /***************第三种用法*******************/

        //发送消息的操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    Message message = new Message();
                    Message message = handler3.obtainMessage();
                    message.arg1 = 88;
                    message.arg2 = 100;
                    Person person = new Person();
                    person.age = 23;
                    person.name = "小明";
                    message.obj = person;
//                    handler3.sendMessage(message);
                    message.sendToTarget();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //移除handler1执行runnable的操作
    @Override
    public void onClick(View v) {
        handler2.removeCallbacks(runnable);

        /***********第四种用法***************/
        handler4.sendEmptyMessage(1);
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 3;
            img.setImageResource(images[index]);
            handler2.postDelayed(runnable, 1000);
        }
    }

    class Person {
        public int age;

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        public String name;
    }
}
