package com.dcentralized.studywallet.contexts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletDatabaseContext;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.commons.lang3.mutable.MutableObject;

import java.util.concurrent.CountDownLatch;

public class StudyWalletDatabaseContext implements IStudyWalletDatabaseContext {
    private static final String TAG = StudyWalletDatabaseContext.class.getSimpleName();
    private FirebaseFirestore database;

    public StudyWalletDatabaseContext() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public boolean isUserInDatabase(String id) {
        final CountDownLatch latch = new CountDownLatch(1);
        final MutableObject<Boolean> result = new MutableObject<>();

        try {
            DocumentReference reference = database.collection("users").document(id);
            reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            result.setValue(true);
                        } else {
                            result.setValue(false);
                        }
                    } else {
                        Log.d(TAG, "Get failed with ", task.getException());
                        result.setValue(false);
                    }
                    latch.countDown();
                }
            });
            latch.await();
            return result.getValue();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }
}
