package com.sources.mycallback2;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Handsome-Chen on 2017/7/4 0004.
 */
public class CallActivity extends Activity {
    MainActivity mMainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivity.getCallbackListener(new MainActivity.CallbackListener() {
            @Override
            public void callback() {
                
            }
        });
    }
}
