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
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.commons.lang3.mutable.MutableObject;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class UserDatabaseContext implements IUserContext {
    private static final String TAG = UserDatabaseContext.class.getSimpleName();
    private FirebaseFirestore database;

    public UserDatabaseContext() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public List<DocumentReference> getReferences(String id, String collection) {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            return new ArrayList<>((ArrayList<DocumentReference>)document.get(collection));
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return new ArrayList<>();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Transaction> getTransactions(List<DocumentReference> references) {
        List<Transaction> result = new ArrayList<>();
        try {
            for (DocumentReference reference : references) {
                DocumentSnapshot document = Tasks.await(reference.get());
                Transaction transaction = document.toObject(Transaction.class);
                transaction.setId(document.getId());
                result.add(transaction);
            }

            return result;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return result;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return result;
        }
    }

    @Override
    public List<Project> getProjects(List<DocumentReference> references) {
        List<Project> result = new ArrayList<>();
        try {
            for (DocumentReference reference : references) {
                DocumentSnapshot document = Tasks.await(reference.get());
                Project project = document.toObject(Project.class);
                project.setId(document.getId());
                project.setOwner(getOwner((DocumentReference)document.get("owner")));
                result.add(project);
            }

            return result;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return result;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return result;
        }
    }

    @Override
    public Company getOwner(DocumentReference reference) {
        try {
            DocumentSnapshot document = Tasks.await(reference.get());
            Company company = document.toObject(Company.class);
            company.setId(document.getId());
            return company;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return null;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return null;
        }
    }
}
