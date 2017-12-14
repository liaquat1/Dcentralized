package com.dcentralized.auth;

import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Docs: https://api.fhict.nl/documentation/Implicit
 * Conf: https://identity.fhict.nl/.well-known/openid-configuration
 */
public class LoginActivity extends AppCompatActivity {
    private static final String AUTH_URL = "https://identity.fhict.nl/connect/authorize?client_id=%s&scope=%s&response_type=token&redirect_uri=%s&state=%s";
    private static final String CLIENT_ID = "i360661-studywalle";
    private static final String SCOPE = "fhict%20fhict_personal";
    private static final String REDIRECT_URI = "com.decentralized.studywallet://callback";
    private static final String STATE = "k4FETESYm9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonLoginClick(View view) {
//        String url = "https://identity.fhict.nl/connect/authorize?client_id=i360661-studywalle&scope=fhict%20fhict_personal&response_type=token&redirect_uri=com.decentralized.studywallet://callback&state=abc";
        String url = String.format(AUTH_URL, CLIENT_ID, SCOPE, REDIRECT_URI, STATE);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent() != null && getIntent().getData() != null && getIntent().getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = getIntent().getData();
            if(uri.getQueryParameter("error") != null) {
                String error = uri.getQueryParameter("error");
                Log.e("LoginActivity", "An error has occurred : " + error);
            } else {
                String state = uri.getQueryParameter("state");
                if(state.equals(STATE)) {
                    String code = uri.getQueryParameter("code");
                }
            }
        }
    }
}
