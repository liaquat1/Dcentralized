package com.dcentralized.studywallet.contexts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dcentralized.studywallet.contexts.interfaces.IUserContext;
import com.dcentralized.studywallet.models.Company;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
    public List<DocumentReference> getReferences(String id, final String collection) {
        final CountDownLatch latch = new CountDownLatch(1);
        final List<DocumentReference> result = new ArrayList<>();

        try {
            DocumentReference reference = database.collection("users").document(id);
            reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        result.addAll((ArrayList<DocumentReference>)task.getResult().get(collection));
                    } else {
                        Log.d(TAG, "Get failed with ", task.getException());
                    }
                    latch.countDown();
                }
            });
            latch.await();
            return result;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return result;
        }
    }

    @Override
    public List<Transaction> getTransactions(List<DocumentReference> references) {
        final CountDownLatch latch = new CountDownLatch(1);
        final List<Transaction> result = new ArrayList<>();

        try {
            for (DocumentReference reference : references) {
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            result.add(task.getResult().toObject(Transaction.class));
                        } else {
                            Log.e(TAG, "Get failed with ", task.getException());
                        }
                        latch.countDown();
                    }
                });
            }
            latch.await();
            return result;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return result;
        }
    }

    @Override
    public List<Project> getProjects(List<DocumentReference> references) {
        final CountDownLatch latch = new CountDownLatch(1);
        final List<Project> result = new ArrayList<>();

        try {
            for (DocumentReference reference : references) {
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Project project = task.getResult().toObject(Project.class);
                            project.setOwner(getOwner((DocumentReference)task.getResult().getData().get("owner")));
                            result.add(project);
                        } else {
                            Log.e(TAG, "Get failed with ", task.getException());
                        }
                        latch.countDown();
                    }
                });
            }
            latch.await();
            return result;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return result;
        }
    }

    @Override
    public Company getOwner(DocumentReference reference) {
        final CountDownLatch latch = new CountDownLatch(1);
        final MutableObject<Company> result = new MutableObject<>();

        try {
            reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        result.setValue(task.getResult().toObject(Company.class));
                    } else {
                        Log.e(TAG, "Get failed with ", task.getException());
                        result.setValue(null);
                    }
                    latch.countDown();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Get failed with ", e);
                }
            });
            latch.await();
            return result.getValue();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return null;
        }
    }
}
