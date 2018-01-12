package com.dcentralized.studywallet.tasks;

import android.os.AsyncTask;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProjectFinishTask extends AsyncTask<Void, Void, Boolean> {
    private static final String TAG = ProjectFinishTask.class.getSimpleName();
    private String projectId;
    private FirebaseFirestore database;

    public ProjectFinishTask(String projectId){
        this.projectId = projectId;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        DocumentReference projectReference = database.collection("projects").document(projectId);
        projectReference.update("finished", true);

        return true;
    }
}
