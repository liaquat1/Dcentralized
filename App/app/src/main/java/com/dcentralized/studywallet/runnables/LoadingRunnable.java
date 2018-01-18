package com.dcentralized.studywallet.runnables;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dcentralized.studywallet.activities.MainActivity;
import com.dcentralized.studywallet.activities.SplashActivity;
import com.dcentralized.studywallet.activities.TutorialActivity;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.services.FirebaseAuthService;
import com.dcentralized.studywallet.services.StorageService;

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
        if (FirebaseAuthService.getInstance(context).login() && StudyWallet.getInstance(context).setCurrentUser()) {
            Intent i;
            String firstStart = StorageService.getInstance(context).loadValue(StorageService.TUTORIAL_PREF_KEY);
            if(firstStart == null || firstStart.isEmpty()){
                i = new Intent(context, TutorialActivity.class);
                StorageService.getInstance(context).storeValue(StorageService.TUTORIAL_PREF_KEY, "1");
            } else {
                i = new Intent(context, MainActivity.class);
            }
            context.startActivity(i);
        } else {
            ((SplashActivity)context).updateUI();
        }
    }


}
