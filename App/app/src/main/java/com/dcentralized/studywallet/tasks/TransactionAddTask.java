package com.dcentralized.studywallet.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dcentralized.studywallet.models.Transaction;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TransactionAddTask extends AsyncTask<Void, Void, Boolean> {
    private static final String TAG = TransactionAddTask.class.getSimpleName();
    private FirebaseFirestore database;
    private Transaction transaction;
    private String userId;

    public TransactionAddTask(Transaction transaction, String userId) {
        this.transaction = transaction;
        this.userId = userId;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            DocumentReference userReference = database.collection("users").document(userId);
            DocumentSnapshot userDocument = Tasks.await(userReference.get());

            // Add transaction
            DocumentReference transactionReference = Tasks.await(database.collection("transactions").add(transaction));

            // Add transaction to user
            List<DocumentReference> references = (List)userDocument.get("transactions");
            references.add(transactionReference);

            // Update user balance
            int balance = (int)userDocument.get("balance");
            userReference.update("balance", balance + transaction.getAmount());

            // Update total coins if amount > 0
            if (transaction.getAmount() > 0) {
                int total = (int)userDocument.get("totalCoins");
                userReference.update("totalCoins", total + transaction.getAmount());
            }

            return true;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }
}
