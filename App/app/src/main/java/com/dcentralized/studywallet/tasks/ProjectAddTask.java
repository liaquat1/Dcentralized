package com.dcentralized.studywallet.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProjectAddTask extends AsyncTask<Void, Void, Boolean> {
    private static final String TAG = ProjectAddTask.class.getSimpleName();
    private String userId;
    private String projectId;
    private FirebaseFirestore database;

    public ProjectAddTask(String userId, String projectId){
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
            userReference.update("projects", references);
            projectReference.update("taken", true);

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