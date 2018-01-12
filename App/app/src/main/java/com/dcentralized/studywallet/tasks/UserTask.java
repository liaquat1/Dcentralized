package com.dcentralized.studywallet.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dcentralized.studywallet.models.Company;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Get users sorted by total coins
 *
 * @author Tom de Wildt
 */
public class UserTask extends AsyncTask<Void, Void, List<User>> {
    private static final String TAG = UserTask.class.getSimpleName();
    private FirebaseFirestore database;

    public UserTask() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        try {
            QuerySnapshot snapshot = Tasks.await(database.collection("users").orderBy("totalCoins", Query.Direction.DESCENDING).get());
            List<User> users = new ArrayList<>();

            for (DocumentSnapshot document : snapshot.getDocuments()) {
                User user = document.toObject(User.class);
                user.setId(document.getId());
                users.add(user);
            }

            return users;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return null;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return null;
        }
    }
}