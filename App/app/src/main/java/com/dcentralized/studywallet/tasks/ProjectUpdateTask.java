package com.dcentralized.studywallet.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProjectUpdateTask extends AsyncTask<Void, Void, Boolean> {
    private static final String TAG = ProjectUpdateTask.class.getSimpleName();
    private String userId;
    private String projectId;
    private FirebaseFirestore database;

    public ProjectUpdateTask(String userId, String projectId){
        this.userId = userId;
        this.projectId = projectId;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            DocumentReference userReference = database.collection("users").document(userId);
            DocumentReference projectReference = database.collection("projects").document(projectId);
            DocumentSnapshot userDocument = Tasks.await(userReference.get());

            // Add project
            List<DocumentReference> references = (List)userDocument.get("projects");
            references.add(projectReference);

            // Update user
            Tasks.await(userReference.update("projects", references));

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