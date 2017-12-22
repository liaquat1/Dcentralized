package com.dcentralized.studywallet.runnables;

import android.content.Context;
import android.content.Intent;

import com.dcentralized.studywallet.activities.MainActivity;
import com.dcentralized.studywallet.activities.SplashActivity;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.services.FirebaseAuthService;

/**
 * Authenticates with the database and set the current user
 *
 * @author Tom de Wildt
 */
public class LoadingRunnable implements Runnable {
    private Context context;

    /**
     * LoadingRunnable constructor
     *
     * @param context activity context
     * @author Tom de Wildt
     */
    public LoadingRunnable(Context context) {
        this.context = context;
    }

    /**
     * Called when the runnable is activated
     *
     * @author Tom de Wildt
     */
    @Override
    public void run() {
        try {
            if (FirebaseAuthService.getInstance(context).login()) {
                StudyWallet.getInstance(context).setCurrentUser();
            } else {
                ((SplashActivity)context).updateUI();
            }
        } finally {
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }
}
