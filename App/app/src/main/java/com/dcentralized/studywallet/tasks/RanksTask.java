package com.dcentralized.studywallet.tasks;

import android.util.Log;

import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

public class RanksTask extends Observable implements Runnable {
    private static final String TAG = RanksTask.class.getSimpleName();
    private FirebaseFirestore database;

    public RanksTask(Observer observer) {
        database = FirebaseFirestore.getInstance();
        addObserver(observer);
    }

    @Override
    public void run() {
        try {
            QuerySnapshot snapshot = Tasks.await(database.collection("users").orderBy("totalCoins", Query.Direction.DESCENDING).get());

            for (DocumentSnapshot document : snapshot.getDocuments()) {
                User user = document.toObject(User.class);
                user.setId(document.getId());
                setChanged();
                notifyObservers(user);
                Thread.sleep(500);
            }
            setChanged();
            notifyObservers(null);
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        }
    }
}
