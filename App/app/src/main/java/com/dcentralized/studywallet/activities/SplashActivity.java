package com.dcentralized.studywallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.runnables.LoadingRunnable;

/**
 * This is the loading activity for the application, it displays a animation during the loading
 *
 * @author Tom de Wildt
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * Initializes activity and starts a new thread
     *
     * @param savedInstanceState
     * @author Tom de Wildt
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new LoadingRunnable(this)).start();
    }

    /**
     * Show a toast if an error occurred and transfers back to the login activity
     *
     * @author Tom de Wildt
     */
    public void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(SplashActivity.this, "Database connection failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
