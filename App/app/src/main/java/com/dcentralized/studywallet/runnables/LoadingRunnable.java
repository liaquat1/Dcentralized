package com.dcentralized.studywallet.runnables;

import android.content.Context;
import android.content.Intent;

import com.dcentralized.studywallet.activities.MainActivity;
import com.dcentralized.studywallet.models.StudyWallet;

/**
 *
 * @author Tom de Wildt
 */
public class LoadingRunnable implements Runnable {
    private Context context;

    public LoadingRunnable(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            StudyWallet.getInstance(context).setCurrentUser();
        } finally {
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }
}
