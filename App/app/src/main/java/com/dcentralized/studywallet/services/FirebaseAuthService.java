package com.dcentralized.studywallet.services;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.lang3.mutable.MutableObject;

import java.util.concurrent.CountDownLatch;

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
        if (auth.getCurrentUser() == null) {
            final CountDownLatch latch = new CountDownLatch(1);
            final MutableObject<Boolean> result = new MutableObject<>();

            try {
                FirebaseAuth.getInstance().signInAnonymously()
                        .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Sign in successful");
                                    result.setValue(true);
                                } else {
                                    Log.d(TAG, "Sign in failed", task.getException());
                                    result.setValue(false);
                                }
                                latch.countDown();
                            }
                        });
                latch.await();
                return result.getValue();
            } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException occurred", e);
                return false;
            }
        }
        Log.d(TAG, "Sign in successful");
        return true;
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
