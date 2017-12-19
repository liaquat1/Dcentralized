package com.dcentralized.studywallet.runnables;

import android.content.Context;
import android.content.Intent;

import com.dcentralized.studywallet.activities.MainActivity;

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
            // TODO: load data into application
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }
}
