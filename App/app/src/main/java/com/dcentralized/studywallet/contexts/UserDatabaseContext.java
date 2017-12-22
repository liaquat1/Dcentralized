package com.dcentralized.studywallet.contexts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dcentralized.studywallet.contexts.interfaces.IUserContext;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UserDatabaseContext implements IUserContext {
    private static final String TAG = UserDatabaseContext.class.getSimpleName();
    private FirebaseFirestore database;

    public UserDatabaseContext() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public List<Transaction> getTransactions(String id) {
        final CountDownLatch latch = new CountDownLatch(1);
        final List<Transaction> result = new ArrayList<>();

        try {
            CollectionReference reference = database.collection("users").document(id).collection("transactions");
            reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                       List<Transaction> transactions = task.getResult().toObjects(Transaction.class);
                    } else {
                        Log.d(TAG, "Get failed with ", task.getException());
                    }
                }
            });
            latch.await();
            return result;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return null;
        }

    }

    @Override
    public List<Project> getProjects(String id) {
        return null;
    }
}
