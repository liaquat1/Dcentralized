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

/**
 * Created by Liwei on 1/12/2018.
 */

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
            DocumentReference reference = database.collection("users").document(userId);
            DocumentSnapshot snapshot = Tasks.await(reference.get());
            DocumentReference projectRef = database.collection("projects").document(projectId);
            List<DocumentReference> referenceList = (ArrayList)snapshot.get("projects");
            referenceList.add(projectRef);
            Tasks.await(database.collection("users").document(userId).update("projects", referenceList));
            return true;
        } catch (ExecutionException | InterruptedException e) {
            Log.d(TAG,  e.getMessage());
        }
        return false;
    }
}
