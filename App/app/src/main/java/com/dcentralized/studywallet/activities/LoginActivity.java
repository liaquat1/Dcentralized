package com.dcentralized.studywallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.services.FirebaseAuthService;
import com.dcentralized.studywallet.services.FontysAuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
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

        if (!FirebaseAuthService.getInstance(this).login()) {
            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            Button button = findViewById(R.id.buttonLogin);
            button.setEnabled(false);
        }

        setContentView(R.layout.activity_login);
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
