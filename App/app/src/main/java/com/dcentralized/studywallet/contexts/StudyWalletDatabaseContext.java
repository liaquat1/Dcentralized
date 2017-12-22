package com.dcentralized.studywallet.contexts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletContext;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.lang3.mutable.MutableObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class StudyWalletDatabaseContext implements IStudyWalletContext {
    private static final String TAG = StudyWalletDatabaseContext.class.getSimpleName();
    private FirebaseFirestore database;

    public StudyWalletDatabaseContext() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public boolean isUserInDatabase(String id) {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            if (document != null) {
                return true;
            }
            return false;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }

    @Override
    public boolean addUserToDatabase(User user) {
        try {
            Tasks.await(database.collection("users").document(user.getId()).set(user));
            return true;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }

    @Override
    public User getUserFromDatabase(String id) {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            User user = document.toObject(User.class);
            user.setId(document.getId());

            return user;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return null;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return null;
        }
    }
}
