package com.sources.mycallback2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.CheckBox1)
    public CheckBox checkbox1;
    @InjectView(R.id.CheckBox2)
    public CheckBox checkbox2;
    @InjectView(R.id.CheckBox3)
    public CheckBox checkbox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册eventbus
        EventBus.getDefault().register(MainActivity.this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册eventbus
        EventBus.getDefault().unregister(MainActivity.this);
    }

    /**
     * 点击跳转到第二个页面
     *
     * @param v
     */
    public void buttonClick(View v) {
        EventBus.getDefault().post(new EventBusMessage("第一个页面发送过来的信息"));

        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        startActivity(intent);
    }

    public void initView() {
        Toast.makeText(MainActivity.this, "dyhghjgy", Toast.LENGTH_SHORT).show();
    }


    private CallbackListener mCallbackListener;

    public void getCallbackListener(CallbackListener listener) {
        this.mCallbackListener = listener;
    }

    public interface CallbackListener {
        void callback();
    }
}
