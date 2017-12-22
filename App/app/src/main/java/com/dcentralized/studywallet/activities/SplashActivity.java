package com.dcentralized.studywallet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.runnables.LoadingRunnable;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new LoadingRunnable(this)).start();
    }

    public void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(SplashActivity.this, "Database connection failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
