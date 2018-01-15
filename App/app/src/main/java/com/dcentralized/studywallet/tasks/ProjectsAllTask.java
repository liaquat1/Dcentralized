package com.dcentralized.studywallet.tasks;

import android.util.Log;

import com.dcentralized.studywallet.models.Company;
import com.dcentralized.studywallet.models.Project;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

/**
 * Get users sorted by total coins
 *
 * @author Tom de Wildt
 */
public class ProjectsAllTask extends Observable implements Runnable {
    private static final String TAG = ProjectsAllTask.class.getSimpleName();
    private FirebaseFirestore database;

    public ProjectsAllTask(Observer observer) {
        database = FirebaseFirestore.getInstance();
        addObserver(observer);
    }

    @Override
    public void run() {
        try {
            QuerySnapshot snapshot = Tasks.await(database.collection("projects").whereEqualTo("finished", false).whereEqualTo("taken", false).get());

            for (DocumentSnapshot document : snapshot.getDocuments()) {
                Project project = document.toObject(Project.class);
                project.setId(document.getId());
                project.setOwner(getOwner((DocumentReference)document.get("owner")));
                setChanged();
                notifyObservers(project);
            }
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        }
    }

    /**
     * Gets a project owner
     *
     * @param reference to the document
     * @return company or null
     * @author Tom de Wildt
     */
    private Company getOwner(DocumentReference reference) {
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
