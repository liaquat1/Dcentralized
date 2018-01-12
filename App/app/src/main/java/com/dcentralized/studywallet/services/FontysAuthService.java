package com.dcentralized.studywallet.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.activities.SplashActivity;
import com.dcentralized.studywallet.utilities.DateUtility;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;

/**
 * This class handles the authentication with the FHICT API
 *
 * @author Tom de Wildt
 */
public class FontysAuthService {
    // API Variables
    private static final String AUTH_URL = "https://identity.fhict.nl/connect/authorize?client_id=%s&scope=%s&response_type=token&redirect_uri=%s&state=%s";
    private static final String CLIENT_ID = "i360661-studywalle";
    private static final String SCOPE = "fhict%20fhict_personal";
    private static final String REDIRECT_URI = "com.decentralized.studywallet://callback";

    // Preferences Keys
    private static final String TOKEN_PREF_KEY = FontysAuthService.class.getSimpleName().toUpperCase() + "_TOKEN";
    private static final String DATE_PREF_KEY = FontysAuthService.class.getSimpleName().toUpperCase() + "_DATE";
    private static final String EXPIRES_PREF_KEY = FontysAuthService.class.getSimpleName().toUpperCase() + "_EXPIRES";

    // Class Variables
    private static final String TAG = FontysAuthService.class.getSimpleName();
    private static FontysAuthService instance;
    private Context context;
    private String state;
    private String token;

    /**
     * Instantiates the class, sets a random state and loads the token if it's present
     *
     * @param context activity context
     * @author Tom de Wildt
     */
    private FontysAuthService(Context context) {
        this.context = context;
        this.state = getRandomState();
        this.token = loadToken();
    }

    /**
     * Opens a custom tab with the API URL
     *
     * @return if token is not null else false
     * @author Tom de Wildt
     */
    public boolean authenticate() {
        if (token != null) {
            return true;
        } else {
            String url = String.format(AUTH_URL, CLIENT_ID, SCOPE, REDIRECT_URI, state);
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
            return false;
        }
    }

    /**
     * Checks if the intent is not null & has data then calls the store method
     *
     * @param intent the intent received when resuming
     * @author Tom de Wildt
     */
    public void onResume(Intent intent) {
        if(intent != null && intent.getData() != null && intent.getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = Uri.parse(intent.getDataString().replace("#", "?"));
            if(uri.getQueryParameter("error") != null) {
                String error = uri.getQueryParameter("error");
                Log.e(TAG, "An error has occurred : " + error);
            } else {
                String state = uri.getQueryParameter("state");
                if(state.equals(state)) {
                    String token = "Bearer " + uri.getQueryParameter("access_token");
                    int expires = Integer.parseInt(uri.getQueryParameter("expires_in"));
                    Calendar date = Calendar.getInstance();
                    this.token = token;
                    storeToken(token, expires, date);
                    startActivity(SplashActivity.class);
                }
            }
        }
    }

    /**
     * Starts a new activity
     *
     * @param activity to start
     * @author Tom de Wildt
     */
    private void startActivity(Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    /**
     * Returns a random string of letters and numbers
     *
     * @return string of 12 chars
     * @author Tom de Wildt
     */
    private String getRandomState() {
        int length = 12;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    /**
     * Stores the token in the shared prefs
     *
     * @param token api token
     * @param expires expire date
     * @param date date token was issued
     * @author Tom de Wildt
     */
    private void storeToken(String token, int expires, Calendar date) {
        SharedPreferences preferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN_PREF_KEY, token);
        editor.putInt(EXPIRES_PREF_KEY, expires);
        editor.putString(DATE_PREF_KEY, DateUtility.calendarToString(date));
        editor.commit();
    }

    /**
     * Loads the token from the shared prefs, returns null if token, expires or the date is null
     * this method also returns null if the date is earlier than today
     *
     * @return token or null
     * @author Tom de Wildt
     */
    private String loadToken() {
        SharedPreferences preferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        String token = preferences.getString(TOKEN_PREF_KEY, null);
        int expires = preferences.getInt(EXPIRES_PREF_KEY, 0);
        Calendar date = DateUtility.stringToCalendar(preferences.getString(DATE_PREF_KEY, null));
        Calendar today = Calendar.getInstance();

        // Validate if null
        if (token == null || expires == 0 || date == null) {
            return null;
        }

        // Add seconds
        date.add(Calendar.SECOND, expires);

        // Check if date is earlier
        if (date.compareTo(today) == 1) {
            return token;
        }
        return null;
    }

    /**
     * Gets the token
     *
     * @return token
     * @author Tom de Wildt
     */
    public String getToken() {
        return token;
    }

    /**
     * Returns the instance of the auth service, if it's null it will create a new one
     *
     * @param context the activity
     * @return instance
     * @author Tom de Wildt
     */
    public static FontysAuthService getInstance(Context context) {
        if (instance == null) {
            instance = new FontysAuthService(context);
        }
        if (instance.context != context) {
            instance.context = context;
        }

        return instance;
    }
}
