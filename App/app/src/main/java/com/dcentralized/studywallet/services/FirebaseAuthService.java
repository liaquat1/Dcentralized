package com.dcentralized.studywallet.services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.dcentralized.studywallet.activities.LoginActivity;
import com.dcentralized.studywallet.utilities.ConverterUtility;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;

/**
 * This class handles the authentication with the Firebase API
 *
 * @author Tom de Wildt
 */
public class FirebaseAuthService {
    private static final String TAG = FirebaseAuthService.class.getSimpleName();
    private static FirebaseAuthService instance;
    private FirebaseAuth auth;
    private Context context;

    /**
     * Instantiates the class, sets a random state and loads the token if it's present
     *
     * @param context activity context
     * @author Tom de Wildt
     */
    private FirebaseAuthService(Context context) {
        this.auth = FirebaseAuth.getInstance();
        this.context = context;
    }

    public boolean login() {
        try {
            FirebaseUser user = auth.getCurrentUser();
            if (user == null) {
                AuthResult result = Tasks.await(auth.signInAnonymously());
                if (result.getUser() != null) {
                    Log.d(TAG, "Sign in successful");
                    return true;
                } else {
                    Log.d(TAG, "Sign in failed");
                    return false;
                }
            } else {
                Log.d(TAG, "Sign in successful");
                return true;
            }
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }

    public void logout() {
        auth.signOut();
    }

    /**
     * Returns the instance of the auth service, if it's null it will create a new one
     *
     * @param context the activity
     * @return instance
     * @author Tom de Wildt
     */
    public static FirebaseAuthService getInstance(Context context) {
        if (instance == null) {
            instance = new FirebaseAuthService(context);
        }
        return instance;
    }
}
