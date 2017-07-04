package com.sources.mycallback2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sources.mycallback2.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.CheckBox1)
    public CheckBox checkbox1;
    @InjectView(R.id.CheckBox2)
    public CheckBox checkbox2;
    @InjectView(R.id.CheckBox3)
    public CheckBox checkbox3;
    @InjectView(R.id.tv1)
    public TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        // 注册eventbus
        EventBus.getDefault().register(this);

        changeState(Utils.STATE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void checkEventBus(EventBusMessage message){
        Log.e("接收： ",""+message.getMessage());
        tv1.setText("接收到的状态值为： " + message.getMessage());

        checkbox1.setChecked((4 & message.getMessage()) == 4);
        checkbox2.setChecked((2 & message.getMessage()) == 2);
        checkbox3.setChecked((1 & message.getMessage()) == 1);

//        Utils.STATE = message.getMessage();
    }

    public void buttonClick(View v) {

        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        startActivity(intent);
    }

    private void changeState(final int state){
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox1.isChecked()) Utils.STATE = Utils.STATE | 4;
                else Utils.STATE = Utils.STATE & 3;
            }
        });

        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox2.isChecked()) Utils.STATE = Utils.STATE | 2;
                else Utils.STATE = Utils.STATE & 5;
            }
        });

        checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox3.isChecked()) Utils.STATE = Utils.STATE | 1;
                else Utils.STATE = Utils.STATE & 6;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Main onDestroy： ","Main onDestroy");
        // 取消注册
        EventBus.getDefault().unregister(this);
    }
}
