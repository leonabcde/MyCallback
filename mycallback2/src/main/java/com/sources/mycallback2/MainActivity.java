package com.sources.mycallback2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {
        android.widget.Toast.makeText(MainActivity.this, "dyhghjgy", Toast.LENGTH_SHORT).show();
    }
}
