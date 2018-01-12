package com.dcentralized.studywallet.tasks;


import android.util.Log;

import com.dcentralized.studywallet.models.Transaction;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class TransactionTask implements Runnable {
    private static final String TAG = TransactionTask.class.getSimpleName();
    private FirebaseFirestore database;
    private List<Transaction> result;
    private CyclicBarrier barrier;
    private String id;

    public TransactionTask(String id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
        this.result = new ArrayList<>();
        database = FirebaseFirestore.getInstance();
    }


    @Override
    public void run() {
        try {
            getTransactions(getRefrences());
            barrier.await();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        } catch (BrokenBarrierException e) {
            Log.e(TAG, "BrokenBarrierException occurred", e);
        }
    }

    public List<Transaction> getResult() {
        return result;
    }

    private List<DocumentReference> getRefrences() {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            return new ArrayList<>((ArrayList<DocumentReference>)document.get("transactions"));
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return new ArrayList<>();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return new ArrayList<>();
        }
    }

    private void getTransactions(List<DocumentReference> references) {
        try {
            for (DocumentReference reference : references) {
                DocumentSnapshot document = Tasks.await(reference.get());
                Transaction transaction = document.toObject(Transaction.class);
                transaction.setId(document.getId());
                result.add(transaction);
            }
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        }
    }
}