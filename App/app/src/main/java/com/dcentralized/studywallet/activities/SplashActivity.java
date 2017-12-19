package com.dcentralized.studywallet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.runnables.LoadingRunnable;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new LoadingRunnable(this)).start();
    }
}
