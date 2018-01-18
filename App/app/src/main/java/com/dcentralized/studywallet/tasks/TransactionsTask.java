package com.dcentralized.studywallet.tasks;


import android.util.Log;

import com.dcentralized.studywallet.models.Transaction;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class TransactionsTask implements Runnable {
    private static final String TAG = TransactionsTask.class.getSimpleName();
    private FirebaseFirestore database;
    private List<Transaction> result;
    private int balance;
    private int rank;
    private CyclicBarrier barrier;
    private String id;

    public TransactionsTask(String id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
        this.result = new ArrayList<>();
        balance = 0;
        database = FirebaseFirestore.getInstance();
    }


    @Override
    public void run() {
        try {
            getTransactionsFromDatabase(getReferencesFromDatabase());
            rank = getRankFromDatabase();
            barrier.await();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        } catch (BrokenBarrierException e) {
            Log.e(TAG, "BrokenBarrierException occurred", e);
        }
    }

    public List<Transaction> getTransactionsFromDatabase() {
        return result;
    }

    public int getRank() {
        return rank;
    }

    public int getBalance() {
        return balance;
    }

    private List<DocumentReference> getReferencesFromDatabase() {
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

    private int getRankFromDatabase() {
        try {
            UsersTask task = new UsersTask();
            List<User> users = task.execute().get();
            User user = null;

            for (User foundUser : users) {
                if (foundUser.getId().equals(id)) {
                    user = foundUser;
                    break;
                }
            }

            return users.indexOf(user) + 1;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return 0;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return 0;
        }
    }

    private void getTransactionsFromDatabase(List<DocumentReference> references) {
        try {
            for (DocumentReference reference : references) {
                DocumentSnapshot document = Tasks.await(reference.get());
                Transaction transaction = document.toObject(Transaction.class);
                transaction.setId(document.getId());
                balance += transaction.getAmount();
                result.add(transaction);
            }
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        }
    }
}