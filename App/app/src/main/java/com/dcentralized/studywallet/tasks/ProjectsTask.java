package com.dcentralized.studywallet.tasks;

import android.util.Log;

import com.dcentralized.studywallet.models.Company;
import com.dcentralized.studywallet.models.Project;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class ProjectsTask implements Runnable {
    private static final String TAG = ProjectsTask.class.getSimpleName();
    private FirebaseFirestore database;
    private List<Project> result;
    private CyclicBarrier barrier;
    private String id;

    public ProjectsTask(String id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
        this.result = new ArrayList<>();
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void run() {
        try {
            getProjects(getRefrences());
            barrier.await();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        } catch (BrokenBarrierException e) {
            Log.e(TAG, "BrokenBarrierException occurred", e);
        }
    }

    public List<Project> getResult() {
        return result;
    }

    private List<DocumentReference> getRefrences() {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            return new ArrayList<>((ArrayList<DocumentReference>)document.get("projects"));
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return new ArrayList<>();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return new ArrayList<>();
        }
    }

    private void getProjects(List<DocumentReference> references) {
        try {
            for (DocumentReference reference : references) {
                DocumentSnapshot document = Tasks.await(reference.get());
                Project project = document.toObject(Project.class);
                project.setId(document.getId());
                project.setOwner(getOwner((DocumentReference)document.get("owner")));

                if (!project.getFinished()) {
                    result.add(project);
                }
            }
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        }
    }

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
