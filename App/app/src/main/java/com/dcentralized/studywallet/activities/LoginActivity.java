package com.dcentralized.studywallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.services.FirebaseAuthService;
import com.dcentralized.studywallet.services.FontysAuthService;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private FontysAuthService service;

    /**
     * Initializes activity
     *
     * @param savedInstanceState
     * @author Tom de Wildt
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.service = FontysAuthService.getInstance(this);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Handles the login button event
     *
     * @param view
     * @author Tom de Wildt
     */
    public void onButtonLoginClick(View view) {
        if (service.authenticate()) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        Intent intent = getIntent();
        super.onResume();
        service.onResume(intent);
    }
}
